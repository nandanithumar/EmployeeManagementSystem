/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.master.mapper;

import com.egovja.tatransform.licencingmanagement.common.dto.info.MetaInfo;
import com.egovja.tatransform.licencingmanagement.common.dto.info.RichTextInfo;
import com.egovja.tatransform.licencingmanagement.master.dto.info.StateInfo;
import com.egovja.tatransform.licencingmanagement.master.model.StateEntity;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This mapper is used for conversion between StateEntity and StateInfo.
 *
 * @author akshay
 * @since 2021-02-25
 */
public class StateMapper {

    private StateMapper() {
    }

    /**
     * Convert StateEntity to StateInfo.
     *
     * @param state state
     * @return StateInfo converted from StateEntity
     */
    public static StateInfo convertStateToStateInfo(StateEntity state) {

        StateInfo stateInfo = new StateInfo();
        MetaInfo meta = new MetaInfo();
        RichTextInfo description = new RichTextInfo();

        description.setFormatted(state.getDescriptionFormatted());
        description.setPlain(state.getDescriptionPlain());

        meta.setCreatedAt(state.getCreatedAt());
        meta.setCreatedBy(state.getCreatedBy());
        meta.setUpdatedAt(state.getUpdatedAt());
        meta.setUpdatedBy(state.getUpdatedBy());
        meta.setVersion(state.getVersion());

        stateInfo.setId(state.getId());
        stateInfo.setName(state.getName());
        stateInfo.setRefObjUri(state.getRefObjectUri());
        stateInfo.setEffectiveDate(state.getEffectiveDate());
        stateInfo.setExpirationDate(state.getExpirationDate());
        stateInfo.setIsInitialState(state.getIsInitialState());
        stateInfo.setDescription(description);
        stateInfo.setMeta(meta);

        return stateInfo;
    }

    /**
     * Convert list of StateEntity to list of StateInfo.
     *
     * @param states list of state
     * @return List of StateInfo converted from List of StateEntity
     */
    public static List<StateInfo> convertStatesToStateInfos(List<StateEntity> states) {
        return states.stream()
                .map(s -> convertStateToStateInfo(s))
                .collect(Collectors.toList());
    }

    /**
     * Convert StateInfo to StateEntity.
     *
     * @param stateInfo stateInfo
     * @return StateEntity converted from StateInfo
     */
    public static StateEntity convertStateInfoToState(StateInfo stateInfo) {

        StateEntity state = new StateEntity();

        state.setId(stateInfo.getId());
        state.setName(stateInfo.getName());
        state.setRefObjectUri(stateInfo.getRefObjectUri());
        state.setIsInitialState(stateInfo.getIsInitialState());

        if (stateInfo.getDescription() != null) {
            state.setDescriptionFormatted(stateInfo.getDescription().getFormatted());
            state.setDescriptionPlain(stateInfo.getDescription().getPlain());
        }

        state.setEffectiveDate(stateInfo.getEffectiveDate());
        state.setExpirationDate(stateInfo.getExpirationDate());

        state.setVersion(stateInfo.getMeta().getVersion());
        return state;
    }

}
