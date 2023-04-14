/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.iface;

/**
 * This Interface is provides contract for LocaleInfo.
 *
 * @author Hiren Morzariya
 * @since 2021-2-11
 */
public interface Locale {

    /**
     * The language portion of the locale information used in this context.
     *
     * @return Locale Language
     */
    public String getLocaleLanguage();

    /**
     * The language variant portion of the locale information to be used in this
     * context .
     *
     * @return Locale Language Variant
     */
    public String getLocaleVariant();

    /**
     * The language Region portion of the locale information to be used in this
     * context.
     *
     * @return Locale Region
     */
    public String getLocaleRegion();

    /**
     * The language Script to be used in this context.
     *
     * @return Locale Script
     */
    public String getLocaleScript();
}
