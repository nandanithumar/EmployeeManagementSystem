/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception;

/**
 * This exception mainly used when updating old data that has been already
 * updated.It Also contains error message with HTTP status.
 *
 * @author Hiren Morzariya
 * @since 2021-2-11
 */
public class VersionMismatchException extends Exception {

    public VersionMismatchException(String message) {
        super(message);
    }

    public VersionMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

}
