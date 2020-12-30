package com.example.test.service;

import com.example.test.utils.AjaxResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface MinioService {


    AjaxResult MinioUpload(MultipartFile file);
    AjaxResult deletePic(String name);
    AjaxResult visitPic(HttpServletResponse response) throws Exception;


}
