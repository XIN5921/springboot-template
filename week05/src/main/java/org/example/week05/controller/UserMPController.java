package org.example.week05.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.example.week05.common.Result;
import org.example.week05.entity.User;
import org.example.week05.service.UserMPService;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhangXin
 * @date 2026/4/3 22:18
 * @description UserMPController
 */
@RestController
@RequestMapping("/api/user/mp")
@RequiredArgsConstructor
public class UserMPController {
    private final UserMPService userMPService;

    /**
     * 条件分页查询接口，分页参数默认：第 1 页，每页 10 条
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param username 用户名
     * @return 分页数据
     */
    @GetMapping("/page")
    public Result<Page<User>> page(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String username) {
        return Result.success(userMPService.page(username, pageNum, pageSize));
    }

    /**
     * 新增接口（无 SQL）
     * @param user 用户 新增数据
     * @return 新增结果
     */
    @PostMapping
    public Result<String> add(@RequestBody User user) {
        int row = userMPService.add(user);
        if (row <= 0) {
            return Result.error("MP 新增失败");
        }
        return Result.success("MP 新增成功");
    }

    /**
     * 删除接口（无 SQL）
     * @param id 删除 id
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        int row = userMPService.delete(id);
        if (row <= 0) {
            return Result.error("MP 删除失败");
        }
        return Result.success("MP 删除成功");
    }

    /**
     * 更新接口（无 SQL）
     * @param user 用户 更新数据
     * @return 更新结果
     */
    @PutMapping
    public Result<String> update(@RequestBody User user) {
        int row = userMPService.update(user);
        if (row <= 0) {
            return Result.error("MP 更新失败");
        }
        return Result.success("MP 更新成功");
    }

    /**
     * 查询单个用户
     * @param id 主键
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Long id) {
        return Result.success(userMPService.getById(id));
    }

    /**
     * 查询所有用户
     * @return 用户列表
     */
    @GetMapping("/list")
    public Result<java.util.List<User>> list() {
        return Result.success(userMPService.list());
    }
}
