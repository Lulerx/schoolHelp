package com.itle.schoolhelp.controller;

import com.itle.schoolhelp.pojo.Admin;
import com.itle.schoolhelp.pojo.School;
import com.itle.schoolhelp.service.IAdminService;
import com.itle.schoolhelp.service.ISchoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther Luler
 * @date 2020/2/16
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IAdminService adminService;

    @Autowired
    private ISchoolService schoolService;


    /**
     * 获取所有学校信息
     * @param model
     * @return
     */
    @RequestMapping("/getAllSchools")
    public String getSchools(Model model) {

        List<School> schoolList = schoolService.findAllSchools();
        model.addAttribute("schoolList", schoolList);
        return "admin/adminSchool";

    }


    /**
     * 根据学校ID获取学校信息
     * @param schoolId
     * @param model
     * @return
     */
    @RequestMapping("/getSchoolInfo")
    public String getSchoolInfo(@RequestParam(value = "schoolId") Integer schoolId, Model model) {

        School school = schoolService.findSchoolById(schoolId);
        model.addAttribute("nowSchool", school);
        return "admin/adminSchoolSetting";

    }


    @PostMapping("/addSchool")
    @ResponseBody
    public Map<String, Object> addSchool(@RequestParam(value = "schoolName") String schoolName) throws ParseException {

        Map<String, Object> map = new HashMap<>();

        School findSchool = schoolService.findSchoolByName(schoolName);
        if (findSchool != null){
            map.put("msg","添加失败，这个学校已存在！");
            return map;
        }

        School school = new School();

        school.setName(schoolName);
        school.setAddTime(new Date());
        school.setState(0);

        int a = schoolService.insertSchool(school);
        if (a <= 0){
            map.put("msg","添加失败，请稍后重试！");
        }else {
            map.put("msg","添加成功！");
        }
        return map;
    }


    /**
     * 更新学校信息
     * @return
     */
    @PostMapping("/updateSchool")
    @ResponseBody
    public Map<String, Object> updateSchool(School school,@RequestParam(value = "Time") String addTime) {

        Map<String, Object> map = new HashMap<>();
        try {
            Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(addTime);
            school.setAddTime(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int state = schoolService.updateSchoolMsg(school);

        if (state <= 0){
            map.put("msg","更新失败，请稍后重试！");
        }else {
            map.put("msg","保存成功！");
            map.put("schoolId",school.getSchoolId());
        }

        return map;
    }


    /**
     * 更新管理员密码
     * @param request
     * @param oldPwd
     * @param password
     * @return
     */
    @PostMapping("/updatePwd")
    @ResponseBody
    public Map<String , Object> updatePwd(HttpServletRequest request, @RequestParam(value = "oldPwd") String oldPwd,
                                          @RequestParam(value = "password") String password){

        Map<String , Object> map = new HashMap<>();
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (!oldPwd.equals(admin.getPassword())){
            map.put("msg","原密码错误，请重新输入！");
            return map;
        }
        admin.setPassword(password);
        int flag = adminService.updateAdmin(admin);
        if (flag <= 0) {
            logger.info("admin数据更新失败!");
            map.put("msg","修改失败，请稍后重试！");

        }else {
            map.put("msg","密码已修改！");
        }
        return map;
    }



    @PostMapping("/addAdmin")
    @ResponseBody
    public Map<String , Object>  addAdmin(Admin admin){

        Map<String , Object> map = new HashMap<>();
        admin.setAddTime(new Date());

        int i = adminService.insertAdmin(admin);
        if (i <= 0){
            logger.info("添加管理员出错！");
            map.put("msg","添加失败，请重试");
        }else {
            map.put("msg","添加成功");
        }

        return map;
    }

}
