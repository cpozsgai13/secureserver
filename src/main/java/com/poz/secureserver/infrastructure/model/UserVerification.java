package com.poz.secureserver.infrastructure.model;

import lombok.Getter;

import jakarta.persistence.*;

@Entity
public class UserVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Column(nullable = false)
    private String email;

    @Getter
    @Column(nullable = false)
    private String verificationCode;

    public UserVerification() {

    }

    public UserVerification(Long id, String email, String code) {
        this.id = id;
        this.email = email;
        this.verificationCode = code;
    }
}
