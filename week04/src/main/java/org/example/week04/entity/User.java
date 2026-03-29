package org.example.week04.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ZhangXin       // 作者：系统当前用户名
 * @date 2026/3/27 15:33 // 创建日期+时间
 * @description 类描述信息
 */
@Data
public class User {
    private long id;
    private  String username;
    private LocalDateTime createTime;
}
