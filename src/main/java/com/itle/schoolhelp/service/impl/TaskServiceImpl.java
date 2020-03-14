package com.itle.schoolhelp.service.impl;

import com.itle.schoolhelp.dto.Page;
import com.itle.schoolhelp.mapper.TaskMapper;
import com.itle.schoolhelp.pojo.Task;
import com.itle.schoolhelp.service.ITaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther Luler
 * @date 2020/1/22
 */
@Service
public class TaskServiceImpl implements ITaskService {

    @Resource
    private TaskMapper taskMapper;

    @Override
    public int createTsk(Task task) {

        return taskMapper.insertTask(task);
    }

    @Override
    public List<Task> getAllTask() {
        return taskMapper.getAllTask();
    }

    @Override
    public List<Task> findTaskByUserId(String studentId) {
        return taskMapper.findTaskByUserId(studentId);
    }

    @Override
    public Task findTaskByTaskId(int taskId) {
        return taskMapper.findTaskByTaskId(taskId);
    }

    @Override
    public boolean updateTask(Task theTask) {
        return taskMapper.updateTask(theTask);
    }

    @Override
    public Page<Task> getPageList(Integer start, Integer limit) {

        Page<Task> page = new Page<>();

        page.setPageNo(start);
        page.setPageSize(limit);
        page.setDataList(taskMapper.getPageList((start-1)*limit,limit));
        int totalSize = taskMapper.getAllTask().size();
        page.setTotalSize(totalSize);

        if (totalSize % limit != 0){
            page.setTotalNo(totalSize / limit + 1);
        } else {
            page.setTotalNo(totalSize / limit);
        }

        return page;
    }
}
