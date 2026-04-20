package maiordev.expense_tracker_api.controller;

import maiordev.expense_tracker_api.model.AuthRequest;
import maiordev.expense_tracker_api.service.UserService;
import maiordev.expense_tracker_api.util.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<GenericResponse> register(@RequestBody AuthRequest authRequest){
            GenericResponse response =  userService.registerUser(authRequest);
            return response.getCode().equals(0) ? ResponseEntity.ok(response) :ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse> login(@RequestBody AuthRequest authRequest) {
        GenericResponse response = userService.loginUser(authRequest);
        return response.getCode().equals(0) ? ResponseEntity.ok(response) :ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}