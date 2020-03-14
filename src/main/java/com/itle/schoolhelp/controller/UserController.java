package com.itle.schoolhelp.controller;

import com.itle.schoolhelp.pojo.Admin;
import com.itle.schoolhelp.pojo.User;
import com.itle.schoolhelp.service.IAdminService;
import com.itle.schoolhelp.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录相关的controller
 * @auther Luler
 * @date 2020/1/16
 */

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserService userService;

    @Autowired
    private IAdminService adminService;



    /**
     * 用户登录
     * @param studentId
     * @param password
     * @param model
     * @param request
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public Map<String , Object> login(String studentId, String password, Model model, HttpServletRequest request) {
        Map<String , Object> map = new HashMap<>();
        HttpSession session = request.getSession();

        Admin admin = adminService.findAdminById(studentId);
        if (admin != null){
            if (admin.getPassword().equals(password)) {
                session.setAttribute("admin",admin);
                map.put("url", "/adminIndex/admin");
                map.put("msg", "管理员登录成功");
                return map;
            }
        }

        User user = userService.findByStudentId(studentId);

        if (user == null) {
            map.put("msg", "登录失败--请确定账号和密码正确");
            return map;
        }

        if (user.getPassword().equals(password)) {
            session.setAttribute("nowUser", user);
            map.put("msg", "登录成功！");
            map.put("url","/task/getTaskList");
            return map;
        } else {
            map.put("msg", "登录失败--请确定账号和密码正确");
            return map;
        }
    }

    /**
     * 用户注册
     * @param user
     * @param model
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public Map<String , Object> register(User user, Model model) {
        Map<String , Object> map = new HashMap<>();
        System.out.println(user);
        //判断学号是否已经存在
        int account = userService.getAccountByStudent(user.getStudentId());
        if (account > 0) {
            map.put("msg", "该学号已经注册");
            return map;
        }
        user.setRegisterTime(new Date());
        boolean state = userService.insertUser(user);
        if (!state) {
            map.put("msg", "注册失败，请重新注册！");
            return map;
        }else {
            map.put("msg", "注册成功，请登录！");
            map.put("code",1);
            return map;
        }
    }
/*
    @RequestMapping("/register")
    public String register(User user, Model model) {

        System.out.println(user);
        //判断学号是否已经存在
        int account = userService.getAccountByStudent(user.getStudentId());
        if (account > 0) {
            model.addAttribute("msg", "该学号已经注册");
            return "register";
        }
        user.setRegisterTime(new Date());
        boolean state = userService.insertUser(user);
        if (!state) {
            model.addAttribute("msg", "注册失败，请重新注册！");
        }
        model.addAttribute("msg", "注册成功，请登录！");

        return "login";
    }
*/


    /**
     * 退出登录
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("nowUser");
        session.removeAttribute("admin");
        return "login";
    }

    /**
     * 修改个人信息
     * @param user
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateInfo(User user, Model model, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        try {
            int state = userService.updateUser(user);
            if (state <= 0){    //更新失败
                map.put("msg","修改失败，请稍后再试！");
            }else {     //更新成功
                User nowUser = userService.findUserByStuId(user.getStuId());
                request.getSession().setAttribute("nowUser",nowUser);
                map.put("msg","更新成功，请重新登录！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            map.put("msg","系统出错，请重新尝试！");
        }
        return map;
    }


    /**
     *  修改密码
     * @param oldpassword
     * @param password
     * @return
     */
    //@PostMapping("/updatePwd")
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    @ResponseBody
    public Map<String , Object> updatePassword(HttpServletRequest request, Model model,
                                               @RequestParam(value = "oldPwd") String oldpassword,
                                               @RequestParam(value = "newPwd") String password){

        User nowUser = (User) request.getSession().getAttribute("nowUser");
        Map<String , Object> map = new HashMap<>();

        if (!nowUser.getPassword().equals(oldpassword)) {

            model.addAttribute("msg","原密码错误！");
            map.put("msg","原密码错误！");
            map.put("code",0);
            return map;
        }

        nowUser.setPassword(password);
        try {
            int state = userService.updateUser(nowUser);
            if (state <= 0){
                map.put("msg","修改密码失败，请稍后再试！");
                map.put("code",0);
                return map;
            }else {
                map.put("msg", "修改成功，请重新登录");
                map.put("code",1);
                return map;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            map.put("msg", "系统出错，请重新尝试！");
            map.put("code",0);
            return map;
        }

    }


}
