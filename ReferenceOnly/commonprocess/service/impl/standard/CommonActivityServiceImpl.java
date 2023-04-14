package com.egovja.tatransform.processmanagement.commonprocess.service.impl.standard;

import com.codahale.metrics.annotation.Timed;
import com.egovja.tatransform.licenceapplicationmanagement.delivery.dto.info.DeliveryInfo;
import com.egovja.tatransform.licenceapplicationmanagement.delivery.service.impl.validation.DeliveryServiceValidationImpl;
import com.egovja.tatransform.licenceapplicationmanagement.letter.dto.info.LetterInfo;
import com.egovja.tatransform.licenceapplicationmanagement.letter.service.impl.validation.LetterServiceValidationImpl;
import com.egovja.tatransform.licenceapplicationmanagement.licencetype.dto.info.LicenceTypeDocumentTypeInfo;
import com.egovja.tatransform.licenceapplicationmanagement.licencetype.dto.info.LicenceTypeInfo;
import com.egovja.tatransform.licenceapplicationmanagement.licencetype.service.impl.standard.LicenceTypeServiceImpl;
import com.egovja.tatransform.licenceapplicationmanagement.licenceapplication.dto.info.LicenceApplicationInfo;
import com.egovja.tatransform.licenceapplicationmanagement.licenceapplication.service.impl.validation.LicenceApplicationServiceValidationImpl;
import static com.egovja.tatransform.licenceapplicationmanagement.payment.service.impl.validation.PaymentServiceValidationImpl.MUST_PROVIDED;
import com.egovja.tatransform.licenceapplicationmanagement.sticker.dto.info.StickerInfo;
import com.egovja.tatransform.licenceapplicationmanagement.sticker.service.impl.validation.StickerServiceValidationImpl;
import com.egovja.tatransform.licencingmanagement.common.constant.Constant;
import com.egovja.tatransform.licencingmanagement.common.constant.ErrorLevel;
import com.egovja.tatransform.licencingmanagement.common.constant.SearchType;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ContextInfo;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ValidationResultInfo;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.*;
import com.egovja.tatransform.licencingmanagement.common.utils.ValidationUtils;
import com.egovja.tatransform.licencingmanagement.document.constant.DocumentServiceConstant;
import com.egovja.tatransform.licencingmanagement.document.dto.info.LaDocumentInfo;
import com.egovja.tatransform.licencingmanagement.document.service.impl.validation.LicenceApplicationDocumentServiceValidationImpl;
import com.egovja.tatransform.licencingmanagement.location.dto.info.OfficeInfo;
import com.egovja.tatransform.licencingmanagement.location.service.impl.standard.LocationServiceImpl;
import com.egovja.tatransform.licencingmanagement.master.service.impl.standard.TypeServiceImpl;
import com.egovja.tatransform.processmanagement.commonprocess.constant.TaskSearchType;
import com.egovja.tatransform.processmanagement.commonprocess.dto.info.ProcessInfo;
import com.egovja.tatransform.processmanagement.commonprocess.dto.info.TaskInfo;
import com.egovja.tatransform.processmanagement.commonprocess.filter.CommonProcessSearchFilter;
import com.egovja.tatransform.processmanagement.commonprocess.mapper.ProcessMapper;
import com.egovja.tatransform.processmanagement.commonprocess.mapper.TaskMapper;
import com.egovja.tatransform.processmanagement.commonprocess.service.contract.CommonActivityService;
import com.egovja.tatransform.processmanagement.constant.ProcessVariableType;
import com.egovja.tatransform.processmanagement.constant.ValidationType;
import com.egovja.tatransform.processmanagement.processdeployment.constant.TaProcessDefinitionServiceConstant;
import com.egovja.tatransform.processmanagement.processdeployment.dto.info.TaProcessDefinitionInfo;
import com.egovja.tatransform.processmanagement.processdeployment.dto.info.TaTaskDefinitionInfo;
import com.egovja.tatransform.processmanagement.processdeployment.filter.TaProcessDefinitionSearchFilter;
import com.egovja.tatransform.processmanagement.processdeployment.service.impl.validation.TaProcessDefinitionServiceValidationImpl;
import com.egovja.tatransform.usermanagement.onlineuser.service.impl.standard.OnlineUserServiceImpl;
import com.egovja.tatransform.usermanagement.user.service.impl.standard.UserServiceImpl;
import io.astefanutti.metrics.aspectj.Metrics;
import org.activiti.api.runtime.shared.NotFoundException;
import org.activiti.api.task.model.builders.CompleteTaskPayloadBuilder;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Metrics(registry = "CommonActivityServiceImpl")
public class CommonActivityServiceImpl implements CommonActivityService {

    private static final String APPLICATION_ID = "applicationId";
    private static final String LICENCE_PROCESS = "licenceProcess";
    private static final String PLEASE_CONTACT_ADMINISTATOR = "Please contact administrator.";
    @Autowired
    TaProcessDefinitionServiceValidationImpl taProcessDefinitionService;

    @Autowired
    private LicenceTypeServiceImpl licenceTypeService;

    @Autowired
    HistoryService historyService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    TaskRuntime taskRuntime;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    OnlineUserServiceImpl onlineUserService;

    @Autowired
    private LicenceApplicationServiceValidationImpl licenceApplicationService;

    @Autowired
    private LocationServiceImpl locationService;

    @Autowired
    private LetterServiceValidationImpl letterServiceValidationImpl;

