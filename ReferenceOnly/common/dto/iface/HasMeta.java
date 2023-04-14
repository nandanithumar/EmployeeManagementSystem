/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.iface;

import com.egovja.tatransform.licencingmanagement.common.dto.info.MetaInfo;

/**
 * This Interface is provides contract for Info that has Meta.
 *
 * @author Hiren Morzariya
 * @since 2021-2-11
 */
public interface HasMeta {

    /**
     * Create and last update and created info for the structure.
     *
     * @return Meta
     * @readOnly
     * @required on updates
     */
    public MetaInfo getMeta();
}
    