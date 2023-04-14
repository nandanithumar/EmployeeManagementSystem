/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.master.service.impl.validation;

import com.codahale.metrics.annotation.Timed;
import com.egovja.tatransform.audit.filter.RevisionSearchFilter;
import com.egovja.tatransform.licencingmanagement.common.constant.Constant;
import com.egovja.tatransform.licencingmanagement.common.constant.ErrorLevel;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ContextInfo;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ValidationResultInfo;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.*;
import com.egovja.tatransform.licencingmanagement.common.utils.ValidationUtils;
import com.egovja.tatransform.licencingmanagement.master.dto.info.StateInfo;
import com.egovja.tatransform.licencingmanagement.master.service.contract.StateService;
import com.egovja.tatransform.licencingmanagement.master.service.contract.StateServiceBaseImpl;
import com.egovja.tatransform.licencingmanagement.master.service.impl.standard.StateServiceImpl;
import io.astefanutti.metrics.aspectj.Metrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.util.CollectionUtils;

/**
 * This StateServiceValidationImpl provides validation for state service.
 *
 * @author akshay
 * @since 2021-02-25
 */
@Service
@Metrics(registry = "StateServiceValidationImpl")
public class StateServiceValidationImpl extends StateServiceBaseImpl implements StateService {

    @Autowired
    StateServiceImpl stateService;

