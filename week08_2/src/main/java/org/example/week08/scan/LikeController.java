package org.example.week08.scan;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    /**
     * 点赞接口
     */
    @PostMapping("/{businessId}/{userId}")
    public String like(@PathVariable String businessId, @PathVariable String userId) {
        boolean success = likeService.like(businessId, userId);
        return success ? "点赞成功" : "重复点赞，操作无效";
    }

    /**
     * 取消点赞接口
     */
    @DeleteMapping("/{businessId}/{userId}")
    public String cancelLike(@PathVariable String businessId, @PathVariable String userId) {
        boolean success = likeService.cancelLike(businessId, userId);
        return success ? "取消点赞成功" : "未点赞，无需取消";
    }

    /**
     * 查询点赞状态
     */
    @GetMapping("/status/{businessId}/{userId}")
    public Boolean isLiked(@PathVariable String businessId, @PathVariable String userId) {
        return likeService.isLiked(businessId, userId);
    }

    /**
     * 查询点赞数
     */
    @GetMapping("/count/{businessId}")
    public Long getLikeCount(@PathVariable String businessId) {
        return likeService.getLikeCount(businessId);
    }

    /**
     * 查询点赞用户列表
     */
    @GetMapping("/users/{businessId}")
    public Set<Object> getLikeUserList(
            @PathVariable String businessId,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        return likeService.getLikeUserList(businessId, pageNum, pageSize);
    }
}