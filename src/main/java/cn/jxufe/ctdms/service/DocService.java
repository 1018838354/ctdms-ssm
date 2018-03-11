package cn.jxufe.ctdms.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DocService {
    //上传教学计划表
    void cp(MultipartFile file) throws IOException;

}
