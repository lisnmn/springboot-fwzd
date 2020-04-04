package com.sanyouseki.fwzd.dao;

import com.sanyouseki.fwzd.entity.Image;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ImageMapper {
    @Insert("insert into image(name, url, thumb_url, book_id) values(#{name}, #{url}, #{thumb_url}, #{book_id})")
    int add(@Param("name") String name, @Param("url") String url, @Param("thumb_url") String thumb_url, @Param("book_id") int book_id);

    @Update("update image set name = #{name}, url = #{url}, thumb_url = #{thumb_url}, #{book_id} where id = #{id}")
    int update(@Param("name") String name, @Param("url") String url, @Param("thumb_url") String thumb_url, @Param("book_id") int book_id, @Param("id") int id);

    @Update("update image set del = #{del} where id = #{id}")
    int safeDelete(@Param("del") int del, @Param("id") int id);

    @Delete("delete from image where id = #{id}")
    int delete(int id);

    @Select("select * from image where id = #{id} and del = 0")
    Image findImage(@Param("id") int id);

    @Select("select * from image where url = #{url} and and del = 0 ORDER BY upload_time DESC")
    List<Image> findImageByUrl(@Param("url") String url);

    @Select("select * from image where book_id = #{book_id} ORDER BY id ASC")
    List<Image> findImageByBookId(@Param("book_id") int bookId);

    @Select("select * from image where del = 0 ORDER BY upload_time DESC")
    List<Image> findImageList();
}
