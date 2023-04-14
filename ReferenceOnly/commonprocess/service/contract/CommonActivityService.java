package com.egovja.tatransform.processmanagement.commonprocess.service.contract;

import com.egovja.tatransform.processmanagement.processdeployment.dto.info.TaProcessDefinitionInfo;
import com.egovja.tatransform.processmanagement.commonprocess.dto.info.ProcessInfo;
import com.egovja.tatransform.processmanagement.commonprocess.dto.info.TaskInfo;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ContextInfo;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ValidationResultInfo;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.DataValidationErrorException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.DoesNotExistException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.InvalidParameterException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.MissingParameterException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.OperationFailedException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.PermissionDeniedException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.VersionMismatchException;
import com.egovja.tatransform.processmanagement.commonprocess.filter.CommonProcessSearchFilter;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommonActivityService {

    /**
     * Unclaim task by given id.
     *
     * @param taskId
     * @param contextInfo information containing the principalId and locale
     * information about the caller of service operation
     * @return TaskInfo
     * @throws DoesNotExistException
     */
    public TaskInfo unclaimTask(
            String taskId,
            ContextInfo contextInfo)
            throws DoesNotExistException;

    /**
     * search userTask.
     *
     * @param pageable
     * @param contextInfo information containing the principalId and locale
     * information about the caller of service operation
     * @return TaskInfo Page
     */
    public Page<TaskInfo> userTask(
            Pageable pageable,
            ContextInfo contextInfo);

    /**
     * Get Process By ProcessId.
     *
     * @param processId
     * @param contextInfo information containing the principalId and locale
     * information about the caller of service operation
     * @return ProcessInfo
     * @throws DoesNotExistException
     */
    public ProcessInfo getProcessByProcessId(
            String processId,
            ContextInfo contextInfo)
            throws DoesNotExistException;

    /**
     * Get Task By TaskId.
     *
     * @param taskId
     * @param contextInfo information containing the principalId and locale
     * information about the caller of service operation
     * @return TaskInfo
     * @throws DoesNotExistException
     */
    public TaskInfo getTaskByTaskId(
            String taskId,
            ContextInfo contextInfo)
            throws DoesNotExistException;

    /**
     * Get Deployed Custom Process.
     *
     * @param contextInfo information containing the principalId and locale
     * information about the caller of service operation
     * @return TaProcessDefinitionInfo List
     * @throws InvalidParameterException signatoryInfo or contextInfo is not
     * valid
     * @throws MissingParameterException signatoryInfo or contextInfo is missing
     * or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<TaProcessDefinitionInfo> getDeployedCustomProcess(
            ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException;

    /**
     * Search Task by given data.
     *
     * @param searchFilter
     * @param pageable
     * @param contextInfo information containing the principalId and locale
     * information about the caller of service operation
     * @return TaskInfo Page
     * @throws InvalidParameterException signatoryInfo or contextInfo is not
     * valid
     */
    public Page<TaskInfo> searchTask(
            CommonProcessSearchFilter searchFilter,
            Pageable pageable,
            ContextInfo contextInfo)
            throws InvalidParameterException;

    /**
     * Search Process By ApplicationId.
     *
     * @param applicationId
     * @param contextInfo information containing the principalId and locale
     * information about the caller of service operation
     * @return ProcessInfo List
     * @throws InvalidParameterException signatoryInfo or contextInfo is not
     * valid
     */
    public List<ProcessInfo> searchProcess(
            String applicationId,
            ContextInfo contextInfo)
            throws InvalidParameterException;

    /**
     * Search Advance Task.
     *
     * @param applicationIds
     * @param group
     * @param taskSearchType
     * @param contextInfo information containing the principalId and locale
     * information about the caller of service operation
     * @return TaskInfo List
     */
    public List<TaskInfo> searchAdvanceTask(
            List<String> applicationIds,
            String group,
            String taskSearchType,
            ContextInfo contextInfo);

    /**
     * Claim Task.
     *
     * @param taskId
     * @param contextInfo information containing the principalId and locale
     * information about the caller of service operation
     * @return TaskInfo
     * @throws InvalidParameterException signatoryInfo or contextInfo is not
     * valid
     * @throws MissingParameterException signatoryInfo or contextInfo is missing
     * or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     * @throws DoesNotExistException
     */
    public TaskInfo claimTask(
            String taskId,
            ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            PermissionDeniedException,
            OperationFailedException,
            MissingParameterException;

    /**
     * Complete Task.
     *
     * @param taskvariables
     * @param taskId
     * @param baseUrl
     * @param contextInfo information containing the principalId and locale
     * information about the caller of service operation
     * @return TaskInfo
     * @throws DataValidationErrorException supplied data is invalid
     * @throws DoesNotExistException
     * @throws InvalidParameterException signatoryInfo or contextInfo is not
     * valid
     * @throws MissingParameterException signatoryInfo or contextInfo is missing
     * or null
     * @throws OperationFailedException unable to complete request
     * @throws VersionMismatchException
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public TaskInfo completeTask(
            Map<String, Object> taskvariables,
            String taskId,
            String baseUrl,
            ContextInfo contextInfo)
            throws PermissionDeniedException,
            DoesNotExistException,
            OperationFailedException,
            MissingParameterException,
            InvalidParameterException,
            VersionMismatchException,
            DataValidationErrorException;

    /**
     * Validate Complete Task.
     *
     * @param taskvariables
     * @param taskId
     * @param baseUrl
     * @param contextInfo information containing the principalId and locale
     * information about the caller of service operation
     * @return ValidationResultInfo List
     * @throws DataValidationErrorException supplied data is invalid
     * @throws InvalidParameterException signatoryInfo or contextInfo is not
     * valid
     * @throws MissingParameterException signatoryInfo or contextInfo is missing
     * or null
     * @throws OperationFailedException unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     * @throws VersionMismatchException
     * @throws DoesNotExistException
     */
    public List<ValidationResultInfo> validateCompleteTask(
            Map<String, Object> taskvariables,
            String taskId,
            String baseUrl,
            ContextInfo contextInfo)
            throws PermissionDeniedException,
            DoesNotExistException,
            OperationFailedException,
            MissingParameterException,
            InvalidParameterException,
            VersionMismatchException,
            DataValidationErrorException;

}
