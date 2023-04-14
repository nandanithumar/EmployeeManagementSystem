/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.info;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.IdTypeEntity;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 *
 * @author akshar
 */
public class IdTypeEntityInfo extends TypeEntityInfo
        implements IdTypeEntity, Serializable {

    @ApiModelProperty(notes = "The unique id of the model",
            allowEmptyValue = false,
            example = "jm.org.ta.state.viapointinfo.active",
            dataType = "String/Integer",
            required = false)
    private String id;

    /**
     * Constructs a new IdEntityInfo.
     */
    public IdTypeEntityInfo() {
    }

    /**
     * Constructs a new IdStateEntityInfo from another IdStateEntity.
     *
     * @param idTypeEntity the IdStateEntity to copy
     */
    public IdTypeEntityInfo(IdTypeEntityInfo idTypeEntity) {
        super(idTypeEntity);
        if (idTypeEntity != null) {
            this.id = idTypeEntity.getId();
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
