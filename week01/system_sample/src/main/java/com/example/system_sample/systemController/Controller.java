package com.example.system_sample.systemController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {


    @GetMapping("/system/info")
    public Object getSimpleSystemInfo() {
        return new Object() {
            // 关键：加上public修饰符，让JSON工具能读取
            public String osName = System.getProperty("os.name");
            public String osArch = System.getProperty("os.arch");
            public String javaVersion = System.getProperty("java.version");
            public int cpuCores = Runtime.getRuntime().availableProcessors();
            public long totalMemoryMB = Runtime.getRuntime().totalMemory() / 1024 / 1024;
            public long freeMemoryMB = Runtime.getRuntime().freeMemory() / 1024 / 1024;
        };
    }
}