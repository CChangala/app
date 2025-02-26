package com.codeCardS.app.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.codeCardS.app.Records.Topics;
import com.codeCardS.app.Records.User;
import com.codeCardS.app.Service.LoginOrSignUpService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class LoginOrSignUp {

    private final LoginOrSignUpService loginOrSignUpService;

    public LoginOrSignUp(LoginOrSignUpService loginOrSignUpService) {
        this.loginOrSignUpService = loginOrSignUpService;
    }


    @GetMapping("/login")
    public String Login(@RequestBody userDetails userDetails) {
        return loginOrSignUpService.Login(userDetails.email, userDetails.password);
        
    }

    public record userDetails(String email, String password) {
    }
    
    @PostMapping("/signup")
    public User createNewUser(@RequestBody newUserDetails newUserDetails) {
        //TODO: process POST request
        
        return loginOrSignUpService.SignUp(newUserDetails.email, newUserDetails.password,newUserDetails.confrimPassword, null);
    }

    public record newUserDetails(String email, String password, String confrimPassword) {
    }
    
}
