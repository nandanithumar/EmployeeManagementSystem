/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.iface;

/**
 * This Interface is provides contract for Info that has Id.
 *
 * @author Hiren Morzariya
 * @since 2021-2-11
 */
public interface HasId extends HasPrimaryKey {

    /**
     * The system assigned unique id to identify this Object. Could be
     * implemented as as sequence number or as a UUID.
     *
     * @return Unique Id
     * @required on updates
     */
    public String getId();
}
