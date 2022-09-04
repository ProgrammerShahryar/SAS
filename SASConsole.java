/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author Shahryar
 */
public class SASConsole {
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static Scanner kbd;
    static SAS system;                      //SeniorHelp system;
    static DateTimeFormatter dateFormat;

    /**
     * This is the main method which receive input from user and display output
     * user.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        User sasadmin = new UniAdmin("sasadmin", "sasadmin", "Super User", 
            "sasadmin@sas.com");
        
        dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        kbd = new Scanner(System.in);
        system = new SAS();
        system.addUser(sasadmin);
        
        //init(); <-- Initialisize some data...

        System.out.println("Welcome to SAS!!!\n");
        char choice = doMenu();
        while (choice != '0') {
            switch (choice) {
                case '1':
                    signUp("Applicant");
                    break;
                case '2':
                    login();
                    break;
                case '0':
                    System.out.println("Thank you for using the program. Have a nice day!");
                    break;
                default:
                    printErrorMessage();
                    break;
                    
            /* NOT REQUIRED IN THE ASSIGNMENT, BUT GOOD TO HAVE
             * SO THAT WE CAN CHECK WHETHER WHAT WE HAVE CREATED ARE CORRECT 
             */
                case '6':
                    listApplications();
                    break;
                case '7':
                    listQualifications();
                    break;
                case '8':
                    listUniversities();
                    break;                            
                case '9':
                    listUsers();
                    break;
            

            }
            System.out.println();
            choice = doMenu();
        }
    }

    public static char doMenu() {
        System.out.println("SAS System");
        System.out.println("1. Sign-up (Applicant)");
        System.out.println("2. Login");
        System.out.println("6. List Applications");
        System.out.println("7. List Qualifications");
        System.out.println("8. List Universities");
        System.out.println("9. List users");
        System.out.println();
        System.out.println("0. Quit");
        System.out.println();
        System.out.print("Your choice? ");
        return kbd.nextLine().trim().charAt(0);
    }

    public static User signUp(String userType) {
        System.out.println("Create User ...");
        
        String username = getUsername();
        String password = getPassword();
        String fullName = getFullName();
        String email = getEmail();
        
        User user;
        if (userType.equalsIgnoreCase("Applicant")) {
            // ADDITIONAL FOR APPLICANT...
            String mobileNo = getMobileNo();
            String idType = getIDType();
            String idNumber = getIDNumber();
            LocalDate dob = getDate("Enter date of birth (eg. 12/03/1999): ");
            
            system.addUser(user = new Applicant(username, password, fullName, email, 
                idType, idNumber, mobileNo, dob));
        }
        else {
            system.addUser(user = new UniAdmin(username, password, fullName, email));
        }
            
        System.out.println("New user created successfully!");
        return user;
    }

    public static void login() {
        User loginUser = signIn();
        if (loginUser instanceof UniAdmin) {
            if (loginUser.getUserName().equals("sasadmin"))
            {
                sasAdminTasks();
            }
            else {
                uniAdminTasks((UniAdmin) loginUser);
            }
        }
        else {
            applicantTasks((Applicant) loginUser);
        }
    }
    
        /**
     * Validate username and password, and return the User whose username and
     * password matches the entered fields
     *
     * @return User The user who matches the username and password entered
     */
    public static User signIn() {
        System.out.println("\nSign in");
        System.out.print("Username: ");
        String inUsername = kbd.nextLine();
        User loginUser = system.findUser(inUsername);
        while (loginUser == null) {
            System.out.println("Username not found!\n");
            System.out.print("Username: ");
            inUsername = kbd.nextLine();
            loginUser = system.findUser(inUsername);
        }
        System.out.print("Password: ");
        String inPassword = kbd.nextLine();
        while (!loginUser.getPassword().equals(inPassword)) {
            System.out.println("Invalid password!\n");
            System.out.print("Password: ");
            inPassword = kbd.nextLine();
        }
        System.out.println("Sign in successfully!");
        return loginUser;
    }
    
    /**************************************************************
     * SASAdmin tasks..... various methods
     */
    public static char sasAdminMenu() {
        System.out.println();
        System.out.println("SAS Admin tasks");
        System.out.println("1. Maintain Qualification");
        System.out.println("2. Create University");
        System.out.println();
        System.out.println("0. Back to main menu...");
        System.out.println();
        System.out.print("Your choice: ");
        return kbd.nextLine().charAt(0);
    }
    
    public static void sasAdminTasks() {       
        char ch = sasAdminMenu();
        while (ch != '0') {
            switch (ch) {
                case '1':
                    maintainQualification();
                    break;
                case '2':
                    createUniversity();
                    break;
                case '0':
                    System.out.println("Back to main menu");
                    break;
                default:
                    printErrorMessage();
                    break;
            }
            ch = sasAdminMenu();
        }
    }
    
    public static void maintainQualification() {
        if (system.noOfQualifications() != 0) {
            System.out.println("Existing Qualification(s):");
            for (Qualification q: system.getQualifications())
                System.out.println(q.getQualificationName() + " - " + q.getMaximumScore());
        }
        System.out.println();
        String createOrEdit;
        do {
            System.out.println("<Create> new qualification\n" + 
                "<Edit> existing qualifcation\nPress <Enter> to quit");
            System.out.print("\nYour choice? ");
            createOrEdit = kbd.nextLine().toUpperCase();
            
            switch (createOrEdit) {
                case "CREATE":
                    createQualification();
                    break;
                case "EDIT":
                    editQualification();
                    break;
                case "":
                    System.out.println("Done with maintain qualification");
                    break;
                default:
                    System.out.println("Invalid choice! Try again!");
            }
        } while (!createOrEdit.equals(""));
        System.out.println();
    }

    public static void createQualification() {    
        System.out.println("Create New Qualification");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        boolean alreadyExist = true;
        String name;
        do {
            System.out.print("Qualification name: ");
            name = kbd.nextLine();
            Qualification foundQuali = system.findQualification(name);
            if (foundQuali != null)
                System.out.println("Qualification with name '" + name + 
                    "' has been created. Enter a new qualification name!");
            else
                alreadyExist = false;
        } while (alreadyExist);
        System.out.print("Minimum score: ");
        double minScore = kbd.nextDouble();
        System.out.print("Maximum score: ");
        double maxScore = kbd.nextDouble();
        kbd.nextLine();
        System.out.print("Score computation description: ");
        String computeDescription = kbd.nextLine();
        ArrayList<Object> gradeList = new ArrayList<Object>();
        System.out.println();
        String gradeType;
        do {
            System.out.print("Qualification with <String> grade list or " +
                    "<Integer> grade list or <None>? ");
            gradeType = kbd.nextLine();
        } while (!gradeType.equalsIgnoreCase("String") && 
                !gradeType.equalsIgnoreCase("Integer") &&
                !gradeType.equalsIgnoreCase("None"));
        
        if (gradeType.equalsIgnoreCase("None"))
            gradeList = null;
        else if (gradeType.equalsIgnoreCase("Integer")) {
            System.out.print("Enter integer score (<Ennter> to stop): ");
            String scoreStr = kbd.nextLine();       
            while (!scoreStr.isEmpty()) {
                int score = Integer.parseInt(scoreStr);
                gradeList.add(score);
                System.out.print("Enter integer score (<Enter> to stop): ");
                scoreStr = kbd.nextLine();
            }
        }
        else {
            String gradeAndScore;
            do {
                System.out.print("Enter string grade and corresponding integer " +
                        "score (eg A - 4.0) (<Enter> to stop): ");
                gradeAndScore = kbd.nextLine().trim();
                if (!gradeAndScore.isEmpty())
                    gradeList.add(gradeAndScore);
            } while (!gradeAndScore.equals(""));
        }
        
        Qualification q = new Qualification(name, minScore, maxScore, 
                computeDescription, gradeList);
        q.setGradeType(gradeType);
        
        system.addQualification(q);
        System.out.println("Qualification successfully added....");
        System.out.println();
    }
    
    public static void editQualification() {
        if (system.noOfQualifications() == 0)
            System.out.println("ERROR: No qualification has been created yet!");
        else {
            System.out.println("EDIT EXISTING QUALIFICATION");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            for (Qualification q: system.getQualifications())
                System.out.println(q.getQualificationName() + " - " +
                    q.getQualificationID());
            System.out.println();
            System.out.print("Please select a qualification name to edit: ");
            String qualiName = kbd.nextLine();
            Qualification selQ = system.findQualification(qualiName);
            if (selQ == null)
                System.out.println("ERROR: Wrong name entered!");
            else {
                boolean hasUpdated = false;
                
                System.out.print("Current name: '" + selQ.getQualificationName() + 
                    "' New name? <Enter> to skip: ");
                String newName = kbd.nextLine();
                if (!newName.equals("")) {
                    selQ.setQualificationName(newName);
                    hasUpdated = true;
                }
      
                if (selQ.getGradeType().equalsIgnoreCase("None")) {
                    System.out.println("Current qualification is based on " +
                        " subject score, and not grade.");
                    String newMode;
                    boolean done = false;
                    do {
                        System.out.print("Do you want to change to grade list " +
                            "that\nbased on <String> or <Integer>? <Enter> to skip ");
                        newMode = kbd.nextLine();
                        if (newMode.equals(""))
                            done = true;
                        else {
                            if (newMode.equalsIgnoreCase("String") ||
                                newMode.equalsIgnoreCase("Integer"))
                                done = true;
                            else
                                System.out.println("Wrong choice! Try again");
                        }
                    } while (!done);
                    if (newMode.equalsIgnoreCase("String")) {
                        ArrayList<Object> newGradeList = getStringGradeList();
                        selQ.setGradeList(newGradeList);
                        selQ.setGradeType("String");
                        hasUpdated = true;
                    } else if (newMode.equalsIgnoreCase("Integer")) {
                        ArrayList<Object> newGradeList = getIntegerGradeList();
                        selQ.setGradeList(newGradeList);
                        selQ.setGradeType("Integer");
                        hasUpdated = true;
                    }
                }
                else if (selQ.getGradeType().equalsIgnoreCase("Integer")) {                
                    System.out.println("Current integer grade list: " + selQ.getGradeList());
                    String newMode;
                    boolean done = false;
                    do {
                        System.out.print("Do you want to change to grade list " +
                            "that\nbased on <String> or <Remove> grade list or " +
                            "<Edit> current grade list? <Enter> to skip ");
                        newMode = kbd.nextLine();
                        if (newMode.equals(""))
                            done = true;
                        else {
                            if (newMode.equalsIgnoreCase("EDIT") ||
                                newMode.equalsIgnoreCase("String") ||
                                newMode.equalsIgnoreCase("REMOVE"))
                                done = true;
                            else
                                System.out.println("Wrong choice! Try again");
                        }
                    } while (!done);
                    if (newMode.equalsIgnoreCase("Remove")) {
                        selQ.setGradeList(null);
                        selQ.setGradeType("None");
                        hasUpdated = true;
                    } else if (newMode.equalsIgnoreCase("String")) {
                        ArrayList<Object> newGradeList = getStringGradeList();
                        selQ.setGradeList(newGradeList);
                        selQ.setGradeType("String");
                        hasUpdated = true;
                    } else if (newMode.equalsIgnoreCase("Edit")) {
                        ArrayList<Object> gradeList = selQ.getGradeList();
                        String doAgain = "N";
                        do {
                            for (int i = 0; i < gradeList.size(); i++)
                                System.out.println((i+1) + ". " + gradeList.get(i));
                            System.out.println("Select a number to edit/insert: ");
                            String posStr = kbd.nextLine();
                            int pos = Integer.parseInt(posStr);
                            if (pos >= 1 && pos <= gradeList.size()) {
                                System.out.print("Current grade: " + 
                                    gradeList.get(pos-1) + " New grade list: ");
                                String newIntegerString = kbd.nextLine();
                                String choice;
                                do {
                                    System.out.print("<E>dit the current or " + 
                                        "<I>nsert before current? ");
                                    choice = kbd.nextLine();
                                } while (!choice.equalsIgnoreCase("E") &&
                                        (!choice.equalsIgnoreCase("I")));
                                if (choice.equalsIgnoreCase("E"))
                                    gradeList.set(pos-1, Integer.parseInt(newIntegerString));
                                else
                                    gradeList.add(pos-1, Integer.parseInt(newIntegerString));
                                hasUpdated = true;
                            } else 
                                System.out.println("Wrong number!");
                            
                            doAgain = getChoice("Try another grade? (Y/N) ");
                        } while (doAgain.equals("Y"));
                    }                    
                }
                else if (selQ.getGradeType().equalsIgnoreCase("String")) {
                    System.out.println("Current string grade list: " + selQ.getGradeList());
                    String newMode;
                    boolean done = false;
                    do {
                        System.out.print("Do you want to change to grade list " +
                            "that\nbased on <Integer> or <Remove> grade list or " +
                            "<Edit> current grade list? <Enter> to skip ");
                        newMode = kbd.nextLine();
                        if (newMode.equals(""))
                            done = true;
                        else {
                            if (newMode.equalsIgnoreCase("Edit") ||
                                newMode.equalsIgnoreCase("Integer") ||
                                newMode.equalsIgnoreCase("Remove"))
                                done = true;
                            else
                                System.out.println("Wrong choice! Try again");
                        }
                    } while (!done);
                    if (newMode.equalsIgnoreCase("Remove")) {
                        selQ.setGradeList(null);
                        selQ.setGradeType("None");
                        hasUpdated = true;
                    } else if (newMode.equalsIgnoreCase("Integer")) {
                        ArrayList<Object> newGradeList = getIntegerGradeList();
                        selQ.setGradeList(newGradeList);
                        selQ.setGradeType("Integer");
                        hasUpdated = true;
                    } else if (newMode.equalsIgnoreCase("Edit")) {
                        ArrayList<Object> gradeList = selQ.getGradeList();
                        String doAgain = "N";
                        do {
                            for (int i = 0; i < gradeList.size(); i++)
                                System.out.println((i+1) + ". " + gradeList.get(i));
                            System.out.println("Select a number to edit/insert: ");
                            String posStr = kbd.nextLine();
                            int pos = Integer.parseInt(posStr);
                            if (pos >= 1 && pos <= gradeList.size()) {
                                System.out.print("Current grade: " + 
                                    gradeList.get(pos-1) + " New grade list: ");
                                String newGradeString = kbd.nextLine();
                                String choice;
                                do {
                                    System.out.print("<E>dit the current or " + 
                                        "<I>nsert before current? ");
                                    choice = kbd.nextLine();
                                } while (!choice.equalsIgnoreCase("E") &&
                                        (!choice.equalsIgnoreCase("I")));
                                if (choice.equalsIgnoreCase("E"))
                                    gradeList.set(pos-1, newGradeString);
                                else
                                    gradeList.add(pos-1, newGradeString);
                                hasUpdated = true;
                            } else 
                                System.out.println("Wrong number!");
                            
                            doAgain = getChoice("Try another grade? (Y/N) ");
                        } while (doAgain.equals("Y"));
                    }                    
                }                
                
                System.out.print("Current minimum score: [" + selQ.getMinimumScore() +
                    "] New minimum score? <Enter> to skip ");
                String newMinScoreStr = kbd.nextLine();
                if (!newMinScoreStr.equals("")) {
                    selQ.setMinimumScore(Double.parseDouble(newMinScoreStr));
                    hasUpdated = true;
                }
                
                System.out.print("Current maximum score: [" + selQ.getMaximumScore() +
                    "] New maximum score? <Enter> to skip ");
                String newMaxScoreStr = kbd.nextLine();
                if (!newMaxScoreStr.equals("")) {
                    selQ.setMaximumScore(Double.parseDouble(newMaxScoreStr));
                    hasUpdated = true;
                }
                
                System.out.print("Current descripton on compute overall score: '" + 
                    selQ.getResultCalcDescription() + 
                    "' New description? <Enter> to skip ");
                String newDescription = kbd.nextLine();
                if (!newDescription.equals("")) {
                    selQ.setResultCalcDescription(newDescription);
                    hasUpdated = true;
                }
                System.out.println();
                if (hasUpdated)
                    System.out.println("Qualification has been updated!\n" 
                            + selQ);
                else
                    System.out.println("Update aborted.....");
            }
        }        
    }
    
    public static ArrayList<Object> getStringGradeList() {
        ArrayList<Object> gradeList = new ArrayList<>();
        String gradeAndScore;
        do {
            System.out.print("Enter string grade and corresponding integer " +
                    "score (eg A - 4.0) (<Enter> to stop): ");
            gradeAndScore = kbd.nextLine().trim();
            if (!gradeAndScore.isEmpty())
                gradeList.add(gradeAndScore);
        } while (!gradeAndScore.equals(""));
        return gradeList;
    }

    public static ArrayList<Object> getIntegerGradeList() {
        ArrayList<Object> gradeList = new ArrayList<>();
        System.out.print("Enter integer score (<Enter> to stop): ");
        String scoreStr = kbd.nextLine();       
        while (!scoreStr.isEmpty()) {
            int score = Integer.parseInt(scoreStr);
            gradeList.add(score);
            System.out.print("Enter integer score (<Enter> to stop): ");
            scoreStr = kbd.nextLine();
        }
        return gradeList;
    }
        
    public static void createUniversity() {
        System.out.println("Register University");
        System.out.print("Enter university name: ");
        String uniName = kbd.nextLine();
        if (system.findUinversity(uniName) == null) {
            University uni = new University(uniName);
            system.addUniversity(uni);
            System.out.println("New university created....");
            System.out.println();
            String choice;
            do {
                System.out.println("Creating University Adminstrator...");
                UniAdmin admin = (UniAdmin) signUp("UniAdmin");
                uni.addAdmin(admin);
                System.out.println();
                choice = getChoice("Add another admin? (Y/N) ");
            } while (choice.equalsIgnoreCase("Y"));
        }
        System.out.println("End of adding new university");
    }

    /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * UniAdmin tasks..... various methods
    */
    public static char uniAdminMenu() {
        System.out.println();
        System.out.println("1. Record Programme");
        System.out.println("2. Review Applicant");
        System.out.println();
        System.out.println("0. Back to main menu...");
        System.out.println();
        System.out.print("Your choice: ");
        return kbd.nextLine().charAt(0);
    }
    
    public static void uniAdminTasks(UniAdmin admin) {
        System.out.println("Welocme " + admin.getName() + " of " + 
                admin.getUniversity().getUniversityName());
        System.out.println("Uni Admin tasks");
        char ch = uniAdminMenu();
        while (ch != '0') {
            switch (ch) {
                case '1':
                    recordProgramme(admin);
                    break;
                case '2':
                    reviewApplicant(admin);
                    break;
                case '0':
                    System.out.println("Back to main menu");
                    break;
                default:
                    printErrorMessage();
                    break;
            }
            
            ch = uniAdminMenu();
        }
    }
    
    public static void recordProgramme(UniAdmin admin) {

        University uni = admin.getUniversity();        
        
        String choice;
        do {
            System.out.println("Record New Programme");
            System.out.print("Programme Name: ");
            String programmeName = kbd.nextLine();
            System.out.print("Programme Description: ");
            String programmeDescription = kbd.nextLine();
            LocalDate closingDate = getDate("Application Closing Date (dd/mm/yyyy): ");
            System.out.println();
            
            Programme np = new Programme(uni, programmeName, programmeDescription, 
                closingDate);
            uni.addProgramme(np);
            
            choice = getChoice("Add another programme? (Y/N) ");
        } while (choice.equalsIgnoreCase("Y"));               
    }
    
    public static void reviewApplicant(UniAdmin admin) {
        System.out.println("REVIEW APPLICATIONS");
        System.out.println();
        ArrayList<Programme> programmes = admin.getUniversity().getProgrammes();
        if (programmes.isEmpty())
            System.out.println("No programme has been added yet....");
        else {
            String reviewAgain = "N";
            do {
                System.out.println("Programme(s):");
                int i = 0;
                for (Programme p: programmes) {
                    String message = (i+1) + ". " + p.getProgrammeName() + " (ID: " +
                        p.getProgrammeID() + ") " + " closing date: " +
                        p.getClosingDate();
                    i++;
                    int numOfApps = p.noOfApplications();
                    if (numOfApps != 0)
                        if (numOfApps == 1)
                            message += " has " + numOfApps + " application";
                        else
                            message += " has " + numOfApps + " applications";
                    else
                        message += " has no application.";
                    System.out.println(message);
                }
                System.out.print("Key in integer number to review or <Enter> to abort: ");
                String programNum = kbd.nextLine();
                if (!programNum.equals("")) {
                    int pos = Integer.parseInt(programNum);
                    if (pos >= 1 && pos <= programmes.size())
                    {
                        Programme selectedProgramme = programmes.get(pos - 1);
                        int noOfApplications = selectedProgramme.noOfApplications();
                        if (noOfApplications == 0)
                            System.out.println("This programme does not have any application yet");
                        else {
                            String choice;
                            do {     
                                ArrayList<Application> appList = selectedProgramme.getApplications();
                
                                i = 0;
                                for (Application ap: appList) {
                                    Applicant applicant = ap.getApplicant();
                                    QualificationObtained qOb = applicant.getQualificationsObtained().get(0);
                                    System.out.println((i+1) + ". " + applicant.getName() +
                                        ", status: " + ap.getStatus() + " " + 
                                        qOb.getQualificationName() + " result with score of " +
                                        qOb.getOverallScore());
                                    i++;
                                }
                                System.out.println("Select integer number of application to review: ");
                                String appNum = kbd.nextLine();
                                pos = Integer.parseInt(appNum);
                                if (pos >= 1 && pos <= noOfApplications) {
                                    Application app = selectedProgramme.getApplications().get(pos - 1);
                                    Applicant applicant = app.getApplicant();
                                    QualificationObtained qOb = applicant.getQualificationsObtained().get(0);
                                    System.out.println("Application details");
                                    System.out.println(applicant.getName() + " using " + 
                                        qOb.getQualificationName() + " rsult with score of " +
                                        qOb.getOverallScore() + "\nand details result as shown:");
                                    for (Result r: qOb.getResults())
                                        System.out.println(r);
                                    System.out.println();
                                    String result = getChoice("Application successful? (Y/N) ");
                                    if (result.equalsIgnoreCase("Y"))
                                        app.setStatus("SUCCESSFUL");
                                    else
                                        app.setStatus("UNSUCCESSFUL");
                                }
                                else
                                    System.out.println("Wrong number!\n");
                                
                                choice = getChoice("Do you want to review another application? (Y/N) ");
                            } while (choice.equalsIgnoreCase("Y"));
                        }
                    }
                }
                else
                    System.out.println("Review application ended...\n");
                
                reviewAgain = getChoice("Review another programme? (y/N) ");
            } while (reviewAgain.equals("Y"));
        } 
    }

    /* ============================================================
     * Applicant tasks..... various methods
     */
    public static char applicantMenu() {
        System.out.println();
        System.out.println("Applicant tasks:");
        System.out.println("1. Apply Programme");
        System.out.println("2. Set Qualificatio Obtained");
        System.out.println();
        System.out.println("0. Back to main menu...");
        System.out.println();
        System.out.print("Your choice: ");
        return kbd.nextLine().charAt(0);
    }
    
    public static void applicantTasks(Applicant applicant) {
        char ch = applicantMenu();
        while (ch != '0') {
            switch (ch) {
                case '1':
                    applyProgramme(applicant);
                    break;
                case '2':
                    setQualiObtained(applicant);
                    break;
                case '0':
                    System.out.println("Back to main menu");
                    break;
                default:
                    printErrorMessage();
                    break;
            }
            
            ch = applicantMenu();
        }
    }

    public static void applyProgramme(Applicant applicant) {
        System.out.println("APPLY FOR PROGRAMME");
        System.out.println("~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        
        int count = 0;
        String choice;
        ArrayList<Programme> programmes;
        do {
            count ++; 
            System.out.print("Enter programme critera (e.g. IT, Business): ");
            String criteria = kbd.nextLine();
            programmes = system.findProgramme(criteria);
            if (programmes.isEmpty()) {
                System.out.println("No programme matches '" + criteria + "'");
            }
             else {
                System.out.println("List of programme(s):");
                for (Programme p: programmes)
                    if (!p.isBefore(LocalDate.now()))
                        System.out.println(p.getProgrammeID() + " : " + 
                            p.getProgrammeName() + ", " +
                            p.getUniversity().getUniversityName());
            }        
            if (count == 3) { // To prevent infinite loop...
                String quit = getChoice("After searching 3 times, quit? (Y/N) ");
                if (quit.equalsIgnoreCase("N"))
                    count = 0;
                else
                    break;
            }
            System.out.println();
            choice = getChoice("Try new search for other programme? (Y/N) ");
        } while (choice.equalsIgnoreCase("Y"));
        
        System.out.println();        
        if (!programmes.isEmpty()) {
            String programmeID = "";
            boolean done = false;
            do {
                System.out.print("Select programme ID to apply or <Enter> to abort>: ");
                programmeID = kbd.nextLine(); 
                if (!programmeID.equals("")) {
                    if (applicant.findApplication(programmeID) != null)
                        System.out.println("You have ALREADY applied for that programme!");
                    else
                        done = true;
                }
                else
                    done = true;
            } while (!done);
            
            if (programmeID.equals(""))
                System.out.println("Bye...");
            else {
                Programme selProg = null;
                for (Programme p: programmes)
                    if (p.getProgrammeID().equalsIgnoreCase(programmeID)) {
                        selProg = p;
                        break;
                    }                
                if (selProg != null) {
                    System.out.println("~~ Selected Programme Details ~~");
                    System.out.println(selProg.getUniversity().getUniversityName());
                    System.out.println(selProg.getProgrammeName());
                    System.out.println(selProg.getDescription());
                    System.out.println("Closing date: " + selProg.getClosingDate());
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                    ArrayList<QualificationObtained> qualObs = applicant.getQualificationsObtained();

                    if (qualObs == null || qualObs.size() == 0) {
                        System.out.println("\nYou have not obtained any qualification yet!");
                        System.out.println("Obtained qualification first!");
                    }
                    else {
                        Application newApp = new Application(selProg, applicant);
                        newApp.setApplicationDate(LocalDate.now());
                        System.out.println("\nApplication submitted...");
                    }
                }
                else
                    System.out.println("Wrong programme ID!");
            }
        }
    }

    public static void setQualiObtained(Applicant applicant) {
        System.out.println("SETTING QUALIFICATION OBTAINED");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        
        ArrayList<Qualification> q = system.getQualifications();
        if (q.size() != 0) {
            System.out.println("List of qualifcation(s)");
            for (Qualification quali: q) {
                System.out.println(quali.getQualificationName() + " iD: " +
                    quali.getQualificationID());
            }
            System.out.println();
            Qualification selectedQualification = null;
            do {               
                System.out.print("Enter qualification name: ");
                String quaName = kbd.nextLine();
                selectedQualification = system.findQualification(quaName);
                if (selectedQualification == null)
                    System.out.println("Please enter correct name!");
            } while (selectedQualification == null);          
            
            System.out.println("\nSelected Qualification Details\n" + selectedQualification);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            QualificationObtained qOb = new QualificationObtained(selectedQualification);

            System.out.println("Key-in your subject result:");
            if (selectedQualification.getGradeType().equalsIgnoreCase("None"))
            {               
                System.out.println("Subject score: 0 - 100");
                System.out.print("Enter subject name (<Enter> to stop): ");
                String subjectName = kbd.nextLine();
                while (!subjectName.equals("")) {
                    System.out.print("Enter score: ");
                    String scoreStr = kbd.nextLine();
                    double score = Double.parseDouble(scoreStr);
                    Result rs = new ResultDouble(subjectName, score);
                    qOb.addResult(rs);
                    System.out.print("Enter subject name (<Enter> to stop): ");
                    subjectName = kbd.nextLine();
                }
            }
            else if (selectedQualification.getGradeType().equalsIgnoreCase("Integer"))
            {               
                System.out.println("Subject score: Integer value");
                System.out.println(selectedQualification.getGradeList());
                System.out.print("Enter subject name (<Enter> to stop): ");
                String subjectName = kbd.nextLine();
                while (!subjectName.equals("")) {
                    System.out.print("Enter score: ");
                    String scoreStr = kbd.nextLine();
                    int score = Integer.parseInt(scoreStr);
                    Result rs = new ResultInteger(subjectName, score);
                    qOb.addResult(rs);
                    System.out.print("Enter subject name (<Enter> to stop: ");
                    subjectName = kbd.nextLine();
                }               
            }
            else if (selectedQualification.getGradeType().equalsIgnoreCase("String"))
            {
                System.out.println("Subject score: letter grade");
                System.out.println(selectedQualification.getGradeList());
                System.out.print("Enter subject name (<Enter> to stop: ");
                String subjectName = kbd.nextLine();
                while (!subjectName.equals("")) {
                    System.out.print("Enter grade: ");
                    String grade = kbd.nextLine();
                    Result rs = new ResultGrade(subjectName, grade);
                    qOb.addResult(rs);
                    System.out.print("Enter subject name (<Enter> to stop: ");
                    subjectName = kbd.nextLine();
                }
            }
            System.out.println();
            System.out.print("Enter the overall qualification score obtained: ");
            String scoreStr = kbd.nextLine();
            qOb.setOverallScore(Double.parseDouble(scoreStr));
            
            selectedQualification.addQualificationObtained(qOb);
            applicant.addQualificationObtained(qOb);
            System.out.println("Qulificationsuccessfully obtained...");
        }
        else
            System.out.println("No qualifcation has been defined yet!");
    }
    
    /** ###########################################################################
     * 
     * Displaying useful information...
     * These tasks are NOT part of the assignment, but added to show 
     * what have been created are correct.
     * 
     */
    public static void listUsers() {
        ArrayList<User> users = system.getUsers();
        if (users.isEmpty()) {
            System.out.println("No registered Users...");
        } else {
            System.out.println("All users in the system:");
            //users.stream().forEach(System.out::println);
            for (User aUser : users) {
                if (aUser instanceof Applicant) {
                    System.out.println(aUser.getName() + " (" + aUser.getUserName() + 
                        " : " + aUser.getPassword() + ") " + aUser.getEmail() +
                        " DOB: " + ((Applicant) aUser).getDateOfBirth());
                }    
                else
                    System.out.println(aUser);
                
            }
        }
    }

    public static void listUniversities() {
        ArrayList<University> unis = system.getUniversities();
        if (unis.isEmpty()) {
            System.out.println("No registered universities...");
        } else {
            System.out.println("All universities in the system:");
            System.out.println();
            for (University u: unis) {
                System.out.println("~~~~~~~~~~~~~~~~~~~");
                System.out.println(u.getUniversityName() + " (ID: " + 
                        u.getUniversityID() + ")");
                if (u.noOfProgrammes() != 0) {
                    for (Programme p: u.getProgrammes()) {
                        System.out.println(p);
                        if (p.noOfApplications() != 0) {
                            System.out.println("Application(s):");
                            for (Application app: p.getApplications()) {
                                System.out.println(app.getApplicant().getName() + 
                                    " applied on " + app.getApplicationDate() + 
                                    " status: " + app.getStatus());
                                //System.out.println();
                            }
                        }
                    System.out.println();
                    } 
                }             
            }
            System.out.println();
        }    
    }

    public static void listQualifications() {
            ArrayList<Qualification> qualis = system.getQualifications();
            if (qualis.isEmpty()) {
                System.out.println("No registered qualifications...");
            } else {
                System.out.println("All qualifications in the system:");
                //qualis.stream().forEach(System.out::println);
                for (Qualification q: qualis) {
                    System.out.println();
                    System.out.println(q);
                    
                 System.out.println(q.noOfQualitificationsObtained() + " qualification obtained");
                 //if (q.noOfQualitificationsObtained() != 0)
                 //   for (QualificationObtained qo: q.getQualificationsObtained())
                 //     System.out.println(qo);    
                }
                System.out.println();               
            }    
    }
    
    public static void listApplications() {
        ArrayList<Application> applications = system.getAllApplications();
        if (applications.size() == 0)
            System.out.println("No applications has been received yet!");
        else {
            Collections.sort(applications,
                    (lhs, rhs) -> lhs.getApplicant().getName().compareToIgnoreCase(
                            rhs.getApplicant().getName() ));
            /*        
            Collections.sort(applications, new Comparator<Application>() {
                public int compare(Application lhs, Application rhs) {
                    return lhs.getApplicant().getName().compareToIgnoreCase(
                            rhs.getApplicant().getName());
                }
            }); 
            */
            System.out.println("All applicaton(s):");
            for (int i = 0; i < applications.size(); i++) {
                Application ap = applications.get(i);
                System.out.println((i+1) + ". " + ap.getApplicant().getName() +
                    " : " + ap.getProgramme().getProgrammeName() + ", " +
                    ap.getProgramme().getUniversity().getUniversityName() +
                    " applied on " + ap.getApplicationDate() + 
                    " status: " + ap.getStatus());
            }
            System.out.println();
        }
    }
    /** ###########################################################################

    // The following methods are used in SignUp for getting user input, and
    // also performing validation checks
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

    /**
     * To valid username
     *
     * @return String username
     */
    public static String getUsername() {
        System.out.print("Username: ");
        String username = kbd.nextLine();
        while (system.findUser(username) != null || username.equals("")) {
            if (username.equals("")) {
                System.out.println("Username cannot be blank! Please "
                        + "enter username!\n");
            } else {
                System.out.println("Username already exists. "
                        + "Please enter another username!\n");
            }
            System.out.print("Username: ");
            username = kbd.nextLine();
        }
        return username;
    }

    /**
     * Get valid password
     *
     * @return String password
     */
    public static String getPassword() {
        System.out.print("Password (Min. length: 8): ");
        String password = kbd.nextLine();
        while (password.length() < 8) {
            if (password.equals("")) {
                System.out.println("Password cannot be blank! "
                        + "Please enter password!\n");
            } else {
                System.out.println("Password must contain at least "
                        + "8 characters!\n");
            }

            System.out.print("Password (Min. length: 8): ");
            password = kbd.nextLine();
        }
        return password;
    }

    /**
     * Get valid full name
     *
     * @return String full name
     */
    public static String getFullName() {
        System.out.print("Full name: ");
        String fullName = kbd.nextLine();
        while (fullName.equals("")) {
            System.out.println("Full name cannot be blank! "
                    + "Please enter full name!\n");
            System.out.print("Full name: ");
            fullName = kbd.nextLine();
        }
        return fullName;
    }
    
    /**
     * Get valid mobile number
     *
     * @return String mobile number
     */
    public static String getEmail() {
        System.out.print("Email (xx@xx): ");
        String email = kbd.nextLine();
        boolean done = !email.equals("") && (email.length() > 2) && 
                       (email.indexOf('@') != -1) && (email.indexOf('@') == email.lastIndexOf('@'));
        while (!done) {
            System.out.println("Error: Email cannot be blank or Invalid email format!");
            System.out.print("Email (xx@xx): ");
            email = kbd.nextLine();
            done = !email.equals("") && (email.length() > 2) && (email.indexOf('@') != -1) &&
                   (email.indexOf('@') == email.lastIndexOf('@'));
        }
        return email;
    }

    /* ++++++++++ The following methods are for Application only ** */
    public static String getIDType() {
        System.out.print("ID Type (MyKad or Passport): ");
        String idType = kbd.nextLine();
        boolean done = !idType.equals("") && (idType.equalsIgnoreCase("MyKad") ||
                idType.equalsIgnoreCase("Passport"));
        while (!done) {
            System.out.println("Error: ID Type must be 'MyKad' or 'Passport'!");
            System.out.print("ID Type (MyKad or Passport) : : ");
            idType  = kbd.nextLine();
            done = !idType.equals("") && (idType.equalsIgnoreCase("MyKad") ||
                idType.equalsIgnoreCase("Passport"));
        }
        return idType ;
    }

    public static String getIDNumber() {
        System.out.print("ID Number: ");
        String idNumber = kbd.nextLine();
        while (idNumber.equals("")) {
            System.out.println("ID Number cannot be blank! "
                    + "Please enter ID Number!\n");
            System.out.print("ID Number: ");
            idNumber = kbd.nextLine();
        }
        return idNumber;
    }
    
    /**
     * Get valid mobile number
     *
     * @return String mobile number
     */
    public static String getMobileNo() {
        System.out.print("Mobile phone number: ");
        String mobileNo = kbd.nextLine();
        while (mobileNo.equals("")) {
            System.out.println("Mobile phone number cannot be blank! "
                    + "Please enter mobile phone number!\n");
            System.out.print("Mobile phone number: ");
            mobileNo = kbd.nextLine();
        }
        return mobileNo;
    }


    /**
     * Use to get date of birth, and closing date...
     * @param prompt message to display
     * @return the required date
     */
    public static LocalDate getDate(String prompt) {
        LocalDate date = null;
        do {
            System.out.print(prompt);
            String dateStr = kbd.nextLine();
            if (!dateStr.equals("")) {
                date = LocalDate.parse(dateStr, dtf);      
            }
            else
                System.out.println("Date cannot be blank!" + 
                    "Please enter a date!\n");
        } while (date == null);      
        return date;
    }
    
    /**
     * Get the valid choice from user
     *
     * @return String Choice
     */
    public static String getChoice(String prompt) {
        System.out.print(prompt);
        String choice = kbd.nextLine().charAt(0) + "";
        while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
            System.out.println("Invalid option! Please enter Y for yes or N for no!");
            System.out.print("(Y/N): ");
            choice = kbd.nextLine().charAt(0) + "";
        }
        return choice.toUpperCase();
    }
    
    public static void printErrorMessage() {
        System.out.println("Invalid choice! Try again!");
    }
    
    public static void init() {
        User u1 = new Applicant("abc", "12121212", "James Bond", "jb@gmail.com", 
        "MyKad", "1234", "012-1212121", LocalDate.parse("12/12/1999", dtf));
        system.addUser(u1);
        
        University help = new University("HELP University");
        help.addAdmin(new UniAdmin("bbc", "2121", "Peter Parker", "spiderman@help.edu.my"));
       
        University taylor = new University("Taylor University");
        taylor.addAdmin(new UniAdmin("cbc", "2121", "Clark Kent", "superman@taylor.edu.my"));
        
        system.addUser(help.getUniAdmins().get(0));
        system.addUser(taylor.getUniAdmins().get(0));
        
        system.addUniversity(help);
        system.addUniversity(taylor);
        
        help.addProgramme(new Programme(help, "BIT", "3-years IT degree programme", 
            LocalDate.parse("12/10/2019", dtf)));
        help.addProgramme(new Programme(help, "BSc (Game Design)", 
                "3-years IT degree IT, majoring in Game Design", 
            LocalDate.parse("05/07/2019", dtf)));
        taylor.addProgramme(new Programme(taylor, "BIT", "Affordable IT degree programme", 
            LocalDate.parse("28/09/2019", dtf)));
        taylor.addProgramme(new Programme(taylor, "BBus", "3-years business degree programme", 
            LocalDate.parse("12/10/2019", dtf)));
        
        ArrayList<Object> gradeList = new ArrayList<>();
        gradeList.add("A - 4.0");
        gradeList.add("B - 3.0");
        gradeList.add("C - 2.0");
        gradeList.add("D - 1.0");
        gradeList.add("F - 0.0");
        Qualification stpm = new Qualification("STPM", 0.0, 4.0, "Average of best 3 subjects",
            gradeList);
        stpm.setGradeType("String");
        system.addQualification(stpm);
        
        system.addQualification(new Qualification("SAM", 0, 100, "Average of 6 subjects", null));
        system.getQualifications().get(1).setGradeType("None");
    }
}