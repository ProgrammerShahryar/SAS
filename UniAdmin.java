/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

/**
 *
 * @author Shahryar
 */
public class UniAdmin extends User {
    private University university;
    
    public UniAdmin(String userName, String password, String name, String email) {
        super(userName, password, name, email);
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
    
}
