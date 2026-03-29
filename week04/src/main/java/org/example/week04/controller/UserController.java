package org.example.week04.controller;

import org.example.week04.common.Result;
import org.example.week04.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author ZhangXin       // 作者：系统当前用户名
 * @date 2026/3/27 15:45 // 创建日期 + 时间
 * @description 类描述信息
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/info")
    public Result<User> getUserInfo() {
        User user = new User();
        user.setId(123456789L);
        user.setUsername("Springmvc-student");
        user.setCreateTime(LocalDateTime.now());
        return Result.success(user);
    }
}
