/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.controlleradvice;

import com.egovja.tatransform.audit.config.CustomApplicationEventPublisher;
import com.egovja.tatransform.audit.config.CustomRevisionListener;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ValidationResultInfo;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.*;
import com.egovja.tatransform.licencingmanagement.master.service.impl.dao.TypeServiceDaoImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * This class is manage occurred exception and its response error massage.
 *
 * @author Hiren Morzariya
 * @since 2021-2-11
 */
@RestControllerAdvice
public class ExceptionController {

    @Autowired
    private Environment environment;

    @Autowired
    CustomApplicationEventPublisher customApplicationEventPublisher;

    @Autowired
    TypeServiceDaoImpl typeServiceDao;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(value = OperationFailedException.class)
    public ResponseEntity<Object> handleException(OperationFailedException exception) {
        String username = CustomRevisionListener.getUserName(SecurityContextHolder.getContext());
        this.customApplicationEventPublisher.publishOperationFailedEvent(
                exception.getMessage(), username, null);
        logger.error("OperationFailedException", exception);
        return new ResponseEntity<>(calErrorResponse(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DoesNotExistException.class)
    public ResponseEntity<Object> handleException(DoesNotExistException exception) {
        logger.error("DoesNotExistException", exception);
        return new ResponseEntity<>(calErrorResponse(exception), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = PermissionDeniedException.class)
    public ResponseEntity<Object> handleException(PermissionDeniedException exception) {
        String username = CustomRevisionListener.getUserName(SecurityContextHolder.getContext());
        exception.getMessage();
        this.customApplicationEventPublisher.publishPermissionDeniedEvent(
                exception.getMessage(), username, null);
        logger.error("PermissionDeniedException", exception);
        return new ResponseEntity<>(calErrorResponse(exception), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = MissingParameterException.class)
    public ResponseEntity<Object> handleException(MissingParameterException exception) {
        return new ResponseEntity<>(calErrorResponse(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = VersionMismatchException.class)
    public ResponseEntity<Object> handleException(VersionMismatchException exception) {
        return new ResponseEntity<>(calErrorResponse(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DataValidationErrorException.class)
    public ResponseEntity<Object> handleException(DataValidationErrorException exception) {
        return new ResponseEntity<>(exception.getValidationResults(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidParameterException.class)
    public ResponseEntity<Object> handleException(InvalidParameterException exception) {
        return new ResponseEntity<>(calErrorResponse(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PropertyReferenceException.class)
    public ResponseEntity<Object> handleException(PropertyReferenceException exception) {
        logger.error("PropertyReferenceException", exception);
        return new ResponseEntity<>(calErrorResponse(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<Object> handleException(ObjectOptimisticLockingFailureException exception) {
        logger.error("ObjectOptimisticLockingFailureException", exception);
        return new ResponseEntity<>(calErrorResponse(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<Object> handleMultipartException(MultipartException exception) {
        String size = environment.getProperty("spring.servlet.multipart.max-file-size");

        OperationFailedException ex = new OperationFailedException(
                "Maximum upload size exceeded.\n"
                + size
                + " allowed per document", exception);
        logger.error("MultipartException", exception);
        return new ResponseEntity<>(calErrorResponse(ex), HttpStatus.BAD_REQUEST);
    }

    private ValidationResultInfo calErrorResponse(Exception exception) {
        ValidationResultInfo resultInfo = new ValidationResultInfo();
        resultInfo.setError(exception.getMessage());
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        resultInfo.setStackTrace(sw.toString());
        return resultInfo;
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException exception) {
        logger.error("NullPointerException ", exception);
        return new ResponseEntity<>(calErrorResponse(exception), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
