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
import com.egovja.tatransform.licencingmanagement.master.dto.info.StateInfo;
import com.egovja.tatransform.licencingmanagement.master.service.contract.StateService;
import com.egovja.tatransform.licencingmanagement.master.service.contract.StateServiceBaseImpl;
import com.egovja.tatransform.licencingmanagement.master.service.impl.dao.StateServiceDaoImpl;
import io.astefanutti.metrics.aspectj.Metrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import javax.annotation.PostConstruct;

/**
 * This StateServiceImpl is for standard implementation for state service.
 *
 * @author akshay
 * @since 2021-02-25
 */
@Service
@Metrics(registry = "StateServiceImpl")
public class StateServiceImpl extends StateServiceBaseImpl implements StateService {

    @Autowired
    StateServiceDaoImpl stateServiceDao;

    @PostConstruct
    public void setNextDecorator() {
        super.setNextDecorator(stateServiceDao);
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
        return stateServiceDao.getRevisions(searchFilter, pageable, contextInfo);
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

        if (StringUtils.isEmpty(stateInfo.getId())) {

            String id = Constant.STATE_ID_PRIFIX
                    + stateInfo.getRefObjectUri().toLowerCase()
                    + Constant.DOT
                    + stateInfo.getName().replaceAll("\\s", "").toLowerCase();

            stateInfo.setId(id);
        }

        return stateServiceDao.createState(stateInfo, contextInfo);
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
        return stateServiceDao.updateState(stateInfo, contextInfo);
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
        return stateServiceDao.searchStatesByRefObjectUriAndName(
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
        return stateServiceDao.getStateById(stateId, contextInfo);
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
        return stateServiceDao.validateState(
                validationTypeKey,
                stateInfo,
                contextInfo);
    }

}
