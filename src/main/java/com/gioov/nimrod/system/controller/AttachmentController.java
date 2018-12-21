package com.gioov.nimrod.system.controller;

import com.gioov.nimrod.common.constant.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

/**
 * @author godcheese
 * @date 2018/2/22
 */
@Controller
@RequestMapping(Page.System.ATTACHMENT)
public class AttachmentController {

    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/SYSTEM/ATTACHMENT/PAGE_ALL1')")
    @RequestMapping("/page_all1")
    public String pageAll1() {
        return Page.System.ATTACHMENT + "/page_all1";
    }

    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/SYSTEM/ATTACHMENT/PAGE_ALL')")
    @RequestMapping("/page_all")
    public String pageAll() {
        return Page.System.ATTACHMENT + "/page_all";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/upload_one_dialog")
    public String uploadOneDialog() {
        return Page.System.ATTACHMENT + "/upload_one_dialog";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/upload_all_dialog")
    public String uploadAllDialog() {
        return Page.System.ATTACHMENT + "/upload_all_dialog";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/edit_dialog")
    public String editDialog() {
        return Page.System.ATTACHMENT + "/edit_dialog";
    }

}
