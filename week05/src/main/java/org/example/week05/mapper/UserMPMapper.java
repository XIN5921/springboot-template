package org.example.week05.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.week05.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ZhangXin
 * @date 2026/4/3 22:27
 * @description 类描述信息
 */
@Mapper
public interface UserMPMapper extends BaseMapper<User> {
}
