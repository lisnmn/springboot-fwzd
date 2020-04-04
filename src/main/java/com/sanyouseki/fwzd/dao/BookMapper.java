package com.sanyouseki.fwzd.dao;

import com.sanyouseki.fwzd.entity.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Insert("insert into book(name, size, uploader, rank) values(#{name}, #{size}, #{uploader}, #{rank})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int add(Book book);

    /*
    @Insert("insert into book(name, uploader, rank) values(#{name}, #{uploader}, #{rank})")
    int add(@Param("name") String name, @Param("uploader") String uploader, @Param("rank") String rank);
    */

    @Update("update book set name = #{name}, size = #{size}, cover = #{cover}, uploader = #{uploader}, rank = #{rank} where id = #{id}")
    int update(@Param("name") String name, @Param("size") int size, @Param("cover") String cover, @Param("uploader") String uploader, @Param("rank") String rank, @Param("id") int id);

    @Update("update book set cover = #{cover} where id = #{id}")
    int updateCover(@Param("cover") String cover, @Param("id") int id);

    @Update("update book set del = #{del} where id = #{id}")
    int safeDelete(@Param("del") int del, @Param("id") int id);

    @Delete("delete from book where id = #{id}")
    int delete(int id);

    @Select("select * from book where id = #{id} and del = 0")
    Book findBook(@Param("id") int id);

    @Select("select * from book where uploader = #{uploader} and del = 0 ORDER BY upload_time DESC")
    List<Book> findBookByUploader(@Param("uploader") String uploader);

    @Select("select * from book where del = 0 ORDER BY upload_time DESC")
    List<Book> findBookList();

}
