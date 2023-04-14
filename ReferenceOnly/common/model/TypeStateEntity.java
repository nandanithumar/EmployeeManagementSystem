/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.model;

import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * This model used in model those contains meta,typeId and stateId.
 *
 * @author sudip
 * @since 2021-2-25
 */
@Audited
@MappedSuperclass
public class TypeStateEntity extends MetaEntity {

    @Column(name = "type_id")
    private String typeId;

    @Column(name = "state_id")
    private String stateId;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

}
