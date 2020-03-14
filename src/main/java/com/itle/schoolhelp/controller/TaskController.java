package com.itle.schoolhelp.controller;


import com.itle.schoolhelp.pojo.Task;
import com.itle.schoolhelp.pojo.User;
import com.itle.schoolhelp.service.ITaskService;
import com.itle.schoolhelp.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @auther Luler
 * @date 2020/1/22
 * *****任务的Controller*****
 * 获取任务列表
 * 发布新任务
 * 任务详细信息
 * 用户自己已发布的任务信息
 * 用户点击接受任务
 * 用户已接受的任务
 * 用户点击取消任务
 * 发布者点击完成任务
 * 根据任务发布者学号获取发布者信息
 *
 */
@Controller
@RequestMapping("/task")
public class TaskController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IUserService userService;


    /**
     * 获取任务列表
     * @param model
     * @return
     */
    @RequestMapping("/getTaskList")
    public String getTaskList(Model model){
        List<Task> list = taskService.getAllTask();
        model.addAttribute("list",list);

        return "index";
    }

    /**
     * 发布新任务
     * @param request
     * @param task
     * @return
     */
    /*@RequestMapping(value = "/newTask" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String , Object> createTask(HttpServletRequest request, Task task ,
                                           @RequestParam(value = "Time") String Time){
        Map<String , Object> map = new HashMap<>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("nowUser");
        if (user == null){  //用户未登录
            map.put("msg","请检查登录状态！");
            map.put("url","/login/getPage");
            return map;
        }

        try {
            Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(Time);
            task.setEndTime(endTime);
        } catch (ParseException e) {
            map.put("msg","时间格式出错！");
            logger.info("时间格式报错！");
            logger.error(e.getMessage(),e);
        }

        task.setPublishUserId(user.getStudentId());
        task.setPublishUserName(user.getName());
        task.setPublishSchoolId(user.getSchoolId());
        task.setAddTime(new Date());

        if ((user.getMoney() - task.getReward()) < 0){  //余额不足
            map.put("msg","发布失败，您的余额不足，请充值！");
            return map;
        }
        user.setMoney(user.getMoney()-task.getReward());
        int insert = userService.updateUser(user);
        int tsk = taskService.createTsk(task);

        if (insert > 0) {
            if (tsk > 0) {
                map.put("msg","成功发布新任务！");
                map.put("url","/task/getTaskList");
                session.setAttribute("nowUser",user);
                return map;
            }else {
                map.put("msg","发布新任务失败，请稍后重试！");
                return map;
            }
        }else {
            map.put("msg","扣除余额失败!");
            return map;
        }


    }
*/


    @RequestMapping(value = "/newTask")
    public String createTask(HttpServletRequest request, Model model, Task task ,
                             @RequestParam(value = "Time") String Time){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("nowUser");
        if (user == null){  //用户未登录
            model.addAttribute("msg","请检查登录状态！");
            return "login";
        }

        try {
            Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(Time);
            task.setEndTime(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        task.setPublishUserId(user.getStudentId());
        task.setPublishUserName(user.getName());
        task.setPublishSchoolId(user.getSchoolId());
        task.setAddTime(new Date());

        if ((user.getMoney() - task.getReward()) < 0){  //余额不足
            model.addAttribute("msg","发布失败，您的余额不足，请充值！");
            return "userNewTask";
        }
        user.setMoney(user.getMoney()-task.getReward());

        try {
            if (taskService.createTsk(task) > 0){   //任务创建成功
                if (userService.updateUser(user) > 0) { //扣除账户余额成功
                    model.addAttribute("msg","成功发布新任务！");
                    session.setAttribute("nowUser",user);
                    return "redirect:/task/getTaskList";
                }else {
                    model.addAttribute("msg","扣除余额失败!");
                    return "111";
                }
            }else {
                model.addAttribute("msg","发布新任务失败，请稍后重试！");
                return "111";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/task/getTaskList";

    }




    /**
     * 任务详细信息
     * @param tId
     * @param model
     * @return
     */
    @RequestMapping("/getTaskInfo")
    public String getTaskInfo(@RequestParam(value = "tId") int tId, Model model) {
        Task theTask = taskService.findTaskByTaskId(tId);
        model.addAttribute("theTask",theTask);
        return "taskInfo";
    }


    /**
     * 用户已发布的任务信息
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/getUserTask")
    public String UserPublishTask(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("nowUser");
        List<Task> taskList = taskService.findTaskByUserId(user.getStudentId());

        model.addAttribute("taskList",taskList);

        return "userPublishTask";
    }





    /**
     * 用户点击取消任务
     * @param taskId
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/closeTask",method = RequestMethod.GET)
    @ResponseBody
    public Map<String , Object> closeTask(@RequestParam(value = "taskId") int taskId, HttpServletRequest request, Model model){
        User user = (User) request.getSession().getAttribute("nowUser");
        Map<String , Object> map = new HashMap<>();
        if (user == null){
            model.addAttribute("msg","请检查登录状态！");
            map.put("msg","请检查登录状态！");
            map.put("url","/schoolhelp");
            map.put("code",0);
            return map;
        }

        Task theTask = taskService.findTaskByTaskId(taskId);
        theTask.setState(Short.parseShort("1"));
        try {
            boolean flag = taskService.updateTask(theTask);
            if (!flag){
                map.put("msg","取消失败，请重试！");
                return map;
            }
        } catch (Exception e) {
            map.put("msg","取消失败，请重试！");
            logger.error(e.getMessage(),e);
            return map;
        }

        user.setMoney(user.getMoney()+ theTask.getReward());

        try {
            if(userService.updateUser(user) <= 0){  //更新失败
                map.put("msg","取消失败，请重试！");
            }else {
                map.put("msg","取消成功");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

        return map;
    }


    /**
     * 用户点击接受任务
     * @param taskId
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/acceptTask")
    public String userAcceptTask(@RequestParam(value = "taskId") Integer taskId, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User nowUser = (User) session.getAttribute("nowUser");
        if (nowUser == null){  //用户未登录
            model.addAttribute("msg","请检查登录状态！");
            return "login";
        }
        Task NowTask = taskService.findTaskByTaskId(taskId);

        NowTask.setState(Short.parseShort("2"));    //状态改为待解决
        NowTask.setAcceptUserID(nowUser.getStuId());

        if(!taskService.updateTask(NowTask)){
            model.addAttribute("msg","系统出错，请稍后重试！");
            return getTaskInfo(taskId,model);
        }
        model.addAttribute("msg","已接受任务，请尽快完成！");
        return getTaskInfo(taskId,model);
    }


    /**
     * 用户接受的任务
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/getUserAcceptTask")
    public String getUserAcceptTask(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        User nowUser = (User) session.getAttribute("nowUser");

        List<Task> AllTaskList = taskService.getAllTask();
        List<Task> UserAcceptList = new ArrayList<>();

        for (Task task : AllTaskList) {
            if (task.getAcceptUserID() == nowUser.getStuId()){
                UserAcceptList.add(task);
            }
        }

        model.addAttribute("AcceptList",UserAcceptList);

        return "userAcceptTask";
    }


    /**
     * 发布者点击完成任务
     * @param taskId
     * @param model
     * @return
     */
    @GetMapping("/taskOK")
    @ResponseBody
    public Map<String , Object> taskOK(@RequestParam(value = "taskId") int taskId,Model model){
        Map<String , Object> map = new HashMap<>();
        Task task = taskService.findTaskByTaskId(taskId);
        User AcceptUser = userService.findUserByStuId(task.getAcceptUserID());

        AcceptUser.setMoney(AcceptUser.getMoney() + task.getReward());

        try {
            task.setState(Short.parseShort("3"));   //任务完成，修改任务状态
            taskService.updateTask(task);
            userService.updateUser(AcceptUser);     //更新任务接受者余额
            map.put("msg","任务已完成！");
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(),e);
            map.put("msg","出现异常！");
        }
        return map;
    }


    /**
     * 根据任务发布者学号获取发布者信息
     * @param strId
     * @param model
     * @return
     */
    @RequestMapping("/getUserMsg")
    public String getUserMsg(@RequestParam(value = "strId") String strId, Model model){
        if (strId.equals(0)){
            model.addAttribute("msg","该用户不存在");
            return "redirect:/task/getUserTask";
        }
        User user = userService.findByStudentId(strId);
        if (user == null){  //用户不存在
            model.addAttribute("msg","查找失败");
            return "userInfo";
        }
        model.addAttribute("theUser",user);
        return "userInfo";
    }


}
