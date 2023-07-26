package com.Neelimay.quizMaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.capstone.quizApp.controller", "com.capstone.quizApp.model", "com.capstone.quizApp.myServices",
//"com.capstone.quizApp.repository"})
public class QuizAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizAppApplication.class, args);
	}

}
