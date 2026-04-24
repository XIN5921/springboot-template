package org.example.week08.sms.dto;

/**
 * @author ZhangXin       // 作者：系统当前用户名
 * @date 2026/4/24 16:21 // 创建日期+时间
 * @description 类描述信息
 */
public record SendCodeResponse(
        String phone,
        int ttlSeconds,
        String codePlain
) {
}