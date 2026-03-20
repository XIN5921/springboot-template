package org.example.week03.config;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppConfigTest {

    @Resource
    private AppConfig appConfig;

    @Test
    public void getAppConfig() {
        assertNotNull(appConfig, "AppConfig 不应该为 null");
        System.out.println("AppConfig 的信息：" + appConfig);
        
        // 验证配置是否正确加载
        assertNotNull(appConfig.getAppName(), "appName 不应该为 null");
        assertNotNull(appConfig.getVersion(), "version 不应该为 null");
        assertNotNull(appConfig.getDescription(), "description 不应该为 null");
        
        System.out.println("appName: " + appConfig.getAppName());
        System.out.println("version: " + appConfig.getVersion());
        System.out.println("description: " + appConfig.getDescription());
        System.out.println("published: " + appConfig.getPublished());
    }
}