package com.gioov.nimrod.system.controller;

import com.gioov.nimrod.common.constant.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author godcheese
 * @date 2018/2/22
 */
@Controller
@RequestMapping(Page.System.DICTIONARY_CATEGORY)
public class DictionaryCategoryController {

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/add_dialog")
    public String addDialog() {
        return Page.System.DICTIONARY_CATEGORY + "/add_dialog";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/edit_dialog")
    public String editDialog() {
        return Page.System.DICTIONARY_CATEGORY + "/edit_dialog";
    }

}