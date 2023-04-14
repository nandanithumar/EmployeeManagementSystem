/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.master.service.impl.standard;

import com.codahale.metrics.annotation.Timed;
import com.egovja.tatransform.audit.filter.RevisionSearchFilter;
import com.egovja.tatransform.licencingmanagement.common.constant.Constant;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ContextInfo;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ValidationResultInfo;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.*;
import com.egovja.tatransform.licencingmanagement.master.dto.info.TypeInfo;
import com.egovja.tatransform.licencingmanagement.master.service.contract.TypeService;
import com.egovja.tatransform.licencingmanagement.master.service.contract.TypeServiceBaseImpl;
import com.egovja.tatransform.licencingmanagement.master.service.impl.dao.TypeServiceDaoImpl;
import io.astefanutti.metrics.aspectj.Metrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import javax.annotation.PostConstruct;

/**
 * This TypeServiceImpl is for standard implementation for type service.
 *
 * @author akshar
 * @since 2021-2-17
 */
@Service
@Metrics(registry = "TypeServiceImpl")
public class TypeServiceImpl extends TypeServiceBaseImpl implements TypeService {

    @Autowired
    TypeServiceDaoImpl typeServiceDao;

    @PostConstruct
    public void setNextDecorator() {
        super.setNextDecorator(typeServiceDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Timed(name = "getRevisions")
    public Page<TypeInfo> getRevisions(
            RevisionSearchFilter searchFilter,
            Pageable pageable,
            ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException {
        return typeServiceDao.getRevisions(searchFilter, pageable, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    @Timed(name = "createType")
    @Transactional(rollbackFor = {
        DoesNotExistException.class,
        OperationFailedException.class,
        MissingParameterException.class,
        PermissionDeniedException.class,
        InvalidParameterException.class,
        VersionMismatchException.class,
        DataValidationErrorException.class
    })
    public TypeInfo createType(
            TypeInfo typeInfo,
            String refObjectUri,
            ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException,
            DataValidationErrorException {
        if (StringUtils.isEmpty(typeInfo.getId())) {

            String id = Constant.TYPE_ID_PRIFIX
                    + typeInfo.getRefObjectUri().toLowerCase()
                    + Constant.DOT
                    + typeInfo.getName().replaceAll("\\s", "").toLowerCase();

            typeInfo.setId(id);
        }
        return typeServiceDao.createType(typeInfo, refObjectUri, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    @Timed(name = "updateType")
    @Transactional(rollbackFor = {
        DoesNotExistException.class,
        OperationFailedException.class,
        MissingParameterException.class,
        PermissionDeniedException.class,
        InvalidParameterException.class,
        VersionMismatchException.class,
        DataValidationErrorException.class
    })
    public TypeInfo updateType(
            TypeInfo typeInfo,
            String refObjectUri,
            ContextInfo contextInfo)
            throws DoesNotExistException,
            OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException,
            VersionMismatchException,
            DataValidationErrorException {
        return typeServiceDao.updateType(typeInfo, refObjectUri, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    @Timed(name = "searchTypesByRefObjectUriAndName")
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
            InvalidParameterException {
        return typeServiceDao.searchTypesByRefObjectUriAndName(
                ids,
                stateId,
                code,
                refObjectUri,
                name,
                pageable,
                contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    @Timed(name = "getTypeById")
    public TypeInfo getTypeById(String typeId, ContextInfo contextInfo)
            throws DoesNotExistException,
            OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException {
        return typeServiceDao.getTypeById(typeId, contextInfo);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    @Timed(name = "validateType")
    public List<ValidationResultInfo> validateType(
            String validationTypeKey,
            TypeInfo typeInfo,
            String refObjectUri,
            ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        return typeServiceDao.validateType(validationTypeKey, typeInfo, refObjectUri, contextInfo);
    }

}
