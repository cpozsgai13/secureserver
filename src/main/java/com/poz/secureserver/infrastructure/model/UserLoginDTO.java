package com.poz.secureserver.infrastructure.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity(name = "user_login")
public class UserLoginDTO {

    @Getter
    @Column(nullable = false)
    private String name;

    @Getter
    @Column(nullable = false)
    private String password;

    @Id
    @Getter
    @Column(nullable = false)
    private Long userId;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String user, String pass, Long userId) {
        this.name = user;
        this.password = pass;
        this.userId = userId;
    }
}
