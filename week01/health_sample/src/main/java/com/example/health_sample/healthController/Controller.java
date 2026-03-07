package com.example.health_sample.healthController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@RestController
public class Controller {
    @GetMapping("/health")
    public Map<String, Object> getHealthStatus() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("appName", "my-springboot-app");
        health.put("timestamp", System.currentTimeMillis());
        long usedMemory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024;
        health.put("jvmUsedMemoryMB", usedMemory);
        return health;
    }
}
