package com.example.test.service.impl;

import com.example.test.service.MinioService;
import com.example.test.utils.AjaxResult;
import com.example.test.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.UUID;

@Service
public class MinioServiceImpl implements MinioService {

    @Autowired
    private MinioUtil minioUtil;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.picName}")
    private String picName;

    /*上传
    file 文件名
    * */
    @Override
    public AjaxResult MinioUpload(MultipartFile file) {
        if (file.isEmpty() || file.getSize() == 0) {
//            return "文件为空";
            return AjaxResult.fail("文件为空");
        }
        try {
            String fileName = file.getOriginalFilename();
            String newName = "project-file/" + UUID.randomUUID().toString().replaceAll("-", "")
                    + fileName.substring(fileName.lastIndexOf("."));

            InputStream inputStream = file.getInputStream();
            minioUtil.putObject(bucketName, newName, inputStream, file.getContentType());

            inputStream.close();

            String url = minioUtil.getObjectUrl(bucketName, newName);
            return AjaxResult.success(url);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.fail("上传失败");
        }
    }

    /*删除
    name 文件名
    * */
    @Override
    public AjaxResult deletePic(String name) {
        try {
            minioUtil.removeObject(bucketName,name);
        } catch (Exception e) {
            return AjaxResult.fail("删除失败"+e.getMessage());
        }
        return AjaxResult.success("删除成功");
    }

    /*查看图片
    * */
    @Override
    public AjaxResult visitPic(HttpServletResponse response) throws Exception {
        String url=minioUtil.getObjectUrl(bucketName, picName);
        response.sendRedirect(url);
        return AjaxResult.success();
    }
}
