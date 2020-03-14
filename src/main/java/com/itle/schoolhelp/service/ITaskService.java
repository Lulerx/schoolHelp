package com.itle.schoolhelp.service;

import com.itle.schoolhelp.dto.Page;
import com.itle.schoolhelp.pojo.Task;

import java.util.List;

/**
 * @auther Luler
 * @date 2020/1/22
 */
public interface ITaskService {

    /**
     * 新建任务
     * @param task
     * @return
     */
    int createTsk(Task task);

    /**
     * 获取全部任务列表
     * @return
     */
    List<Task> getAllTask();

    /**
     * 根据发布者ID查找任务
     * @param studentId
     * @return
     */
    List<Task> findTaskByUserId(String studentId);

    /**
     * 根据任务id查找任务信息
     * @param taskId
     * @return
     */
    Task findTaskByTaskId(int taskId);

    /**
     * 更新任务信息
     * @param theTask
     * @return
     */
    boolean updateTask(Task theTask);

    Page<Task> getPageList(Integer start, Integer limit);
}
