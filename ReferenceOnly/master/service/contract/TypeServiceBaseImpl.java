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
import com.egovja.tatransform.licencingmanagement.master.dto.info.TypeInfo;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This is Base implementation for TypeService Service.
 *
 * @author sudip
 * @since 2021-12-13
 */
public class TypeServiceBaseImpl implements TypeService {

    TypeService typeService;

    public void setNextDecorator(TypeService typeService) {
        this.typeService = typeService;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public TypeInfo createType(TypeInfo typeInfo, String refObjectUri, ContextInfo contextInfo) throws OperationFailedException, InvalidParameterException, MissingParameterException, PermissionDeniedException, DataValidationErrorException {
        return typeService.createType(typeInfo, refObjectUri, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public TypeInfo updateType(TypeInfo typeInfo, String refObjectUri, ContextInfo contextInfo) throws DoesNotExistException, OperationFailedException, MissingParameterException, PermissionDeniedException, VersionMismatchException, DataValidationErrorException, InvalidParameterException {
        return typeService.updateType(typeInfo, refObjectUri, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Page<TypeInfo> searchTypesByRefObjectUriAndName(List<String> ids, String stateId, String code, String refObjectUri, String name, Pageable pageable, ContextInfo contextInfo) throws OperationFailedException, MissingParameterException, PermissionDeniedException, InvalidParameterException {
        return typeService.searchTypesByRefObjectUriAndName(ids, stateId, code, refObjectUri, name, pageable, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public List<ValidationResultInfo> validateType(String validationTypeKey, TypeInfo typeInfo, String refObjectUri, ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException, PermissionDeniedException {
        return typeService.validateType(validationTypeKey, typeInfo, refObjectUri, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public TypeInfo getTypeById(String typeId, ContextInfo contextInfo) throws DoesNotExistException, OperationFailedException, MissingParameterException, PermissionDeniedException, InvalidParameterException {
        return typeService.getTypeById(typeId, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Page<TypeInfo> getRevisions(RevisionSearchFilter searchFilter, Pageable pageable, ContextInfo contextInfo) throws OperationFailedException, MissingParameterException, PermissionDeniedException, InvalidParameterException {
        return typeService.getRevisions(searchFilter, pageable, contextInfo);
    }

}
