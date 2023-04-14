/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.master.service.impl.dao;

import com.codahale.metrics.annotation.Timed;
import com.egovja.tatransform.audit.filter.RevisionSearchFilter;
import com.egovja.tatransform.audit.utils.AuditUtils;
import com.egovja.tatransform.licencingmanagement.common.constant.Constant;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ContextInfo;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ValidationResultInfo;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.*;
import com.egovja.tatransform.licencingmanagement.master.dto.info.TypeInfo;
import com.egovja.tatransform.licencingmanagement.master.mapper.TypeMapper;
import com.egovja.tatransform.licencingmanagement.master.model.TypeEntity;
import com.egovja.tatransform.licencingmanagement.master.repository.TypeRepository;
import com.egovja.tatransform.licencingmanagement.master.service.contract.TypeService;
import io.astefanutti.metrics.aspectj.Metrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import org.springframework.util.CollectionUtils;

/**
 * This TypeServiceDaoImpl contains DAO implementation for type service.
 *
 * @author akshar
 * @since 2021-2-17
 */
@Service
@Metrics(registry = "TypeServiceDaoImpl")
public class TypeServiceDaoImpl implements TypeService {

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    AuditUtils auditUtils;

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
        return auditUtils.<TypeInfo, TypeEntity, String>getRevisionEntitiesPage(
                TypeEntity.class,
                searchFilter.getIds(),
                searchFilter,
                false,
                pageable,
                TypeMapper::convertTypeToTypeInfo
        );
    }

    /**
     * {@inheritdoc}
     */
    @Override
    @Timed(name = "createType")
    public TypeInfo createType(
            TypeInfo typeInfo,
            String refObjectUri,
            ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException,
            DataValidationErrorException {

        if (StringUtils.isEmpty(contextInfo.getEmail())) {
            throw new PermissionDeniedException("Can't parse PrincipalUserName from Context");
        }
        TypeEntity type = TypeMapper.convertTypeInfoToType(typeInfo);
        if (StringUtils.isEmpty(type.getId())) {
            type.setId(UUID.randomUUID().toString());
        }
        Page<Object[]> types;
        types = typeRepository
                .findByRefObjectUri(type.getRefObjectUri(),
                        Constant.LAST_CRETED_TYPE_RECORD_PAGE);
        if (types.getContent().size() == 1) {
            TypeEntity lastCreatedType = (TypeEntity) types.getContent().get(0)[0];
            type.setCode(lastCreatedType.getCode() + 1);
        } else {
            type.setCode(1);
        }
        type.setVersion(null);
        typeRepository.save(type);
        return TypeMapper.convertTypeToTypeInfo(type);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    @Timed(name = "updateType")
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

        Optional<TypeEntity> typeOptional = typeRepository.findById(typeInfo.getId());
        if (!typeOptional.isPresent()) {
            throw new DoesNotExistException("Type not found");
        }
        TypeEntity type = TypeMapper.convertTypeInfoToType(typeInfo);
        if (StringUtils.isEmpty(contextInfo.getEmail())) {
            throw new PermissionDeniedException("Can't parse PrincipalUserName from Context");
        }
        type.setCode(typeOptional.get().getCode());
        if (type.getVersion() == null) {
            throw new VersionMismatchException("Version misMatch");
        }
        typeRepository.save(type);

        return TypeMapper.convertTypeToTypeInfo(type);
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
            Pageable pageable,
            ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException {

        if (!CollectionUtils.isEmpty(ids)) {
            return this.searchTypesById(ids, pageable);
        } else {
            Page<Object[]> types;
            Integer codeInteger = null;
            if (StringUtils.isEmpty(name)) {
                name = "";
            }
            if (!StringUtils.isEmpty(code)) {
                codeInteger = Integer.parseInt(code);
            }
            if (StringUtils.isEmpty(stateId)) {
                stateId = null;
            }
            if (!StringUtils.isEmpty(name) || !StringUtils.isEmpty(stateId)) {
                types = typeRepository
                        .findByRefObjectUriAndNameContainingIgnoreCase(
                                stateId,
                                codeInteger,
                                refObjectUri,
                                name,
                                pageable);
            } else {
                types = typeRepository
                        .findByRefObjectUri(refObjectUri, pageable);
            }
            if (types.isEmpty()) {
                return new PageImpl<>(new ArrayList<>(),
                        pageable,
                        0);
            }
            List<TypeInfo> typeInfos = TypeMapper.convertTypeObjectsToTypeInfos(types.getContent());
            return new PageImpl<>(typeInfos,
                    pageable,
                    types.getTotalElements());
        }

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
        if (StringUtils.isEmpty(typeId)) {
            throw new DoesNotExistException("Type by id :"
                    + typeId
                    + " not found");
        }
        Optional<TypeEntity> typeOptional = typeRepository.findById(typeId);
        if (!typeOptional.isPresent()) {
            throw new DoesNotExistException("Type :"
                    + typeId
                    + " not found");
        }
        return TypeMapper.convertTypeToTypeInfo(typeOptional.get());
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
        return new ArrayList<>();
    }

    @Timed(name = "searchTypesById")
    public Page<TypeInfo> searchTypesById(
            List<String> ids,
            Pageable pageable)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException {
        List<TypeEntity> types = typeRepository.findTypesByIds(ids);
        if (CollectionUtils.isEmpty(types)) {
            return new PageImpl<>(
                    new ArrayList<>(),
                    pageable,
                    0);
        }

        List<TypeInfo> typeInfos = TypeMapper.convertTypesToTypeInfos(types);

        return new PageImpl<>(typeInfos,
                pageable,
                typeInfos.size());
    }

}
