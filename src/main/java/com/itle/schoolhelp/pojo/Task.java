package com.itle.schoolhelp.pojo;

import java.util.Date;

/**
 * @auther Luler
 * @date 2020/1/22
 */
public class Task {

    private Integer taskId;  //任务id

    private String publishUserId;    //发布者学号

    private String publishUserName;  //发布者姓名

    private Integer publishSchoolId; //发布者学校ID

    private Integer  acceptUserID;   //接受任务的学生ID

    private Double reward;   //任务奖励

    private Date addTime;    //发布时间

    private Date endTime;    //结束时间

    private String taskName; //任务名称

    private String taskContext;  //任务描述

    private Short state;    //任务状态

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(String publishUserId) {
        this.publishUserId = publishUserId;
    }

    public String getPublishUserName() {
        return publishUserName;
    }

    public void setPublishUserName(String publishUserName) {
        this.publishUserName = publishUserName;
    }

    public Integer getPublishSchoolId() {
        return publishSchoolId;
    }

    public void setPublishSchoolId(Integer publishSchoolId) {
        this.publishSchoolId = publishSchoolId;
    }

    public Integer getAcceptUserID() {
        return acceptUserID;
    }

    public void setAcceptUserID(Integer acceptUserID) {
        this.acceptUserID = acceptUserID;
    }

    public Double getReward() {
        return reward;
    }

    public void setReward(Double reward) {
        this.reward = reward;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskContext() {
        return taskContext;
    }

    public void setTaskContext(String taskContext) {
        this.taskContext = taskContext;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", publishUserId='" + publishUserId + '\'' +
                ", publishUserName='" + publishUserName + '\'' +
                ", publishSchoolId=" + publishSchoolId +
                ", acceptUserID=" + acceptUserID +
                ", reward=" + reward +
                ", addTime=" + addTime +
                ", endTime=" + endTime +
                ", taskName='" + taskName + '\'' +
                ", taskContext='" + taskContext + '\'' +
                ", state=" + state +
                '}';
    }
}
