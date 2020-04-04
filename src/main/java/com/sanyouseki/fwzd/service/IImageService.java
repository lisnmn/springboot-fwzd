package com.sanyouseki.fwzd.service;

import com.sanyouseki.fwzd.entity.Image;

import java.util.List;

public interface IImageService {
    void add(Image entity);
    void deleteById(int id);
    void update(Image entity);
    Image getById(int id);
    List<Image> getByUrl(String url);
    List<Image> getByBookId(int bookId);
    List<Image> getAll();
}
