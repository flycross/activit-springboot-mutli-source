package com.example.demo.activiti.service.impl;

import com.example.demo.activiti.service.ActivitiCustomService;
import com.example.demo.bean.ActHiDetail;
import com.example.demo.mapper.activiti.ActHiDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

/**
 * Created by a2 on 2018-03-06.
 */
@Service("activitiCustomService")
public class ActivitiCustomServiceImpl implements ActivitiCustomService {

    @Autowired
    private ActHiDetailMapper actHiDetailMapper;


    @Override
    public int deleteFormPropertyByProcessInstanceId(String processInstanceId) {
        Condition fetchDetailCon = new Condition(ActHiDetail.class);
        fetchDetailCon.createCriteria().andEqualTo("procInstId",processInstanceId).andEqualTo("type","FormProperty");

        int i=actHiDetailMapper.deleteByCondition(fetchDetailCon);

        return i;
    }
}