    @Autowired
    private StickerServiceValidationImpl stickerServiceValidationImpl;

    @Autowired
    private DeliveryServiceValidationImpl deliveryServiceValidationImpl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    TypeServiceImpl typeService;

    @Autowired
    private LicenceApplicationDocumentServiceValidationImpl licenceApplicationDocumentServiceValidationImpl;

    @Value("${app.context.path}")
    private String contextPath;

    @Override
    @Timed(name = "unclaimTask")
    public TaskInfo unclaimTask(
            String taskId,
            ContextInfo contextInfo)
            throws DoesNotExistException {
        taskService.unclaim(taskId);
        return getTaskByTaskId(taskId, contextInfo);
    }

    @Override
    @Timed(name = "getProcessByProcessId")
    public ProcessInfo getProcessByProcessId(
            String processId,
            ContextInfo contextInfo)
            throws DoesNotExistException {
        ProcessInstance processInstance
                = runtimeService
                        .createProcessInstanceQuery()
                        .includeProcessVariables()
                        .processInstanceId(processId)
                        .singleResult();

        if (processInstance == null) {
            throw new DoesNotExistException("Process does not exist");
        }
        return ProcessMapper.convertProcessToProcessInfo(processInstance);
    }

    @Override
    @Timed(name = "getTaskByTaskId")
    public TaskInfo getTaskByTaskId(
            String taskId,
            ContextInfo contextInfo) throws DoesNotExistException {
        Task task
                = taskService
                        .createTaskQuery()
                        .includeProcessVariables()
                        .includeTaskLocalVariables()
                        .taskId(taskId)
                        .singleResult();

        if (task == null) {
            throw new DoesNotExistException("Task does not exist.");
        }

        ProcessInstance processInstance
                = runtimeService
                        .createProcessInstanceQuery()
                        .processInstanceId(task.getProcessInstanceId())
                        .singleResult();
        return TaskMapper
                .convertTaskToTaskInfo(
                        task,
                        processInstance.getProcessDefinitionKey(),
                        processInstance.getProcessDefinitionVersion());
    }

    @Override
    @Timed(name = "userTask")
    public Page<TaskInfo> userTask(
            Pageable pageable,
            ContextInfo contextInfo) {
        Long count = taskService
                .createTaskQuery()
                .taskAssignee(contextInfo.getUsername())
                .count();
        List<TaskInfo> tasks = taskService
                .createTaskQuery()
                .includeProcessVariables()
                .includeTaskLocalVariables()
                .taskAssignee(contextInfo.getUsername())
                .listPage(pageable.getPageNumber(), pageable.getPageSize())
                .stream()
                .map(task -> {
                    ProcessInstance processInstance
                            = runtimeService
                                    .createProcessInstanceQuery()
                                    .processInstanceId(
                                            task.getProcessInstanceId())
                                    .singleResult();
                    return TaskMapper
                            .convertTaskToTaskInfo(
                                    task,
                                    processInstance
                                            .getProcessDefinitionKey(),
                                    processInstance
                                            .getProcessDefinitionVersion());
                })
                .collect(Collectors.toList());
        return new PageImpl<>(tasks,
                pageable,
                count);
    }

