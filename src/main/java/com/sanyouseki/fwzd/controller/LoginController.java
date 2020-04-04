package com.sanyouseki.fwzd.controller;

import com.sanyouseki.fwzd.entity.User;
import com.sanyouseki.fwzd.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/")
    public String login(@RequestBody User data) {
        User user = userService.getByName(data.getName());
        if (user == null) {
            return "null";
        }
        if (user.getPassword().equals(data.getPassword())) {
            return "success";
        } else {
            return "failed";
        }
    }
}
