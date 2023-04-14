
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.master.service.impl.rest;

import com.codahale.metrics.annotation.Timed;
import com.egovja.tatransform.audit.filter.RevisionSearchFilter;
import com.egovja.tatransform.licencingmanagement.common.constant.
        
import com.egovja.tatransform.licencingmanagement.common.dto.info.ContextInfo;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ValidationResultInfo;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.*;
import com.egovja.tatransform.licencingmanagement.master.dto.info.StateInfo;
import com.egovja.tatransform.licencingmanagement.master.service.contract.StateService;
import com.egovja.tatransform.licencingmanagement.master.service.contract.StateServiceBaseImpl;
import com.egovja.tatransform.licencingmanagement.master.service.impl.validation.StateServiceValidationImpl;
import io.astefanutti.metrics.aspectj.Metrics;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.annotation.PostConstruct;

/**
 * This StateServiceRestController maps end points with standard service.
 *
 * @author akshay
 * @since 2021-02-25
 */
@RestController
@Metrics(registry = "StateServiceRestController")
@RequestMapping(Constant.CONTEXT_PATH + "/states")
@Api(value = "REST API for State services", tags = {"State API"})
public class StateServiceRestController extends StateServiceBaseImpl implements StateService {

    @Autowired
    StateServiceValidationImpl stateServiceValidation;

    @PostConstruct
    public void setNextDecorator() {
        super.setNextDecorator(stateServiceValidation);
    }

    /**
     * {@inheritDoc}
     */
    @ApiOperation(value = "Retrieve revision entities",
            response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200,
                message = "Successfully retrieved revision entities"),
        @ApiResponse(code = 401,
                message = "You are not authorized to retrieve revision entities"),
        @ApiResponse(code = 403,
                message = "Accessing the resource you were trying to reach is forbidden")
    })
    @Override
    @Timed(name = "getRevisions")
    @GetMapping("/revision")
    public Page<StateInfo> getRevisions(
            RevisionSearchFilter searchFilter,
            @PageableDefault(size = 10, direction = Sort.Direction.DESC) Pageable pageable,
            @RequestAttribute("contextInfo") ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException {
        return stateServiceValidation.getRevisions(searchFilter, pageable, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @ApiOperation(value = "Create new state", response = StateInfo.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully created state"),
        @ApiResponse(code = 401, message = "You are not authorized to create the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    @Override
    @Timed(name = "createState")
    @PostMapping("")
    public StateInfo createState(@RequestBody StateInfo stateInfo,
            @RequestAttribute(name = "contextInfo") ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException,
            DataValidationErrorException {

        return stateServiceValidation.createState(stateInfo, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @ApiOperation(value = "Update existing state", response = StateInfo.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully updated state"),
        @ApiResponse(code = 401, message = "You are not authorized to create the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    @Override
    @Timed(name = "updateState")
    @PutMapping("")
    public StateInfo updateState(
            @RequestBody StateInfo stateInfo,
            @RequestAttribute(name = "contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException,
            OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException,
            VersionMismatchException,
            DataValidationErrorException {

        return stateServiceValidation.updateState(stateInfo, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @ApiOperation(value = "View available state with supplied id", response = StateInfo.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved state"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @Override
    @Timed(name = "getStateById")
    @GetMapping("/{stateId}")
    public StateInfo getStateById(
            @PathVariable("stateId") String stateId,
            @RequestAttribute("contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException,
            OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException {

        return stateServiceValidation.getStateById(stateId, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @ApiOperation(value = "View a page of available filtered state", response = Page.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved page"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @Override
    @Timed(name = "searchStatesByRefObjectUriAndName")
    @GetMapping("")
    public Page<StateInfo> searchStatesByRefObjectUriAndName(
            @RequestParam(name = "ids", required = false) List<String> ids,
            @RequestParam(name = "refObjectUri", required = false) String refObjectUri,
            @RequestParam(name = "name", required = false) String stateName,
            @PageableDefault(size = 1000) Pageable pageable,
            ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException {

        return stateServiceValidation.searchStatesByRefObjectUriAndName(
                ids,
                refObjectUri,
                stateName,
                pageable,
                contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @ApiOperation(value = "View a list of validation errors for state", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved Validation errors"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    @Override
    @Timed(name = "validateState")
    @PostMapping("/validate")
    public List<ValidationResultInfo> validateState(
            @RequestParam(name = "validationTypeKey", required = true) String validationTypeKey,
            @RequestBody(required = true) StateInfo stateInfo,
            @RequestAttribute("contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        return stateServiceValidation.validateState(
                validationTypeKey,
                stateInfo,
                contextInfo);
    }

}
