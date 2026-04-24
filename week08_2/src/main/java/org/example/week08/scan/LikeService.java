package org.example.week08.scan;

/**
 * @author ZhangXin       // 作者：系统当前用户名
 * @date 2026/4/24 19:59 // 创建日期+时间
 * @description 类描述信息
 */
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 点赞功能核心服务类
 */
@Service
@RequiredArgsConstructor
public class LikeService {
    // Redis Key前缀常量
    private static final String LIKE_STATUS_KEY = "like:status:%s";   // 点赞状态Key
    private static final String LIKE_COUNT_KEY = "like:count:%s";     // 点赞数Key
    private static final String LIKE_USERS_KEY = "like:users:%s";     // 点赞用户列表Key

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 点赞操作
     * @param businessId 业务ID（如文章ID）
     * @param userId     用户ID
     * @return 操作结果：true=点赞成功，false=重复点赞
     */
    public boolean like(String businessId, String userId) {
        // 1. 拼接Key
        String statusKey = String.format(LIKE_STATUS_KEY, businessId);
        String countKey = String.format(LIKE_COUNT_KEY, businessId);
        String usersKey = String.format(LIKE_USERS_KEY, businessId);

        // 2. 检查是否已点赞
        Object isLiked = redisTemplate.opsForHash().get(statusKey, userId);
        if (isLiked != null && "1".equals(isLiked.toString())) {
            return false;
        }

        // 3. 原子操作：更新状态、计数、用户列表
        redisTemplate.opsForHash().put(statusKey, userId, "1");
        redisTemplate.opsForValue().increment(countKey, 1);
        redisTemplate.opsForZSet().add(usersKey, userId, System.currentTimeMillis());

        // 4. 可选：设置Key过期时间（7天，避免Redis内存膨胀）
        redisTemplate.expire(statusKey, 7, TimeUnit.DAYS);
        redisTemplate.expire(countKey, 7, TimeUnit.DAYS);
        redisTemplate.expire(usersKey, 7, TimeUnit.DAYS);

        return true;
    }

    /**
     * 取消点赞操作
     * @param businessId 业务ID
     * @param userId     用户ID
     * @return 操作结果：true=取消成功，false=未点赞
     */
    public boolean cancelLike(String businessId, String userId) {
        String statusKey = String.format(LIKE_STATUS_KEY, businessId);
        String countKey = String.format(LIKE_COUNT_KEY, businessId);
        String usersKey = String.format(LIKE_USERS_KEY, businessId);

        // 检查是否已点赞
        Object isLiked = redisTemplate.opsForHash().get(statusKey, userId);
        if (isLiked == null || !"1".equals(isLiked.toString())) {
            return false;
        }

        // 原子操作：更新状态、计数、用户列表
        redisTemplate.opsForHash().put(statusKey, userId, "0");
        redisTemplate.opsForValue().decrement(countKey, 1);
        redisTemplate.opsForZSet().remove(usersKey, userId);

        return true;
    }

    /**
     * 查询用户点赞状态
     * @param businessId 业务ID
     * @param userId     用户ID
     * @return true=已点赞，false=未点赞
     */
    public boolean isLiked(String businessId, String userId) {
        String statusKey = String.format(LIKE_STATUS_KEY, businessId);
        Object isLiked = redisTemplate.opsForHash().get(statusKey, userId);
        return isLiked != null && "1".equals(isLiked.toString());
    }

    /**
     * 查询内容总点赞数
     * @param businessId 业务ID
     * @return 点赞数（默认0）
     */
    public long getLikeCount(String businessId) {
        String countKey = String.format(LIKE_COUNT_KEY, businessId);
        Object count = redisTemplate.opsForValue().get(countKey);
        return count == null ? 0 : Long.parseLong(count.toString());
    }

    /**
     * 分页查询点赞用户列表（按点赞时间倒序）
     * @param businessId 业务ID
     * @param pageNum    页码（从1开始）
     * @param pageSize   页大小
     * @return 用户ID列表
     */
    public Set<Object> getLikeUserList(String businessId, int pageNum, int pageSize) {
        String usersKey = String.format(LIKE_USERS_KEY, businessId);
        long start = (pageNum - 1) * pageSize;
        long end = pageNum * pageSize - 1;
        return redisTemplate.opsForZSet().reverseRange(usersKey, start, end);
    }
}
