package com.egovja.tatransform.licencingmanagement.common.model;

import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * This model used in model those, which contains meta and typeId.
 */
@Audited
@MappedSuperclass
public class TypeEntity extends MetaEntity {

    @Column(name = "type_id")
    private String typeId;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
