package org.example.week03.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云短信配置属性类
 * @author mqxu
 * @date 2026/3/19
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.sms")
public class ALiyunSmsProperties {
    /**
     * 是否启用短信服务
     */
    private boolean enabled = true;
    
    /**
     * 服务端点地址，短信认证服务为 dypnsapi.aliyuncs.com
     */
    private String endpoint = "dypnsapi.aliyuncs.com";
    
    /**
     * 地域 ID
     */
    private String regionId = "cn-hangzhou";
    
    /**
     * AccessKey ID（敏感信息，建议使用环境变量）
     */
    private String accessKeyId;
    
    /**
     * AccessKey Secret（敏感信息，建议使用环境变量）
     */
    private String accessKeySecret;
    
    /**
     * 赠送签名名称
     */
    private String signName;
    
    /**
     * 赠送模板 CODE
     */
    private String templateCode;
    
    /**
     * 方案名称，不填为默认方案
     */
    private String schemeName = "默认方案";
}
