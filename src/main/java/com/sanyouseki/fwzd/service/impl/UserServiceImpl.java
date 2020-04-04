package com.sanyouseki.fwzd.service.impl;

import com.sanyouseki.fwzd.entity.User;
import com.sanyouseki.fwzd.dao.UserMapper;
import com.sanyouseki.fwzd.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public void add(User entity) {
        userMapper.add(entity.getName(), entity.getPassword());
    }

    @Override
    public void deleteById(int id) {
        userMapper.safeDelete(1, id);
    }

    @Override
    public void deleteByName(String name) {
        userMapper.safeDeleteByName(1, name);
    }

    @Override
    public void update(User entity) {
        userMapper.update(entity.getName(), entity.getPassword(), entity.getId());
    }

    @Override
    public User getById(int id) {
        return userMapper.findUser(id);
    }

    @Override
    public User getByName(String name) {
        return userMapper.findUserByName(name);
    }

    @Override
    public List<User> getAll() {
        return userMapper.findUserList();
    }
}
