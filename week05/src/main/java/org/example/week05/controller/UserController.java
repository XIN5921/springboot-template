package org.example.week05.controller;

import lombok.RequiredArgsConstructor;
import org.example.week05.common.Result;
import org.example.week05.entity.User;
import org.example.week05.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangxin
 * @date 2026/4/3
 * @description UserController
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public Result<String> add(@RequestBody User user) {
        int row = userService.add(user);
        if (row != 1) {
            return Result.error("添加失败");
        }
        return Result.success("添加成功");
    }

    @GetMapping("/{id}")
    public Result<User> get(@PathVariable Long id) {
        return Result.success(userService.getById(id));
    }

    @GetMapping("/list")
    public Result<List<User>> list() {
        return Result.success(userService.list());
    }

    @GetMapping("/search")
    public Result<List<User>> search(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge) {
        return Result.success(userService.search(username, minAge, maxAge));
    }

    @PutMapping
    public Result<String> update(@RequestBody User user) {
        int row = userService.update(user);
        if (row != 1) {
            return Result.error("更新失败");
        }
        return Result.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        int row = userService.delete(id);
        if (row != 1) {
            return Result.error("删除失败");
        }
        return Result.success("删除成功");
    }

}
