/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.master.service.impl.rest;

import com.codahale.metrics.annotation.Timed;
import com.egovja.tatransform.audit.filter.RevisionSearchFilter;
import com.egovja.tatransform.licencingmanagement.common.constant.Constant;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ContextInfo;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ValidationResultInfo;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.*;
import com.egovja.tatransform.licencingmanagement.master.dto.info.TypeInfo;
import com.egovja.tatransform.licencingmanagement.master.service.contract.TypeService;
import com.egovja.tatransform.licencingmanagement.master.service.contract.TypeServiceBaseImpl;
import com.egovja.tatransform.licencingmanagement.master.service.impl.validation.TypeServiceValidationImpl;
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
 * This TypeServiceRestController maps end points with standard service.
 *
 * @author akshar
 * @since 2021-2-17
 */
@RestController
@Metrics(registry = "TypeServiceRestController")
@RequestMapping(Constant.CONTEXT_PATH + "/types")
@Api(value = "REST API for Type services", tags = {"Type API"})
public class TypeServiceRestController extends TypeServiceBaseImpl implements TypeService {

    @Autowired
    TypeServiceValidationImpl typeServiceValidation;

    @PostConstruct
    public void setNextDecorator() {
        super.setNextDecorator(typeServiceValidation);
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
    public Page<TypeInfo> getRevisions(
            RevisionSearchFilter searchFilter,
            @PageableDefault(size = 10, direction = Sort.Direction.DESC) Pageable pageable,
            @RequestAttribute("contextInfo") ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException {
        return typeServiceValidation.getRevisions(searchFilter, pageable, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @ApiOperation(value = "Create new type", response = TypeInfo.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully created type"),
        @ApiResponse(code = 401, message = "You are not authorized to create the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    @Override
    @Timed(name = "createType")
    @PostMapping("/{refObjectUri}")
    public TypeInfo createType(@RequestBody TypeInfo typeInfo,
            @PathVariable("refObjectUri") String refObjectUri,
            @RequestAttribute(name = "contextInfo") ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException,
            DataValidationErrorException {

        return typeServiceValidation.createType(typeInfo, refObjectUri, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @ApiOperation(value = "Update existing type", response = TypeInfo.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully updated typr"),
        @ApiResponse(code = 401, message = "You are not authorized to create the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    @Override
    @Timed(name = "updateType")
    @PutMapping("/{refObjectUri}")
    public TypeInfo updateType(
            @RequestBody TypeInfo typeInfo,
            @PathVariable("refObjectUri") String refObjectUri,
            @RequestAttribute(name = "contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException,
            OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException,
            VersionMismatchException,
            DataValidationErrorException {

        return typeServiceValidation.updateType(typeInfo, refObjectUri, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @ApiOperation(value = "View available type with supplied id", response = TypeInfo.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved type"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @Override
    @Timed(name = "getTypeById")
    @GetMapping("/{typeId}")
    public TypeInfo getTypeById(
            @PathVariable("typeId") String typeId,
            @RequestAttribute("contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException,
            OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException {

        return typeServiceValidation.getTypeById(typeId, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @ApiOperation(value = "View a page of available filtered type", response = Page.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved page"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @Override
    @Timed(name = "searchTypesByRefObjectUriAndName")
    @GetMapping("")
    public Page<TypeInfo> searchTypesByRefObjectUriAndName(
            @RequestParam(name = "id", required = false) List<String> ids,
            @RequestParam(name = "stateId", required = false) String stateId,
            @RequestParam(name = "code", required = false) String code,
            @RequestParam(name = "refObjectUri", required = false) String refObjectUri,
            @RequestParam(name = "name", required = false) String typeName,
            @PageableDefault(size = 1000) Pageable pageable,
            ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException {

        return typeServiceValidation.searchTypesByRefObjectUriAndName(
                ids,
                stateId,
                code,
                refObjectUri,
                typeName,
                pageable,
                contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @ApiOperation(value = "View a list of validation errors for type", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved Validation errors"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    @Override
    @Timed(name = "validateType")
    @PostMapping("/validate/{refObjectUri}")
    public List<ValidationResultInfo> validateType(
            @RequestParam(name = "validationTypeKey", required = true) String validationTypeKey,
            @RequestBody(required = true) TypeInfo typeInfo,
            @PathVariable("refObjectUri") String refObjectUri,
            @RequestAttribute("contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        return typeServiceValidation.validateType(validationTypeKey, typeInfo, refObjectUri, contextInfo);
    }

}
