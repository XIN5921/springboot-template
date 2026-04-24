package org.example.week08.sms.dto;

/**
 * @author ZhangXin       // 作者：系统当前用户名
 * @date 2026/4/24 16:20 // 创建日期+时间
 * @description 类描述信息
 */
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SendCodeRequest(
        @NotBlank
        @Pattern(regexp = "^1\\d{10}$", message = "请输入 11 位国内手机号")
        String phone
) {
}