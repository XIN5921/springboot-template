package org.example.week08.sms.dto;

/**
 * @author ZhangXin       // 作者：系统当前用户名
 * @date 2026/4/24 16:21 // 创建日期+时间
 * @description 类描述信息
 */
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ValidateCodeRequest(
        @NotBlank
        @Pattern(regexp = "^1\\d{10}$")
        String phone,
        @NotBlank
        @Size(min = 4, max = 8)
        String code
) {
}
