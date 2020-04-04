package com.sanyouseki.fwzd.controller;

import com.sanyouseki.fwzd.entity.Image;
import com.sanyouseki.fwzd.service.impl.ImageServiceImpl;
import com.sanyouseki.fwzd.util.MyFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageServiceImpl imageService;

    @GetMapping("/")
    public List<Image> list() {
        return imageService.getAll();
    }

}
