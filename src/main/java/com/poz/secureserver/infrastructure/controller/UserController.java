package com.poz.secureserver.infrastructure.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poz.secureserver.infrastructure.model.UserDTO;
import com.poz.secureserver.infrastructure.model.UserLoginDTO;
import com.poz.secureserver.infrastructure.model.Users;
import com.poz.secureserver.infrastructure.service.UserServiceImpl;

@RestController
// @CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/")
    public String home() {
        return "Home";
    }

    @GetMapping("/echo")
    public String echo() {
        logger.info("Servicing /echo GET");
        return "Echo";
    }

    @GetMapping("/users")
    public List<String> getUsers() {
        if (this.userService == null) {
            return null;
        }
        logger.info("Get user list called");
        return this.userService.getUserList();
    }

    @PostMapping("/user")
    public Users createUser(@RequestBody UserDTO userDTO) {
        Users users = new Users(userDTO);
        return userService.saveUser(users);
    }

    @PostMapping("/login")
    public Boolean loginUser(@RequestBody UserLoginDTO user) {
        logger.info("Got login request for {}", user);
        return userService.loginUser(user.getName(), user.getPassword());
    }
}
