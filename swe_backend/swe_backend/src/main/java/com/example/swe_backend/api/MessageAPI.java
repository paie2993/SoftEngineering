package com.example.swe_backend.api;

import com.example.swe_backend.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageAPI {
    @GetMapping("/helloworld")
    public Message sayHelloWorld(){
        return new Message("Hello world!");
    }
}