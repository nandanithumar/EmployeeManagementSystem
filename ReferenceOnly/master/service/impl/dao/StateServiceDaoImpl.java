package com.egovja.tatransform.licencingmanagement.master.service.impl.dao;

import com.codahale.metrics.annotation.Timed;
import com.egovja.tatransform.audit.filter.RevisionSearchFilter;
import com.egovja.tatransform.audit.utils.AuditUtils;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ContextInfo;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ValidationResultInfo;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.*;
import com.egovja.tatransform.licencingmanagement.master.dto.info.StateInfo;
import com.egovja.tatransform.licencingmanagement.master.mapper.StateMapper;
import com.egovja.tatransform.licencingmanagement.master.model.StateEntity;
import com.egovja.tatransform.licencingmanagement.master.repository.StateRepository;
import com.egovja.tatransform.licencingmanagement.master.service.contract.StateService;
import io.astefanutti.metrics.aspectj.Metrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import org.springframework.util.CollectionUtils;

/**
 * This StateServiceDaoImpl contains DAO implementation for state service.
 *
 * @author akshay
 * @since 2021-02-25
 */
@Service
@Metrics(registry = "StateServiceDaoImpl")
public class StateServiceDaoImpl implements StateService {

    @Autowired
    StateRepository stateRepository;

    @Autowired
    AuditUtils auditUtils;

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
        return auditUtils.<StateInfo, StateEntity, String>getRevisionEntitiesPage(
                StateEntity.class,
                searchFilter.getIds(),
                searchFilter,
                false,
                pageable,
                StateMapper::convertStateToStateInfo
        );
    }

    /**
     * {@inheritdoc}
     */
    @Override
    @Timed(name = "createState")
    @Transactional
    public StateInfo createState(StateInfo stateInfo, ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException,
            DataValidationErrorException {
        if (StringUtils.isEmpty(contextInfo.getEmail())) {
            throw new PermissionDeniedException("Can't parse PrincipalUserName from "
                    + "Context");
        }
        StateEntity state = StateMapper.convertStateInfoToState(stateInfo);
        if (StringUtils.isEmpty(state.getId())) {
            state.setId(UUID.randomUUID().toString());
        }
        state.setVersion(null);
        stateRepository.save(state);
        return StateMapper.convertStateToStateInfo(state);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    @Timed(name = "updateState")
    @Transactional
    public StateInfo updateState(StateInfo stateInfo, ContextInfo contextInfo)
            throws DoesNotExistException,
            OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException,
            VersionMismatchException,
            DataValidationErrorException {
        Optional<StateEntity> stateOptional = stateRepository
                .findById(stateInfo.getId());
        if (!stateOptional.isPresent()) {
            throw new DoesNotExistException("State not found");
        }
        StateEntity state = StateMapper.convertStateInfoToState(stateInfo);
        if (StringUtils.isEmpty(contextInfo.getEmail())) {
            throw new PermissionDeniedException("Can't parse PrincipalUserName "
                    + "from Context");
        }
        if (state.getVersion() == null) {
            throw new VersionMismatchException("Version misMatch");
        }
        state = stateRepository.save(state);
        return StateMapper
                .convertStateToStateInfo(state);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    @Timed(name = "searchStatesByRefObjectUriAndName")
    public Page<StateInfo> searchStatesByRefObjectUriAndName(
            List<String> ids,
            String refObjectUri,
            String stateName,
            Pageable pageable,
            ContextInfo contextInfo)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException {

        if (!CollectionUtils.isEmpty(ids)) {
            return this.searchStatesById(ids, pageable);
        } else {
            Page<StateEntity> states;
            if (!StringUtils.isEmpty(stateName)) {
                states = stateRepository
                        .findByRefObjectUriAndNameContainingIgnoreCase(
                                refObjectUri,
                                stateName,
                                pageable);
            } else {
                states = stateRepository
                        .findByRefObjectUri(refObjectUri, pageable);
            }
            if (states.isEmpty()) {
                return new PageImpl<>(new ArrayList<>(),
                        pageable,
                        0);
            }
            List<StateInfo> stateInfos = StateMapper
                    .convertStatesToStateInfos(states.getContent());
            return new PageImpl<>(stateInfos,
                    pageable,
                    states.getTotalElements());
        }

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
            throw new DoesNotExistException("State by id :"
                    + stateId
                    + " not found");
        }
        Optional<StateEntity> stateOptional = stateRepository.findById(stateId);
        if (!stateOptional.isPresent()) {
            throw new DoesNotExistException("State by id :"
                    + stateId
                    + " not found");
        }
        return StateMapper.convertStateToStateInfo(stateOptional.get());
    }

    /**
     * {@inheritdoc}
     */
    @Override
    @Timed(name = "validateState")
    public List<ValidationResultInfo> validateState(String validationTypeKey,
            StateInfo stateInfo, ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        return new ArrayList<>();
    }

    @Timed(name = "searchStatesById")
    public Page<StateInfo> searchStatesById(
            List<String> ids,
            Pageable pageable)
            throws OperationFailedException,
            MissingParameterException,
            PermissionDeniedException,
            InvalidParameterException {

        List<StateEntity> states
                = stateRepository.findStatesByIds(ids);
        if (CollectionUtils.isEmpty(states)) {
            return new PageImpl<>(
                    new ArrayList<>(),
                    pageable,
                    0);
        }

        List<StateInfo> stateInfos = StateMapper
                .convertStatesToStateInfos(states);

        return new PageImpl<>(stateInfos,
                pageable,
                stateInfos.size());
    }

}
