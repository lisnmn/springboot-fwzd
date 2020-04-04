package com.sanyouseki.fwzd.entity;

import lombok.Data;

@Data
public class Image {
    private int id;
    private String name;
    private String url;
    private String thumbUrl;
    private int bookId;
    private int del;
}
