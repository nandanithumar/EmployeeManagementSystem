/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.master.model;

import com.egovja.tatransform.licencingmanagement.common.model.TemporalStateEntity;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This model is mapped to type table in database.
 *
 * @author akshar
 * @since 2021-2-17
 */
@Entity
@Audited
@Table(name = "type")
public class TypeEntity extends TemporalStateEntity {

    @Column(name = "ref_object_uri")
    private String refObjectUri;

    @Column(name = "icon")
    private String icon;

    @Column(name = "code")
    private Integer code;

    public String getRefObjectUri() {
        return refObjectUri;
    }

    public void setRefObjectUri(String refObjectUri) {
        this.refObjectUri = refObjectUri;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Type{" + "id=" + this.getId()
                + ", name=" + this.getName()
                + ", code=" + this.getCode()
                + ", refObjectUri=" + this.getRefObjectUri()
                + ", icon=" + this.getIcon()
                + ", effectiveDate=" + this.getEffectiveDate()
                + ", stateId =" + this.getStateId()
                + ", expirationDate=" + this.getExpirationDate()
                + ", descriptionPlain=" + this.getDescriptionPlain()
                + ", descriptionFormatted=" + this.getDescriptionFormatted()
                + ", createdAt=" + this.getCreatedAt()
                + ", createdBy=" + this.getCreatedBy()
                + ", updatedAt=" + this.getUpdatedAt()
                + ", updatedBy=" + this.getUpdatedBy()
                + ", Version=" + this.getVersion() + '}';
    }

}
