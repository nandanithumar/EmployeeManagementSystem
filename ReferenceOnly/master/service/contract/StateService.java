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
import com.egovja.tatransform.licencingmanagement.master.dto.info.StateInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * This interface provides contract for State API.
 *
 * @author akshay
 * @since 2021-02-25
 */
public interface StateService {

    /**
     * Creates a new State. In the state Id, Description, EffectiveDates and
     * Meta information may not be set in the supplied stateInfo.
     *
     * @param stateInfo   state
     * @param contextInfo information containing the principalId and locale
     *                    information about the caller of service operation
     * @return StateInfo the State just created
     * @throws DataValidationErrorException supplied data is invalid
     * @throws InvalidParameterException    stateInfo or contextInfo is not valid
     * @throws MissingParameterException    stateInfo or contextInfo is missing or
     *                                      null
     * @throws OperationFailedException     unable to complete request
     * @throws PermissionDeniedException    an authorization failure occurred
     */
    public StateInfo createState(StateInfo stateInfo, ContextInfo contextInfo)
            throws OperationFailedException,
            InvalidParameterException,
            MissingParameterException,
            PermissionDeniedException,
            DataValidationErrorException;

    /**
     * Updates an existing State.
     *
     * @param stateInfo   the new data for the State
     * @param contextInfo information containing the principalId and locale
     *                    information about the caller of service operation
     * @return StateInfo the details of State just updated
     * @throws DataValidationErrorException supplied data is invalid
     * @throws DoesNotExistException        state Id not found
     * @throws InvalidParameterException    stateInfo or contextInfo is not valid
     * @throws MissingParameterException    stateInfo, or contextInfo is missing or
     *                                      null
     * @throws OperationFailedException     unable to complete request
     * @throws PermissionDeniedException    an authorization failure occurred
     * @throws VersionMismatchException     optimistic locking failure or the action
     *                                      was attempted on an out of date version
     */
    public StateInfo updateState(StateInfo stateInfo, ContextInfo contextInfo)
            throws DoesNotExistException,
            OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            VersionMismatchException,
            DataValidationErrorException,
            InvalidParameterException;

    /**
     * Retrieves a list of States corresponding to the given State Name and
     * RefObjectUri .The returned list may be in any order with unique set.
     * @param ids list of Ids of States
     * @param refObjectUri
     * @param name
     * @param pageable     Contains Index number of the Page, Max size of the single
     *                     page,Name of the field for sorting and sortDirection sorting direction
     * @param contextInfo  information containing the principalId and locale
     *                     information about the caller of service operation
     * @return a list of State name start with given stateName and refObjecturi
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException name or refObjectUri or contextInfo is
     *                                   missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public Page<StateInfo> searchStatesByRefObjectUriAndName(
            List<String> ids,
            String refObjectUri, 
            String name,
            Pageable pageable, 
            ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException;

    /**
     * Validates a State.Depending on the value of validationType, this
     * validation could be limited to tests on just the current object and its
     * directly contained sub-objects or expanded to perform all tests related
     * to this object
     *
     * @param validationTypeKey the identifier of the extent of validation
     * @param stateInfo         the state information to be tested
     * @param contextInfo       information containing the principalId and locale
     *                          information about the caller of service operation
     * @return Results state performing the validation
     * @throws DoesNotExistException     validationTypeKey or buildingId not found
     * @throws InvalidParameterException stateInfo or contextInfo is not valid
     * @throws MissingParameterException validationTypeKey, stateInfo, or
     *                                   contextInfo is missing or null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public List<ValidationResultInfo> validateState(
            String validationTypeKey,
            StateInfo stateInfo,
            ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException;

    /**
     * Retrieves a State corresponding to the given State Id.
     *
     * @param stateId     stateId of State to be retrieved
     * @param contextInfo information containing the principalId and locale
     *                    information about the caller of service operation
     * @return a State
     * @throws DoesNotExistException     a stateId in stateIds not found
     * @throws InvalidParameterException invalid contextInfo
     * @throws MissingParameterException stateId or contextInfo is missing or
     *                                   null
     * @throws OperationFailedException  unable to complete request
     * @throws PermissionDeniedException an authorization failure occurred
     */
    public StateInfo getStateById(String stateId, ContextInfo contextInfo)
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
    public Page<StateInfo> getRevisions(
            RevisionSearchFilter searchFilter,
            Pageable pageable,
            ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException;

}
