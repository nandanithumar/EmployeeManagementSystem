
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.master.model;

import com.egovja.tatransform.licencingmanagement.common.model.TemporalTypeStatelessEntity;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This model is mapped to state table in database.
 *
 * @author akshay
 * @since 2021-02-25
 */


@Entity
@Audited
@Table(name = "state")
public class StateEntity extends TemporalTypeStatelessEntity {

    @Column(name = "ref_object_uri")
    private String refObjectUri;

    @Column(name = "is_initial_state")
    private boolean isInitialState;

    public String getRefObjectUri() {
        return refObjectUri;
    }

    public void setRefObjectUri(String refObjectUri) {
        this.refObjectUri = refObjectUri;
    }

    public boolean getIsInitialState() {
        return isInitialState;
    }

    public void setIsInitialState(boolean isInitialState) {
        this.isInitialState = isInitialState;
    }

    @Override
    public String toString() {
        return "State{" + "id=" + this.getId()
                + ", name=" + this.getName()
                + ", refObjectUri=" + this.getRefObjectUri()
                + ", isInitialState=" + this.getIsInitialState()
                + ", effectiveDate=" + this.getEffectiveDate()
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
