/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.time.LocalDate;

/**
 *
 * @author Shahryar
 */
public class Application {

    
    // attributes
    private static int nextApplicationID = 1;
    private String applicationID;
    private LocalDate applicationDate;
    private String status;
    private Applicant applicant;
    private Programme programme;
    
    // constructors

    public Application(Programme prog, Applicant applicant) {
        setStatus("New");
        setApplicant(applicant);
        setProgramme(prog);
                setApplicationID();
        prog.addApplication(this);
        applicant.addApplication(this);
    }

    
    @Override
    public String toString() {
        return getApplicationID() + "\n" 
                + "Applicant: " + getApplicant() + "\n" 
                + "Date Applied: " + getApplicationDate() + "\n" 
                + "Status: " + getStatus();
    }

    /**
     * @return the nextApplicatonID
     */
    public static int getNextApplicatoonID() {
        return nextApplicationID;
    }

    /**
     * @param aNextApplicatonID the nextApplicatonID to set
     */
    public static void setNextApplicatonID(int aNextApplicationID) {
        nextApplicationID = aNextApplicationID;
    }

    /**
     * @return the applicationID
     */
    public String getApplicationID() {
        return applicationID;
    }

    /**
     * 
     */
    public void setApplicationID() {
        this.applicationID = String.format("", getProgramme().getProgrammeID() + "-" +
                getApplicant().getQualificationsObtained().get(0).getQualificationName() + 
                nextApplicationID ++);
    }

    /**
     * @return the applicationDate
     */
    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    /**
     * @param applicationDate the applicationDate to set
     */
    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the applicant
     */
    public Applicant getApplicant() {
        return applicant;
    }

    /**
     * @param applicant the applicant to set
     */
    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    /**
     * @return the programme
     */
    public Programme getProgramme() {
        return programme;
    }

    /**
     * @param programme the programme to set
     */
    public void setProgramme(Programme programme) {
        this.programme = programme;
    }
}
