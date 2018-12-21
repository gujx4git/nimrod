package com.gioov.nimrod.system.api;

import com.gioov.common.util.ImageUtil;
import com.gioov.common.util.RandomUtil;
import com.gioov.nimrod.common.constant.Api;
import com.gioov.nimrod.common.operationlog.OperationLog;
import com.gioov.nimrod.common.operationlog.OperationLogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;


/**
 * @author godcheese
 * @date 2018/2/22
 */
@RestController
@RequestMapping(value = Api.SYSTEM, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SystemRestController {

    public static final String VERIFY_CODE_NAME = "verifyCode";

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemRestController.class);

    /**
     * 获取验证码
     */
    @OperationLog(value = "获取验证码", type = OperationLogType.API)
//    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + API_CATEGORY + "/PAGE_ALL_PARENT')")
    @GetMapping(value = "/verify_code")
    public void verifCode(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
        ImageUtil.VerifyCodeImage verifyCodeImage = ImageUtil.createVerifyCodeImage(114, 40, new Color(249, 157, 46), RandomUtil.randomString(4, RandomUtil.NUMBER_LETTER), Color.WHITE, true, 5, 10);
        httpServletResponse.addHeader("Pragma", "no-cache");
        httpServletResponse.addHeader("Cache-Control", "no-cache");
        httpServletResponse.addHeader("Expires", "0");
        // 生成验证码，写入用户session
        httpServletRequest.getSession().setAttribute(VERIFY_CODE_NAME, verifyCodeImage);
        // 输出验证码给客户端
        httpServletResponse.setContentType("image/jpeg");

        try {
            ImageIO.write(verifyCodeImage.getBufferedImage(), "jpg", httpServletResponse.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
