package com.example.test.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface MinioService {


    String MinioUpload(MultipartFile file);
    String deletePic(String name);
    void visitPic(HttpServletResponse response) throws Exception;


}
