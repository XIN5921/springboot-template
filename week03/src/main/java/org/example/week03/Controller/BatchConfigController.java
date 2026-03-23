package org.example.week03.Controller;

import lombok.RequiredArgsConstructor;
import org.example.week03.common.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/batch")
@RequiredArgsConstructor
public class BatchConfigController {

    @GetMapping("/list")
    public Result<List<Map<String, Object>>> getBatchList() {
        List<Map<String, Object>> list = List.of(
                Map.of("id", 1, "name", "批量配置 1", "status", "active"),
                Map.of("id", 2, "name", "批量配置 2", "status", "inactive"),
                Map.of("id", 3, "name", "批量配置 3", "status", "active")
        );
        return Result.success(list);
    }

    @PostMapping("/create")
    public Result<Map<String, Object>> createBatch(@RequestBody Map<String, Object> body) {
        String name = (String) body.get("name");
        if (name == null || name.isBlank()) {
            return Result.error(400, "配置名称不能为空");
        }
        
        Map<String, Object> created = Map.of(
                "id", System.currentTimeMillis(),
                "name", name,
                "status", "active"
        );
        return Result.success(created, "批量配置创建成功");
    }

    @PutMapping("/update/{id}")
    public Result<String> updateBatch(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        return Result.success("批量配置更新成功，ID=" + id);
    }

    @DeleteMapping("/delete/{id}")
    public Result<String> deleteBatch(@PathVariable Long id) {
        return Result.success("批量配置删除成功，ID=" + id);
    }

    @GetMapping("/detail/{id}")
    public Result<Map<String, Object>> getBatchDetail(@PathVariable Long id) {
        Map<String, Object> detail = Map.of(
                "id", id,
                "name", "批量配置详情-" + id,
                "description", "这是批量配置的详细信息",
                "createTime", "2026-03-23 10:00:00",
                "updateTime", "2026-03-23 12:00:00"
        );
        return Result.success(detail);
    }
}
