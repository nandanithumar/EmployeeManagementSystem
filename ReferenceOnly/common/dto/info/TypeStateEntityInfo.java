/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.info;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.TypeStateEntity;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * This class is provides implementation for TypeStateEntity.
 *
 * @author Hiren Morzariya
 * @since 2021/02/19
 */
public class TypeStateEntityInfo extends HasMetaInfo implements TypeStateEntity, Serializable {

    @ApiModelProperty(notes = "The id of type model",
            allowEmptyValue = false,
            example = "jm.org.ta.type.viapointinfo.terminal",
            dataType = "String",
            required = true,
            reference = "type(id)")
    private String typeId;

    @ApiModelProperty(notes = "The id of state model",
            allowEmptyValue = false,
            example = "jm.org.ta.state.viapointinfo.active",
            dataType = "String",
            required = true,
            reference = "state(id)")
    private String stateId;

    /**
     * Constructs a new TypeStateEntityInfo.
     */
    public TypeStateEntityInfo() {
    }

    /**
     * Constructs a new TypeStateEntityInfo from another TypeStateEntity.
     *
     * @param typeStateEntity the TypeStateEntity to copy
     */
    public TypeStateEntityInfo(TypeStateEntity typeStateEntity) {
        super(typeStateEntity);
        if (null != typeStateEntity) {
            this.typeId = typeStateEntity.getTypeId();
            this.stateId = typeStateEntity.getStateId();
        }
    }

    @Override
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Override
    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

}
