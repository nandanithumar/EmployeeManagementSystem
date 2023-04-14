package com.egovja.tatransform.processmanagement.commonprocess.dto.info;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import com.egovja.tatransform.processmanagement.commonprocess.dto.infc.Process;

public class ProcessInfo implements Process, Serializable {

    String id;
    String name;
    String description;
    String startUserId;
    String activityId;
    String businessKey;
    String deploymentId;
    String localizedDescription;
    String localizedName;
    String parentId;
    String parentProcessInstanceId;
    String processDefinitionId;
    String processDefinitionKey;
    String processDefinitionName;
    String rootProcessInstanceId;
    String superExecutionId;
    String tenantId;
    Integer appVersion;
    Integer processDefinitionVersion;
    transient Map<String, Object> processVariables;
    Date startTime;

    public ProcessInfo() {
    }

    public ProcessInfo(Process process) {
        if (process != null) {
            this.activityId = process.getActivityId();
            this.appVersion = process.getAppVersion();
            this.businessKey = process.getBusinessKey();
            this.deploymentId = process.getDeploymentId();
            this.description = process.getDescription();
            this.id = process.getId();
            this.localizedDescription = process.getLocalizedDescription();
            this.localizedName = process.getLocalizedName();
            this.name = process.getName();
            this.parentId = process.getParentId();
            this.parentProcessInstanceId = process.getProcessDefinitionId();
            this.processDefinitionId = process.getProcessDefinitionKey();
            this.processDefinitionKey = process.getProcessDefinitionKey();
            this.processDefinitionName = process.getProcessDefinitionName();
            this.processDefinitionVersion = process.getAppVersion();
            this.processVariables = process.getProcessVariables();
            this.rootProcessInstanceId = process.getRootProcessInstanceId();
            this.startTime = new Date(process.getStartTime().getTime());
            this.startUserId = process.getStartUserId();
            this.superExecutionId = process.getSuperExecutionId();
            this.tenantId = process.getTenantId();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getStartUserId() {
        return startUserId;
    }

    public void setStartUserId(String startUserId) {
        this.startUserId = startUserId;
    }

    @Override
    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Override
    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    @Override
    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    @Override
    public String getLocalizedDescription() {
        return localizedDescription;
    }

    public void setLocalizedDescription(String localizedDescription) {
        this.localizedDescription = localizedDescription;
    }

    @Override
    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String getParentProcessInstanceId() {
        return parentProcessInstanceId;
    }

    public void setParentProcessInstanceId(String parentProcessInstanceId) {
        this.parentProcessInstanceId = parentProcessInstanceId;
    }

    @Override
    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    @Override
    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    @Override
    public String getProcessDefinitionName() {
        return processDefinitionName;
    }

    public void setProcessDefinitionName(String processDefinitionName) {
        this.processDefinitionName = processDefinitionName;
    }

    @Override
    public String getRootProcessInstanceId() {
        return rootProcessInstanceId;
    }

    public void setRootProcessInstanceId(String rootProcessInstanceId) {
        this.rootProcessInstanceId = rootProcessInstanceId;
    }

    @Override
    public String getSuperExecutionId() {
        return superExecutionId;
    }

    public void setSuperExecutionId(String superExecutionId) {
        this.superExecutionId = superExecutionId;
    }

    @Override
    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public Integer getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(Integer appVersion) {
        this.appVersion = appVersion;
    }

    @Override
    public Integer getProcessDefinitionVersion() {
        return processDefinitionVersion;
    }

    public void setProcessDefinitionVersion(Integer processDefinitionVersion) {
        this.processDefinitionVersion = processDefinitionVersion;
    }

    @Override
    public Map<String, Object> getProcessVariables() {
        return processVariables;
    }

    public void setProcessVariables(Map<String, Object> processVariables) {
        this.processVariables = processVariables;
    }

    @Override
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "ProcessInfo{"
                + "id=" + id
                + ", name=" + name
                + ", description=" + description
                + ", startUserId=" + startUserId
                + ", activityId=" + activityId
                + ", businessKey=" + businessKey
                + ", deploymentId=" + deploymentId
                + ", localizedDescription=" + localizedDescription
                + ", localizedName=" + localizedName
                + ", parentId=" + parentId
                + ", parentProcessInstanceId=" + parentProcessInstanceId
                + ", processDefinitionId=" + processDefinitionId
                + ", processDefinitionKey=" + processDefinitionKey
                + ", processDefinitionName=" + processDefinitionName
                + ", rootProcessInstanceId=" + rootProcessInstanceId
                + ", superExecutionId=" + superExecutionId
                + ", tenantId=" + tenantId
                + ", appVersion=" + appVersion
                + ", processDefinitionVersion=" + processDefinitionVersion
                + ", processVariables=" + processVariables
                + ", startTime=" + startTime
                + '}';
    }

}
