/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.info;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.IdType;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * This class is provides implementation for IdTypeInfo.
 *
 * @author sudip
 * @since 2021/03/25
 */
public class IdTypeInfo extends HasTypeInfo implements IdType, Serializable {

    @ApiModelProperty(notes = "The unique id of the model",
            allowEmptyValue = false,
            example = "jm.org.ta.state.viapointinfo.active",
            dataType = "String/Integer",
            required = false)
    private String id;

    /**
     * Constructs a new IdTypeInfo.
     */
    public IdTypeInfo() {
    }

    /**
     * Constructs a new IdTypeInfo from another IdType.
     *
     * @param idType the idType to copy
     */
    public IdTypeInfo(IdTypeInfo idType) {
        super(idType);
        if (idType != null) {
            this.id = idType.getId();
        }
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
