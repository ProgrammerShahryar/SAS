/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Shahryar
 */
public class Qualification {
    
    private String gradeType;  // either "String", "Integer", "None"
    private static int num2Used = 1;
    private String qualificationID;
    private String qualificationName;
    private double minimumScore;
    private double maximumScore;
    private String resultCalcDescription;
    private ArrayList<Object> gradeList;
    private ArrayList<QualificationObtained> qualificationsObtained;


    public Qualification(String qualificationName, double minimumScore, 
        double maximumScore, String resultCalcDescription, 
        ArrayList<Object> gradeList) {
        setQualificationID();
        setQualificationName(qualificationName);
        setMinimumScore(minimumScore);
        setMaximumScore(maximumScore);
        setResultCalcDescription(resultCalcDescription);
        setGradeList(gradeList);
        setQualificationsObtained(new ArrayList<QualificationObtained>());
    }


    public ArrayList<QualificationObtained> getQualificationsObtained() {
        return qualificationsObtained;
    }

    public void setQualificationsObtained(ArrayList<QualificationObtained> qualificationsObtained) {
        this.qualificationsObtained = qualificationsObtained;
    }
    
    public String getGradeType() {
        return gradeType;
    }

    public void setGradeType(String gradeType) {
        this.gradeType = gradeType;
    }
    
    public boolean addQualificationObtained(QualificationObtained quaObtained) {
        return getQualificationsObtained().add(quaObtained);
    }
    
    public String toString() {
        return getQualificationName() + " (ID:" + getQualificationID() + "):\n" +
            "Min Score: " + getMinimumScore() + " Max Score: " + getMaximumScore() + "\n" +
            "Result Calculation: " + getResultCalcDescription() + "\n" +
            "Grades: " + (getGradeList() != null?getGradeList():" - None -");
    }
    
    /**
     * @return the num2Used
     */
    public static int getNum2Used() {
        return num2Used;
    }

    /**
     * @param aNum2Used the num2Used to set
     */
    public static void setNum2Used(int aNum2Used) {
        num2Used = aNum2Used;
    }

    /**
     * @return the qualiticationID
     */
    public String getQualificationID() {
        return qualificationID;
    }

    /**
     * @param qualiticationID the qualiticationID to set
     */
    public void setQualificationID() {
        this.qualificationID = "Q" + getNum2Used();
        setNum2Used(getNum2Used() + 1);
    }

    /**
     * @return the qualificationName
     */
    public String getQualificationName() {
        return qualificationName;
    }

    /**
     * @param qualificationName the qualificationName to set
     */
    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    /**
     * @return the minimumScore
     */
    public double getMinimumScore() {
        return minimumScore;
    }

    /**
     * @param minimumScore the minimumScore to set
     */
    public void setMinimumScore(double minimumScore) {
        this.minimumScore = minimumScore;
    }

    /**
     * @return the maximumScore
     */
    public double getMaximumScore() {
        return maximumScore;
    }

    /**
     * @param maximumScore the maximumScore to set
     */
    public void setMaximumScore(double maximumScore) {
        this.maximumScore = maximumScore;
    }

    /**
     * @return the resultCalcDescription
     */
    public String getResultCalcDescription() {
        return resultCalcDescription;
    }

    /**
     * @param resultCalcDescription the resultCalcDescription to set
     */
    public void setResultCalcDescription(String resultCalcDescription) {
        this.resultCalcDescription = resultCalcDescription;
    }

    /**
     * @return the gradeList
     */
    public ArrayList<Object> getGradeList() {
        return gradeList;
    }

    /**
     * @param gradeList the gradeList to set
     */
    public void setGradeList(ArrayList<Object> gradeList) {
        this.gradeList = gradeList;
    }

    public int noOfQualitificationsObtained() {
        return getQualificationsObtained().size();
    }
    
    public static void main(String[] args) {
        
        Scanner kbd = new Scanner(System.in);
        
        System.out.println("Create New Qualification");
        System.out.print("Qualification name: ");
        String name = kbd.nextLine();
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
            System.out.print("Qualification withe <G>rade list or <I>nteger list or " +
                    "<N>one? ");
            gradeType = kbd.nextLine();
        } while (!gradeType.equalsIgnoreCase("G") && !gradeType.equalsIgnoreCase("I") &&
                !gradeType.equalsIgnoreCase("N"));
        
        if (gradeType.equalsIgnoreCase("N"))
            gradeList = null;
        else if (gradeType.equalsIgnoreCase("I")) {
            System.out.print("Enter integer score (-1 to stop): ");
            String scoreStr = kbd.nextLine();
            int score = Integer.parseInt(scoreStr);
            while (score != -1) {
                gradeList.add(score);
                System.out.print("Enter integer score (-1 to stop): ");
                scoreStr = kbd.nextLine();
                score = Integer.parseInt(scoreStr);
            }           
        }
        else {
            
            String gradeAndScore;
            do {
                System.out.print("Enter string grade and corresponding integer " +
                        "score (eg A - 4.0) <Enter> to stop): ");
                gradeAndScore = kbd.nextLine().trim();
                if (!gradeAndScore.isEmpty())
                    gradeList.add(gradeAndScore);
            } while (!gradeAndScore.equals(""));
        }
        
        Qualification q = new Qualification(name, minScore, maxScore, 
                computeDescription, gradeList);
        
        System.out.println("Qualifcation: " + q);
    }
}
