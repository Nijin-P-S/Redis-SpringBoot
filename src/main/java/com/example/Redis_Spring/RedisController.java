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
    private static final String PERSON_LIST_KEY = "person_list::";

    //---------------------------------------Value Operations ---------------------------------------------

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

    //----------------------------------------List Operations -----------------------------------------------

    @PostMapping("/lpush")
    public void lpsuh(@Valid @RequestBody Person person){
        redisTemplate.opsForList().leftPush(PERSON_LIST_KEY, person);
    }

    @PostMapping("/rpush")
    public void rpsuh(@Valid @RequestBody Person person){
        redisTemplate.opsForList().rightPush(PERSON_LIST_KEY, person);
    }

    @DeleteMapping("/lpop")
    public void lpop(@RequestParam(value = "count", required = false, defaultValue = "1") int count){
        redisTemplate.opsForList().leftPop(PERSON_LIST_KEY, count);
    }

    @PostMapping("/rpop")
    public void rpop(@RequestParam(value = "count", required = false, defaultValue = "1") int count){
        redisTemplate.opsForList().rightPop(PERSON_LIST_KEY, count);
    }

    @GetMapping("/lrange")
    public List<Person> lrange(@RequestParam(value = "start", required = false,defaultValue = "0") int start,
                               @RequestParam(value = "end" ,required = false, defaultValue = "-1") int end){
        return redisTemplate.opsForList().range(PERSON_LIST_KEY, start, end).stream()
                .map(obj -> (Person)obj)
                .collect(Collectors.toList());
    }
}
