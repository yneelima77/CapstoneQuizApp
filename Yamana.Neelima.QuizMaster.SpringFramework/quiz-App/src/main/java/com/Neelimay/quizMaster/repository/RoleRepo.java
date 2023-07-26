package com.Neelimay.quizMaster.repository;

import com.Neelimay.quizMaster.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
