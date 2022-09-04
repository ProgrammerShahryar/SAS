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
public class User {
    private String userName;
    private String password;
    private String name;
    private String email;
    
    public User(String userName, String password, String name, String email) {
        setUserName(userName);
        setPassword(password);
        setName(name);
        setEmail(email);
    }
    
    public User() {
        this("not set", "not set", "not set", "not set");
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String toString() {
        return getName() + " (" + getUserName() + " : " + getPassword() + ") " +
                getEmail();
    }
    
}
