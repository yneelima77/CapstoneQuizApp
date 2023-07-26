package com.Neelimay.quizMaster.service;

import com.Neelimay.quizMaster.dto.UserDto;
import com.Neelimay.quizMaster.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
