/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.info;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.IdStateEntity;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * This class is provides implementation for IdStateEntityInfo.
 *
 * @author Hiren Morzariya
 * @since 2021/03/23
 */
public class IdStateEntityInfo
        extends StateEntityInfo
        implements IdStateEntity, Serializable {

    @ApiModelProperty(notes = "The unique id of the model",
            allowEmptyValue = false,
            example = "jm.org.ta.state.viapointinfo.active",
            dataType = "String/Integer",
            required = false)
    private String id;

    /**
     * Constructs a new IdEntityInfo.
     */
    public IdStateEntityInfo() {
    }

    /**
     * Constructs a new IdStateEntityInfo from another IdStateEntity.
     *
     * @param idStateEntity the IdStateEntity to copy
     */
    public IdStateEntityInfo(IdStateEntity idStateEntity) {
        super(idStateEntity);
        if (idStateEntity != null) {
            this.id = idStateEntity.getId();
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
