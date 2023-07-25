package com.capstone.quizApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.capstone.quizApp.entity.Questions;
import com.capstone.quizApp.entity.QuizQuestions;
import com.capstone.quizApp.entity.Result;
import com.capstone.quizApp.repository.QuestionsRepo;
import com.capstone.quizApp.repository.ResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    @Autowired
    private Questions questions;
    @Autowired
    private QuizQuestions quizQuestions;
    @Autowired
    QuestionsRepo questionsRepo;
    @Autowired
    Result result;
    @Autowired
    ResultRepo resultRepo;

    //gets the random 10 questions from questions and add them to quizQuestions object to display
    public QuizQuestions getQuestions() {
        //to create a copy of all the questions list obtained from findAll() method as arraylist so that list can be modifiable by that
        //we can ensure that each questions is unique in quiz
        //Here allQuesList(modifiable list) is the copy of quiz questions that we got through findall()(un modifiable list)
        List<Questions> allQuesList = new ArrayList<>(questionsRepo.findAll());
        List<Questions> quesList = new ArrayList<Questions>();

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int rand = random.nextInt(allQuesList.size());
            quesList.add(allQuesList.get(rand));
            allQuesList.remove(rand);
        }

        quizQuestions.setQuestions(quesList);

        return quizQuestions;
    }

    //calculates the score
    public int getResult(QuizQuestions quizQuestions){
        int correct = 0;

        /*Getting null in ques*/
        try {
            for (Questions ques : quizQuestions.getQuestions()) {
                if (ques.getAns() == ques.getChoose()) {
                    correct++;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return correct;
    }

    //saves the quiz result to resultRepo to persist in result entity
    public void saveResult(Result result) {
        Result saveScore = new Result();
        saveScore.setUsername(result.getUsername());
        saveScore.setCorrectAns(result.getCorrectAns());
        resultRepo.save(saveScore);
    }

    //Gets the topN records from result table
    public List<Result> getTopResult(int topN) {
        Pageable pageable = PageRequest.of(0, topN, Sort.by(Sort.Direction.DESC, "correctAns"));
        Page<Result> page = resultRepo.findAll(pageable);

        // Return the content of the first page as a list of top results
        return page.getContent();
    }
}