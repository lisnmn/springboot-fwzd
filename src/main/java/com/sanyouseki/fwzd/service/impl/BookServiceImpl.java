package com.sanyouseki.fwzd.service.impl;

import com.sanyouseki.fwzd.dao.BookMapper;
import com.sanyouseki.fwzd.dao.ImageMapper;
import com.sanyouseki.fwzd.entity.Book;
import com.sanyouseki.fwzd.service.IBookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookServiceImpl implements IBookService {
    @Resource
    private BookMapper bookMapper;

    @Resource
    private ImageMapper imageMapper;

    @Override
    public int add(Book entity) {
        //bookMapper.add(entity.getName(), entity.getUploader(), entity.getRank());
        bookMapper.add(entity);
        return entity.getId();
    }

    @Override
    public void deleteById(int id) {
        bookMapper.safeDelete(1, id);
    }

    @Override
    public void update(Book entity) {
        bookMapper.update(entity.getName(), entity.getSize(), entity.getCover(), entity.getUploader(), entity.getRank(), entity.getId());
    }

    @Override
    public void updateCover(String cover, int id) {
        bookMapper.updateCover(cover, id);
    }

    @Override
    public Book getById(int id) {
        return bookMapper.findBook(id);
    }

    @Override
    public List<Book> getByUploader(String uploader) {
        return bookMapper.findBookByUploader(uploader);
    }

    @Override
    public List<Book> getAll() {
        return bookMapper.findBookList();
    }

    @Override
    public String getCoverById(int bookId) {
        return imageMapper.findImageByBookId(bookId).get(0).getThumbUrl();
    }

}
