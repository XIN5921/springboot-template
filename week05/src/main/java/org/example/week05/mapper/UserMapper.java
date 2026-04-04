package org.example.week05.mapper;

import org.apache.ibatis.annotations.*;
import org.example.week05.entity.User;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.week05.entity.User;

/**
 * @author zhangxin
 * @description 针对表【t_user(用户表)】的数据库操作 Mapper
 * @createDate 2026-04-03 16:40:24
 */
@Mapper
public interface UserMapper {

    /**
     * 插入数据
     *
     * @param record 插入的数据记录
     * @return 受影响的记录行数
     */
    @Insert("INSERT INTO t_user(username,password,age,email) VALUES (#{username}, #{password}, #{age}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User record);

    /**
     * 根据 id 查询数据
     *
     * @param id 主键
     * @return 数据记录
     */
    @Select("SELECT * FROM t_user WHERE id = #{id}")
    User selectByPrimaryKey(Long id);

    /**
     * 查询所有数据
     *
     * @return 数据列表
     */
    @Select("SELECT * FROM t_user")
    List<User> selectList();

    /**
     * 根据条件查询数据
     * @param query 查询条件
     * @return 数据列表
     */


    /**
     * 根据 id 更新数据
     *
     * @param record 更新的数据记录
     * @return 受影响的记录行数
     */
    @Update("UPDATE t_user SET username=#{username}, age=#{age}, email=#{email} WHERE id=#{id}")
    int updateByPrimaryKey(User record);

    /**
     * 根据 id 删除数据
     *
     * @param id 主键
     * @return 受影响的记录行数
     */
    @Delete("DELETE FROM t_user WHERE id = #{id}")
    int deleteByPrimaryKey(Long id);

    @Mapper
    public interface UserMPMapper extends BaseMapper<User> {


    }
}
