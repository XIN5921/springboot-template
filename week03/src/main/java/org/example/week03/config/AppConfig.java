package org.example.week03.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private String appName;
    private String version;
    private String description;
    private Boolean published;
    public Author author;
    public List<String> features;

    @Data
    public static class Author {
        private String name;
        private String website;
        private String email;
    }

}
