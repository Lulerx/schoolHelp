package com.itle.schoolhelp.controller;

import com.itle.schoolhelp.dto.Page;
import com.itle.schoolhelp.pojo.School;
import com.itle.schoolhelp.pojo.Task;
import com.itle.schoolhelp.pojo.User;
import com.itle.schoolhelp.service.ISchoolService;
import com.itle.schoolhelp.service.ITaskService;
import com.itle.schoolhelp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther Luler
 * @date 2020/1/25
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITaskService taskService;

    @Autowired
    private ISchoolService schoolService;

    /**
     * 传入用户id获取并展示用户详细信息
     * @param strId
     * @param model
     * @return
     */
    @RequestMapping("/getUserMsg")
    public String getUserMessage(@RequestParam(value = "strId") Integer strId, Model model){
        if (strId == 0){
            model.addAttribute("msg","该用户不存在");
            return "redirect:/task/getUserTask";
        }
        User user = userService.findUserByStuId(strId);
        if (user == null){  //用户不存在
            model.addAttribute("msg","查找失败");
            return "userInfo";
        }
        model.addAttribute("theUser",user);
        return "userInfo";
    }


    @PostMapping("/getAllSchools")
    @ResponseBody
    public Map<String , Object> getAllSchools(){

        List<School> allSchools = schoolService.findAllSchools();

        Map<String , Object> map = new HashMap<>();
        map.put("schoolList", allSchools);

        return map;

    }



    @RequestMapping(value = "/getPageList")
    @ResponseBody
    public Map<String , Object> getPageList(HttpServletRequest request) {

        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer limit = Integer.valueOf(request.getParameter("limit"));

        Map<String , Object> map = new HashMap<>();

        Page<Task> pageList = taskService.getPageList(start, limit);

        map.put("page",pageList);


        return map;
    }


}
