package com.capstone.quizApp.controller;

import com.capstone.quizApp.dto.UserDto;
import com.capstone.quizApp.model.QuizQuestions;
import com.capstone.quizApp.model.Result;
import com.capstone.quizApp.model.User;
import com.capstone.quizApp.myServices.QuizService;
import com.capstone.quizApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class QuizController {

    @Autowired
    Result result;
    @Autowired
    QuizService quizService;

    private UserService userService;

   /* public QuizController(UserService userService) {
        this.userService = userService;
    }*/

    Boolean submitted = false;

    @ModelAttribute("result")
    public Result getResult() {
        return result;
    }
    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

   /* @PostMapping("/login-process")
    public String loginProcess(@RequestParam("email") String email,
                                @RequestParam("password") String password,
                                RedirectAttributes redirectAttributes){
        boolean isAuthenticated = authenticate(email, password);
        if(isAuthenticated){
            return "redirect:/index";
        }else {
            redirectAttributes.addAttribute("error", true);
            return "redirect:/login";
        }
    }*/


    // handler method to handle user registration form request
    @GetMapping("register")
    public String showRegistration(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/registration-process")
    public String addUser(@ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
        User existingUser = userService.findByEmail(userDto.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "There is already an account registered with the same email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "register";
        }
        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    // handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
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
    }



}
