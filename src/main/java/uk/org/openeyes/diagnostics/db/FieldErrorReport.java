package uk.org.openeyes.diagnostics.db;
// Generated 28-Feb-2014 14:23:53 by Hibernate Tools 3.2.1.GA



/**
 * FieldErrorReport generated by hbm2java
 */
public class FieldErrorReport  implements java.io.Serializable {


     private Integer id;
     private FieldReport fieldReport;
     private FieldError fieldError;

    public FieldErrorReport() {
    }

    public FieldErrorReport(FieldReport fieldReport, FieldError fieldError) {
       this.fieldReport = fieldReport;
       this.fieldError = fieldError;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public FieldReport getFieldReport() {
        return this.fieldReport;
    }
    
    public void setFieldReport(FieldReport fieldReport) {
        this.fieldReport = fieldReport;
    }
    public FieldError getFieldError() {
        return this.fieldError;
    }
    
    public void setFieldError(FieldError fieldError) {
        this.fieldError = fieldError;
    }




}


