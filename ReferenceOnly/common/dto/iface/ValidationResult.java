/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.iface;

import com.egovja.tatransform.licencingmanagement.common.constant.ErrorLevel;
import java.util.Map;

/**
 * This Interface is provides contract for ValidationResultInfo.
 *
 * @author sudip
 * @since 2021-02-15
 */
public interface ValidationResult {

    /**
     * Element of the Error.
     *
     * @return Element
     */
    public String getElement();

    /**
     * Level of the error.
     *
     * @return Error Level
     */
    public ErrorLevel getLevel();

    /**
     * Message corresponding to error.
     *
     * @return Message Text
     */
    public String getMessage();

    /**
     * Map of key/id and values as display text
     *
     * @return References
     */
    public Map<String, String> getReferences();

    /**
     * StackTrace for error.
     *
     * @return StackTrace
     */
    public String getStackTrace();
}
