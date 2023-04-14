/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.iface;

import java.util.Date;

/**
 * This Interface is provides contract for EffectiveDates.
 *
 * @author Hiren Morzariya
 * @since 2021-2-17
 */
public interface EffectiveDates {

    /**
     * Date/time this object became effective. Must be less than or equal to the
     * expirationDate specified.
     *
     * @return Effective Date
     */
    public Date getEffectiveDate();

    /**
     * Date/time this relationship is no longer effective. Must be greater than
     * or equal to the effectiveDate specified.
     *
     * @return Expiration Date
     */
    public Date getExpirationDate();
}
