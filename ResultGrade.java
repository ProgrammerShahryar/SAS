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
public class ResultGrade extends Result { 

    private String grade;

    public ResultGrade(String subjectName, String grade) {
        super(subjectName);
        setGrade(grade);
    }

    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String toString() {
        return super.toString() + " " + getGrade();
    }
/*
    public static void main(String[] args) {
        SubjectResult<Grade> r2 = new ResultGrade(STPM_Grade.A);
        System.out.println(r2);
        System.out.println(r2.getResult());
        System.out.println(r2.getResult().getTheGrade());
        System.out.println(r2.getResult().getTheGrade().getClass());

        STPM_Grade sr = (STPM_Grade) r2.getResult();
        System.out.println(sr.getGrade());
        System.out.println(sr.getPoint());
        
        SubjectResult<Grade> r1 = new ResultGrade(STPM_Grade.AMINUS);
        SubjectResult<Grade> r3 = new ResultGrade(STPM_Grade.D);
        SubjectResult<Grade> r4 = new ResultGrade(STPM_Grade.BPLUS);
        SubjectResult<Grade> r5 = new ResultGrade(STPM_Grade.AMINUS);
        SubjectResult<Grade> r6 = new ResultGrade(STPM_Grade.CMINUS);
        
        ArrayList<SubjectResult> results = new ArrayList<>();
        results.add(r1);
        results.add(r2);
        results.add(r3);
        results.add(r4);
        results.add(r5);
        results.add(r6);
        
        System.out.println();
        results.forEach(System.out::println);
        
        results.sort( new CompareResult() );
        System.out.println();
        results.forEach(System.out::println);
        
        System.out.println(results.get(0));
        System.out.println(results.get(0).getClass());
        ResultGrade rg = (ResultGrade) results.get(0);
        System.out.println(rg);
        System.out.println(rg.getResult().getClass());
        STPM_Grade stpmg = (STPM_Grade) rg.getResult();
        System.out.println(stpmg.name());
        
        
        
        //Result<STPM_Grade> r1 = new Result()
    }*/
}
/*
class CompareResult implements Comparator<ResultGrade> { //SubjectResult> {
    public int compare(ResultGrade lhs, ResultGrade rhs) {
        STPM_Grade l = (STPM_Grade) lhs.getResult();
        STPM_Grade r = (STPM_Grade) lhs.getResult();
        
        return l.compareTo(r);
        //return ((Result) lhs.getResult()).
    }
}
*/