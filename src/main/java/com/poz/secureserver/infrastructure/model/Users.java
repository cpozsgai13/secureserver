package com.poz.secureserver.infrastructure.model;

import java.time.Instant;

import com.poz.secureserver.infrastructure.validation.ValidEmail;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "users")
public class Users {
    public Users() {
    }
    public Users(Long id, String name, String first, String last, String email, boolean active,
        boolean deleted, boolean use_2fa, Instant creation_time) {
        this.id = id;
        this.name = name;
        this.first = first;
        this.last = last;
        this.email = email;
        this.active = active;
        this.deleted = deleted;
        this.use_2fa = use_2fa;
        this.creation_time = creation_time;
    }

    public Users(UserDTO dto) {
        this.name = dto.getName();
        this.first = dto.getFirst();
        this.last = dto.getLast();
        this.email = dto.getEmail();
        this.creation_time = dto.getCreation_time();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private String first;

    @Getter
    private String last;

    @Getter
    @Setter
    @ValidEmail
    private String email;

    @Getter
    @Setter
    private boolean active;

    @Getter
    @Setter
    private boolean deleted;

    @Getter
    @Setter
    private boolean use_2fa;

    @Getter
    private Instant creation_time;

    Users(Builder builder) {
        this.name = builder.name;
    }
            
    public static class Builder {
        public String name;
        private String first;
        private String last;
        private String email;
        private boolean active;
        private boolean deleted;
        private boolean use_2fa;
        private Instant creation_time;
    
        public Builder name(String name) {
            this.name = name;
            return this;
        }        

        public Builder first(String first) {
            this.first = first;
            return this;
        }        

        public Builder last(String last) {
            this.last = last;
            return this;
        }        

        public Builder email(String email) {
            this.email = email;
            return this;
        }        

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }        

        public Builder deleted(boolean deleted) {
            this.deleted = deleted;
            return this;
        }        

        public Builder use_2fa(boolean use_2fa) {
            this.use_2fa = use_2fa;
            return this;
        }        

        public Builder creationTime() {
            this.creation_time = Instant.now();
            return this;
        }        

        public Users build() {
            return new Users(this);
        }
    }

};

