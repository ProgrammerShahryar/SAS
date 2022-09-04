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
public class Programme {



//////////////////////////////////////
    private static int nextProgrammeID = 1;
    private University university;
    private String programmeID;
    private String programmeName;
    private String description;
    private LocalDate closingDate;
    private ArrayList<Application> applications;
    
    // constructors

    public Programme(University university, String programmeName, String description, LocalDate closingDate) {
        setUniversity(university);
        setProgrammeID();
        setProgrammeName(programmeName);
        setDescription(description);
        setClosingDate(closingDate);
        setApplications(new ArrayList<Application>());
    }


    /**
     * @return the nextProgrammeID
     */
    public static int getNextProgrammeID() {
        return nextProgrammeID;
    }

    /**
     * @param aNextProgrammeID the nextProgrammeID to set
     */
    public static void setNextProgrammeID(int aNextProgrammeID) {
        nextProgrammeID = aNextProgrammeID;
    }

    /**
     * @return the university
     */
    public University getUniversity() {
        return university;
    }

    /**
     * @param university the university to set
     */
    public void setUniversity(University university) {
        this.university = university;
    }

    /**
     * @return the programmeID
     */
    public String getProgrammeID() {
        return programmeID;
    }

    /**
     * @param programmeID the programmeID to set
     */
    public void setProgrammeID() {
        programmeID = getUniversity().getUniversityID() + "-" + 
                String.format("%02d", nextProgrammeID++);
    }

    /**
     * @return the programmeName
     */
    public String getProgrammeName() {
        return programmeName;
    }

    /**
     * @param programmeName the programmeName to set
     */
    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the closingDate
     */
    public LocalDate getClosingDate() {
        return closingDate;
    }

    /**
     * @param closingDate the closingDate to set
     */
    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    /**
     * @return the applications
     */
    public ArrayList<Application> getApplications() {
        return applications;
    }

    /**
     * @param applications the applications to set
     */
    public void setApplications(ArrayList<Application> applications) {
        this.applications = applications;
    }    

    public void addApplication(Application app) {
        getApplications().add(app);
    }
    
    public int noOfApplications() {
        return getApplications().size();
    }
 
    public String allApplications() {
        String result = "";
        for (Application app: getApplications())
            result += app + "\n";
        return result;
    }
    
    @Override
    public String toString() {
        return getProgrammeID() + " - " + getProgrammeName() +
                "\nDescription: " + getDescription() + 
                "\nClosing Date: " + getClosingDate() +
                (isBefore(LocalDate.now())? " [CLOSED]":""); 
    }


    public boolean isBefore(LocalDate aDate) {
        return getClosingDate().isBefore(aDate);
    }
    
    public static void main(String[] args) {
        Programme p = new Programme(new University("HELP"), "BIT", "HOT", LocalDate.now());
        System.out.println(p);
    }
}
