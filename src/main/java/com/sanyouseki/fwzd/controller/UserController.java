package com.sanyouseki.fwzd.controller;

import com.sanyouseki.fwzd.entity.User;
import com.sanyouseki.fwzd.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/getByName")
    public User getByName(@RequestParam("username") String username) {
        return userService.getByName(username);
    }

    @PostMapping("/update")
    public String modify(@RequestBody User user) {
        User currentUser = userService.getById(user.getId());
        for (User other : userService.getAll()) {
            if (user.getName().equals(other.getName()) && !user.getId().equals(other.getId())) {
                return "failed";
            }
        }
        currentUser.setName(user.getName());
        currentUser.setPassword(user.getPassword());
        userService.update(currentUser);
        return "success";
    }
}
