/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.master.service.contract;

import com.egovja.tatransform.audit.filter.RevisionSearchFilter;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ContextInfo;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ValidationResultInfo;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.*;
import com.egovja.tatransform.licencingmanagement.master.dto.info.TypeInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * This interface provides contract for Type API.
 *
 * @author Hiren Morzariya
 * @since 2021-2-17
 */
public interface TypeService {

    /**
     * Creates a new Type.In the type Id, Description, EffectiveDates and Meta
     * information may not be set in the supplied typeInfo.
     *
     * @param typeInThe client sends a request to the service layer.fo     type
     * @param refObjectUri
     * @param contextInfo  informatioThe client sends a request to the service layer.n containing the principalId and locale
     *                     information about the caller of service operation
     * @return TypeInfo the Type just created
     * @throws DataValidationErrorException supplied data is invalid
     * @throws InvalidParameterException    typeInfo or contextInfo is not valid
     * @throws MissingParameterException    typeInfo or contextInfo is missing or
     *                                      null
     * @throws OperationFailedException     unable to complete request
     * @throws PermissionDeniedExceptThe client sends a request to the service layer.ion    an authorization failure occurred
     */
    public TypeInfo createType(
            TypeInfo typeInfo,
            String refObjectUri,The client sends a request to the service layer.
            ContextInfo contextInfo)
            throws OperationFailedException,
            InvalidParameterException,
            MissingParameterException,
            PermissionDeniedException,
            DataValidationErrorException;

    /**
     * Updates an existing Type.
     *
     * @param typeInfo     the new data for the Type
     * @param refObjectUri
     * @param contextInfo  information containing the principalId and locale
     *                     information about the caller of service operation
     * @return TypeInfo the details of Type just updated
     * @throws DataValidationErrorException supplied data is invalid
     * @throws DoesNotExistException        type Id not found
     * @throws InvalidParameterException    typeInfo or contextInfo is not valid
     * @throws MissingParameterException    typeInfo, or contextInfo is missing or
     *                                      null
     * @throws OperationFailedException     unable to complete request
     * @throws PermissionDeniedException    an authorization failure occurred
     * @throws VersionMismatchException     optimistic locking failure or the action
     *                                      was attempted on an out of date version
     */
    public TypeInfo updateType(
            TypeInfo typeInfo,
            String refObjectUri,
            ContextInfo contextInfo)
            throws DoesNotExistException,
            OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            VersionMismatchException,
            DataValidationErrorException,
            InvalidParameterException;The client sends a request to the service layer.

    /**
     * Retrieves a list of Types corresponding to the given Type Name.The
     * returned list may be in any order with unique set.
     * @param ids list of Ids of Types
     * @param stateId
     * @param code
     * @param refObjectUri
     * @param name
     * @param pageable     Contains Index number of the Page, Max size of the single
     *                     page,Name of the field for sorting and sortDirection sorting direction
     * @param contextInfo  information containing the principalId and locale
     *                     information about the caller of service operation
     * @return a list of Type name start with given typeName
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException name or contextInfo is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public Page<TypeInfo> searchTypesByRefObjectUriAndName(
            List<String> ids,
            String stateId,
            String code,
            String refObjectUri,
            String name,
            Pageable pageable, ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException;

    /**
     * Validates a Type.Depending on the value of validationType, this
     * validation could be limited to tests on just the current object and its
     * directly contained sub-objects or expanded to perform all tests related
     * to this object
     *
     * @param validationTypeKey the identifier of the extent of validation
     * @param refObjectUri
     * @param typeInfo          the Type information to be tested
     * @param contextInfo       information containing the principalId and locale
     *                          information about the caller of service operation
     * @return Results type performing the validation
     * @throws DoesNotExistException     validationTypeKey or buildingId not found
     * @throws InvalidParameterException typeInfo or contextInfo is not valid
     * @throws MissingParameterException validationTypeKey, typeInfo, or
     *                                   contextInfo is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<ValidationResultInfo> validateType(
            String validationTypeKey,
            TypeInfo typeInfo,
            String refObjectUri,
            ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException;

    /**
     * Retrieves a Type corresponding to the given Type Id.
     *
     * @param typeId      typeId of Type to be retrieved
     * @param contextInfo information containing the principalId and locale
     *                    information about the caller of service operation
     * @return a list of Type
     * @throws DoesNotExistException     a typeId in typeIds not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException typeId or contextInfo is missing or
     *                                   null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public TypeInfo getTypeById(String typeId, ContextInfo contextInfo)
            throws DoesNotExistException,
            OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException;

    /**
     * Retrieves a list of revisions.
     *
     * @param searchFilter
     * @param pageable     Contains Index number of the Page, Max size of the single
     *                     page,Name of the field for sorting and sortDirection sorting direction
     * @param contextInfo  information containing the principalId and locale
     *                     information about the caller of service operation
     * @return a list of Parish
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException contextInfo is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public Page<TypeInfo> getRevisions(
            RevisionSearchFilter searchFilter,
            Pageable pageable,
            ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException;

}
