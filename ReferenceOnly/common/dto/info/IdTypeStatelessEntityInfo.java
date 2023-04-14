/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.info;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.IdTypeStatelessEntity;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * This class is provides implementation for IdTypeStatelessEntity.
 *
 * @author Hiren Morzariya
 * @since 2021/02/19
 */
public class IdTypeStatelessEntityInfo
        extends TypeStatelessEntityInfo
        implements IdTypeStatelessEntity, Serializable {

    @ApiModelProperty(notes = "The unique id of the model",
            allowEmptyValue = false,
            example = "jm.org.ta.state.viapointinfo.active",
            dataType = "String/Integer",
            required = false)
    private String id;

    /**
     * Constructs a new IdEntityInfo.
     */
    public IdTypeStatelessEntityInfo() {
    }

    /**
     * Constructs a new IdEntityInfo from another IdEntity.
     *
     * @param idEntity the IdEntity to copy
     */
    public IdTypeStatelessEntityInfo(IdTypeStatelessEntity idEntity) {
        super(idEntity);
        if (idEntity != null) {
            this.id = idEntity.getId();
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
