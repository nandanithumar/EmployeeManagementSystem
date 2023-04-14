package com.egovja.tatransform.licencingmanagement.master.service.contract;

import com.egovja.tatransform.audit.filter.RevisionSearchFilter;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ContextInfo;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ValidationResultInfo;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.DataValidationErrorException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.DoesNotExistException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.InvalidParameterException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.MissingParameterException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.OperationFailedException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.PermissionDeniedException;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.VersionMismatchException;
import com.egovja.tatransform.licencingmanagement.master.dto.info.StateInfo;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This is Base implementation for StateService Service.
 *
 * @author sudip
 * @since 2021-12-13
 */
public class StateServiceBaseImpl implements StateService {

    StateService stateService;

    public void setNextDecorator(StateService stateService) {
        this.stateService = stateService;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public StateInfo createState(StateInfo stateInfo, ContextInfo contextInfo) throws OperationFailedException, InvalidParameterException, MissingParameterException, PermissionDeniedException, DataValidationErrorException {
        return stateService.createState(stateInfo, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public StateInfo updateState(StateInfo stateInfo, ContextInfo contextInfo) throws DoesNotExistException, OperationFailedException, MissingParameterException, PermissionDeniedException, VersionMismatchException, DataValidationErrorException, InvalidParameterException {
        return stateService.updateState(stateInfo, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Page<StateInfo> searchStatesByRefObjectUriAndName(List<String> ids, String refObjectUri, String name, Pageable pageable, ContextInfo contextInfo) throws OperationFailedException, MissingParameterException, PermissionDeniedException, InvalidParameterException {
        return stateService.searchStatesByRefObjectUriAndName(ids, refObjectUri, name, pageable, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<ValidationResultInfo> validateState(String validationTypeKey, StateInfo stateInfo, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        return stateService.validateState(validationTypeKey, stateInfo, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public StateInfo getStateById(String stateId, ContextInfo contextInfo) throws DoesNotExistException, OperationFailedException, MissingParameterException, PermissionDeniedException, InvalidParameterException {
        return stateService.getStateById(stateId, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Page<StateInfo> getRevisions(RevisionSearchFilter searchFilter, Pageable pageable, ContextInfo contextInfo) throws OperationFailedException, MissingParameterException, PermissionDeniedException, InvalidParameterException {
        return stateService.getRevisions(searchFilter, pageable, contextInfo);
    }

}
