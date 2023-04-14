/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.iface;

import com.egovja.tatransform.licencingmanagement.common.dto.info.RichTextInfo;

/**
 * This Interface is provides contract for Info that has RichTextInfo.
 *
 * @author Hiren Morzariya
 * @since 2021-2-11
 */
public interface HasRichText {

    /**
     * plain description and formatted description for the structure.
     *
     * @return Description
     *
     */
    public RichTextInfo getDescription();
}
