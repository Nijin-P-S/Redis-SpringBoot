package com.example.Redis_Spring;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    

    @PostMapping("/setValue")
    public void setValue(@RequestBody Person person){

    }
}
