package org.example.week04.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.week04.common.FileUtil;
import org.example.week04.common.Result;
import org.example.week04.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangXin
 * @date 2026/3/27 17:04
 * @description 文件上传控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/file")
public class UploadController {

    private static final String FILE_URL_PREFIX = "http://localhost:8080/upload/";

    @Autowired
    private FileUtil fileUtil;

    /**
     * 单文件上传
     * @param file 上传文件
     * @return 文件上传结果
     */
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        log.info("接收到文件上传请求，原始文件名：{}, 大小：{} bytes", file.getOriginalFilename(), file.getSize());
        
        if (file.isEmpty()) {
            log.warn("上传文件为空");
            throw new BusinessException("文件不能为空");
        }
        
        try {
            String fileName = fileUtil.upload(file);
            String url = FILE_URL_PREFIX + fileName;
            log.info("文件上传成功，访问 URL: {}", url);
            return Result.success(url);
        } catch (BusinessException e) {
            log.error("文件上传业务异常：{}", e.getMessage());
            throw e;
        } catch (IOException e) {
            log.error("文件上传 IO 异常：{}", e.getMessage(), e);
            throw new RuntimeException("文件上传失败：" + e.getMessage(), e);
        } catch (Exception e) {
            log.error("文件上传未知异常：{}", e.getMessage(), e);
            throw new RuntimeException("文件上传失败：" + e.getMessage(), e);
        }
    }

    /**
     * 批量文件上传
     * @param files 上传文件数组
     * @return 文件上传结果列表
     */
    @PostMapping("/upload/batch")
    public Result<List<String>> uploadBatch(@RequestParam("files") MultipartFile[] files) {
        log.info("接收到批量上传请求，文件数量：{}", files != null ? files.length : 0);
        
        if (files == null || files.length == 0) {
            log.warn("上传文件数组为空");
            throw new BusinessException("文件不能为空");
        }

        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                log.warn("跳过空文件");
                continue;
            }
            try {
                String fileName = fileUtil.upload(file);
                String url = FILE_URL_PREFIX + fileName;
                urls.add(url);
                log.info("文件上传成功：{}", url);
            } catch (IOException e) {
                log.error("文件上传失败：{}, 文件名：{}", e.getMessage(), file.getOriginalFilename(), e);
                throw new RuntimeException("文件上传失败：" + file.getOriginalFilename() + ": " + e.getMessage(), e);
            }
        }

        log.info("批量上传完成，成功 {} 个文件", urls.size());
        return Result.success(urls);
    }
}
