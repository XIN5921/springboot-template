package org.example.week04.common;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.example.week04.exception.BusinessException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.UUID;

/**
 * @author ZhangXin
 * @date 2026/3/27 17:09
 * @description 文件上传工具类
 */
@Slf4j
@Component
public class FileUtil {

    private String uploadDir;

    private static final Set<String> ALLOWED_EXTENSIONS = Set.of(
            "jpg", "jpeg", "png", "gif", "bmp", "webp",
            "pdf", "doc", "docx", "ppt", "pptx", "xls", "xlsx",
            "txt", "md", "csv",
            "zip", "rar", "7z",
            "json", "xml"
    );

    @PostConstruct
    public void init() {
        try {
            String baseDir = System.getProperty("user.dir");
            Path uploadPath = Paths.get(baseDir, "static", "upload");
            Files.createDirectories(uploadPath);
            uploadDir = uploadPath.toAbsolutePath().toString() + File.separator;
            log.info("上传目录初始化成功：{}", uploadDir);
        } catch (IOException e) {
            log.error("创建上传目录失败：{}", e.getMessage(), e);
            throw new RuntimeException("创建上传目录失败：" + e.getMessage(), e);
        }
    }

    /**
     * 文件上传
     * @param file 上传文件
     * @return 文件名
     * @throws IOException IO 异常
     */
    public String upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new BusinessException("文件名不能为空");
        }

        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();

        if (!ALLOWED_EXTENSIONS.contains(suffix)) {
            throw new BusinessException("不支持的文件类型：" + suffix);
        }

        String filename = UUID.randomUUID() + "." + suffix;
        File dest = new File(uploadDir + filename);
        
        file.transferTo(dest);
        
        log.info("文件上传成功：{}", dest.getAbsolutePath());
        return filename;
    }
}
