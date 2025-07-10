package com.poz.secureserver.infrastructure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poz.secureserver.infrastructure.model.UserDTO;
import com.poz.secureserver.infrastructure.model.Users;
import com.poz.secureserver.infrastructure.service.EmailService;
import com.poz.secureserver.infrastructure.service.UserServiceImpl;

@RestController
// @CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {
    static Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    @Autowired
    UserServiceImpl userService;
    @Autowired
    EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<Users> registerNewUser(@RequestBody UserDTO userDTO) {
        logger.info("Registering user {}", userDTO.getName());
        if (!userService.userExists(userDTO.getName())) {

            // First get the email address and send an email.
            String email = userDTO.getEmail();
            emailService.sendVerificationEmail(email, "904266");
            Users user = new Users(userDTO);
            return ResponseEntity.status(HttpStatus.OK).body(userService.saveUser(user));
        }
        // Return an exception.
        logger.info("Error registering {}, already exists", userDTO.getName());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

}
