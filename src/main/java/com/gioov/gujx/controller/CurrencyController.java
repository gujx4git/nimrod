package com.gioov.gujx.controller;

import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("currencyController")
public class CurrencyController {
	/**
     * 新建币种 页面
     *
     * @return String
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/SYSTEM/MAIL/SEND')")
    @RequestMapping("/page_all")
    public String page_all() {
        return "gujx/currency/page_all";
    }
    /**
     * 新建币种 页面
     *
     * @return String
     */
 
    @RequestMapping("/add")
    public String addOne() {
        return "gujx/currency/add_dialog";
    }

    @RequestMapping("/edit_dialog")
    public String edit() {
        return "gujx/currency/edit_dialog";
    }
    
}
