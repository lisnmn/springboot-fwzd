package com.sanyouseki.fwzd.entity;

import lombok.Data;

@Data
public class Book {
    private int id;
    private String name;
    private int size;
    private String cover;
    private String uploader;
    private String uploadTime;
    private String rank;
    private int del;
}
