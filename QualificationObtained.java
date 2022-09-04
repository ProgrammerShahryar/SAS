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
public class QualificationObtained {
    private ArrayList<Result> results;
    private Qualification qualification;
    private double overallScore;

    public double getOverallScore() {
        return overallScore;
    }

    public String getQualificationName() {
        return getQualification().getQualificationName();
    }
    
    public void setOverallScore(double overallScore) {
        this.overallScore = overallScore;
    }
    
    public QualificationObtained(Qualification qualification) {
        this(qualification, 0.0);
    }
    
    public QualificationObtained(Qualification qualification, double overallScore) {
        setQualification(qualification);
        setOverallScore(overallScore);
        setResults(new ArrayList<>());
    }

    /*
    FUTURE WORK...
    
    public double overallScore() {       
        return ComputeScore.getScore(getQualification(), getResults());
    }
    */

    /**
     * @return the results
     */
    public ArrayList<Result> getResults() {
        return results;
    }

    /**
     * @param results the results to set
     */
    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    /**
     * @return the qualificationID
     */
    public Qualification getQualification() {
        return qualification;
    }

    /**
     * @param qualificationID the qualificationID to set
     */
    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }
    
    public boolean addResult(Result result) {
        return getResults().add(result);
    }
    
    public String toString() {
        String details = getQualification().getQualificationName() + " (" +
            getQualification().getQualificationID() + "):\n";
        for (Result r: getResults())
            details += r.toString() + "\n";
     
        return details;
    }
}