    @PostConstruct
    public void setNextDecorator() {
        super.setNextDecorator(stateService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Timed(name = "getRevisions")
    public Page<StateInfo> getRevisions(
            RevisionSearchFilter searchFilter,
            Pageable pageable,
            ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException {
        return stateService.getRevisions(searchFilter, pageable, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    @Timed(name = "createState")
    public StateInfo createState(StateInfo stateInfo, ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException,
            DataValidationErrorException {
        if (stateInfo == null) {
            throw new MissingParameterException("State is missing");
        }
        // CREATE
        List<ValidationResultInfo> validationResultInfos
                = this.validateState(Constant.CREATE_VALIDATION, stateInfo, contextInfo);
        if (ValidationUtils.containsErrors(validationResultInfos, ErrorLevel.ERROR)) {
            throw new DataValidationErrorException("Error(s) occurred validating",
                    validationResultInfos);
        }
        return stateService.createState(stateInfo, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    @Timed(name = "updateState")
    public StateInfo updateState(StateInfo stateInfo, ContextInfo contextInfo)
            throws DoesNotExistException,
            OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException,
            VersionMismatchException,
            DataValidationErrorException {
        if (stateInfo == null) {
            throw new MissingParameterException("State is missing");
        }
        // UPDATE
        List<ValidationResultInfo> validationResultInfos
                = this.validateState(Constant.UPDATE_VALIDATION, stateInfo, contextInfo);
        if (ValidationUtils.containsErrors(validationResultInfos, ErrorLevel.ERROR)) {
            throw new DataValidationErrorException("Error(s) occurred "
                    + "validating", validationResultInfos);
        }
        return stateService.updateState(stateInfo, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    @Timed(name = "searchStatesByRefObjectUriAndName")
    public Page<StateInfo> searchStatesByRefObjectUriAndName(
            List<String> ids,
            String refObjectUri, String stateName,
            Pageable pageable, ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException {
        if (CollectionUtils.isEmpty(ids) && StringUtils.isEmpty(refObjectUri)) {
            throw new MissingParameterException("refObjectUri is missing");
        }
        return stateService.searchStatesByRefObjectUriAndName(
                ids,
                refObjectUri,
                stateName,
                pageable,
                contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    @Timed(name = "getStateById")
    public StateInfo getStateById(String stateId, ContextInfo contextInfo)
            throws DoesNotExistException,
            OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException {
        if (StringUtils.isEmpty(stateId)) {
            throw new MissingParameterException("id is missing");
        }
        return stateService.getStateById(stateId, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    @Timed(name = "validateState")
    public List<ValidationResultInfo> validateState(String validationTypeKey,
            StateInfo stateInfo,
            ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException {
        // VALIDATE
        List<ValidationResultInfo> errors = new ArrayList<>();
        StateInfo originalInfo = null;
        trimState(stateInfo);

        // check Common Required
        this.validateCommonRequired(stateInfo, errors);

        // check Common Unique
        this.validateCommonUnique(stateInfo,
                validationTypeKey,
                errors,
                contextInfo);

        switch (validationTypeKey) {
            case Constant.UPDATE_VALIDATION:

                // get the info
                if (stateInfo.getId() != null) {
                    try {
                        originalInfo
                                = this.stateService.getStateById(
                                        stateInfo.getId(),
                                        contextInfo);
                    } catch (DoesNotExistException ex) {
                        String fieldName = "id";
                        errors.add(new ValidationResultInfo(fieldName,
                                ErrorLevel.ERROR,
                                "The id supplied to the update does not exists"));
                    }
                }

                if (ValidationUtils.containsErrors(errors, ErrorLevel.ERROR)) {
                    return errors;
                }

                this.validateUpdateState(errors,
                        stateInfo,
                        originalInfo);
                break;
            case Constant.CREATE_VALIDATION:
                this.validateCreateState(errors, stateInfo, contextInfo);
                break;
            default:
                throw new InvalidParameterException("Invalid validationTypeKey");
        }

        // For :Unique Id
        validateStateInfoId(stateInfo,
                errors);
        // For :Unique RefObjectUri
        validateStateInfoRefObjectUri(stateInfo,
                errors);
        // For :Unique EffectiveDates
        validateStateInfoEffectiveDates(
                stateInfo,
                errors);
        // For :Name
        validateStateInfoName(stateInfo,
                errors);
        // For :Description
        validateStateInfoDescription(stateInfo,
                originalInfo,
                validationTypeKey,
                errors,
                contextInfo);
        // For :Meta
        validateStateInfoMeta(stateInfo,
                originalInfo,
                validationTypeKey,
                errors,
                contextInfo);

        return errors;
    }

    //validate update
    protected void validateUpdateState(List<ValidationResultInfo> errors,
            StateInfo stateInfo,
            StateInfo originalInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException {
        // required validation
        ValidationUtils.validateRequired(
                stateInfo.getId(),
                "id",
                errors);
        //check the meta required
        if (ValidationUtils.metaIsEmpty(stateInfo.getMeta())) {
            String fieldName = "meta";
            errors.add(new ValidationResultInfo(fieldName,
                    ErrorLevel.ERROR,
                    fieldName + " must be provided"));
        }
        // check meta version id
        if (stateInfo.getMeta().getVersion() == null
                || !stateInfo.getMeta().getVersion()
                        .equals(originalInfo.getMeta().getVersion())) {
            String fieldName = "meta.version";
            errors.add(new ValidationResultInfo(fieldName,
                    ErrorLevel.ERROR,
                    "someone else has updated the state since you"
                    + " started updating, you might want to refresh"
                    + " your copy."));
        }
    }

    //validate create
    protected void validateCreateState(List<ValidationResultInfo> errors,
            StateInfo stateInfo,
            ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException {
        if (stateInfo.getId() != null) {
            try {
                this.stateService.getStateById(stateInfo.getId(),
                        contextInfo);
                // if info found with same id than
                String fieldName = "id";
                errors.add(
                        new ValidationResultInfo(fieldName,
                                ErrorLevel.ERROR,
                                "The id supplied to the create already"
                                + " exists"));
            } catch (DoesNotExistException ex) {
                // This is ok becuase created id should be unique
            }
        }
        // check at a creation time meta should be null
        if (!ValidationUtils.metaIsEmpty(stateInfo.getMeta())) {
            String fieldName = "meta";
            errors.add(
                    new ValidationResultInfo(fieldName,
                            ErrorLevel.ERROR,
                            "no meta data should be supplied on a"
                            + " create"));
        }
    }

    //Validate Required
    protected void validateCommonRequired(StateInfo stateInfo,
            List<ValidationResultInfo> errors) {
        //check the name required
        ValidationUtils
                .validateRequired(
                        stateInfo.getName(),
                        "name",
                        errors);
        //check the refObjectUri required
        ValidationUtils
                .validateRequired(
                        stateInfo.getRefObjectUri(),
                        "refObjectUri",
                        errors);
    }

    //Validate Common Unique
    protected void validateCommonUnique(StateInfo stateInfo,
            String validationTypeKey,
            List<ValidationResultInfo> errors,
            ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException {
        // check unique field
        if (stateInfo.getRefObjectUri() != null && stateInfo.getName() != null) {
            Page<StateInfo> states
                    = this.stateService
                            .searchStatesByRefObjectUriAndName(
                                    null,
                                    stateInfo.getRefObjectUri(),
                                    stateInfo.getName(),
                                    Constant.FULL_PAGE,
                                    contextInfo);

            // if info found with same name than and not current id
            boolean flag
                    = states.stream().anyMatch(s -> ((validationTypeKey.equals(Constant.CREATE_VALIDATION)
                    || !s.getId().equals(stateInfo.getId()))
                    && s.getName().equalsIgnoreCase(stateInfo.getName())));
            if (flag) {
                String fieldName = "name";
                errors.add(
                        new ValidationResultInfo(fieldName,
                                ErrorLevel.ERROR,
                                "State with given name are already exists"));
            }
        }
    }

    //Validation For :Id
    protected void validateStateInfoId(StateInfo stateInfo,
            List<ValidationResultInfo> errors) {
        ValidationUtils.validateNotEmpty(
                stateInfo.getId(),
                "id",
                errors);
    }

    //Validation For :Name
    protected void validateStateInfoName(StateInfo stateInfo,
            List<ValidationResultInfo> errors) {
        ValidationUtils.validatePattern(
                stateInfo.getName(),
                "name",
                Constant.ALLOWED_CHARS_IN_NAMES,
                "Only alphanumeric and " + Constant.ALLOWED_CHARS_IN_NAMES + " are allowed.",
                errors);
        ValidationUtils.validateLength(
                stateInfo.getName(),
                "name",
                3,
                Integer.MAX_VALUE,
                errors);
    }

    //Validation For :Description
    protected void validateStateInfoDescription(StateInfo stateInfo,
            StateInfo originalInfo,
            String validationTypeKey,
            List<ValidationResultInfo> errors,
            ContextInfo contextInfo) {
        // For :Description
        // write validations that you want to validate for this field ...
    }

    //Validation For :Meta
    protected void validateStateInfoMeta(StateInfo stateInfo,
            StateInfo originalInfo,
            String validationTypeKey,
            List<ValidationResultInfo> errors,
            ContextInfo contextInfo) {
        // For :Meta
        // write validations that you want to validate for this field ...
    }

    //Validation For :RefObjectUri
    protected void validateStateInfoRefObjectUri(StateInfo stateInfo,
            List<ValidationResultInfo> errors) {
        ValidationUtils.validateLength(
                stateInfo.getRefObjectUri(),
                "refObjectUri",
                3,
                Integer.MAX_VALUE,
                errors);
    }

    //Validation For :EffectiveDates
    protected void validateStateInfoEffectiveDates(
            StateInfo stateInfo,
            List<ValidationResultInfo> errors) {
        ValidationUtils.validateEffectiveDates(
                stateInfo.getEffectiveDate(),
                stateInfo.getExpirationDate(),
                errors);
    }

    //trim all parish field
    protected void trimState(StateInfo stateInfo) {
        if (stateInfo.getDescription() != null) {
            if (stateInfo.getDescription().getFormatted() != null) {
                stateInfo
                        .getDescription()
                        .setFormatted(stateInfo
                                .getDescription()
                                .getFormatted()
                                .trim());
            }
            if (stateInfo.getDescription().getPlain() != null) {
                stateInfo
                        .getDescription()
                        .setPlain(stateInfo
                                .getDescription()
                                .getPlain()
                                .trim());
            }
        }
        if (stateInfo.getId() != null) {
            stateInfo.setId(stateInfo.getId().trim());
        }
        if (stateInfo.getName() != null) {
            stateInfo.setName(stateInfo.getName().trim());
        }
        if (stateInfo.getRefObjectUri() != null) {
            stateInfo.setRefObjUri(stateInfo.getRefObjectUri().trim());
        }
    }

}
