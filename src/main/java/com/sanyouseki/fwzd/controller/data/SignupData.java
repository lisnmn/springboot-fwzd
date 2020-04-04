package com.sanyouseki.fwzd.controller.data;

import lombok.Data;

@Data
public class SignupData {
    private String name;
    private String password;
    private String passwordConfirm;
    private String answer;
}
