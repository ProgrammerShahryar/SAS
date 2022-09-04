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
public class ResultInteger extends Result { 
    private int score;
    
    public ResultInteger(String subjectName, int score) {
        super(subjectName);
        setScore(score);
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }
    
    public String toString() {
        return super.toString() + " " + getScore();
    }
}
