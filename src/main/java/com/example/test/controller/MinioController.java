package com.example.test.controller;

import com.example.test.service.MinioService;
import com.example.test.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/minio")
public class MinioController {

    @Autowired
    public MinioService minioService;

    @PostMapping("/upload")
    public AjaxResult uploadPic(MultipartFile file){
        return minioService.MinioUpload(file);
    }
}
