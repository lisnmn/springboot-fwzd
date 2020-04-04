package com.sanyouseki.fwzd.controller;

import com.sanyouseki.fwzd.entity.Book;
import com.sanyouseki.fwzd.entity.Image;
import com.sanyouseki.fwzd.service.impl.BookServiceImpl;
import com.sanyouseki.fwzd.service.impl.ImageServiceImpl;
import com.sanyouseki.fwzd.util.MyFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private ImageServiceImpl imageService;

    @GetMapping("/")
    public List<Book> list() {
        return bookService.getAll();
    }

    @GetMapping("/imageList")
    public List<Image> bookImages(@RequestParam("bookId") int bookId) {
        return imageService.getByBookId(bookId);
    }

    @GetMapping("/userBooks")
    public List<Book> listOfUser(@RequestParam("uploader") String uploader) {
        return bookService.getByUploader(uploader);
    }

    @PostMapping("/add")
    public Map<String, Object> upload(@RequestParam("bookName") String bookName,
                                      @RequestParam("bookUploader") String bookUploader,
                                      @RequestParam("bookRank") String bookRank,
                                      @RequestParam("file") MultipartFile[] files) {
        Map<String, Object> map = new HashMap<String, Object>();
        int index = 0;
        try {
            // 1. 创建无cover的book实体
            Book book = new Book();
            book.setName(bookName);
            book.setSize(files.length);
            // 暂时不能设置cover
            book.setUploader(bookUploader);
            book.setRank(bookRank);
            bookService.add(book);
            // Sql语句完成后ID可以被获取了
            int bookId = book.getId();

            // 2. 将所有图片上传
            for (index = 0; index < files.length; index++) {
                String originalFilename = files[index].getOriginalFilename();
                assert originalFilename != null;
                String suffix = originalFilename.substring(originalFilename.lastIndexOf('.'));
                // build String as:"bookid/bookid_pi.suffix"
                MyFileUtil myFileUtil = new MyFileUtil(bookId + "/" + bookId + "_p" + index + suffix);
                // 2.1. 保存文件到后台服务器
                files[index].transferTo(myFileUtil.getFilePath());
                // 2.2 创建缩略图
                myFileUtil.createThumb();
                // 2.3. 将图像数据写入数据库
                Image image = new Image();
                image.setName(myFileUtil.getName());
                image.setUrl(myFileUtil.getFileUrl());
                image.setThumbUrl(myFileUtil.getThumbUrl());
                image.setBookId(bookId);
                imageService.add(image);
            }

            // 3. 给book赋予cover
            bookService.updateCover(bookService.getCoverById(bookId), bookId);
            map.put("message", "success");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "failed");
            map.put("failedIndex", index);
            return map;
        }
    }

    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestBody Book book) {
        Map<String, Object> map = new HashMap<String, Object>();
        int index = 0;
        try {
            List<Image> imageList = imageService.getByBookId(book.getId());
            for (index = 0; index < imageList.size(); index++) {
                // 先删除url和thumb
                Image image = imageList.get(index);
                MyFileUtil myFileUtil = new MyFileUtil(image.getUrl());
                myFileUtil.delete();
                // 再删除数据库项
                imageService.deleteById(image.getId());
            }
            // 最后删除book
            bookService.deleteById(book.getId());
            map.put("message", "success");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "failed");
            map.put("failedIndex", index);
            return map;
        }
    }

    @PostMapping("/update")
    public String update(@RequestBody Book book) {
        try {
            bookService.update(book);
            return "success";
        }catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }

    }
}
