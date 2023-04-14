/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.iface;

/**
 * This Interface is provides contract for RichTextInfo.
 *
 * @author Hiren Morzariya
 * @since 2021-2-11
 */
public interface RichText {

    /**
     * Plain text version of the rich text with all the special formatting
     * stripped out.
     *
     * @return Plain Text
     */
    public String getPlain();

    /**
     * Formatted version of the rich text with all the formatting included.
     *
     * @return Formatted Text
     */
    public String getFormatted();

}
