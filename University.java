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
public class University {
    private static int nextUniIDNumber = 1;
    private String universityName;
    private String universityID;
    private ArrayList<UniAdmin> uniAdmins;
    private ArrayList<Programme> programmes;

    public University(String universityName) {
        setUniversityName(universityName);
        setUniversityID();
        setUniAdmins(new ArrayList<>());
        setProgrammes(new ArrayList<>());
    }
    
    public void addAdmin(UniAdmin admin) {
        getUniAdmins().add(admin);
        admin.setUniversity(this);
    }
    
    public void addProgramme(Programme programme) {
        getProgrammes().add(programme);
        //programme.setUniversity(this);
    }
    /**
     * @return the uniAdmins
     */
    public ArrayList<UniAdmin> getUniAdmins() {
        return uniAdmins;
    }

    /**
     * @param uniAdmins the uniAdmins to set
     */
    public void setUniAdmins(ArrayList<UniAdmin> uniAdmins) {
        this.uniAdmins = uniAdmins;
    }

    /**
     * @return the programmes
     */
    public ArrayList<Programme> getProgrammes() {
        return programmes;
    }

    /**
     * @param programmes the programmes to set
     */
    public void setProgrammes(ArrayList<Programme> programmes) {
        this.programmes = programmes;
    }

    /**
     * @return the nextUniIDNumber
     */
    public static int getNextUniIDNumber() {
        return nextUniIDNumber;
    }

    /**
     * @param aNextUniIDNumber the nextUniIDNumber to set
     */
    public static void setNextUniIDNumber(int aNextUniIDNumber) {
        nextUniIDNumber = aNextUniIDNumber;
    }

    /**
     * @return the universityName
     */
    public String getUniversityName() {
        return universityName;
    }

    /**
     * @param universityName the universityName to set
     */
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    /**
     * @return the universityID
     */
    public String getUniversityID() {
        return universityID;
    }

    /**
     * set university id automatically
     */
    public void setUniversityID() {
        this.universityID = String.format("U%03d", nextUniIDNumber ++);
    }
    
    public int noOfProgrammes() {
        return getProgrammes().size();
    }
    
    public int noOfUniAdmins() {
        return getUniAdmins().size();
    }
    
    public String toString() {
        return String.format("%s (%s) with %d programmes, and %d adminitrator(s)", getUniversityName(),
                getUniversityID(), noOfProgrammes(), noOfUniAdmins());
    }
}
