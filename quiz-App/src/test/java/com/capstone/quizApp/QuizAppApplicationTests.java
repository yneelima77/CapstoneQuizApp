package com.capstone.quizApp;

import com.capstone.quizApp.dto.UserDto;
import com.capstone.quizApp.entity.Questions;
import com.capstone.quizApp.entity.QuizQuestions;
import com.capstone.quizApp.entity.User;
import com.capstone.quizApp.repository.QuestionsRepo;
import com.capstone.quizApp.repository.UserRepo;
import com.capstone.quizApp.service.QuizService;
import com.capstone.quizApp.service.UserService;
import com.capstone.quizApp.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class QuizAppApplicationTests {

    //mocks the questions repo and inject into question service
    @MockBean
    private QuestionsRepo questionsRepo;

    //inject the QuizService dependency so that can test the behaviour of service
    @Autowired
    private QuizService quizService;

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    //Test the quizService.getQuestions() method and verifies the returned object QuizQuestions in that method.
    @Test
    public void testGetQuestions() {
        //questions for testing
        List<Questions> questionsList = Arrays.asList(
                new Questions(1, "Question 1", "Option A", "Option B", "Option C", "Option D", 2),
                new Questions(2, "Question 2", "Option A", "Option B", "Option C", "Option D", 1),
                new Questions(3, "Question 3", "Option A", "Option B", "Option C", "Option D", 3),
                new Questions(4, "Question 4", "Option A", "Option B", "Option C", "Option D", 4),
                new Questions(5, "Question 5", "Option A", "Option B", "Option C", "Option D", 2),
                new Questions(6, "Question 6", "Option A", "Option B", "Option C", "Option D", 1),
                new Questions(7, "Question 7", "Option A", "Option B", "Option C", "Option D", 3),
                new Questions(8, "Question 8", "Option A", "Option B", "Option C", "Option D", 4),
                new Questions(9, "Question 9", "Option A", "Option B", "Option C", "Option D", 2),
                new Questions(10, "Question 10", "Option A", "Option B", "Option C", "Option D", 1),
                new Questions(11, "Question 11", "Option A", "Option B", "Option C", "Option D", 3),
                new Questions(12, "Question 12", "Option A", "Option B", "Option C", "Option D", 4)
        );

        //mocks the behavior of the object to return the list of questions
        when(questionsRepo.findAll()).thenReturn(questionsList);

        //then test the method that needs to return list of quiz questions to user and assert
        QuizQuestions quizQuestions = quizService.getQuestions();
        assertNotNull(quizQuestions);

        //test that quizQuestions is returning random 10 questions
        List<Questions> queList = quizQuestions.getQuestions();
        assertNotNull(queList);
        assertEquals(10, queList.size());

    }

    //test the findByEmail() userRepo method
    @Test
    public void testFindByEmail() {
        //list of users
        User expectedUser = new User("tom@gmail.com");

        //mock the userRepo.findAll() method to return the sample list of users
        when(userRepo.findByEmail("tom@gmail.com")).thenReturn(expectedUser);

        //then compare the email get from userService method call
        User actualUser = userService.findByEmail("tom@gmail.com");
        assertEquals(expectedUser, actualUser);
    }
}
