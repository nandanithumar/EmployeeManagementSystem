/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.info;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.HasType;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * This class is provides implementation for HasType.
 *
 * @author Hiren Morzariya
 * @since 2021-2-19
 */
public abstract class HasTypeInfo implements HasType, Serializable {

    @ApiModelProperty(notes = "The id of type model",
            allowEmptyValue = false,
            example = "jm.org.ta.type.viapointinfo.terminal",
            dataType = "String",
            required = true,
            reference = "type(id)")
    private String typeId;

    HasTypeInfo() {
    }

    /**
     * This is copy ConStructure that make deep copy of the HasTypeInfo.
     *
     * @param hasTypeInfo hasTypeInfo
     */
    HasTypeInfo(HasTypeInfo hasTypeInfo) {
        this.typeId = hasTypeInfo.getTypeId();
    }

    @Override
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

}
