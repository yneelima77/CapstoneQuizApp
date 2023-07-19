package com.springboot.studentLogin.security;

import com.springboot.studentLogin.model.Student;
import com.springboot.studentLogin.repository.StudentRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomStudentDetailsService implements UserDetailsService {

    private StudentRepository studentRepository;

    //the dependencies are injected as arguments to constructor

    public CustomStudentDetailsService(StudentRepository studentRepository) {
        super();
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Student student = studentRepository.findByEmail(usernameOrEmail);
        if (student != null) {
            return new org.springframework.security.core.userdetails.User(student.
                    getEmail(), student.getPassword(), student.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
