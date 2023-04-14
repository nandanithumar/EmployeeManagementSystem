/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.iface;

/**
 * A common interface pattern for service entities.
 *
 * @author Hiren Morzariya
 * @since 2021/02/19
 */
public interface TypeStatelessEntity extends HasPrimaryKey, HasMeta {

    /**
     * A display name for this entity.
     *
     * @return
     * @return Name
     */
    public String getName();

    /**
     * A description of the entity.
     *
     * @return
     * @return Description
     */
    public RichText getDescription();
}
