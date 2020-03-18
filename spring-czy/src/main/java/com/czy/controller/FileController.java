package com.czy.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileController {

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public void uploadFile(MultipartHttpServletRequest request) throws IOException {
        MultipartFile file = request.getFile("a.txt");// 与页面input的name相同
        File imageFile = new File("d:/Download/a.txt");// 上传后的文件保存目录及名字
        file.transferTo(imageFile);// 将上传文件保存到相应位置
    }
}
