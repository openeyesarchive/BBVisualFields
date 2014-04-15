/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.openeyes.diagnostics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.io.FilenameUtils;
import org.apache.xmlbeans.XmlException;
import org.hl7.fhir.Attachment;
import org.hl7.fhir.Base64Binary;
import org.hl7.fhir.DiagnosticReport;
import org.hl7.fhir.DiagnosticReportDocument;
import org.hl7.fhir.DiagnosticReportImage;
import org.hl7.fhir.Media;
import org.hl7.fhir.MediaDocument;
import org.hl7.fhir.Observation;
import org.hl7.fhir.OperationOutcomeDocument;
import org.hl7.fhir.Patient;
import org.hl7.fhir.ResourceInline;
import org.hl7.fhir.ResourceReference;
import org.w3.x2005.atom.ContentType;
import org.w3.x2005.atom.EntryType;
import org.w3.x2005.atom.FeedDocument;
import uk.org.openeyes.diagnostics.db.FieldReport;

/**
 * Class for marshaling and un-marshaling FHIR resources, as well as reading
 * resources from remote locations.
 */
public class FhirUtils {

	/**
	 *
	 * @param options
	 */
	public static void printHelp(Options options) {

		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("annotator-app [options], where [options] can be any of:", options);
		System.exit(0);
	}

	/**
	 *
	 * @param type
	 * @param data
	 */
	public static boolean send(String host, int port, String type, String data,
			String username, String password) {

		boolean sent = false;
		String ref = null;
		HttpTransfer sender = new HttpTransfer();
		sender.setHost(host);
		sender.setPort(port);
		int code = -1;
		try {
			code = sender.send(type, data, username, password);
			sent = true;
		} catch (ConnectException ex) {
			// nothing to do
		}
		if (code == 201) {
			String response = sender.getResponse();
			try {
				OperationOutcomeDocument opdoc = OperationOutcomeDocument.Factory.parse(response);
				ref = opdoc.getOperationOutcome().getIssueArray()[0].getDetails().getValue();

			} catch (XmlException xmlex) {
				xmlex.printStackTrace();
			}
		}
		return sent;
	}

	/**
	 *
	 * @param reference
	 * @param attachments
	 * @return
	 * @throws IOException
	 */
	public DiagnosticReport createDiagnosticReport(int patientRef, String reference, File[] attachments)
			throws IOException {
		DiagnosticReport report = DiagnosticReport.Factory.newInstance();

		ResourceReference subject = report.addNewSubject();
		subject.addNewReference().setValue("patient/pat-" + patientRef);
		DiagnosticReportImage image = report.addNewImage();
		ResourceReference imageRef = image.addNewLink();
		org.hl7.fhir.String ref = imageRef.addNewReference();
		ref.setValue(reference);
		for (int i = 0; i < attachments.length; i++) {
			ResourceReference resref = report.addNewResult();
			resref.addNewReference().setValue("#r" + (i + 1)); // TODO
			ResourceInline inline = report.addNewContained();
			Observation o = inline.addNewObservation();
			o.setId("r" + (i + 1));

			Attachment att = o.addNewValueAttachment();
			Base64Binary binary = att.addNewData();
			Path path = FileSystems.getDefault().getPath(attachments[i].getParentFile().getAbsolutePath(), attachments[i].getName());
			att.addNewContentType().setValue(Files.probeContentType(path));
			att.addNewTitle().setValue(attachments[i].getName());
			binary.setValue(this.base64encode(attachments[i]));
			att.setData(binary);

		}
		DiagnosticReportDocument doc = DiagnosticReportDocument.Factory.newInstance();
		doc.setDiagnosticReport(report);
		return report;
	}

	/**
	 * @param type
	 * @param media
	 * @param attachments
	 */
	public Media createMedia(File media)
			throws IOException {
		Media m = Media.Factory.newInstance();
		Attachment att = m.addNewContent();
		Base64Binary data = att.addNewData();

		Path path = FileSystems.getDefault().getPath(media.getParentFile().getAbsolutePath(), media.getName());
		att.addNewContentType().setValue(Files.probeContentType(path));
		att.addNewTitle().setValue(media.getName());
		data.setValue(this.base64encode(media));
		att.setData(data);
		m.addNewSubject().addNewReference().setValue("media/med-1");
		MediaDocument doc = MediaDocument.Factory.newInstance();
		doc.setMedia(m);
		return m;
	}
	
	/**
	 * 
	 * @param host
	 * @param port
	 * @param metaData
	 * @param username
	 * @param password
	 * @return
	 * @throws ConnectException 
	 */
	public Patient readPatient(String host, int port,
			HumphreyFieldMetaData metaData, String username, String password)
			throws ConnectException {
		Patient p = null;
		HttpTransfer sender = new HttpTransfer();
		String requestParams = "identifier=" + metaData.getPatientId()
				+ "&last_name=" + metaData.getFamilyName()
				+ "&first_name=" + metaData.getGivenName();
		sender.setHost(host);
		sender.setPort(port);
		int result = -1;
		try {
			result = sender.read("Patient", "pat", requestParams, username, password);
                        System.out.println("result:=" + result);
			if (result == 200) {
				FeedDocument doc = FeedDocument.Factory.parse(sender.getResponse());
				EntryType entry = doc.getFeed().getEntryArray(0);
				ContentType content = entry.getContentArray(0);
				p = content.getPatient();
				// reference to patient, last part is ID:
				p.setId(FilenameUtils.getBaseName(entry.getIdArray(0).getStringValue()));
			}
		} catch (ConnectException ex) {
			throw ex;
		} catch (XmlException ex) {
			ex.printStackTrace();
		}
		return p;
	}

	/**
	 *
	 * @param f
	 * @return
	 */
	private byte[] base64encode(File f) throws IOException {
		Path p = Paths.get(f.getAbsolutePath());
		return Files.readAllBytes(p);
	}
}
