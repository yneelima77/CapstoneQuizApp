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

    public QuizQuestions getQuestions() {
        List<Questions> allQuesList = questionsRepo.findAll();
        List<Questions> quesList = new ArrayList<Questions>();

        Random random = new Random();

        for (int i = 0; i < allQuesList.size(); i++) {
            int rand = random.nextInt(allQuesList.size());
            quesList.add(allQuesList.get(rand));
            allQuesList.remove(rand);
        }

        quizQuestions.setQuestions(quesList);

        return quizQuestions;
    }

    public int getResult(QuizQuestions quizQuestions) throws Exception {
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