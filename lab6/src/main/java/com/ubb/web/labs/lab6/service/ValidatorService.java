package com.ubb.web.labs.lab6.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ubb.web.labs.lab6.domain.UserEntity;
import com.ubb.web.labs.lab6.repository.UserRepository;

public class ValidatorService {

    @Autowired
    UserRepository userRepository;

    public UserEntity validateData(String userName, String password) {
        return userRepository.findByUserNameAndPassword(userName, password);
    }
}
