package com.gioov.nimrod.user.controller;

import com.gioov.nimrod.common.constant.Page;
import com.gioov.nimrod.common.operationlog.OperationLog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

/**
 * @author godcheese
 * @date 2018/2/22
 */
@Controller
@RequestMapping(Page.USER)
public class UserController {

    @OperationLog("登录页")
    @RequestMapping("/login")
    public String login() {
        return Page.User.LOGIN;
    }

    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/USER/PAGE_ALL')")
    @RequestMapping("/page_all")
    public String pageAll() {
        return Page.USER + "/page_all";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/add_dialog")
    public String addDialog() {
        return Page.USER + "/add_dialog";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/edit_dialog")
    public String editDialog() {
        return Page.USER + "/edit_dialog";
    }

}
