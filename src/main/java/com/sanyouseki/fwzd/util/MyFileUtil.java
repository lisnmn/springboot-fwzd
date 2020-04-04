package com.sanyouseki.fwzd.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyFileUtil {

    private final static int WIDTH = 400;

    private final static int HEIGHT = 400;

    private File classPath;

    private File filePath;

    private String fileUrl;

    private String suffix;

    private String thumb;

    private String thumbUrl;

    private String name;

    public MyFileUtil(String fileUrl) throws FileNotFoundException {
        this.classPath = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!classPath.exists()) classPath = new File("");
        this.filePath = new File(classPath.getAbsolutePath() + "/static/book/upload/", fileUrl);
        if (!filePath.exists()) filePath.mkdirs();
        this.fileUrl = fileUrl;
        String prePath = filePath.getAbsolutePath();
        this.suffix = prePath.substring(prePath.lastIndexOf('.'));
        this.thumb = prePath.substring(0, prePath.lastIndexOf('.')) + "_" + WIDTH + "x" + HEIGHT + suffix;
        this.thumbUrl = fileUrl.substring(0, fileUrl.lastIndexOf('.')) + "_" + WIDTH + "x" + HEIGHT + suffix;
        this.name = fileUrl.substring(fileUrl.lastIndexOf('/') + 1, fileUrl.lastIndexOf('.'));
    }

    public void createThumb() throws IOException {
        Thumbnails.of(filePath).size(WIDTH, HEIGHT).toFile(thumb);
    }

    public void deleteThumb() {
        File file = new File(thumb);
        file.delete();
    }

    public void delete() {
        filePath.delete();
        this.deleteThumb();
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public File getClassPath() {
        return classPath;
    }

    public void setClassPath(File classPath) {
        this.classPath = classPath;
    }

    public File getFilePath() {
        return filePath;
    }

    public void setFilePath(File file) {
        this.filePath = file;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
