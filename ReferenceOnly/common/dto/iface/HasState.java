/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.iface;

/**
 * A common service pattern for entities.This interface is applied to entities
 * with a State.
 *
 * @author Hiren Morzariya
 * @since 2021/02/19
 */
public interface HasState {

    /**
     * A unique identifier for the state of this object.
     *
     * @return
     * @return State Id
     */
    public String getStateId();
}
