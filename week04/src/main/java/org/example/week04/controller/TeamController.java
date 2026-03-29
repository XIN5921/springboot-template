package org.example.week04.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.week04.common.Result;
import org.example.week04.entity.Team;
import org.example.week04.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangXin       // 作者：系统当前用户名
 * @date 2026/3/27 16:25 // 创建日期 + 时间
 * @description Team 控制器类
 */
@Slf4j
@RestController
@RequestMapping("/api/team")
public class TeamController {
    
    @Autowired
    private Team team;
    
    @PostMapping("/add")
    public Result<String> addTeam(@Validated @RequestBody Team team, HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token == null || token.isBlank()) {
            throw new BusinessException(401, "请先登录");
        }
        if (!"admin".equals(token)) {
            throw new BusinessException(401, "token 无效");
        }
        
        log.info("添加团队：{}", team);
        return Result.success("添加成功");
    }
}
