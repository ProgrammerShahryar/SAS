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
public class ResultDouble extends Result { 
    private double score;
    
    public ResultDouble(String subjectName, double score) {
        super(subjectName);
        setScore(score);
    }
    
    public void setScore(double score) {
        this.score = score;
    }
    public double getScore() {
        return score;
    }
    
    public String toString() {
        return super.toString() + " " + getScore();
    }
}
