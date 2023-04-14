package com.egovja.tatransform.processmanagement.commonprocess.dto.info;

import com.egovja.tatransform.processmanagement.commonprocess.dto.infc.Task;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import org.activiti.engine.task.DelegationState;

public class TaskInfo implements Task, Serializable {

    String id;
    String name;
    String assignee;
    Date claimTime;
    Date createTime;
    Date startTime;
    Date dueDate;
    Date endTime;
    String appVersion;
    String businessKey;
    String category;
    String description;
    String executionId;
    String formKey;
    String owner;
    String parentTaskId;
    Integer priority;
    String processDefinitionId;
    String processInstanceId;
    transient Map<String, Object> processVariables;
    String taskDefinitionKey;
    String processDefinitionKey;
    Integer processDefinitionVersion;
    transient Map<String, Object> taskLocalVariables;
    String tenantId;
    DelegationState delegationState;

    public TaskInfo() {
    }

    public TaskInfo(Task task) {
        if (task != null) {
            this.appVersion = task.getAppVersion();
            this.assignee = task.getAssignee();
            this.businessKey = task.getBusinessKey();
            this.category = task.getCategory();
            this.claimTime = new Date(task.getClaimTime().getTime());
            this.createTime = new Date(task.getCreateTime().getTime());
            this.delegationState = task.getDelegationState();
            this.description = task.getDescription();
            this.dueDate = new Date(task.getDueDate().getTime());
            this.endTime = new Date(task.getEndTime().getTime());
            this.executionId = task.getExecutionId();
            this.formKey = task.getFormKey();
            this.id = task.getId();
            this.name = task.getName();
            this.owner = task.getOwner();
            this.parentTaskId = task.getParentTaskId();
            this.priority = task.getPriority();
            this.processDefinitionId = task.getProcessDefinitionId();
            this.processInstanceId = task.getProcessInstanceId();
            this.processVariables = task.getProcessVariables();
            this.taskDefinitionKey = task.getTaskDefinitionKey();
            this.taskLocalVariables = task.getTaskLocalVariables();
            this.tenantId = task.getTenantId();
            this.processDefinitionVersion = task.getProcessDefinitionVersion();
            this.processDefinitionKey = task.getProcessDefinitionKey();
        }
    }

    @Override
    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    @Override
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    @Override
    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    @Override
    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    @Override
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    @Override
    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    @Override
    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    @Override
    public Date getClaimTime() {
        return claimTime;
    }

    public void setClaimTime(Date claimTime) {
        this.claimTime = claimTime;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    @Override
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public Map<String, Object> getProcessVariables() {
        return processVariables;
    }

    public void setProcessVariables(Map<String, Object> processVariables) {
        this.processVariables = processVariables;
    }

    @Override
    public Map<String, Object> getTaskLocalVariables() {
        return taskLocalVariables;
    }

    public void setTaskLocalVariables(Map<String, Object> taskLocalVariables) {
        this.taskLocalVariables = taskLocalVariables;
    }

    @Override
    public DelegationState getDelegationState() {
        return delegationState;
    }

    public void setDelegationState(DelegationState delegationState) {
        this.delegationState = delegationState;
    }

    @Override
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    @Override
    public Integer getProcessDefinitionVersion() {
        return processDefinitionVersion;
    }

    public void setProcessDefinitionVersion(Integer processDefinitionVersion) {
        this.processDefinitionVersion = processDefinitionVersion;
    }

    @Override
    public String toString() {
        return "TaskInfo{"
                + "id=" + id
                + ", name=" + name
                + ", assignee=" + assignee
                + ", claimTime=" + claimTime
                + ", createTime=" + createTime
                + ", dueData=" + dueDate
                + ", endTime=" + endTime
                + ", appVersion=" + appVersion
                + ", businessKey=" + businessKey
                + ", category=" + category
                + ", description=" + description
                + ", executionId=" + executionId
                + ", formKey=" + formKey
                + ", owner=" + owner
                + ", parentTaskId=" + parentTaskId
                + ", priority=" + priority
                + ", processDefinitionId=" + processDefinitionId
                + ", processInstanceId=" + processInstanceId
                + ", processVariables=" + processVariables
                + ", taskDefinitionKey=" + taskDefinitionKey
                + ", taskLocalVariables=" + taskLocalVariables
                + ", tenantId=" + tenantId
                + ", delegationState=" + delegationState
                + ", processDefinitionVersion=" + processDefinitionVersion
                + '}';
    }

}
