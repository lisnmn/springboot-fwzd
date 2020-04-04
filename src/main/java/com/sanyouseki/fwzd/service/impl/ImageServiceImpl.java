package com.sanyouseki.fwzd.service.impl;

import com.sanyouseki.fwzd.dao.ImageMapper;
import com.sanyouseki.fwzd.entity.Image;
import com.sanyouseki.fwzd.service.IImageService;
import com.sanyouseki.fwzd.util.MyFileUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ImageServiceImpl implements IImageService {
    @Resource
    private ImageMapper imageMapper;

    @Override
    public void add(Image entity) {
        imageMapper.add(entity.getName(), entity.getUrl(), entity.getThumbUrl(), entity.getBookId());
    }

    @Override
    public void deleteById(int id) {
        imageMapper.safeDelete(1, id);
    }

    @Override
    public void update(Image entity) {
        imageMapper.update(entity.getName(), entity.getUrl(), entity.getThumbUrl(), entity.getBookId(), entity.getId());
    }

    @Override
    public Image getById(int id) {
        return imageMapper.findImage(id);
    }

    @Override
    public List<Image> getByUrl(String url) {
        return imageMapper.findImageByUrl(url);
    }

    @Override
    public List<Image> getByBookId(int bookId) {
        return imageMapper.findImageByBookId(bookId);
    }

    @Override
    public List<Image> getAll() {
        return imageMapper.findImageList();
    }

}
