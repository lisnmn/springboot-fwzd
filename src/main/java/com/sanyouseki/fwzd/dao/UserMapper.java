package com.sanyouseki.fwzd.dao;

import com.sanyouseki.fwzd.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into user(name, password) values(#{name}, #{password})")
    int add(@Param("name") String name, @Param("password") String password);

    @Update("update user set name = #{name}, password = #{password} where id = #{id}")
    int update(@Param("name") String name, @Param("password") String password, @Param("id") int id);

    @Update("update user set del = #{del} where id = #{id}")
    int safeDelete(@Param("del") int del, @Param("id") int id);

    @Update("update user set del = #{del} where name = #{name}")
    int safeDeleteByName(@Param("del") int del, @Param("name") String name );

    @Delete("delete from user where id = #{id}")
    int delete(int id);

    @Select("select * from user where id = #{id} and del = 0")
    User findUser(@Param("id") int id);

    @Select("select * from user where name = #{name} and del = 0")
    User findUserByName(@Param("name") String name);

    @Select("select * from user where del = 0")
    List<User> findUserList();

}
