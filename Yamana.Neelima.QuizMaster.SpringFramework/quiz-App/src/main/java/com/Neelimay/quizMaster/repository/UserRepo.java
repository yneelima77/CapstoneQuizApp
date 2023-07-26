package com.Neelimay.quizMaster.repository;

import com.Neelimay.quizMaster.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
