package com.Sprinboot.JWT.MongoDB.SprinbootJWTMongoDB.controller;

import com.Sprinboot.JWT.MongoDB.SprinbootJWTMongoDB.model.User;
import com.Sprinboot.JWT.MongoDB.SprinbootJWTMongoDB.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService service;

    @Autowired

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody User user)
    {
        return service.signUp(user);

    }
    @PostMapping("/login")
    public String login(HttpServletRequest httpServletRequest)
    {
        String email = (String) httpServletRequest.getAttribute("email");
        String pswrd = (String) httpServletRequest.getAttribute("password");
        return service.login(email,pswrd);
    }
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers()
    {

        return service.getAllUsers();
    }



}
