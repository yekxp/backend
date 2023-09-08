package com.developers.hireasenior.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    private final BCryptPasswordEncoder encoder;

    public PasswordEncoder() {
        encoder = new BCryptPasswordEncoder();
    }

    public String encrypt(String password) {
        return encoder.encode(password);
    }

    public Boolean matches(String password, String encodedPassword) {
        return encoder.matches(password, encodedPassword);
    }
}
