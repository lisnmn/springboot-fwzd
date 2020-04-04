package com.sanyouseki.fwzd.controller;

import com.sanyouseki.fwzd.controller.data.SignupData;
import com.sanyouseki.fwzd.entity.User;
import com.sanyouseki.fwzd.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignupController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/")
    public String signup(@RequestBody SignupData data) {
        String answer = data.getAnswer();
        if (!answer.equals("ミスティア・ローレライ") && !answer.equals("Mystia Lorelei") && !answer.equals("米斯蒂娅·萝蕾拉") && !answer.equals("小碎骨") && !answer.equals("老板娘")) {
            return "answerFailed";
        } else {
            if (userService.getByName(data.getName()) != null) {
                return "failed";
            } else {
                User user = new User();
                user.setName(data.getName());
                user.setPassword(data.getPassword());
                userService.add(user);
                return "success";
            }
        }
    }
}
