package com.egovja.tatransform.processmanagement.commonprocess.dto.infc;

import java.util.Date;
import java.util.Map;

public interface Process {

    public String getId();

    public String getName();

    public String getDescription();

    public String getStartUserId();

    public String getActivityId();

    public String getBusinessKey();

    public String getDeploymentId();

    public String getLocalizedDescription();

    public String getLocalizedName();

    public String getParentId();

    public String getParentProcessInstanceId();

    public String getProcessDefinitionId();

    public String getProcessDefinitionKey();

    public String getProcessDefinitionName();

    public String getRootProcessInstanceId();

    public String getSuperExecutionId();

    public String getTenantId();

    public Integer getAppVersion();

    public Integer getProcessDefinitionVersion();

    public Map<String, Object> getProcessVariables();

    public Date getStartTime();
}
