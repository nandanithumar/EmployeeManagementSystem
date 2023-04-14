/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.info;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.HasState;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * This class is provides implementation for HasState.
 *
 * @author Hiren Morzariya
 * @since 2021-2-19
 */
public abstract class HasStateInfo implements HasState, Serializable {

    @ApiModelProperty(notes = "The id of state model",
            allowEmptyValue = false,
            example = "jm.org.ta.state.viapointinfo.active",
            dataType = "String",
            required = true,
            reference = "state(id)")
    private String stateId;

    HasStateInfo() {
    }

    /**
     * This is copy ConStructure that make deep copy of the HasStateInfo.
     *
     * @param hasStateInfo hasStateInfo
     */
    HasStateInfo(HasStateInfo hasStateInfo) {
        if (hasStateInfo != null) {
            this.stateId = hasStateInfo.getStateId();
        }
    }

    @Override
    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

}
