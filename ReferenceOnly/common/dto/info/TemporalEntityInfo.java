/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.info;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.TemporalEntity;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * This class is provides implementation for TemporalEntity.
 *
 * @author Hiren Morzariya
 * @since 2021/02/19
 */
public class TemporalEntityInfo extends IdEntityInfo implements TemporalEntity, Serializable {

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
    public TemporalEntityInfo() {
    }

    /**
     * Constructs a new TemporalEntityInfo from another TemporalEntity.
     *
     * @param temporalEntity the TemporalEntity top copy
     */
    public TemporalEntityInfo(TemporalEntity temporalEntity) {
        super(temporalEntity);
        if (temporalEntity != null) {
            Date newEffectiveDate = temporalEntity.getEffectiveDate();
            Date newExpirationDate = temporalEntity.getExpirationDate();
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
