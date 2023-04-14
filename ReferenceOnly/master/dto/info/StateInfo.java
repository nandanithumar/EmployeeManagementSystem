
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.master.dto.info;

import com.egovja.tatransform.licencingmanagement.common.dto.info.TemporalTypeStatelessEntityInfo;
import com.egovja.tatransform.licencingmanagement.master.dto.infc.State;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * This info is for state DTO that contains all the state model's data.
 *
 * @author hiren
 * @since 2021-02-25
 */
public class StateInfo
        extends TemporalTypeStatelessEntityInfo
        implements State, Serializable {

    @ApiModelProperty(notes = "The refObjectUri",
            allowEmptyValue = false,
            example = "ViaPointInfo",
            dataType = "String",
            required = true)
    private String refObjectUri;

    @ApiModelProperty(notes = "initial state for same refObjectUri",
            example = "true",
            dataType = "Boolean",
            required = false)
    private boolean isInitialState;

    public StateInfo() {
    }

    /**
     * This is copy constructor that make deep copy of the State.
     *
     * @param state
     */
    public StateInfo(State state) {
        super(state);
        if (state != null) {
            this.refObjectUri = state.getRefObjectUri();
            this.isInitialState = state.getIsInitialState();
        }
    }

    @Override
    public String getRefObjectUri() {
        return refObjectUri;
    }

    public void setRefObjUri(String refObjUri) {
        this.refObjectUri = refObjUri;
    }

    @Override
    public boolean getIsInitialState() {
        return isInitialState;
    }

    public void setIsInitialState(boolean isInitialState) {
        this.isInitialState = isInitialState;
    }

    @Override
    public String toString() {
        return "StateInfo{"
                + "id=" + this.getId()
                + ", name=" + this.getName()
                + ", refObjectUri=" + refObjectUri
                + ", isInitialState=" + isInitialState
                + ", effectiveDate" + this.getEffectiveDate()
                + ", expirationDate" + this.getExpirationDate()
                + ", meta=" + this.getMeta()
                + ", description=" + this.getDescription()
                + '}';
    }

}
