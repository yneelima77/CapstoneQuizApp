package com.capstone.quizApp.myServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.capstone.quizApp.model.Questions;
import com.capstone.quizApp.model.QuizQuestions;
import com.capstone.quizApp.model.Result;
import com.capstone.quizApp.repository.QuestionsRepo;
import com.capstone.quizApp.repository.ResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

        for (int i = 0; i < 10; i++) {
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

    public List<Result> getTopResult() {

        return resultRepo.findAll(Sort.by(Sort.Direction.DESC, "correctAns"));
    }
}