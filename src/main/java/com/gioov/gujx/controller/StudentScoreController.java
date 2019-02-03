package com.gioov.gujx.controller;

import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("studentScoreController")
public class StudentScoreController {
	/**
     * 新建学生成绩 页面
     *
     * @return String
     */
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/SCHOOL/STUDENTSCORE/PAGE_ALL')")
    @RequestMapping("/page_all")
    public String page_all() {
        return "gujx/school/student/student_score_page_all";
    }
    /**
     * 新建 学生成绩 页面
     *
     * @return String
     */
 
    @RequestMapping("/add")
    public String addOne() {
        return "gujx/school/student/student_score_add_dialog";
    }

    @RequestMapping("/edit_dialog")
    public String edit() {
        return "gujx/school/student/student_score_edit_dialog";
    }
    
}
