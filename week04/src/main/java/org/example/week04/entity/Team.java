package org.example.week04.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

/**
 * @author ZhangXin       // 作者：系统当前用户名
 * @date 2026/3/27 16:21 // 创建日期 + 时间
 * @description 团队配置信息类
 */
@Data
@Component
public class Team {
    
    @Value("${team.leader}")
    @NotBlank(message = "负责人姓名不能为空")
    @Length(min = 2, max = 10, message = "负责人姓名长度 2-10 位")
    private String leader;
    
    @Value("${team.age}")
    @Max(value = 60, message = "年龄不能大于 60")
    @Min(value = 30, message = "年龄不能小于 30")
    private Integer age;
    
    @Value("${team.email}")
    @Email(message = "邮箱格式不正确")
    private String email;
    
    @Value("${team.phone}")
    @Pattern(regexp = "^[0-9]{11}$", message = "手机号格式不正确")
    private String phone;
    
    @Value("${team.createTime}")
    @Past(message = "创建时间必须早于当前时间")
    private LocalDate createTime;
}
