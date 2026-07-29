package com.bloc.JournalApp.Controller;

import com.bloc.JournalApp.API.Response.WeatherResponse;
import com.bloc.JournalApp.Repository.UserRepository;
import com.bloc.JournalApp.Service.JournalEntryService;
import com.bloc.JournalApp.Service.UserService;
import com.bloc.JournalApp.Service.WeatherService;
import com.bloc.JournalApp.entity.JournalEntry;
import com.bloc.JournalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherservice;

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User byUserName = userService.findByUserName(userName);
            byUserName.setUserName(user.getUserName());
            byUserName.setPassword(user.getPassword());
            userService.saveNewUser(byUserName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherservice.getWeather("Mumbai");
        String greeting=" ";
        if(weatherResponse!=null){
            greeting=", Weather Feels Like" + weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi" + authentication.getName() +  greeting ,HttpStatus.OK);

    }
}







