package maiordev.expense_tracker_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @RequestMapping("/register")
    public String register(){
        return "User registered";
    }

    @RequestMapping("/login")
    public String login(){
        return "User logged in";
    }
}
