package com.emse.spring.faircorp.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DummyUserService implements UserService{

    @Autowired
    GreetingService greetingService;

    @Override
    public void greetAll() {
        Arrays.asList("Elodie","Charles").forEach(name -> greetingService.greet(name));
    }
}