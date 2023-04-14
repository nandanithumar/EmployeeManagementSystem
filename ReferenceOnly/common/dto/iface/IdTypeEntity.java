/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.iface;

/**
 *
 * @author akshar
 */
public interface IdTypeEntity extends HasId, TypeEntity {

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
