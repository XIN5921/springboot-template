package org.example.week08;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.week08.entity.Address;
import org.example.week08.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import jakarta.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
public class RedisServiceTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 测试 StringRedisTemplate 模板的基本操作
     */
    @Test
    public void testStringTemplate() throws Exception {
        // 测试字符串操作
        stringRedisTemplate.opsForValue().set("hello", "world", 30, TimeUnit.SECONDS);
        stringRedisTemplate.opsForValue().set("code:13900001111", "1234");
        stringRedisTemplate.opsForValue().set("code:13900002222", "8899");

        // 取值
        String value = stringRedisTemplate.opsForValue().get("hello");
        log.info("Redis 字符串测试结果：{}", value);
        String code = stringRedisTemplate.opsForValue().get("code:13900001111");
        log.info("13900001111 验证码测试结果：{}", code);
        String code2 = stringRedisTemplate.opsForValue().get("code:13900002222");
        log.info("13900002222 验证码测试结果：{}", code2);
    }

    /**
     * 测试 RedisTemplate 对象存取操作
     */
    @Test
    public void testRedisTemplate() throws Exception {
        // 测试字符串操作，有效时间30秒
        redisTemplate.opsForValue().set("code:13900003333", "1234", 20, TimeUnit.SECONDS);
        String code = Objects.requireNonNull(redisTemplate.opsForValue().get("code:13900003333")).toString();
        log.info("13900003333 验证码测试结果：{}", code);

        // 测试对象操作
        Address address = new Address();
        address.setCity("南京市");
        address.setStreet("栖霞区羊山北路1号");
        address.setZipCode("210000");

        User user = new User();
        user.setName("张鑫");
        user.setAge(20);
        user.setEmail("zhangxin@qq.com");
        user.setAddress(address);

        // 将对象存入 Redis
        redisTemplate.opsForValue().set("user:001", user);

        Object userObj = redisTemplate.opsForValue().get("user:001");

        // 反序列化
        User user2 = JSON.parseObject(JSON.toJSONString(userObj), User.class);
        log.info("user:001 测试结果：{}", user2);
    }
}
