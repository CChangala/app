package com.codeCardS.app.Service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.codeCardS.app.Records.User;
import com.codeCardS.app.Respository.UserRepository;

@Service
public class LoginOrSignUpService {

    @Autowired
    private UserRepository userRepository;

    public String Login(String email, String password) {
        User user = userRepository.findAll().stream().filter(u -> u.email().equals(email)).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
        if(user.password().equals(password)) {
            return "loggedIn";
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is incorrect");
        }
    }

    public User SignUp(String email, String password,String confirmPassword, String userId) {
        if(!password.equals(confirmPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password and Confirm Password do not match");
        }

        if(userRepository.findAll().stream().anyMatch(u -> u.email().equals(email))) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "User already exists");
        }

        User user = new User(email,password,UUID.randomUUID().toString(),null);
        userRepository.save(user);
        return user;
    }

    
}
