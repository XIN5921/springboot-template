package org.example.week05.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.example.week05.entity.User;
import org.example.week05.mapper.UserMPMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMPService {
    private final UserMPMapper userMPMapper;

    /**
     * 分页查询
     * @param username 用户名
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return 分页数据
     */
    public Page<User> page(String username, Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }
        
        Page<User> page = Page.of(pageNum, pageSize);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(username != null && !username.isEmpty(), User::getUsername, username);
        return userMPMapper.selectPage(page, wrapper);
    }

    /**
     * 新增用户
     * @param user 用户
     * @return 影响条数
     */
    public int add(User user) {
        if (user.getCreateTime() == null) {
            user.setCreateTime(java.time.LocalDateTime.now());
        }
        return userMPMapper.insert(user);
    }

    /**
     * 删除用户
     * @param id 主键
     * @return 影响条数
     */
    public int delete(Long id) {
        return userMPMapper.deleteById(id);
    }

    /**
     * 更新用户
     * @param user 用户
     * @return 影响条数
     */
    public int update(User user) {
        return userMPMapper.updateById(user);
    }

    /**
     * 查询单个用户
     * @param id 主键
     * @return 用户
     */
    public User getById(Long id) {
        return userMPMapper.selectById(id);
    }

    /**
     * 查询所有用户
     * @return 用户列表
     */
    public java.util.List<User> list() {
        return userMPMapper.selectList(null);
    }
}
