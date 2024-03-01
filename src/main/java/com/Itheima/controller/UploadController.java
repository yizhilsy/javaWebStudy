package com.Itheima.controller;

import com.Itheima.pojo.Result;
import com.Itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
    //本地存储文件的方式
//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
//        log.info("文件上传: {}, {}, {}",username,age,image);
//        //将文件存储在服务器(本地)的磁盘目录中
//        //获取原始文件名
//        String originalFilename = image.getOriginalFilename();
//        //构造唯一的文件名 --uuid通用唯一识别码
//        int index = originalFilename.lastIndexOf(".");
//        String extname = originalFilename.substring(index);
//        String newFileName = UUID.randomUUID().toString() + extname;
//        log.info("经过uuid处理后的文件名: {}",newFileName);
//
//        //存储到本地
//        image.transferTo(new File("E:\\develop\\ServerDir\\images\\"+newFileName));
//        return Result.success();
//    }

    //oss阿里云存储文件

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException{
        log.info("文件上传，文件名: {}",image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成,文件访问的url: {}",url);
        return Result.success(url);
    }

}
