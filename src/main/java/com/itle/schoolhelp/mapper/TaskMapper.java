package com.itle.schoolhelp.mapper;

import com.itle.schoolhelp.pojo.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther Luler
 * @date 2020/2/20
 */
@Mapper
public interface TaskMapper {

    int insertTask(Task task);

    List<Task> getAllTask();

    List<Task> findTaskByUserId(String studentId);

    Task findTaskByTaskId(int taskId);

    boolean updateTask(Task theTask);

    List<Task> getPageList(Integer start, Integer limit);
}
