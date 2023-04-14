/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.processmanagement.commonprocess.mapper;

import com.egovja.tatransform.processmanagement.commonprocess.dto.info.ProcessInfo;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * This mapper is used for conversion between Process and ProcessInfo.
 *
 * @author sudip
 * @since 2021-07-07
 */
public class ProcessMapper {

    private ProcessMapper() {
    }

    /**
     * Convert Process to ProcessInfo.
     *
     * @param process process
     * @return ProcessInfo converted from Process
     */
    public static ProcessInfo convertProcessToProcessInfo(ProcessInstance process) {

        ProcessInfo processInfo = new ProcessInfo();
        processInfo.setId(process.getId());
        processInfo.setName(process.getProcessDefinitionKey());
        processInfo.setDescription(process.getDescription());
        processInfo.setStartUserId(process.getStartUserId());
        processInfo.setActivityId(process.getActivityId());
        processInfo.setBusinessKey(process.getBusinessKey());
        processInfo.setDeploymentId(process.getDeploymentId());
        processInfo.setLocalizedDescription(process.getLocalizedDescription());
        processInfo.setLocalizedName(process.getLocalizedName());
        processInfo.setParentId(process.getParentId());
        processInfo.setParentProcessInstanceId(process.getParentProcessInstanceId());
        processInfo.setProcessDefinitionId(process.getProcessDefinitionId());
        processInfo.setProcessDefinitionKey(process.getProcessDefinitionKey());
        processInfo.setProcessDefinitionName(process.getProcessDefinitionName());
        processInfo.setRootProcessInstanceId(process.getRootProcessInstanceId());
        processInfo.setSuperExecutionId(process.getSuperExecutionId());
        processInfo.setTenantId(process.getTenantId());
        processInfo.setAppVersion(process.getAppVersion());
        processInfo.setProcessDefinitionVersion(process.getProcessDefinitionVersion());
        processInfo.setProcessVariables(process.getProcessVariables());
        processInfo.setStartTime(process.getStartTime());
        return processInfo;
    }

}
