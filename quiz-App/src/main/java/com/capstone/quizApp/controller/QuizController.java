package com.capstone.quizApp.controller;

import com.capstone.quizApp.dto.UserDto;
import com.capstone.quizApp.entity.QuizQuestions;
import com.capstone.quizApp.entity.Result;
import com.capstone.quizApp.entity.User;
import com.capstone.quizApp.service.QuizService;
import com.capstone.quizApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class QuizController {


    private UserService userService;

    @Autowired
    Result result;
    @Autowired
    QuizService quizService;

    Boolean submitted = false;

    public QuizController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("result")
    public String getResult(){
        return "result";
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }



    @PostMapping("/quiz")
    public String quiz(@RequestParam String username, Model m) {
        if(username.equals("")) {
            return "redirect:/";
        }

        submitted = false;
        result.setUsername(username);

        QuizQuestions quizQuestions = quizService.getQuestions();
        m.addAttribute("quizQuestions", quizQuestions);

        return "quiz";
       /* submitted = false;
        result.setUsername(userDetails.getUsername());
        QuizQuestions quizQuestions = quizService.getQuestions();
        m.addAttribute("quizQuestions", quizQuestions);
        return "quiz";*/
    }

    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute QuizQuestions quizQuestions, Model model) throws Exception {
        if(!submitted) {
            result.setCorrectAns(quizService.getResult(quizQuestions));
            quizService.saveResult(result);
            submitted = true;
        }
        System.out.println(quizQuestions);

        return "redirect:/result";
    }



    @GetMapping("/score")
    public String score(Model m) {
        List<Result> resultList = quizService.getTopResult(10);
        m.addAttribute("resultList", resultList);
        return "score";
    }

}
