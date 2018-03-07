package com.example.demo.activiti.service;

/**
 * Created by a2 on 2018-03-06.
 */
public interface ActivitiCustomService {

    /**
     * 根据instanceId删除明细
     * @param processInstanceId
     * @return
     */
    public  int deleteFormPropertyByProcessInstanceId(String processInstanceId);
}
