package com.ifiport.service;

/**
 * Created by Martin on 2017/08/14.
 */
import com.ifiport.model.User;
import com.ifiport.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void save(User user){
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        repo.save(user);
    }

    public User getUser(String username) {
        return repo.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }
}