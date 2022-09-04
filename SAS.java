/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.util.ArrayList;

/**
 *
 * @author Shahryar
 */
public class SAS {
    private ArrayList<User> users;
    private ArrayList<University> universities;
    private ArrayList<Qualification> qualifications;

    public SAS() {
        setUsers(new ArrayList<>());
        setUniversities(new ArrayList<>());
        setQualifications(new ArrayList<>());
    }
    
    /**
     * Adding a user with unique username to the system.
     * 
     * @param user 
     */
    public void addUser(User user) {
        getUsers().add(user);
    }

        /**
     * Find the User object from user list by matching the username
     *
     * @param username A parameter that used to match username of user in
     * userList
     * @return User This returns user with the username that matches the input
     * parameter
     */
    public User findUser(String username) {
        for (User user : getUsers()) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }
        return null;
    }
    
    public ArrayList<Programme> findProgramme(String criteria) {
        ArrayList<Programme> allProgrammes = new ArrayList<>();
        criteria = criteria.toUpperCase();
        for (University u: getUniversities())
            for (Programme p: u.getProgrammes())
                if (p.getProgrammeName().toUpperCase().contains(criteria) || 
                        p.getDescription().toUpperCase().contains(criteria))
                    allProgrammes.add(p);
        
        return allProgrammes;
    }
    
    public Qualification findQualification(String quaName) {
        for (Qualification q: getQualifications())
            if (quaName.equalsIgnoreCase(q.getQualificationName()))
                return q;
        return null;
    }
    
    public void addUniversity(University university) {
        getUniversities().add(university);
    }

    public University findUinversity(String uniName) {
        for (University uni : getUniversities()) {
            if (uni.getUniversityName().equalsIgnoreCase(uniName)) {
                return uni;
            }
        }
        return null;        
    }
    
    public void addQualification(Qualification qualification) {
        getQualifications().add(qualification);
    } 
    
    /**
     * @return the users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * @return the universities
     */
    public ArrayList<University> getUniversities() {
        return universities;
    }

    /**
     * @param universities the universities to set
     */
    public void setUniversities(ArrayList<University> universities) {
        this.universities = universities;
    }

    /**
     * @return the qualifications
     */
    public ArrayList<Qualification> getQualifications() {
        return qualifications;
    }

    /**
     * @param qualifications the qualifications to set
     */
    public void setQualifications(ArrayList<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    public int noOfUsers() {
        return getUsers().size();
    }
    
    public int noOfUniversities() {
        return getUniversities().size();
    }
    
    public int noOfQualifications() {
        return getQualifications().size();
    }
    
    public ArrayList<Application> getAllApplications() {
        ArrayList<Application> allApps = new ArrayList<>();
        for (University uni: getUniversities())
            for (Programme prog: uni.getProgrammes())
                if (prog.noOfApplications() != 0)
                    for (Application ap: prog.getApplications())
                        allApps.add(ap);
        return allApps;
    }
}
