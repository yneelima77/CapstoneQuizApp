package com.springSecurity.userRegistration.repository;

import com.springSecurity.userRegistration.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


//RoleRepository class by extending the JpaRepository interface.
// This is a Spring Data interface and gives us all the CRUD operations automatically
@Repository

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findRoleByName(String role);

    @Query(value = "select * from role where role.id= (select role_id from users_roles where user_id = :id)", nativeQuery = true)
    public List<Role> findRoleByUser(@Param("id") long id);

}
