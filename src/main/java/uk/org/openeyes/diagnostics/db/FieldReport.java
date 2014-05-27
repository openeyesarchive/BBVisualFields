/**
 * OpenEyes
 *
 * (C) Moorfields Eye Hospital NHS Foundation Trust, 2008-2011
 * (C) OpenEyes Foundation, 2011-2013
 * This file is part of OpenEyes.
 * OpenEyes is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * OpenEyes is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with OpenEyes in a file titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package OpenEyes
 * @link http://www.openeyes.org.uk
 * @author OpenEyes <info@openeyes.org.uk>
 * @copyright Copyright (c) 2008-2011, Moorfields Eye Hospital NHS Foundation Trust
 * @copyright Copyright (c) 2011-2013, OpenEyes Foundation
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */
package uk.org.openeyes.diagnostics.db;
// Generated 28-Feb-2014 14:23:53 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * FieldReport generated by hbm2java
 */
public class FieldReport  implements java.io.Serializable {


     private Integer id;
     private Directory directory;
     private Date reportTime;
     private Boolean parsed;
     private String fileName;
     private String fileReference;
     private String studyDate;
     private String studyTime;
     private String testType;
     private String testName;
     private String patientId;
     private String firstName;
     private String lastName;
     private String dob;
     private String gender;
     private String eye;
     private Set<CommsLog> commsLogs = new HashSet<CommsLog>(0);
     private Set<FieldErrorReport> fieldErrorReports = new HashSet<FieldErrorReport>(0);

    public FieldReport() {
    }

	
    public FieldReport(Date reportTime) {
        this.reportTime = reportTime;
    }
    public FieldReport(Directory directory, Date reportTime, Boolean parsed, String fileName, String fileReference, String studyDate, String studyTime, String testType, String testName, String patientId, String firstName, String lastName, String dob, String gender, String eye, Set<CommsLog> commsLogs, Set<FieldErrorReport> fieldErrorReports) {
       this.directory = directory;
       this.reportTime = reportTime;
       this.parsed = parsed;
       this.fileName = fileName;
       this.fileReference = fileReference;
       this.studyDate = studyDate;
       this.studyTime = studyTime;
       this.testType = testType;
       this.testName = testName;
       this.patientId = patientId;
       this.firstName = firstName;
       this.lastName = lastName;
       this.dob = dob;
       this.gender = gender;
       this.eye = eye;
       this.commsLogs = commsLogs;
       this.fieldErrorReports = fieldErrorReports;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Directory getDirectory() {
        return this.directory;
    }
    
    public void setDirectory(Directory directory) {
        this.directory = directory;
    }
    public Date getReportTime() {
        return this.reportTime;
    }
    
    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }
    public Boolean getParsed() {
        return this.parsed;
    }
    
    public void setParsed(Boolean parsed) {
        this.parsed = parsed;
    }
    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileReference() {
        return this.fileReference;
    }
    
    public void setFileReference(String fileReference) {
        this.fileReference = fileReference;
    }
    public String getStudyDate() {
        return this.studyDate;
    }
    
    public void setStudyDate(String studyDate) {
        this.studyDate = studyDate;
    }
    public String getStudyTime() {
        return this.studyTime;
    }
    
    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime;
    }
    public String getTestType() {
        return this.testType;
    }
    
    public void setTestType(String testType) {
        this.testType = testType;
    }
    public String getTestName() {
        return this.testName;
    }
    
    public void setTestName(String testName) {
        this.testName = testName;
    }
    public String getPatientId() {
        return this.patientId;
    }
    
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getDob() {
        return this.dob;
    }
    
    public void setDob(String dob) {
        this.dob = dob;
    }
    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getEye() {
        return this.eye;
    }
    
    public void setEye(String eye) {
        this.eye = eye;
    }
    public Set<CommsLog> getCommsLogs() {
        return this.commsLogs;
    }
    
    public void setCommsLogs(Set<CommsLog> commsLogs) {
        this.commsLogs = commsLogs;
    }
    public Set<FieldErrorReport> getFieldErrorReports() {
        return this.fieldErrorReports;
    }
    
    public void setFieldErrorReports(Set<FieldErrorReport> fieldErrorReports) {
        this.fieldErrorReports = fieldErrorReports;
    }




}


