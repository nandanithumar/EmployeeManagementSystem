/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.info;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.HasId;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * This class is provides implementation for HasId.
 *
 * @author Hiren Morzariya
 * @since 2021-2-19
 */
public class HasIdInfo implements HasId, Serializable {

    @ApiModelProperty(notes = "The unique id of the model",
            allowEmptyValue = false,
            example = "1",
            dataType = "String/Integer",
            required = false)
    private String id;

    public HasIdInfo() {
    }

    /**
     * This is copy ConStructure that make deep copy of the HasId.
     *
     * @param hasId hasId
     */
    public HasIdInfo(HasId hasId) {
        if (hasId != null) {
            this.id = hasId.getId();
        }
    }

    @Override
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
