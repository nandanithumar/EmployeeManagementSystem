/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.master.dto.infc;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.TemporalStateEntity;

/**
 * This Interface is provides contract for Type.
 *
 * @author Hiren Morzariya
 * @since 2021-2-17
 */
public interface Type extends TemporalStateEntity {

    /**
     * The Icon of the Type.
     *
     * @return Icon
     * @required
     */
    public String getIcon();

    /**
     * The Reference Object URI of the Type.
     *
     * @return RefObjectUrl
     * @required
     */
    public String getRefObjectUri();

    /**
     * The code of the Type.
     *
     * @return code
     */
    public Integer getCode();

}
