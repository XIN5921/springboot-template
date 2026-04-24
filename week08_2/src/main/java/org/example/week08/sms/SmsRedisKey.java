package org.example.week08.sms;

/**
 * @author ZhangXin       // 作者：系统当前用户名
 * @date 2026/4/24 16:19 // 创建日期+时间
 * @description 类描述信息
 */
public final class SmsRedisKey {

    public static final String PREFIX = "week08:sms:code:";

    private SmsRedisKey() {
    }

    public static String ofPhone(String phone) {
        return PREFIX + phone;
    }
}
