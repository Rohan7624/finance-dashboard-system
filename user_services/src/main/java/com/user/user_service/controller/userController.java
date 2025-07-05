package com.user.user_service.controller;

import com.user.user_service.entity.User;
import com.user.user_service.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private userService userService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") Long userId){

        User user = this.userService.getUser(userId);
        //http://localhost:8081/contact/user/1311

        List Contact = this.restTemplate.getForObject
                ("http://localhost:8081/contact/user/"+user.getUserId(), List.class);

        user.setContacts(Contact);

        return user;
    }
}
