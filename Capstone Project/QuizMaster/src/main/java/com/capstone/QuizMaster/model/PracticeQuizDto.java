package com.capstone.QuizMaster.model;

import lombok.Data;


/*Wrapper class to hide the answer and certain details about question and to display just the question with options*/
public class PracticeQuizDto {
    private long id;
    private String qTitle;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    public PracticeQuizDto(long id, String qTitle, String optionA, String optionB, String optionC, String optionD) {
        this.id = id;
        this.qTitle = qTitle;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getqTitle() {
        return qTitle;
    }

    public void setqTitle(String qTitle) {
        this.qTitle = qTitle;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }
}
