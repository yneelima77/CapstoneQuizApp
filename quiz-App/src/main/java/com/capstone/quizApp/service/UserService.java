package com.capstone.quizApp.service;

import com.capstone.quizApp.dto.UserDto;
import com.capstone.quizApp.model.User;
import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
