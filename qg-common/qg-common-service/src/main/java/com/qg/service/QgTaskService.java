package com.qg.service;

import com.qg.pojo.QgTask;

import java.util.List;

public interface QgTaskService {
    /**
     * 添加一条新的任务
     */
    int saveTask(QgTask task);

    /**
     * 根据任务的id，读取该任务
     */
    QgTask getTaskById(Integer id);

    /**
     * 查询指定类型的待处理任务
     */
    public List<QgTask> getTasksPending(Integer taskType);

    /**
     * 更新任务的状态
     */
    int update(QgTask task);


    /**
     * 通过版本锁住该消息
     */
    int lockTask(Integer id, Integer version);
}
