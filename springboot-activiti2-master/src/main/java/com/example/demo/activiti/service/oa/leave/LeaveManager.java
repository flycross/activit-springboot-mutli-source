package com.example.demo.activiti.service.oa.leave;

import com.example.demo.bean.oa.Leave;
import com.example.demo.mapper.oa.LeaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 请假实体管理
 *
 * @author HenryYan
 */
@Service
@Transactional
public class LeaveManager {

    @Autowired
    private LeaveMapper leaveMapper;

    public Leave getLeave(Long id) {
        return leaveMapper.selectByPrimaryKey(id);
    }

    //@Transactional(readOnly = false)
    public void saveLeave(Leave entity) {
        if (entity.getId() == null) {
            entity.setApplyTime(new Date());
            leaveMapper.insert(entity);
        }else {
            leaveMapper.updateByPrimaryKey(entity);
        }


    }



}
