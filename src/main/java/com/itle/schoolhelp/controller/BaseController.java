package com.itle.schoolhelp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于做页面映射跳转的控制层
 * @auther Luler
 * @date 2020/1/17
 */
@Controller
public class BaseController {

    @RequestMapping(value = "/schoolhelp")
    public String index(){
        return "login";
    }

    /**
     * 用于a标签超链接跳转另一个页面
     * @param test
     * @return
     */
    @RequestMapping(value = "/{test}/getPage")
    public String into(@PathVariable String test){
        return test;
    }

    @RequestMapping(value = "/{test}/admin")
    public String intoAdmin(@PathVariable String test){
        return "admin/"+test;
    }




}
