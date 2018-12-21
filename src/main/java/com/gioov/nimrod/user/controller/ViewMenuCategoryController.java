package com.gioov.nimrod.user.controller;

import com.gioov.nimrod.common.constant.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author godcheese
 * @date 2018/2/22
 */
@Controller
@RequestMapping(Page.User.VIEW_MENU_CATEGORY)
public class ViewMenuCategoryController {

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/add_dialog")
    public String addDialog() {
        return Page.User.VIEW_MENU_CATEGORY + "/add_dialog";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/edit_dialog")
    public String editDialog() {
        return Page.User.VIEW_MENU_CATEGORY + "/edit_dialog";
    }

}
