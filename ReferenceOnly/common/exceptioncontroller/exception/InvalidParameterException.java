/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception;

/**
 * This exception mainly used when parameter are invalid.It Also contains error
 * message with HTTP status.
 *
 * @author Hiren Morzariya
 * @since 2021-2-11
 */
public class InvalidParameterException extends Exception {

    public InvalidParameterException(String message) {
        super(message);
    }

    public InvalidParameterException(String message, Throwable cause) {
        super(message, cause);
    }

}
