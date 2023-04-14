package com.egovja.tatransform.processmanagement.commonprocess.service.impl.rest;

import com.codahale.metrics.annotation.Timed;
import com.egovja.tatransform.processmanagement.processdeployment.dto.info.TaProcessDefinitionInfo;
import com.egovja.tatransform.processmanagement.commonprocess.dto.info.ProcessInfo;
import com.egovja.tatransform.processmanagement.commonprocess.dto.info.TaskInfo;
import com.egovja.tatransform.processmanagement.commonprocess.service.impl.standard.CommonActivityServiceImpl;
import com.egovja.tatransform.licencingmanagement.common.constant.Constant;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ContextInfo;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ValidationResultInfo;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.DataValidationErrorException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.DoesNotExistException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.OperationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.egovja.tatransform.processmanagement.commonprocess.service.contract.CommonActivityService;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.InvalidParameterException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.MissingParameterException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.PermissionDeniedException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.VersionMismatchException;
import com.egovja.tatransform.processmanagement.commonprocess.filter.CommonProcessSearchFilter;
import com.egovja.tatransform.processmanagement.commonprocess.service.contract.CommonActivityServiceBaseImpl;
import io.astefanutti.metrics.aspectj.Metrics;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

@RestController
@Metrics(registry = "CommonActivityServiceRestController")
@RequestMapping(Constant.CONTEXT_PATH + "/process")
@Api(value = "REST API for process", tags = {"Process API"})
public class CommonActivityServiceRestController extends CommonActivityServiceBaseImpl
        implements CommonActivityService {

    @Autowired
    CommonActivityServiceImpl commomActivityService;

    @PostConstruct
    public void setNextDecorator() {
        super.setNextDecorator(commomActivityService);
    }

    @Override
    @Timed(name = "unclaimTask")
    @ApiOperation(value = "Unclaim task", response = TaskInfo.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully unclaimed task"),
        @ApiResponse(code = 401, message = "You are not authorized to create the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    @GetMapping("/task/unclaim-task")
    public TaskInfo unclaimTask(
            @RequestParam(name = "taskId", required = true) String taskId,
            @RequestAttribute(name = "contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException {
        return commomActivityService.unclaimTask(taskId, contextInfo);
    }

    @Override
    @Timed(name = "userTask")
    @ApiOperation(value = "View principal's assigned task", response = Page.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved principal's task"),
        @ApiResponse(code = 401, message = "You are not authorized to create the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    @GetMapping("/task/my-task")
    public Page<TaskInfo> userTask(
            Pageable pageable,
            @RequestAttribute(name = "contextInfo") ContextInfo contextInfo) {
        return commomActivityService.userTask(pageable, contextInfo);
    }

    @Override
    @Timed(name = "getProcessByProcessId")
    @ApiOperation(value = "View available process with supplied id", response = ProcessInfo.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrived process"),
        @ApiResponse(code = 401, message = "You are not authorized to create the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    @GetMapping("/{processId}")
    public ProcessInfo getProcessByProcessId(
            @PathVariable String processId,
            @RequestAttribute(name = "contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException {
        return commomActivityService.getProcessByProcessId(processId, contextInfo);
    }

    @Override
    @Timed(name = "getTaskByTaskId")
    @ApiOperation(value = "View available task with supplied id", response = TaskInfo.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrived task"),
        @ApiResponse(code = 401, message = "You are not authorized to create the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    @GetMapping("/task/{taskId}")
    public TaskInfo getTaskByTaskId(
            @PathVariable String taskId,
            @RequestAttribute(name = "contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException {
        return commomActivityService.getTaskByTaskId(taskId, contextInfo);
    }

    @Override
    @Timed(name = "getDeployedCustomProcess")
    @ApiOperation(value = "View available deployed process", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrived deployed process"),
        @ApiResponse(code = 401, message = "You are not authorized to create the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    @GetMapping("/deployed-process")
    public List<TaProcessDefinitionInfo> getDeployedCustomProcess(
            ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException {
        return commomActivityService.getDeployedCustomProcess(contextInfo);
    }

    @Override
    @Timed(name = "searchTask")
    @ApiOperation(value = "View available task with supplied data", response = Page.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrived task with supplied data"),
        @ApiResponse(code = 401, message = "You are not authorized to create the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    @GetMapping("/task")
    public Page<TaskInfo> searchTask(
            CommonProcessSearchFilter searchFilter,
            Pageable pageable,
            @RequestAttribute(name = "contextInfo") ContextInfo contextInfo)
            throws InvalidParameterException {
        return commomActivityService.searchTask(
                searchFilter,
                pageable,
                contextInfo);
    }

    @Override
    @Timed(name = "searchAdvanceTask")
    @ApiOperation(value = "View available advance task with supplied data", response = Page.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrived task with supplied data"),
        @ApiResponse(code = 401, message = "You are not authorized to create the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    @GetMapping("/task/advance")
    public List<TaskInfo> searchAdvanceTask(
            @RequestParam(name = "applicationId", required = false) List<String> applicationIds,
            @RequestParam(name = "group", required = false) String group,
            @RequestParam(name = "taskSearchType", defaultValue = "unfinished") String taskSearchType,
            @RequestAttribute(name = "contextInfo") ContextInfo contextInfo) {
        return commomActivityService.searchAdvanceTask(
                applicationIds,
                group,
                taskSearchType,
                contextInfo);
    }

    @Override
    @Timed(name = "claimTask")
    @ApiOperation(value = "Claim task", response = TaskInfo.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully claimed task"),
        @ApiResponse(code = 401, message = "You are not authorized to create the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    @GetMapping("/task/claim-task")
    public TaskInfo claimTask(
            @RequestParam(name = "taskId", required = true) String taskId,
            @RequestAttribute(name = "contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            PermissionDeniedException,
            OperationFailedException,
            MissingParameterException {
        return commomActivityService.claimTask(taskId, contextInfo);
    }

    @Override
    @Timed(name = "completeTask")
    @ApiOperation(value = "Complete task", response = TaskInfo.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully completed task"),
        @ApiResponse(code = 401, message = "You are not authorized to create the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    @PostMapping("/task/complete-task")
    public TaskInfo completeTask(
            @RequestBody Map<String, Object> taskvariables,
            @RequestParam(name = "taskId", required = true) String taskId,
            @RequestParam(name = "baseUrl", required = true) String baseUrl,
            @RequestAttribute(name = "contextInfo") ContextInfo contextInfo)
            throws PermissionDeniedException,
            DoesNotExistException,
            OperationFailedException,
            MissingParameterException,
            InvalidParameterException,
            VersionMismatchException,
            DataValidationErrorException {
        return commomActivityService
                .completeTask(taskvariables, taskId, baseUrl, contextInfo);
    }

    @Override
    @Timed(name = "validateCompleteTask")
    @ApiOperation(value = "Validate complete task", response = TaskInfo.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrived list of validation results"),
        @ApiResponse(code = 401, message = "You are not authorized to create the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    @PostMapping("/task/complete-task/validate")
    public List<ValidationResultInfo> validateCompleteTask(
            @RequestBody Map<String, Object> taskvariables,
            @RequestParam(name = "taskId", required = true) String taskId,
            @RequestParam(name = "baseUrl", required = true) String baseUrl,
            @RequestAttribute(name = "contextInfo") ContextInfo contextInfo)
            throws PermissionDeniedException,
            DoesNotExistException,
            OperationFailedException,
            MissingParameterException,
            InvalidParameterException,
            VersionMismatchException,
            DataValidationErrorException {
        return commomActivityService
                .validateCompleteTask(taskvariables, taskId, baseUrl, contextInfo);
    }

    @Override
    @Timed(name = "searchProcess")
    @ApiOperation(value = "View available process with supplied data", response = Page.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrived process with supplied data"),
        @ApiResponse(code = 401, message = "You are not authorized to create the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    @GetMapping("")
    public List<ProcessInfo> searchProcess(
            @RequestParam(name = "applicationId", required = false) String applicationId,
            @RequestAttribute(name = "contextInfo") ContextInfo contextInfo)
            throws InvalidParameterException {
        return commomActivityService.searchProcess(
                applicationId,
                contextInfo);
    }
}
