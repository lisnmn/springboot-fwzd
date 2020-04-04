package com.sanyouseki.fwzd.service;

import com.sanyouseki.fwzd.entity.User;

import java.util.List;

public interface IUserService {
    void add(User entity);
    void deleteById(int id);
    void deleteByName(String name);
    void update(User entity);
    User getById(int id);
    User getByName(String name);
    List<User> getAll();
}
