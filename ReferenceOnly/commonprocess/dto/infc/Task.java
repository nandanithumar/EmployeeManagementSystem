package com.egovja.tatransform.processmanagement.commonprocess.dto.infc;

import java.util.Date;
import java.util.Map;
import org.activiti.engine.task.DelegationState;

public interface Task {

    public String getBusinessKey();

    public String getCategory();

    public String getDescription();

    public String getExecutionId();

    public String getFormKey();

    public String getOwner();

    public String getParentTaskId();

    public String getProcessDefinitionId();

    public String getProcessInstanceId();

    public String getTaskDefinitionKey();

    public String getTenantId();

    public Date getEndTime();

    public String getName();

    public String getId();

    public String getAssignee();

    public Date getClaimTime();

    public Date getCreateTime();

    public Date getDueDate();

    public String getAppVersion();

    public Integer getPriority();

    public Map<String, Object> getProcessVariables();

    public Map<String, Object> getTaskLocalVariables();

    public DelegationState getDelegationState();

    public Date getStartTime();

    public String getProcessDefinitionKey();

    public Integer getProcessDefinitionVersion();
}
