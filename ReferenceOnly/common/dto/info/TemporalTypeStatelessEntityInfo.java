/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.info;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.TemporalTypeStatelessEntity;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * This class is provides implementation for TemporalEntity.
 *
 * @author Hiren Morzariya
 * @since 2021/02/19
 */
public class TemporalTypeStatelessEntityInfo
        extends IdTypeStatelessEntityInfo
        implements TemporalTypeStatelessEntity, Serializable {

    @ApiModelProperty(notes = "The effectiveDate for the model",
            allowEmptyValue = false,
            example = "2014-02-28",
            dataType = "Date",
            required = false)
    private Date effectiveDate;

    @ApiModelProperty(notes = "The expirationDate for the model",
            allowEmptyValue = false,
            example = "2014-02-28",
            dataType = "Date",
            required = false)
    private Date expirationDate;

    /**
     * Constructs a new TemporalEntityInfo.
     */
    public TemporalTypeStatelessEntityInfo() {
    }

    /**
     * Constructs a new TemporalEntityInfo from another TemporalEntity.
     *
     * @param temporalTypeStatelessEntity the temporalTypeStatelessEntity top
     * copy
     */
    public TemporalTypeStatelessEntityInfo(
            TemporalTypeStatelessEntity temporalTypeStatelessEntity) {
        super(temporalTypeStatelessEntity);
        if (temporalTypeStatelessEntity != null) {
            Date newEffectiveDate = temporalTypeStatelessEntity.getEffectiveDate();
            Date newExpirationDate = temporalTypeStatelessEntity.getExpirationDate();
            this.effectiveDate = new Date(newEffectiveDate.getTime());
            this.expirationDate = new Date(newExpirationDate.getTime());
        }
    }

    @Override
    public Date getEffectiveDate() {
        return effectiveDate != null
                ? new Date(effectiveDate.getTime()) : null;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate != null
                ? new Date(effectiveDate.getTime()) : null;
    }

    @Override
    public Date getExpirationDate() {
        return expirationDate != null
                ? new Date(expirationDate.getTime()) : null;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate != null
                ? new Date(expirationDate.getTime()) : null;
    }
}
