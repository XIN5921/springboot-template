package org.example.week08.scan;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class SignQrController {

    private final QrSignService qrSignService;

    @GetMapping("/createQr")
    public String createQr(@RequestParam String articleId) {
        String qrBase64 = qrSignService.generateQrCode(articleId);
        return """
                <!DOCTYPE html>
                <html lang="zh-CN">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
                    <title>文章点赞</title>
                    <style>
                        * { margin: 0; padding: 0; box-sizing: border-box; }
                        body { 
                            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif; 
                            background: linear-gradient(135deg, #fce7f3 0%, #fbcfe8 100%); 
                            min-height: 100vh; 
                            display: flex; 
                            align-items: center; 
                            justify-content: center; 
                            padding: 20px; 
                        }
                        .container { 
                            background: white; 
                            border-radius: 20px; 
                            padding: 40px 30px; 
                            box-shadow: 0 10px 40px rgba(0,0,0,0.1); 
                            text-align: center; 
                            width: 100%; 
                            max-width: 450px; 
                            animation: slideUp 0.5s;
                        }
                        h2 { 
                            color: #1f2937; 
                            font-size: 24px; 
                            margin-bottom: 25px; 
                            font-weight: 600; 
                        }
                        .qr-wrapper {
                            background: #f8fafc;
                            border-radius: 15px;
                            padding: 20px;
                            display: inline-block;
                            margin-bottom: 20px;
                        }
                        img { 
                            width: 280px; 
                            height: 280px; 
                            border-radius: 10px;
                            display: block;
                        }
                        .tips { 
                            color: #6b7280; 
                            font-size: 14px; 
                            line-height: 1.6;
                            margin-top: 15px;
                        }
                        .countdown {
                            color: #ec4899;
                            font-size: 18px;
                            font-weight: 600;
                            margin-top: 10px;
                        }
                        @keyframes slideUp {
                            from { transform: translateY(30px); opacity: 0; }
                            to { transform: translateY(0); opacity: 1; }
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h2>👍 扫码点赞</h2>
                        <div class="qr-wrapper">
                            <img src="PLACEHOLDER_QR" alt="点赞二维码"/>
                        </div>
                        <div class="countdown">⏰ 有效期：7天</div>
                        <p class="tips">请使用手机浏览器或微信扫一扫<br/>为喜欢的文章点赞支持一下~</p>
                    </div>
                </body>
                </html>
                """.replace("PLACEHOLDER_QR", qrBase64);
    }

    @GetMapping("/scan")
    public String scanLike(@RequestParam String code,
                           @RequestParam(defaultValue = "user_1001") String userId) {
        boolean result = qrSignService.checkLikeCode(code, userId);
        if (result) {
            return """
                    <!DOCTYPE html>
                    <html lang="zh-CN">
                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
                        <title>点赞结果</title>
                        <style>
                            * { margin: 0; padding: 0; box-sizing: border-box; }
                            body { 
                                font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif; 
                                background: linear-gradient(135deg, #fce7f3 0%, #f9a8d4 100%); 
                                min-height: 100vh; 
                                display: flex; 
                                align-items: center; 
                                justify-content: center; 
                                padding: 20px; 
                            }
                            .container { 
                                background: white; 
                                border-radius: 20px; 
                                padding: 40px 30px; 
                                box-shadow: 0 10px 40px rgba(0,0,0,0.1); 
                                text-align: center; 
                                width: 100%; 
                                max-width: 400px; 
                            }
                            .icon { font-size: 80px; margin-bottom: 20px; animation: bounceIn 0.6s; }
                            h2 { color: #ec4899; font-size: 28px; margin-bottom: 15px; font-weight: 600; }
                            .message { color: #6b7280; font-size: 16px; line-height: 1.6; }
                            @keyframes bounceIn { 
                                0% { transform: scale(0); opacity: 0; }
                                50% { transform: scale(1.1); }
                                100% { transform: scale(1); opacity: 1; }
                            }
                        </style>
                    </head>
                    <body>
                        <div class="container">
                            <div class="icon">❤️</div>
                            <h2>点赞成功</h2>
                            <p class="message">感谢您的支持与鼓励<br/>我会继续创作更多优质内容！</p>
                        </div>
                    </body>
                    </html>
                    """;
        } else {
            return """
                    <!DOCTYPE html>
                    <html lang="zh-CN">
                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
                        <title>点赞失败</title>
                        <style>
                            * { margin: 0; padding: 0; box-sizing: border-box; }
                            body { 
                                font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif; 
                                background: linear-gradient(135deg, #fee2e2 0%, #fecaca 100%); 
                                min-height: 100vh; 
                                display: flex; 
                                align-items: center; 
                                justify-content: center; 
                                padding: 20px; 
                            }
                            .container { 
                                background: white; 
                                border-radius: 20px; 
                                padding: 40px 30px; 
                                box-shadow: 0 10px 40px rgba(0,0,0,0.1); 
                                text-align: center; 
                                width: 100%; 
                                max-width: 400px; 
                            }
                            .icon { font-size: 80px; margin-bottom: 20px; animation: shakeIn 0.6s; }
                            h2 { color: #ef4444; font-size: 28px; margin-bottom: 15px; font-weight: 600; }
                            .message { color: #6b7280; font-size: 16px; line-height: 1.6; }
                            @keyframes shakeIn { 
                                0%, 100% { transform: translateX(0); }
                                25% { transform: translateX(-10px); }
                                75% { transform: translateX(10px); }
                            }
                        </style>
                    </head>
                    <body>
                        <div class="container">
                            <div class="icon">❌</div>
                            <h2>二维码已失效</h2>
                            <p class="message">二维码已过期或已被使用<br/>请重新获取点赞码</p>
                        </div>
                    </body>
                    </html>
                    """;
        }
    }
}