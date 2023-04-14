/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.processmanagement.commonprocess.mapper;

import com.egovja.tatransform.processmanagement.commonprocess.dto.info.TaskInfo;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;

/**
 * This mapper is used for conversion between Task and TaskInfo.
 *
 * @author sudip
 * @since 2021-07-07
 */
public class TaskMapper {

    private TaskMapper() {
    }

    /**
     * Convert Task to TaskInfo.
     *
     * @param task task
     * @param processDefinitionKey
     * @param processDefinitionVersion
     * @return TaskInfo converted from Task
     */
    public static TaskInfo convertTaskToTaskInfo(
            Task task,
            String processDefinitionKey,
            Integer processDefinitionVersion) {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setProcessDefinitionKey(processDefinitionKey);
        taskInfo.setProcessDefinitionVersion(processDefinitionVersion);
        taskInfo.setAssignee(task.getAssignee());
        taskInfo.setName(task.getName());
        taskInfo.setId(task.getId());
        taskInfo.setClaimTime(task.getClaimTime());
        taskInfo.setCreateTime(task.getCreateTime());
        taskInfo.setDueDate(task.getDueDate());
        if (task.getAppVersion() != null) {
            taskInfo.setAppVersion(task.getAppVersion().toString());
        }
        taskInfo.setBusinessKey(task.getBusinessKey());
        taskInfo.setCategory(task.getCategory());
        taskInfo.setDescription(task.getDescription());
        taskInfo.setExecutionId(task.getExecutionId());
        taskInfo.setFormKey(task.getFormKey());
        taskInfo.setOwner(task.getOwner());
        taskInfo.setParentTaskId(task.getParentTaskId());
        taskInfo.setPriority(task.getPriority());
        taskInfo.setProcessDefinitionId(task.getProcessDefinitionId());
        taskInfo.setProcessInstanceId(task.getProcessInstanceId());
        taskInfo.setProcessVariables(task.getProcessVariables());
        taskInfo.setTaskDefinitionKey(task.getTaskDefinitionKey());
        taskInfo.setTaskLocalVariables(task.getTaskLocalVariables());
        taskInfo.setTenantId(task.getTenantId());
        taskInfo.setDelegationState(task.getDelegationState());
        return taskInfo;
    }

    /**
     * Convert org.activiti.api.task.model.Task to TaskInfo.
     *
     * @param task org.activiti.api.task.model.Task
     * @param processDefinitionKey
     * @return TaskInfo converted from Task
     */
    public static TaskInfo convertTaskToTaskInfo(
            org.activiti.api.task.model.Task task,
            String processDefinitionKey,
            Integer processDefinitionVersion) {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setProcessDefinitionKey(processDefinitionKey);
        taskInfo.setProcessDefinitionVersion(processDefinitionVersion);
        taskInfo.setAssignee(task.getAssignee());
        taskInfo.setName(task.getName());
        taskInfo.setId(task.getId());
        taskInfo.setDueDate(task.getDueDate());
        taskInfo.setAppVersion(task.getAppVersion());
        taskInfo.setBusinessKey(task.getBusinessKey());
        taskInfo.setDescription(task.getDescription());
        taskInfo.setFormKey(task.getFormKey());
        taskInfo.setOwner(task.getOwner());
        taskInfo.setParentTaskId(task.getParentTaskId());
        taskInfo.setPriority(task.getPriority());
        taskInfo.setProcessDefinitionId(task.getProcessDefinitionId());
        taskInfo.setProcessInstanceId(task.getProcessInstanceId());
        taskInfo.setTaskDefinitionKey(task.getTaskDefinitionKey());
        return taskInfo;
    }

    /**
     * Convert HistoricTaskInstance to TaskInfo.
     *
     * @param task HistoricTaskInstance
     * @param processDefinitionKey
     * @param processDefinitionVersion
     * @return TaskInfo converted from HistoricTaskInstance
     */
    public static TaskInfo convertHistoricTaskInstanceToTaskInfo(HistoricTaskInstance task, String processDefinitionKey, Integer processDefinitionVersion) {

        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setProcessDefinitionKey(processDefinitionKey);
        taskInfo.setProcessDefinitionVersion(processDefinitionVersion);
        taskInfo.setAssignee(task.getAssignee());
        taskInfo.setName(task.getName());
        taskInfo.setId(task.getId());
        taskInfo.setClaimTime(task.getClaimTime());
        taskInfo.setCreateTime(task.getCreateTime());
        taskInfo.setDueDate(task.getDueDate());
        taskInfo.setStartTime(task.getStartTime());
        taskInfo.setEndTime(task.getEndTime());
        taskInfo.setBusinessKey(task.getBusinessKey());
        taskInfo.setCategory(task.getCategory());
        taskInfo.setDescription(task.getDescription());
        taskInfo.setExecutionId(task.getExecutionId());
        taskInfo.setFormKey(task.getFormKey());
        taskInfo.setOwner(task.getOwner());
        taskInfo.setParentTaskId(task.getParentTaskId());
        taskInfo.setPriority(task.getPriority());
        taskInfo.setProcessDefinitionId(task.getProcessDefinitionId());
        taskInfo.setProcessInstanceId(task.getProcessInstanceId());
        taskInfo.setProcessVariables(task.getProcessVariables());
        taskInfo.setTaskDefinitionKey(task.getTaskDefinitionKey());
        taskInfo.setTaskLocalVariables(task.getTaskLocalVariables());
        taskInfo.setTenantId(task.getTenantId());
        return taskInfo;
    }

}
