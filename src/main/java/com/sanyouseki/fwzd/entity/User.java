package com.sanyouseki.fwzd.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String password;
    private Integer del;
}
