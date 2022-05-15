package com.example.Redis_Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RedisController {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private static final String KEY_PREFIX = "person::";

    @PostMapping("/setValue")
    public void setValue(@Valid @RequestBody Person person){
        String key = KEY_PREFIX+person.getId();
        redisTemplate.opsForValue().set(key, person);
    }

    @GetMapping("/getValue")
    public Person getValue(@RequestParam("id") int id){
        String key = KEY_PREFIX+id;
        return (Person)redisTemplate.opsForValue().get(key);
    }

    @GetMapping("/getAllValues")  //Getting all the person
    public List<Person> getAllPerson(){
        String pattern = KEY_PREFIX+"*";
        return redisTemplate.keys(pattern).stream()
                .map(key -> redisTemplate.opsForValue().get(key))
                .map(person -> (Person) person)
                .collect(Collectors.toList());
    }

}
