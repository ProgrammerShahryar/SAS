/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Shahryar
 */
public class Applicant extends User {
    private String idType;
    private String idNumber;
    private String mobileNo;
    private LocalDate dateOfBirth;
    private ArrayList<Application> applications;
    private ArrayList<QualificationObtained> qualificationsObtained;

    public Applicant(String userName, String password, String name, String email, 
        String idType, String idNumber, String mobileNo, LocalDate dateOfBirth) {
        super(userName, password, name, email);
        setIdType(idType);
        setIdNumber(idNumber);
        setMobileNo(mobileNo);
        setDateOfBirth(dateOfBirth);
        setApplications(new ArrayList<>());
        setQualificationsObtained(new ArrayList<>());
    }
    
    public int noOfApplications() {
        return getApplications().size();
    }
    
    public int noOfQualificationsObtained() {
        return getQualificationsObtained().size();
    }

    /**
     * @return the idType
     */
    public String getIdType() {
        return idType;
    }

    /**
     * @param idType the idType to set
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /**
     * @return the idNumber
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * @param idNumber the idNumber to set
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * @return the mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * @param mobileNo the mobileNo to set
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * @return the dateOfBirth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String toString() {
        return super.toString() + "\n" + getIdType() + " - " + getIdNumber() + " " +
                getMobileNo() + " " + getDateOfBirth();
    }
    
    public ArrayList<QualificationObtained> getQualificationsObtained() {
        return qualificationsObtained;
    }

    public void setQualificationsObtained(ArrayList<QualificationObtained> qualificationsObtained) {
        this.qualificationsObtained = qualificationsObtained;
    }

    public void addQualificationObtained(QualificationObtained qualiOb) {
        getQualificationsObtained().add(qualiOb);
    }
    
    public void addApplication(Application app) {
        getApplications().add(app);
    }
    
    public ArrayList<Application> getApplications() {
        return applications;
    }
    
    public Application findApplication(String programmeID) {
        for (Application app: getApplications())
            if (app.getProgramme().getProgrammeID().equalsIgnoreCase(programmeID))
                return app;
        return null;
    }

    public void setApplications(ArrayList<Application> applications) {
        this.applications = applications;
    }
}
