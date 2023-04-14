/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.iface;

/**
 * An entity that has a beginning and an end.
 *
 * A null expiration date is interpreted as forever. A null effective date is
 * interpreted as the Big Bang.
 *
 * @author Hiren Morzariya
 * @since 2021/02/19
 */
public interface TemporalEntity extends IdEntity, EffectiveDates {

}