    @Override
    @Timed(name = "getDeployedCustomProcess")
    public List<TaProcessDefinitionInfo> getDeployedCustomProcess(
            ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException {
        TaProcessDefinitionSearchFilter searchFilter = new TaProcessDefinitionSearchFilter();
        searchFilter.setStateId(TaProcessDefinitionServiceConstant.TA_PROCESS_DEFINITION_STATE_ACTIVE);
        List<TaProcessDefinitionInfo> taProcessDefinitionInfos
                = taProcessDefinitionService.searchTaProcessDefinitions(
                        null,
                        searchFilter,
                        SearchType.CONTAINING,
                        Constant.FULL_PAGE,
                        contextInfo).getContent();
        searchFilter = new TaProcessDefinitionSearchFilter();
        searchFilter.setStateId(TaProcessDefinitionServiceConstant.TA_PROCESS_DEFINITION_STATE_INACTIVE);
        List<TaProcessDefinitionInfo> inactiveTaProcessDefinitionInfos
                = taProcessDefinitionService.searchTaProcessDefinitions(
                        null,
                        searchFilter,
                        SearchType.CONTAINING,
                        Constant.FULL_PAGE,
                        contextInfo).getContent();
        inactiveTaProcessDefinitionInfos.forEach(taProcessDefinitionInfos::add);
        return taProcessDefinitionInfos;
    }

    @Override
    @Timed(name = "claimTask")
    public TaskInfo claimTask(
            String taskId,
            ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            PermissionDeniedException,
            OperationFailedException,
            MissingParameterException {

        TaskInfo taskInfo = getTaskByTaskId(taskId, contextInfo);
        String appliationId
                = (String) taskInfo.getProcessVariables().get(APPLICATION_ID);

        Boolean licenceProcess
                = (Boolean) taskInfo.getProcessVariables().get(LICENCE_PROCESS);

        if (contextInfo.getIsSystemUser()) {

            if (StringUtils.isEmpty(contextInfo.getOfficeId())
                    && StringUtils.isEmpty(contextInfo.getRegionId())
                    && !contextInfo.getIsHigherAccess()) {
                throw new PermissionDeniedException("You are unauthorised to "
                        + "claim any applications.\nPlease contact "
                        + "administrator.");
            }

            LicenceApplicationInfo licenceApplicationInfo
                    = licenceApplicationService
                            .getLicenceApplicationById(
                                    appliationId,
                                    contextInfo);
            String officeId;
            if (Boolean.TRUE.equals(licenceProcess)) {
                officeId = licenceApplicationInfo
                        .getLicence()
                        .getOfficeId();
            } else {
                officeId = licenceApplicationInfo.getOriginOffice();
            }
            if (!StringUtils.isEmpty(contextInfo.getOfficeId())
                    && !officeId
                            .equals(contextInfo.getOfficeId())) {
                throw new PermissionDeniedException("You are unauthorised to claim other offices applications.\n"
                        + PLEASE_CONTACT_ADMINISTATOR);
            }
            if (!StringUtils.isEmpty(contextInfo.getRegionId())) {
                OfficeInfo office = locationService
                        .getOfficeById(
                                officeId,
                                contextInfo);
                if (!office.getRegionId().equals(contextInfo.getRegionId())) {
                    throw new PermissionDeniedException("You are unauthorised "
                            + "to claim other regions applications.\n"
                            + PLEASE_CONTACT_ADMINISTATOR);
                }
            }
        } else {
            LicenceApplicationInfo licenceApplicationInfo
                    = licenceApplicationService
                            .getLicenceApplicationById(
                                    appliationId,
                                    contextInfo);
            if (!licenceApplicationInfo.getAppliedFor()
                    .equals(contextInfo.getCustomerId().toString())
                    && (StringUtils.isEmpty(licenceApplicationInfo.getAppliedBy())
                    || !licenceApplicationInfo.getAppliedBy()
                            .equals(contextInfo.getCustomerId().toString()))) {
                throw new PermissionDeniedException("You are unauthorised to "
                        + "claim other customers applications.\n"
                        + PLEASE_CONTACT_ADMINISTATOR);
            }
        }

        try {
            org.activiti.api.task.model.Task task = taskRuntime.claim(
                    TaskPayloadBuilder
                            .claim()
                            .withTaskId(taskId)
                            .withAssignee(contextInfo.getUsername())
                            .build()
            );

            ProcessInstance processInstance
                    = runtimeService
                            .createProcessInstanceQuery()
                            .processInstanceId(task.getProcessInstanceId())
                            .singleResult();

            return TaskMapper
                    .convertTaskToTaskInfo(
                            task,
                            processInstance.getProcessDefinitionKey(),
                            processInstance.getProcessDefinitionVersion());
        } catch (NotFoundException e) {
            throw new PermissionDeniedException("You are unauthorized to claim this task.\n"
                    + PLEASE_CONTACT_ADMINISTATOR, e);
        }
    }

    @Override
    @Timed(name = "completeTask")
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
            DataValidationErrorException {

        this.unclaimTask(taskId, contextInfo);
        this.claimTask(taskId, contextInfo);

        List<ValidationResultInfo> validationResultInfos
                = this.validateCompleteTask(taskvariables, taskId, baseUrl, contextInfo);

        if (ValidationUtils.containsErrors(validationResultInfos, ErrorLevel.ERROR)) {
            throw new DataValidationErrorException(
                    "Error(s) occurred in the validating",
                    validationResultInfos);
        }

        Task task
                = taskService
                        .createTaskQuery()
                        .taskId(taskId)
                        .singleResult();

        ProcessInstance processInstance
                = runtimeService
                        .createProcessInstanceQuery()
                        .processInstanceId(task.getProcessInstanceId())
                        .singleResult();

        TaProcessDefinitionInfo taProcessDefinitionInfo
                = taProcessDefinitionService
                        .getTaProcessDefinitionByProcessDefinationKeyAndVersion(
                                processInstance.getProcessDefinitionKey(),
                                processInstance
                                        .getProcessDefinitionVersion()
                                        .toString(),
                                contextInfo);

        Optional<TaTaskDefinitionInfo> taTaskDefinitionInfoOptional
                = taProcessDefinitionInfo
                        .getTasks()
                        .stream()
                        .filter(taskInfo
                                -> taskInfo.getTaskDefinitionKey()
                                .equals(task.getTaskDefinitionKey())
                        ).findFirst();
        if (taTaskDefinitionInfoOptional.isEmpty()) {
            throw new DoesNotExistException("taTaskDefinitionInfo not found");
        }
        taskvariables.put("group", taTaskDefinitionInfoOptional.get().getCandidateGroups());
        taskvariables.put("assigneeFirstName", contextInfo.getFirstName());
        taskvariables.put("assigneeLastName", contextInfo.getLastName());
        taskvariables.put("approvedBy", contextInfo.getUserId());

        try {
            CompleteTaskPayloadBuilder completeTaskPayloadBuilder = new CompleteTaskPayloadBuilder();
            taskRuntime.complete(
                    completeTaskPayloadBuilder.withTaskId(taskId).withVariables(taskvariables).build()
            );
        } catch (NotFoundException e) {
            throw new PermissionDeniedException("You are unauthorized to complete this task", e);
        } catch (RuntimeException e) {
            OperationFailedException oe = new OperationFailedException(e.getMessage(), e);
            oe.setStackTrace(e.getStackTrace());
            throw oe;
        }

        HistoricTaskInstance completedTask
                = historyService.createHistoricTaskInstanceQuery()
                        .includeProcessVariables()
                        .includeTaskLocalVariables()
                        .taskId(taskId).singleResult();

        return TaskMapper
                .convertHistoricTaskInstanceToTaskInfo(
                        completedTask,
                        processInstance.getProcessDefinitionKey(),
                        processInstance.getProcessDefinitionVersion());
    }

    @Override
    @Timed(name = "validateCompleteTask")
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
            DataValidationErrorException {
        List<ValidationResultInfo> errors = new ArrayList<>();

        Task task
                = taskService
                        .createTaskQuery()
                        .taskId(taskId)
                        .includeProcessVariables()
                        .includeTaskLocalVariables()
                        .singleResult();

        if (task == null || task.getProcessInstanceId() == null) {
            String fieldName = "taskId";
            errors.add(
                    new ValidationResultInfo(fieldName,
                            ErrorLevel.ERROR,
                            "task with given id does not exist"));
            return errors;
        }

        String remarkField = "remark";
        if (taskvariables != null && taskvariables.get(remarkField) != null) {
            taskvariables.put(remarkField, ((String) taskvariables.get(remarkField)).trim());
            if (StringUtils.isEmpty(taskvariables.get(remarkField))) {
                errors.add(
                        new ValidationResultInfo(remarkField,
                                ErrorLevel.ERROR,
                                "Please enter valid remark."));
            } else if (((String) taskvariables.get(remarkField)).length() < 2) {
                errors.add(
                        new ValidationResultInfo(remarkField,
                                ErrorLevel.ERROR,
                                "Remark field must have at least two alphabets."));
            }
        }

        ProcessInstance processInstance
                = runtimeService
                        .createProcessInstanceQuery()
                        .processInstanceId(task.getProcessInstanceId())
                        .includeProcessVariables()
                        .singleResult();

        TaProcessDefinitionInfo taProcessDefinitionInfo
                = taProcessDefinitionService
                        .getTaProcessDefinitionByProcessDefinationKeyAndVersion(
                                processInstance.getProcessDefinitionKey(),
                                processInstance
                                        .getProcessDefinitionVersion()
                                        .toString(),
                                contextInfo);

        Optional<TaTaskDefinitionInfo> taTaskDefinitionInfoOptional
                = taProcessDefinitionInfo
                        .getTasks()
                        .stream()
                        .filter(taskInfo
                                -> taskInfo.getTaskDefinitionKey()
                                .equals(task.getTaskDefinitionKey())
                        ).findFirst();
        if (taTaskDefinitionInfoOptional.isEmpty()) {
            throw new DoesNotExistException("TaTaskDefinitionInfo not found");
        }
        List<String> requiredDocumentType = new ArrayList<>();
        List<String> requiredDeferredDocumentType = new ArrayList<>();
        for (String key : taTaskDefinitionInfoOptional.get()
                .getRequiredVariables().keySet()) {
            ProcessVariableType value = taTaskDefinitionInfoOptional.get()
                    .getRequiredVariables().get(key);
            String applicationId = processInstance
                    .getProcessVariables()
                    .get(APPLICATION_ID).toString();
            switch (value) {
                case VALIDATE: {
                    switch (ValidationType.valueOf(key)) {
                        case DELIVERY: {
                            DeliveryInfo deliveryInfo = new DeliveryInfo();
                            deliveryInfo.setLicenceApplicationId(applicationId);
                            errors.addAll(deliveryServiceValidationImpl.validateDelivery(Constant.GENERATE_VALIDATION, deliveryInfo, contextInfo));
                            break;
                        }
                        case LETTER: {
                            LetterInfo letterInfo = new LetterInfo();
                            letterInfo.setLicenceApplicationId(applicationId);
                            errors.addAll(letterServiceValidationImpl.validateLetter(Constant.GENERATE_VALIDATION, letterInfo, contextInfo));
                            break;
                        }
                        case STICKER: {
                            StickerInfo stickerInfo = new StickerInfo();
                            stickerInfo.setLicenceApplicationId(applicationId);
                            errors.addAll(stickerServiceValidationImpl.validateSticker(Constant.GENERATE_VALIDATION, stickerInfo, contextInfo));
                            break;
                        }
                        case TIME_TABLE_OVERRIDE: {
                            LicenceApplicationInfo laInfo = new LicenceApplicationInfo();
                            laInfo.setId(applicationId);
                            errors.addAll(licenceApplicationService.validateLicenceApplication(Constant.TIME_TABLE_VALIDATION, laInfo, contextInfo));
                            break;
                        }
                    }
//              restTemplate algo
//                    String url = "";
//                    if (key.contains("{")) {
//                        String[] splitedKeys = key.split("[{]");
//                        for (int i = 0; i < splitedKeys.length; i++) {
//                            if (splitedKeys[i].contains("}")) {
//                                String[] splitedVariables = splitedKeys[i].split("[}]");
//                                splitedKeys[i]
//                                        = processInstance
//                                                .getProcessVariables()
//                                                .get(splitedVariables[0]).toString();
//                                if (splitedVariables.length == 2) {
//                                    splitedKeys[i] += splitedVariables[1];
//                                }
//                            }
//                            url += splitedKeys[i];
//                        }
//                    } else {
//                        url = key;
//                    }
//                    String restUrl = baseUrl + url;
//
//                    HttpHeaders headers = new HttpHeaders();
//                    headers.setContentType(MediaType.APPLICATION_JSON);
//
//                    String restUrlPath = restUrl;
//                    Map<String, String> map = new HashMap<>();
//                    if (restUrl.contains("$")) {
//                        restUrlPath = restUrl.split("[$]")[0];
//                        String[] reqBody = restUrl.split("[$]");
//                        for (int j = 1; j < reqBody.length; j++) {
//                            map.put(reqBody[j].split("[=]")[0],
//                                    reqBody[j].split("[=]")[1]);
//                        }
//                    }
//                    HttpEntity entity = new HttpEntity<>(map, headers);
//                    try {
//                        ResponseEntity<Object> response = restTemplate.postForEntity(restUrlPath, entity, Object.class);
//                        List<Object> list = (List<Object>) response.getBody();
//                        if (!list.isEmpty()) {
//                            String fieldName = "taskVariables";
//                            errors.add(
//                                    new ValidationResultInfo(fieldName,
//                                            ErrorLevel.ERROR,
//                                            (String) ((Map) list.get(0)).get("message")));
//                        }
//                    } catch (RestClientException e) {
//                        String fieldName = "taskVariables \"Error occurred while calling POST: \" + restUrlPath + \" with Body: \" + map";
//                        ValidationResultInfo validationResultInfo = new ValidationResultInfo(fieldName,
//                                ErrorLevel.ERROR,
//                                e.getMessage());
//                        StringWriter sw = new StringWriter();
//                        PrintWriter pw = new PrintWriter(sw);
//                        e.printStackTrace(pw);
//                        validationResultInfo.setStackTrace(sw.toString());
//
//                        System.out.println("==========================>In ValidateCompleteTask");
//                        e.printStackTrace();
//                        errors.add(validationResultInfo);
//                    }
                    break;
                }
                case DOCUMENT_UPLOAD: {
                    String[] keys = key.split("[|]");
                    boolean required = true;
                    if (keys.length > 2) {
                        required = keys[2].equals("false");
                    }
                    if (keys.length > 3) {
                        String notRequiredconditionsString = keys[3];
                        String[] notRequired
                                = notRequiredconditionsString.split("[=]");
                        if (taskvariables.get(notRequired[0]).equals(notRequired[1])) {
                            required = !required;
                        }
                    }
                    if (!required) {
                        break;
                    }
                    requiredDocumentType.add(keys[0]);
                    break;
                }
                case DEFERRED_DOCUMENT_UPLOAD: {
                    String[] keys = key.split("[|]");
                    boolean required = true;
                    if (keys.length > 2) {
                        required = keys[2].equals("false");
                    }
                    if (keys.length > 3) {
                        String notRequiredconditionsString = keys[3];
                        String[] notRequired
                                = notRequiredconditionsString.split("[=]");
                        if (taskvariables.get(notRequired[0]).equals(notRequired[1])) {
                            required = !required;
                        }
                    }
                    if (!required) {
                        break;
                    }
                    requiredDeferredDocumentType.add(keys[0]);
                    break;
                }
                case BUTTON: {
                    break;
                }
                case ICON: {
                    break;
                }
                case FORM: {
                    break;
                }
                case SWITCH: {
                    key = key.split("[|]")[0];
                    if (taskvariables.get(key) == null) {
                        String fieldName = "taskVariables";
                        errors.add(
                                new ValidationResultInfo(fieldName,
                                        ErrorLevel.ERROR,
                                        key + " must be provided"));
                    }
                    break;
                }
                default: {
                    if (taskvariables.get(key) == null) {
                        String fieldName = "taskVariables";
                        errors.add(
                                new ValidationResultInfo(fieldName,
                                        ErrorLevel.ERROR,
                                        key + " must be provided"));
                    }
                }
            }
        }
        if (!requiredDeferredDocumentType.isEmpty()) {
            TaskInfo taskInfo = getTaskByTaskId(taskId, contextInfo);
            String applicationId
                    = (String) taskInfo.getProcessVariables().get(APPLICATION_ID);

            List<LaDocumentInfo> submmitedApplicationDocumentInfos
                    = licenceApplicationDocumentServiceValidationImpl.getLicenceApplicationDocumentsByLicenceApplicationId(
                            DocumentServiceConstant.LICENCE_APPLICATION_DOCUMENT_STATE_ACTIVE,
                            applicationId,
                            Constant.FULL_PAGE,
                            contextInfo
                    ).getContent();

            Map<String, LaDocumentInfo> licenceApplicationDocumentMap = new HashMap<>();
            submmitedApplicationDocumentInfos.forEach(docuemnt
                    -> licenceApplicationDocumentMap.put(docuemnt.getDocumentTypeId(), docuemnt)
            );
            requiredDeferredDocumentType.stream().forEach(documentType -> {
                LaDocumentInfo uploadedDocument = licenceApplicationDocumentMap.get(documentType);
                if (uploadedDocument == null) {
                    String documentName;
                    try {
                        documentName = typeService.getTypeById(documentType, contextInfo).getName();
                        errors.add(new ValidationResultInfo(documentName,
                                ErrorLevel.ERROR,
                                documentName + MUST_PROVIDED));
                    } catch (DoesNotExistException
                            | OperationFailedException
                            | MissingParameterException
                            | PermissionDeniedException
                            | InvalidParameterException ex) {
                        errors.add(new ValidationResultInfo("documentType",
                                ErrorLevel.ERROR,
                                "error occured while fetching document type."));
                    }

                }
            });

        }
        if (!requiredDocumentType.isEmpty()) {
            TaskInfo taskInfo = getTaskByTaskId(taskId, contextInfo);
            String applicationId
                    = (String) taskInfo.getProcessVariables().get(APPLICATION_ID);

            LicenceApplicationInfo info
                    = licenceApplicationService.getLicenceApplicationById(applicationId, contextInfo);

            String applicationTypeId = info.getApplicationTypeId();
            List<LaDocumentInfo> submmitedApplicationDocumentInfos
                    = licenceApplicationDocumentServiceValidationImpl.getLicenceApplicationDocumentsByLicenceApplicationId(
                            DocumentServiceConstant.LICENCE_APPLICATION_DOCUMENT_STATE_ACTIVE,
                            applicationId,
                            Constant.FULL_PAGE,
                            contextInfo
                    ).getContent();

            Map<String, LaDocumentInfo> licenceApplicationDocumentMap = new HashMap<>();
            submmitedApplicationDocumentInfos.forEach(docuemnt
                    -> licenceApplicationDocumentMap.put(docuemnt.getDocumentTypeId(), docuemnt)
            );

            String licenceTypeId = info.getLicenceTypeId();
            LicenceTypeInfo licenceTypeInfo
                    = licenceTypeService.getLicenceTypeById(
                            licenceTypeId,
                            contextInfo
                    );
            List<LicenceTypeDocumentTypeInfo> associatedDocumentTypes = licenceTypeInfo.getAssociatedDocumentTypes();

            if (!CollectionUtils.isEmpty(associatedDocumentTypes)) {
                List<LicenceTypeDocumentTypeInfo> requiredDoucmentInfo = associatedDocumentTypes.stream()
                        .filter(associatedDocumentType
                                -> associatedDocumentType.getIsApprovalRequired()
                        && associatedDocumentType.getApplicationTypeId().equals(applicationTypeId)
                        && requiredDocumentType.contains(associatedDocumentType.getDocumentTypeId())
                        ).collect(Collectors.toList());
                requiredDoucmentInfo.stream().forEach(document -> {
                    LaDocumentInfo uploadedDocument = licenceApplicationDocumentMap.get(document.getDocumentTypeId());
                    if (uploadedDocument == null) {
                        String documentName;
                        try {
                            documentName = typeService.getTypeById(document.getDocumentTypeId(), contextInfo).getName();
                            errors.add(new ValidationResultInfo(documentName,
                                    ErrorLevel.ERROR,
                                    documentName + MUST_PROVIDED));
                        } catch (DoesNotExistException
                                | OperationFailedException
                                | MissingParameterException
                                | PermissionDeniedException
                                | InvalidParameterException ex) {
                            errors.add(new ValidationResultInfo("documentType",
                                    ErrorLevel.ERROR,
                                    "error occured while fetching document type."));
                        }

                    }
                });
            }
        }
        return errors;
    }

    @Override
    @Timed(name = "searchAdvanceTask")
    public List<TaskInfo> searchAdvanceTask(
            List<String> applicationIds,
            String group,
            String taskSearchType,
            ContextInfo contextInfo) {
        if (TaskSearchType.UNFINISHED.equals(taskSearchType)) {
            return searchUnfinishedTasks(
                    applicationIds,
                    group);
        }
        return searchHistoryTasks(
                applicationIds,
                taskSearchType);
    }

    @Timed(name = "searchUnfinishedTasks")
    public List<TaskInfo> searchUnfinishedTasks(
            List<String> applicationIds,
            String group) {
        List<TaskInfo> allTasks = new ArrayList<>();
        applicationIds.stream().forEach(applicationId -> {

            TaskQuery taskQuery = taskService
                    .createTaskQuery();
            if (!StringUtils.isEmpty(applicationId)) {
                taskQuery
                        = taskQuery
                                .processVariableValueEquals(
                                        APPLICATION_ID,
                                        applicationId);
            }
            taskQuery
                    = taskQuery
                            .processVariableValueEquals(
                                    LICENCE_PROCESS,
                                    true);
            if (!StringUtils.isEmpty(group)) {
                taskQuery = taskQuery.taskCandidateGroup(group);
            }

            allTasks.addAll(taskQuery
                    .includeProcessVariables()
                    .includeTaskLocalVariables()
                    .list()
                    .stream()
                    .map(task -> {
                        ProcessInstance processInstance
                                = runtimeService
                                        .createProcessInstanceQuery()
                                        .processInstanceId(
                                                task.getProcessInstanceId())
                                        .singleResult();
                        return TaskMapper
                                .convertTaskToTaskInfo(
                                        task,
                                        processInstance
                                                .getProcessDefinitionKey(),
                                        processInstance
                                                .getProcessDefinitionVersion());
                    })
                    .collect(Collectors.toList())
            );
        });

        return allTasks;
    }

    @Timed(name = "searchHistoryTasks")
    public List<TaskInfo> searchHistoryTasks(
            List<String> applicationIds,
            String taskSearchType) {
        List<TaskInfo> allTasks = new ArrayList<>();
        applicationIds.stream().forEach(applicationId -> {
            HistoricTaskInstanceQuery taskQuery
                    = historyService
                            .createHistoricTaskInstanceQuery();
            if (!StringUtils.isEmpty(applicationId)) {
                taskQuery = taskQuery
                        .processVariableValueEquals(APPLICATION_ID, applicationId);
            }
            if (TaskSearchType.FINISHED.equals(taskSearchType)) {
                taskQuery = taskQuery.finished();
            }
            taskQuery
                    = taskQuery
                            .processVariableValueEquals(
                                    LICENCE_PROCESS,
                                    true);
            allTasks.addAll(
                    taskQuery
                            .includeProcessVariables()
                            .includeTaskLocalVariables()
                            .list()
                            .stream()
                            .map(task -> {
                                HistoricProcessInstance processInstance
                                        = historyService
                                                .createHistoricProcessInstanceQuery()
                                                .processInstanceId(
                                                        task.getProcessInstanceId())
                                                .singleResult();
                                return TaskMapper
                                        .convertHistoricTaskInstanceToTaskInfo(
                                                task,
                                                processInstance
                                                        .getProcessDefinitionKey(),
                                                processInstance
                                                        .getProcessDefinitionVersion());
                            })
                            .collect(Collectors.toList())
            );
        });
        return allTasks;
    }

    @Override
    @Timed(name = "searchTask")
    public Page<TaskInfo> searchTask(
            CommonProcessSearchFilter searchFilter,
            Pageable pageable,
            ContextInfo contextInfo)
            throws InvalidParameterException {
        if (TaskSearchType.UNFINISHED.equals(searchFilter.getTaskSearchType())) {
            return searchUnfinishedTasks(
                    searchFilter,
                    pageable,
                    contextInfo);
        }

        return searchHistoryTasks(
                searchFilter,
                pageable,
                contextInfo);
    }

    @Timed(name = "searchHistoryTasks")
    public Page<TaskInfo> searchHistoryTasks(
            CommonProcessSearchFilter searchFilter,
            Pageable pageable,
            ContextInfo contextInfo) {
        List<Sort.Order> sortOrders = pageable.getSort().get().collect(Collectors.toList());
        HistoricTaskInstanceQuery taskQuery = historyService
                .createHistoricTaskInstanceQuery();
        if (!StringUtils.isEmpty(searchFilter.getProcessInstanceId())) {
            taskQuery = taskQuery
                    .processInstanceId(searchFilter.getProcessInstanceId());
        }
        if (!StringUtils.isEmpty(searchFilter.getApplicationTypeId())) {
            taskQuery = taskQuery
                    .processVariableValueEquals("applicationTypeId", searchFilter.getApplicationTypeId());
        }
        if (!StringUtils.isEmpty(searchFilter.getStateId())) {
            taskQuery = taskQuery
                    .processVariableValueEquals("requestApproval", searchFilter.getStateId());
        }
        if (Boolean.TRUE.equals(searchFilter.getLicenceProcess())) {
            taskQuery = taskQuery
                    .processVariableValueEquals(LICENCE_PROCESS, true);
        } else {
            taskQuery = taskQuery
                    .processVariableValueEquals(LICENCE_PROCESS, false);
        }

        if (!StringUtils.isEmpty(searchFilter.getApplicationId())) {
            taskQuery = taskQuery
                    .processVariableValueEquals(APPLICATION_ID, searchFilter.getApplicationId());
        } else if (!contextInfo.getIsHigherAccess()) {
            if (!StringUtils.isEmpty(contextInfo.getRegionId())) {
                taskQuery = taskQuery
                        .processVariableValueEquals("regionId", contextInfo.getRegionId());
            } else if (!StringUtils.isEmpty(contextInfo.getOfficeId())) {
                taskQuery = taskQuery
                        .processVariableValueEquals("officeId", contextInfo.getOfficeId());
            }
        }
        if (!StringUtils.isEmpty(searchFilter.getTrn())) {
            taskQuery = taskQuery
                    .processVariableValueEquals("trn", searchFilter.getTrn());
        }
        if (!StringUtils.isEmpty(searchFilter.getGroup())) {
            taskQuery = taskQuery.taskVariableValueEquals("group", searchFilter.getGroup());
        }
        if (!StringUtils.isEmpty(searchFilter.getProcessDefinitionKey())) {
            taskQuery = taskQuery.processDefinitionKey(searchFilter.getProcessDefinitionKey());
        }
        if (!StringUtils.isEmpty(searchFilter.getTaskDefinitionKey())) {
            taskQuery = taskQuery.taskDefinitionKey(searchFilter.getTaskDefinitionKey());
        }
        if (!StringUtils.isEmpty(searchFilter.getProcessId())) {
            taskQuery = taskQuery.processInstanceId(searchFilter.getProcessId());
        }
        if (TaskSearchType.FINISHED.equals(searchFilter.getTaskSearchType())) {
            taskQuery = taskQuery.finished();
        }
        if (!sortOrders.isEmpty()) {
            for (Sort.Order sortOrder : pageable.getSort().get().collect(Collectors.toList())) {
                switch (sortOrder.getProperty()) {
                    case "name": {
                        taskQuery = taskQuery.orderByTaskName();
                        break;
                    }
                    case "createdAt": {
                        taskQuery = taskQuery.orderByTaskCreateTime();
                        break;
                    }
                    default: {
                        break;
                    }
                }

                taskQuery = sortOrder.getDirection().isDescending() ? taskQuery.desc() : taskQuery.asc();
            }
        }

        Long count = taskQuery.count();
        List<TaskInfo> tasks
                = taskQuery
                        .includeProcessVariables()
                        .includeTaskLocalVariables()
                        .listPage(pageable.getPageNumber() * pageable.getPageSize(),
                                pageable.getPageSize())
                        .stream()
                        .map(task -> {
                            HistoricProcessInstance processInstance
                                    = historyService
                                            .createHistoricProcessInstanceQuery()
                                            .processInstanceId(
                                                    task.getProcessInstanceId())
                                            .singleResult();
                            return TaskMapper
                                    .convertHistoricTaskInstanceToTaskInfo(
                                            task,
                                            processInstance
                                                    .getProcessDefinitionKey(),
                                            processInstance
                                                    .getProcessDefinitionVersion());
                        })
                        .collect(Collectors.toList());
        return new PageImpl<>(tasks,
                pageable,
                count);
    }

    public Page<TaskInfo> searchUnfinishedTasks(
            CommonProcessSearchFilter searchFilter,
            Pageable pageable,
            ContextInfo contextInfo) {
        List<Sort.Order> sortOrders = pageable.getSort().get().collect(Collectors.toList());
        TaskQuery taskQuery = taskService
                .createTaskQuery();
        if (!StringUtils.isEmpty(searchFilter.getProcessInstanceId())) {
            taskQuery = taskQuery
                    .processInstanceId(searchFilter.getProcessInstanceId());
        }
        if (!StringUtils.isEmpty(searchFilter.getApplicationTypeId())) {
            taskQuery = taskQuery
                    .processVariableValueEquals("applicationTypeId", searchFilter.getApplicationTypeId());
        }
        if (!StringUtils.isEmpty(searchFilter.getStateId())) {
            taskQuery = taskQuery
                    .processVariableValueEquals("requestApproval", searchFilter.getStateId());
        }
        if (Boolean.TRUE.equals(searchFilter.getLicenceProcess())) {
            taskQuery = taskQuery
                    .processVariableValueEquals(LICENCE_PROCESS, true);
        } else {
            taskQuery = taskQuery
                    .processVariableValueEquals(LICENCE_PROCESS, false);
        }
        if (!StringUtils.isEmpty(searchFilter.getApplicationId())) {
            taskQuery
                    = taskQuery
                            .processVariableValueEquals(
                                    APPLICATION_ID,
                                    searchFilter.getApplicationId());
        } else if (!contextInfo.getIsHigherAccess()) {
            if (!StringUtils.isEmpty(contextInfo.getRegionId())) {
                taskQuery = taskQuery
                        .processVariableValueEquals("regionId", contextInfo.getRegionId());
            } else if (!StringUtils.isEmpty(contextInfo.getOfficeId())) {
                taskQuery = taskQuery
                        .processVariableValueEquals("officeId", contextInfo.getOfficeId());
            }
        }
        if (!StringUtils.isEmpty(searchFilter.getTrn())) {
            taskQuery
                    = taskQuery
                            .processVariableValueEquals(
                                    "trn",
                                    searchFilter.getTrn());
        }
        if (!StringUtils.isEmpty(searchFilter.getGroup())) {
            taskQuery = taskQuery.taskCandidateGroup(searchFilter.getGroup());
        }
        if (!StringUtils.isEmpty(searchFilter.getProcessDefinitionKey())) {
            taskQuery = taskQuery.processDefinitionKey(searchFilter.getProcessDefinitionKey());
        }
        if (!StringUtils.isEmpty(searchFilter.getTaskDefinitionKey())) {
            taskQuery = taskQuery.taskDefinitionKey(searchFilter.getTaskDefinitionKey());
        }
        if (!StringUtils.isEmpty(searchFilter.getProcessId())) {
            taskQuery = taskQuery.processInstanceId(searchFilter.getProcessId());
        }
        if (searchFilter.isOnlyUnAssigned()) {
            taskQuery = taskQuery.taskUnassigned();
        }
        if (sortOrders.size() != 0) {
            for (Sort.Order sortOrder : pageable.getSort().get().collect(Collectors.toList())) {
                switch (sortOrder.getProperty()) {
                    case "name": {
                        taskQuery = taskQuery.orderByTaskName();
                        break;
                    }
                    case "createdAt": {
                        taskQuery = taskQuery.orderByTaskCreateTime();
                        break;
                    }
                    default: {
                        break;
                    }
                }

                taskQuery = sortOrder.getDirection().isDescending() ? taskQuery.desc() : taskQuery.asc();
            }
        }

        Long count = taskQuery.count();
        List<TaskInfo> tasks
                = taskQuery
                        .includeProcessVariables()
                        .includeTaskLocalVariables()
                        .listPage(pageable.getPageNumber() * pageable.getPageSize(),
                                pageable.getPageSize())
                        .stream()
                        .map(task -> {
                            ProcessInstance processInstance
                                    = runtimeService
                                            .createProcessInstanceQuery()
                                            .processInstanceId(
                                                    task.getProcessInstanceId())
                                            .singleResult();
                            return TaskMapper
                                    .convertTaskToTaskInfo(
                                            task,
                                            processInstance
                                                    .getProcessDefinitionKey(),
                                            processInstance
                                                    .getProcessDefinitionVersion());
                        })
                        .collect(Collectors.toList());
        return new PageImpl<>(tasks,
                pageable,
                count);
    }

    @Override
    @Timed(name = "searchProcess")
    public List<ProcessInfo> searchProcess(
            String applicationId,
            ContextInfo contextInfo)
            throws InvalidParameterException {
        return runtimeService
                .createProcessInstanceQuery()
                .includeProcessVariables()
                .variableValueEquals(APPLICATION_ID, applicationId)
                .list()
                .stream()
                .map(ProcessMapper::convertProcessToProcessInfo)
                .collect(Collectors.toList());
    }
}
