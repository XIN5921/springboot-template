package org.example.week08.sms;

/**
 * @author ZhangXin       // 作者：系统当前用户名
 * @date 2026/4/24 16:23 // 创建日期+时间
 * @description 类描述信息
 */
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.week08.common.dto.ApiResult;
import org.example.week08.sms.dto.*;

/**
 * 短信验证码 HTTP 接口
 * <p>发码：POST /api/sms/verify-codes
 * <p>校验：POST /api/sms/verify-codes/validate
 */
@RestController
@RequestMapping("/api/sms")
@RequiredArgsConstructor
public class SmsVerifyCodeController {

    private final SmsVerifyCodeService smsVerifyCodeService;

    @PostMapping("/verify-codes")
    public ApiResult<SendCodeResponse> send(
            @RequestBody @Valid SendCodeRequest request) {
        return ApiResult.success(smsVerifyCodeService.sendCode(request.phone()));
    }

    @PostMapping("/verify-codes/validate")
    public ResponseEntity<ApiResult<ValidateCodeView>> validate(
            @RequestBody @Valid ValidateCodeRequest request) {
        if (!smsVerifyCodeService.validateCode(request.phone(), request.code())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(ApiResult.error(422, "验证码错误或已过期"));
        }
        return ResponseEntity.ok(ApiResult.success(new ValidateCodeView(true)));
    }
}