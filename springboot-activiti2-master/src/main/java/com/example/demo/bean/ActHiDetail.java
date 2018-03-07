package com.example.demo.bean;

import java.util.Date;
import javax.persistence.*;

@Table(name = "act_hi_detail")
public class ActHiDetail {
    @Id
    @Column(name = "ID_")
    private String id;

    @Column(name = "TYPE_")
    private String type;

    @Column(name = "PROC_INST_ID_")
    private String procInstId;

    @Column(name = "EXECUTION_ID_")
    private String executionId;

    @Column(name = "TASK_ID_")
    private String taskId;

    @Column(name = "ACT_INST_ID_")
    private String actInstId;

    @Column(name = "NAME_")
    private String name;

    @Column(name = "VAR_TYPE_")
    private String varType;

    @Column(name = "REV_")
    private Integer rev;

    @Column(name = "TIME_")
    private Date time;

    @Column(name = "BYTEARRAY_ID_")
    private String bytearrayId;



    @Column(name = "TEXT_")
    private String text;

    @Column(name = "TEXT2_")
    private String text2;

    /**
     * @return ID_
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return TYPE_
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return PROC_INST_ID_
     */
    public String getProcInstId() {
        return procInstId;
    }

    /**
     * @param procInstId
     */
    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    /**
     * @return EXECUTION_ID_
     */
    public String getExecutionId() {
        return executionId;
    }

    /**
     * @param executionId
     */
    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    /**
     * @return TASK_ID_
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * @param taskId
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * @return ACT_INST_ID_
     */
    public String getActInstId() {
        return actInstId;
    }

    /**
     * @param actInstId
     */
    public void setActInstId(String actInstId) {
        this.actInstId = actInstId;
    }

    /**
     * @return NAME_
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return VAR_TYPE_
     */
    public String getVarType() {
        return varType;
    }

    /**
     * @param varType
     */
    public void setVarType(String varType) {
        this.varType = varType;
    }

    /**
     * @return REV_
     */
    public Integer getRev() {
        return rev;
    }

    /**
     * @param rev
     */
    public void setRev(Integer rev) {
        this.rev = rev;
    }

    /**
     * @return TIME_
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return BYTEARRAY_ID_
     */
    public String getBytearrayId() {
        return bytearrayId;
    }

    /**
     * @param bytearrayId
     */
    public void setBytearrayId(String bytearrayId) {
        this.bytearrayId = bytearrayId;
    }





    /**
     * @return TEXT_
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return TEXT2_
     */
    public String getText2() {
        return text2;
    }

    /**
     * @param text2
     */
    public void setText2(String text2) {
        this.text2 = text2;
    }
}