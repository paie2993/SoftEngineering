package com.example.swe_backend.api;

import com.example.swe_backend.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MessageAPI {
    @GetMapping("/message")
    public Message sendMessage(){
        return new Message("Hello world!");
    }
}