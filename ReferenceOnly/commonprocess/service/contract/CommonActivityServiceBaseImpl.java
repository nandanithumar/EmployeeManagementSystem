package com.egovja.tatransform.processmanagement.commonprocess.service.contract;

import com.egovja.tatransform.licencingmanagement.common.dto.info.ContextInfo;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ValidationResultInfo;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.DataValidationErrorException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.DoesNotExistException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.InvalidParameterException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.MissingParameterException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.OperationFailedException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.PermissionDeniedException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.VersionMismatchException;
import com.egovja.tatransform.processmanagement.commonprocess.dto.info.ProcessInfo;
import com.egovja.tatransform.processmanagement.commonprocess.dto.info.TaskInfo;
import com.egovja.tatransform.processmanagement.commonprocess.filter.CommonProcessSearchFilter;
import com.egovja.tatransform.processmanagement.processdeployment.dto.info.TaProcessDefinitionInfo;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This is Base implementation for CommonActivityService Service.
 *
 * @author sudip
 * @since 2021-12-13
 */
public class CommonActivityServiceBaseImpl implements CommonActivityService {

    CommonActivityService commonActivityService;

    public void setNextDecorator(CommonActivityService commonActivityService) {
        this.commonActivityService = commonActivityService;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public TaskInfo unclaimTask(String taskId, ContextInfo contextInfo) throws DoesNotExistException {
        return commonActivityService.unclaimTask(taskId, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Page<TaskInfo> userTask(Pageable pageable, ContextInfo contextInfo) {
        return commonActivityService.userTask(pageable, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public ProcessInfo getProcessByProcessId(String processId, ContextInfo contextInfo) throws DoesNotExistException {
        return commonActivityService.getProcessByProcessId(processId, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public TaskInfo getTaskByTaskId(String taskId, ContextInfo contextInfo) throws DoesNotExistException {
        return commonActivityService.getTaskByTaskId(taskId, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<TaProcessDefinitionInfo> getDeployedCustomProcess(ContextInfo contextInfo) throws OperationFailedException, MissingParameterException, PermissionDeniedException, InvalidParameterException {
        return commonActivityService.getDeployedCustomProcess(contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Page<TaskInfo> searchTask(CommonProcessSearchFilter searchFilter, Pageable pageable, ContextInfo contextInfo) throws InvalidParameterException {
        return commonActivityService.searchTask(searchFilter, pageable, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<ProcessInfo> searchProcess(String applicationId, ContextInfo contextInfo) throws InvalidParameterException {
        return commonActivityService.searchProcess(applicationId, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<TaskInfo> searchAdvanceTask(List<String> applicationIds, String group, String taskSearchType, ContextInfo contextInfo) {
        return commonActivityService.searchAdvanceTask(applicationIds, group, taskSearchType, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public TaskInfo claimTask(String taskId, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, PermissionDeniedException, OperationFailedException, MissingParameterException {
        return commonActivityService.claimTask(taskId, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public TaskInfo completeTask(Map<String, Object> taskvariables, String taskId, String baseUrl, ContextInfo contextInfo) throws PermissionDeniedException, DoesNotExistException, OperationFailedException, MissingParameterException, InvalidParameterException, VersionMismatchException, DataValidationErrorException {
        return commonActivityService.completeTask(taskvariables, taskId, baseUrl, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<ValidationResultInfo> validateCompleteTask(Map<String, Object> taskvariables, String taskId, String baseUrl, ContextInfo contextInfo) throws PermissionDeniedException, DoesNotExistException, OperationFailedException, MissingParameterException, InvalidParameterException, VersionMismatchException, DataValidationErrorException {
        return commonActivityService.validateCompleteTask(taskvariables, taskId, baseUrl, contextInfo);
    }

}
