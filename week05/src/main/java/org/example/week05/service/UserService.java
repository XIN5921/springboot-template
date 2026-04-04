package org.example.week05.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.week05.entity.User;
import org.example.week05.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author mqxu
 * @date 2026/4/2
 * @description UserService
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserMapper userMapper;

    public int add(User user) {
        int result = userMapper.insert(user);
        user.setCreateTime(LocalDateTime.now());
        log.info("添加用户:{}", user);
        return result;
    }

    public User getById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public List<User> list() {
        return userMapper.selectList();
    }

    public List<User> search(String username, Integer minAge, Integer maxAge) {
        List<User> allUsers = userMapper.selectList();
        return allUsers.stream()
                .filter(user -> {
                    boolean matchUsername = username == null || user.getUsername().contains(username);
                    boolean matchMinAge = minAge == null || user.getAge() >= minAge;
                    boolean matchMaxAge = maxAge == null || user.getAge() <= maxAge;
                    return matchUsername && matchMinAge && matchMaxAge;
                })
                .toList();
    }

    public int update(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Transactional
    public int delete(Long id) {
        int row = userMapper.deleteByPrimaryKey(id);
        log.info("删除用户 ID:{}, 影响行数:{}", id, row);
        return row;
    }
}
