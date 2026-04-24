package org.example.week08.entity;

import lombok.Data;

/**
 * @author ZhangXin       // 作者：系统当前用户名
 * @date 2026/4/24 15:04 // 创建日期+时间
 * @description 类描述信息
 */
@Data
public class User {

    private String name;
    private Integer age;
    private String email;
    private Address address;
}
