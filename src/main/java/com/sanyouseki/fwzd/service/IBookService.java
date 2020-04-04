package com.sanyouseki.fwzd.service;

import com.sanyouseki.fwzd.entity.Book;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IBookService {
    int add(Book entity);
    void deleteById(int id);
    void update(Book entity);
    void updateCover(String cover, int id);
    Book getById(int id);
    List<Book> getByUploader(String uploader);
    List<Book> getAll();
    String getCoverById(int bookId);
}
