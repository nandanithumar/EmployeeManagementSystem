/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egovja.tatransform.processmanagement.commonprocess.filter;

import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;

/**
 * SearchFilter for CommonProcess.
 *
 * @author sudip
 * @since 2021-12-15
 *
 */
public class CommonProcessSearchFilter {

    @ApiParam(
            value = "processInstanceId of the common process"
    )
    private String processInstanceId;
    @ApiParam(
            value = "applicationTypeId of the common process"
    )
    private String applicationTypeId;

    @ApiParam(
            value = "trn of the common process"
    )
    private String trn;
    @ApiParam(
            value = "licenceProcess of the common process"
    )
    private Boolean licenceProcess;
    @ApiParam(
            value = "stateId of the common process"
    )
    private String stateId;
    @ApiParam(
            value = "applicationId of the common process"
    )
    private String applicationId;
    @ApiParam(
            value = "group of the common process"
    )
    private String group;
    @ApiParam(
            value = "processDefinitionKey of the common process"
    )
    private String processDefinitionKey;
    @ApiParam(
            value = "taskDefinitionKey of the common process"
    )
    private String taskDefinitionKey;
    @ApiParam(
            value = "processId of the common process"
    )
    private String processId;
    @ApiParam(
            value = "onlyUnAssigned of the common process"
    )
    private boolean onlyUnAssigned;
    @ApiParam(
            value = "taskSearchType of the common process"
    )
    private String taskSearchType;

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getApplicationTypeId() {
        return applicationTypeId;
    }

    public void setApplicationTypeId(String applicationTypeId) {
        this.applicationTypeId = applicationTypeId;
    }

    public String getTrn() {
        return trn;
    }

    public void setTrn(String trn) {
        this.trn = trn;
    }

    public Boolean getLicenceProcess() {
        return licenceProcess;
    }

    public void setLicenceProcess(Boolean licenceProcess) {
        this.licenceProcess = licenceProcess;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public boolean isOnlyUnAssigned() {
        return onlyUnAssigned;
    }

    public void setOnlyUnAssigned(boolean onlyUnAssigned) {
        this.onlyUnAssigned = onlyUnAssigned;
    }

    public String getTaskSearchType() {
        if (StringUtils.isEmpty(taskSearchType)) {
            taskSearchType = "unfinished";
        }
        return taskSearchType;
    }

    public void setTaskSearchType(String taskSearchType) {
        this.taskSearchType = taskSearchType;
    }

}
