package org.example.week08.scan;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import lombok.RequiredArgsConstructor;
import org.example.week08.util.RedisUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class QrSignService {

    private final RedisUtil redisUtil;

    public String generateQrCode(String articleId) {
        String likeCode;
        do {
            likeCode = RandomUtil.randomStringUpper(6);
        } while (redisUtil.hasKey(SignConstant.LIKE_ARTICLE_KEY + likeCode));

        redisUtil.set(SignConstant.LIKE_ARTICLE_KEY + likeCode, articleId, SignConstant.EXPIRE_DAY, TimeUnit.DAYS);

        String scanUrl = "http://localhost:8080/like/scan?code=" + likeCode;

        byte[] bytes = QrCodeUtil.generatePng(scanUrl, 300, 300);
        String base64 = Base64.getEncoder().encodeToString(bytes);

        return "data:image/png;base64," + base64;
    }

    public boolean checkLikeCode(String code, String userId) {
        if (!StringUtils.hasText(code)) {
            return false;
        }
        String key = SignConstant.LIKE_ARTICLE_KEY + code;
        Object raw = redisUtil.get(key);
        if (raw == null) {
            return false;
        }
        String articleId = raw.toString();
        if (!StringUtils.hasText(articleId)) {
            return false;
        }
        redisUtil.delete(key);
        return true;
    }
}