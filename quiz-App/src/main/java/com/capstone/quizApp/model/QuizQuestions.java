package com.capstone.quizApp.model;

import org.springframework.stereotype.Component;

import java.util.List;

/*This class is responsible for getting random list of questions for the quiz*/
@Component
public class QuizQuestions {
    private List<Questions> questionsList;
    public List<Questions> getQuestions() {
        return questionsList;
    }

    public void setQuestions(List<Questions> questionsList) {
        this.questionsList = questionsList;
    }
}
