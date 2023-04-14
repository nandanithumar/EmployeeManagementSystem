/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.model;

import org.hibernate.envers.Audited;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * This model used in model those contains Effective date and Expiration date.
 *
 * @author sudip
 * @since 2021-3-23
 */
@Audited
@MappedSuperclass
public class TemporalStateEntity extends IdStateEntity {

    @Column(name = "effective_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate;

    @Column(name = "expiration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;

    public Date getEffectiveDate() {
        return effectiveDate != null
                ? new Date(effectiveDate.getTime()) : null;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate != null
                ? new Date(effectiveDate.getTime()) : null;
    }

    public Date getExpirationDate() {
        return expirationDate != null
                ? new Date(expirationDate.getTime()) : null;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate != null
                ? new Date(expirationDate.getTime()) : null;
    }
}
