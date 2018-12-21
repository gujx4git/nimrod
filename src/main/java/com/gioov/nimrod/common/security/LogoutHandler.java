package com.gioov.nimrod.common.security;

import com.gioov.common.web.http.SuccessEntity;
import com.gioov.nimrod.common.Common;
import com.gioov.nimrod.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author godcheese
 */
@Component
public class LogoutHandler implements org.springframework.security.web.authentication.logout.LogoutHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private Common common;

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        userService.logout(httpServletRequest, httpServletResponse, authentication);
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            PrintWriter printWriter = httpServletResponse.getWriter();
            printWriter.write(common.objectToJson(new SuccessEntity("注销成功")));
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
