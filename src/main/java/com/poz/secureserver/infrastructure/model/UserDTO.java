package com.poz.secureserver.infrastructure.model;

import java.time.Instant;

import com.poz.secureserver.infrastructure.validation.ValidEmail;
import com.poz.secureserver.infrastructure.validation.ValidPassword;

import lombok.Getter;
import lombok.Setter;

public class UserDTO {
    public UserDTO() {
    }

    public UserDTO(Long id, String name, String first, String last, String email, String password,
            boolean use_2fa, Instant creation_time) {
        this.id = id;
        this.name = name;
        this.first = first;
        this.last = last;
        this.email = email;
        this.use_2fa = use_2fa;
        this.creation_time = creation_time;
    }

    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private String first;

    @Getter
    private String last;

    @Getter
    @ValidEmail
    private String email;

    @Getter
    @ValidPassword
    private String password;

    @Getter
    @Setter
    private boolean use_2fa;

    @Getter
    private Instant creation_time;

    UserDTO(Builder builder) {
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

        public UserDTO build() {
            return new UserDTO(this);
        }
    }

};
