/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.iface;

/**
 * A common interface pattern for service entities.This interface is applied to
 * entities without a primary identifier but with type, state, and meta
 * information.
 *
 * @author Hiren Morzariya
 * @since 2021/02/19
 */
public interface TypeStateEntity extends HasType, HasState, HasMeta {

}
