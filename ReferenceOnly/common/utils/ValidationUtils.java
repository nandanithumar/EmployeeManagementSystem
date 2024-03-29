
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.utils;

import com.codahale.metrics.annotation.Timed;
import com.egovja.tatransform.licencingmanagement.common.constant.Constant;
import com.egovja.tatransform.licencingmanagement.common.constant.ErrorLevel;
import com.egovja.tatransform.licencingmanagement.common.dto.info.MetaInfo;
import com.egovja.tatransform.licencingmanagement.common.dto.info.ValidationResultInfo;
import com.egovja.tatransform.licencingmanagement.common.exceptioncontroller.exception.DataValidationErrorException;
import com.egovja.tatransform.licencingmanagement.parameter.constant.KeyCatagory;
import io.astefanutti.metrics.aspectj.Metrics;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * This ValidationUtils provides methods for validation.
 *
 * @author sudip
 * @since 2021-2-11
 */
@Metrics(registry = "ValidationUtils")
public final class ValidationUtils {

    public static final String PROVIDED_KEY = "Provided key:";

    private ValidationUtils() {
    }

    /**
     * Validation method for Required field.
     *
     * @param field contains field values
     * @param fieldName name of the field
     * @param error
     */
    @Timed(name = "validateRequired")
    public static void validateRequired(
            Object field,
            String fieldName,
            List<ValidationResultInfo> error) {
        if (field == null) {
            error.add(new ValidationResultInfo(fieldName,
                    ErrorLevel.ERROR,
                    fieldName + " must be provided"));
        }
    }

    /**
     * Validation method for Not Empty.
     *
     * @param field contains field values
     * @param fieldName name of the field
     * @param errors
     */
    @Timed(name = "validateNotEmpty")
    public static void validateNotEmpty(
            String field,
            String fieldName,
            List<ValidationResultInfo> errors) {
        if (field != null && field.isEmpty()) {
            errors
                    .add(new ValidationResultInfo(fieldName,
                            ErrorLevel.ERROR,
                            fieldName + " field must not be empty"));
        }
    }

    /**
     * Validation method for RefObjectUri.
     *
     * @param refObjectUri contains refObjectUri values
     * @param fieldName name of the field
     * @param infoName name of the info
     * @param errors
     */
    @Timed(name = "validateRefObjectUri")
    public static void validateRefObjectUri(
            String refObjectUri,
            String fieldName,
            String infoName,
            List<ValidationResultInfo> errors) {
        if (StringUtils.isEmpty(refObjectUri) || !refObjectUri.equals(infoName)) {
            errors
                    .add(new ValidationResultInfo(fieldName,
                            ErrorLevel.ERROR,
                            "Provided " + fieldName
                            + " must be for " + infoName));
        }
    }

    /**
     * Validation method for RefObjectUri.
     *
     * @param keyCatagory KeyCatagory values
     * @param fieldName name of the field
     * @param key key of the info
     * @param value value of the info
     * @param errors
     */
    @Timed(name = "validateKeyValue")
    public static void validateKeyValue(
            KeyCatagory keyCatagory,
            String key,
            String value,
            BigDecimal minValue,
            BigDecimal maxValue,
            String fieldName,
            List<ValidationResultInfo> errors) {
        switch (keyCatagory) {
            case BIGDECIMAL: {
                try {
                    Float f = Float.parseFloat(value);
                    validateFloatRange(
                            f,
                            fieldName,
                            minValue == null ? null : minValue.floatValue(),
                            maxValue == null ? null : maxValue.floatValue(),
                            errors);
                } catch (NumberFormatException e) {
                    errors
                            .add(new ValidationResultInfo(fieldName,
                                    ErrorLevel.ERROR,
                                    PROVIDED_KEY + key
                                    + "'s value must be for BigDecimal"));
                }

                break;
            }
            case CURRENCY: {
                try {
                    Float f = Float.parseFloat(value);
                    validateFloatRange(
                            f,
                            fieldName,
                            minValue == null ? null : minValue.floatValue(),
                            maxValue == null ? null : maxValue.floatValue(),
                            errors);
                } catch (NumberFormatException e) {
                    errors
                            .add(new ValidationResultInfo(fieldName,
                                    ErrorLevel.ERROR,
                                    PROVIDED_KEY + key
                                    + "'s value must be for Currency"));
                }

                break;
            }
            case PERCENTAGE: {
                try {
                    Float f = Float.parseFloat(value);
                    validateFloatRange(
                            f,
                            fieldName,
                            minValue == null ? null : minValue.floatValue(),
                            maxValue == null ? null : maxValue.floatValue(),
                            errors);
                } catch (NumberFormatException e) {
                    errors
                            .add(new ValidationResultInfo(fieldName,
                                    ErrorLevel.ERROR,
                                    PROVIDED_KEY + key
                                    + "'s value must be for Percentage"));
                }

                break;
            }
            case BOOLEAN: {
                if (StringUtils.isEmpty(value)
                        || (!value.toLowerCase().equals(Constant.TRUE_STRING)
                        && !value.toLowerCase().equals(Constant.FALSE_STRING))) {
                    errors
                            .add(new ValidationResultInfo(fieldName,
                                    ErrorLevel.ERROR,
                                    PROVIDED_KEY + key
                                    + "'s value must be for BOOLEAN"));
                }
                break;
            }
            case INTEGER: {
                try {
                    Integer i = Integer.parseInt(value);
                    validateIntegerRange(
                            i,
                            fieldName,
                            minValue == null ? null : minValue.intValue(),
                            maxValue == null ? null : maxValue.intValue(),
                            errors);
                } catch (NumberFormatException e) {
                    errors
                            .add(new ValidationResultInfo(fieldName,
                                    ErrorLevel.ERROR,
                                    PROVIDED_KEY + key
                                    + "'s value must be for INTEGER"));
                }
                break;
            }
            case STRING: {
                if (StringUtils.isEmpty(value)) {
                    errors
                            .add(new ValidationResultInfo(fieldName,
                                    ErrorLevel.ERROR,
                                    PROVIDED_KEY + key
                                    + "'s value must be for STRING"));
                }
                break;
            }
            case DATE: {
                DateFormat sdf = new SimpleDateFormat(Constant.DATE_FORMATE_STRING);
                sdf.setLenient(false);
                try {
                    sdf.parse(value);
                } catch (ParseException e) {
                    errors
                            .add(new ValidationResultInfo(fieldName,
                                    ErrorLevel.ERROR,
                                    PROVIDED_KEY + key
                                    + "'s value must be for DATE with format: " + Constant.DATE_FORMATE_STRING
                
                ));
                }
                break;
            }
        }
    }

    /**
     * Validation method for Float.
     *
     * @param field contains field values
     * @param fieldName name of the field
     * @param minValue
     * @param maxValue
     * @param errors
     */
    @Timed(name = "validateFloat")
    public static void validateFloat(
            String field,
            String fieldName,
            Float minValue,
            Float maxValue,
            List<ValidationResultInfo> errors) {
        if (field != null) {
            try {
                Float floatValue = Float.parseFloat(field);
                validateFloatRange(floatValue,
                        fieldName,
                        minValue,
                        maxValue,
                        errors);
            } catch (NumberFormatException e) {
                errors
                        .add(new ValidationResultInfo(fieldName,
                                ErrorLevel.ERROR,
                                fieldName + " field must contain decimal value"));
            }
        }
    }

    /**
     * Validation method for Float.
     *
     * @param field contains field values
     * @param fieldName name of the field
     * @param minValue
     * @param maxValue
     * @param errors
     */
    @Timed(name = "validateFloatRange")
    public static void validateFloatRange(
            Float field,
            String fieldName,
            Float minValue,
            Float maxValue,
            List<ValidationResultInfo> errors) {
        if (field != null) {
            if (minValue != null && field < minValue) {
                errors
                        .add(new ValidationResultInfo(fieldName,
                                ErrorLevel.ERROR,
                                fieldName + " field must be greater than " + minValue));
            } else if (maxValue != null && field > maxValue) {
                errors
                        .add(new ValidationResultInfo(fieldName,
                                ErrorLevel.ERROR,
                                fieldName + " field must be less than " + maxValue));
            }
        }
    }

    /**
     * Validation method for effectiveDates.
     *
     * @param effectiveDate contains effectiveDate
     * @param expirationDate contain expirationDate
     * @param error
     */
    @Timed(name = "validateEffectiveDates")
    public static void validateEffectiveDates(
            Date effectiveDate,
            Date expirationDate,
            List<ValidationResultInfo> error) {
        if (effectiveDate != null
                && expirationDate != null
                && effectiveDate.after(expirationDate)) {
            error
                    .add(new ValidationResultInfo("effectiveDates",
                            ErrorLevel.ERROR,
                            "expirationDate must come after effectiveDate"));
        }
    }

    /**
     * Validation method for effectiveDates.
     *
     * @param effectiveDate contains effectiveDate
     * @param expirationDate contain expirationDate
     * @param error
     */
    @Timed(name = "validateEffectiveDates")
    public static void validateEffectiveDates(
            LocalDate effectiveDate,
            LocalDate expirationDate,
            List<ValidationResultInfo> error) {
        if (effectiveDate != null
                && expirationDate != null
                && effectiveDate.isAfter(expirationDate)) {
            error
                    .add(new ValidationResultInfo("effectiveDates",
                            ErrorLevel.ERROR,
                            "expirationDate must come after effectiveDate"));
        }
    }

    /**
     * Check if date is effective.
     *
     * @param date date to check
     * @param effectiveDate contains effectiveDate
     * @param expirationDate contain expirationDate
     * @return true date is effective.
     */
    @Timed(name = "isEffectiveDate")
    public static boolean isEffectiveDate(
            Date date,
            Date effectiveDate,
            Date expirationDate) {
        return date != null
                && (effectiveDate == null || date.after(effectiveDate))
                && (expirationDate == null || date.before(expirationDate));
    }

    /**
     * Replace CommonFuzzyText in name and split from space
     *
     * @param name name to replace
     * @return set of words.
     */
    @Timed(name = "getSetReplacingCommonFuzzyText")
    public static Set<String> getSetReplacingCommonFuzzyText(String name) {
        Set<String> word = new HashSet<>();
        if (name != null) {
            name = name
                    .replaceAll("[^a-zA-Z0-9\\s]", "")
                    .toLowerCase();
            for (Map.Entry<String, String> entry
                    : Constant.COMMON_FUZZY_TEXT.entrySet()) {
                name = name.replace(entry.getKey(), entry.getValue());
            }
            Collections.addAll(word, name.split("[\\s]+"));
        }
        return word;
    }

    /**
     * Validation method for Integer.
     *
     * @param field contains field values
     * @param fieldName name of the field
     * @param errors
     */
    @Timed(name = "validateInteger")
    public static void validateInteger(
            String field,
            String fieldName,
            List<ValidationResultInfo> errors) {
        if (field != null) {
            try {
                Integer.parseInt(field);
            } catch (NumberFormatException e) {
                errors
                        .add(new ValidationResultInfo(fieldName,
                                ErrorLevel.ERROR,
                                fieldName + " field must contain integer value"));
            }
        }
    }

    /**
     * Validation method for Long.
     *
     * @param field contains field values
     * @param fieldName name of the field
     * @param errors
     */
    @Timed(name = "validateLong")
    public static void validateLong(
            String field,
            String fieldName,
            List<ValidationResultInfo> errors) {
        if (field != null) {
            try {
                Long.parseLong(field);
            } catch (NumberFormatException e) {
                errors
                        .add(new ValidationResultInfo(fieldName,
                                ErrorLevel.ERROR,
                                fieldName + " field must contain numeric value"));
            }
        }
    }

    /**
     * check meta empty or not.
     *
     * @param meta
     * @return true if meta is empty
     */
    @Timed(name = "metaIsEmpty")
    public static boolean metaIsEmpty(MetaInfo meta) {
        return meta.getCreatedAt() == null
                && meta.getUpdatedAt() == null
                && meta.getCreatedBy() == null
                && meta.getUpdatedBy() == null
                && meta.getVersion() == null;
    }

    /**
     * Validation method for Integer.
     *
     * @param field contains field values
     * @param fieldName name of the field
     * @param minValue
     * @param maxValue
     * @param errors
     */
    @Timed(name = "validateIntegerRange")
    public static void validateIntegerRange(
            Integer field,
            String fieldName,
            Integer minValue,
            Integer maxValue,
            List<ValidationResultInfo> errors) {
        if (field != null) {
            if (minValue != null && field < minValue) {
                errors
                        .add(new ValidationResultInfo(fieldName,
                                ErrorLevel.ERROR,
                                fieldName + " field must be greater than " + minValue));
            } else if (maxValue != null && field > maxValue) {
                errors
                        .add(new ValidationResultInfo(fieldName,
                                ErrorLevel.ERROR,
                                fieldName + " field must be less than " + maxValue));
            }
        }
    }

    /**
     * Validation method for not updatable field.
     *
     * @param field contains field values
     * @param originalField contains original fields values
     * @param fieldName name of the field
     * @param errors
     */
    @Timed(name = "validateNotUpdatable")
    public static void validateNotUpdatable(
            Object field,
            Object originalField,
            String fieldName,
            List<ValidationResultInfo> errors) {
        if ((field != null || originalField != null)
                && (originalField == null
                || field == null
                || !field.equals(originalField))) {
            errors
                    .add(new ValidationResultInfo(fieldName,
                            ErrorLevel.ERROR,
                            fieldName + " field is not updatable"));
        }
    }

    /**
     * Validation method for list size.
     *
     * @param field contains field values
     * @param fieldName name of the field
     * @param minValue
     * @param maxValue
     * @param errors
     */
    @Timed(name = "validateListSize")
    public static void validateListSize(
            List<?> field,
            String fieldName,
            Integer minValue,
            Integer maxValue,
            List<ValidationResultInfo> errors) {
        if (field != null) {
            if (minValue != null
                    && maxValue != null
                    && minValue.equals(maxValue)
                    && !minValue.equals(field.size())) {
                errors
                        .add(new ValidationResultInfo(fieldName,
                                ErrorLevel.ERROR,
                                fieldName + " must contain "
                                + minValue + " item"));
            } else {
                if (minValue != null && field.size() < minValue) {
                    errors
                            .add(new ValidationResultInfo(fieldName,
                                    ErrorLevel.ERROR,
                                    fieldName + " must contain more than "
                                    + minValue + " items"));
                } else if (maxValue != null && field.size() > maxValue) {
                    errors
                            .add(new ValidationResultInfo(fieldName,
                                    ErrorLevel.ERROR,
                                    fieldName + " must contain less than "
                                    + maxValue + " items"));
                }
            }
        }
    }

    @Timed(name = "throwDataValidationOnValidationErrors")
    public static void throwDataValidationOnValidationErrors(
            List<ValidationResultInfo> validationResultInfos
    ) throws DataValidationErrorException {
        if (ValidationUtils.containsErrors(validationResultInfos, ErrorLevel.ERROR)) {
            throw new DataValidationErrorException("Error(s) occurred validating", validationResultInfos);
        }
    }

    /**
     * Validation method for pattern match.
     *
     * @param fieldValue contains field values
     * @param fieldName name of the field
     * @param patternString pattern for the field
     * @param errorMassage
     * @param errors
     */
    @Timed(name = "validatePattern")
    public static void validatePattern(String fieldValue,
            String fieldName,
            String patternString,
            String errorMassage,
            List<ValidationResultInfo> errors) {
        Pattern pattern = Pattern.compile(patternString);
        if (fieldValue != null && !pattern.matcher(fieldValue).matches()) {
            errors
                    .add(new ValidationResultInfo(fieldName,
                            ErrorLevel.ERROR,
                            errorMassage));
        }
    }

    /**
     * Validation method for password match.
     *
     * @param password contains field values
     * @param fieldName name of the field
     * @param minLen Integer password length
     * @param maxLen Integer password length
     * @param errors error list where we can add details.
     */
    @Timed(name = "validatePasswordString")
    public static void validatePasswordString(
            String password,
            String fieldName,
            Integer minLen,
            Integer maxLen,
            List<ValidationResultInfo> errors) {
        StringBuilder msg = new StringBuilder();

        boolean upper = false;
        boolean lower = false;
        boolean digit = false;
        boolean special = false;
        boolean white = false;

        boolean minLength = false;
        boolean maxLength = false;

        if (password.length() >= minLen) {
            minLength = true;
        }

        if (password.length() <= maxLen) {
            maxLength = true;
        }

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                upper = true;
            }

            if (Character.isLowerCase(c)) {
                lower = true;
            }
            if (Character.isDigit(c)) {
                digit = true;
            }
            if (Character.isWhitespace(c)) {
                white = true;
            }
            if (c == '!' || c == '@' || c == '#' || c == '$' || c == '5' || c == '&') {
                special = true;
            }
        }
        if (!digit) {
            msg.append("Must have at least one numeric character\\n");
        }
        if (!lower) {
            msg.append("Must have at least one lowercase character\n");
        }
        if (!upper) {
            msg.append("Must have at least one uppercase character\n");
        }
        if (white) {
            msg.append("Must not contain white space.\n");
        }
        if (!special) {
            msg.append("Must have at least one special symbol among !@#$%&\n");
        }
        if (!minLength || !maxLength) {
            msg.append("Password length should be between 9 and 255\n");
        }

        String errMsg = msg.toString();
        if (errMsg.length() > 0) {
            errors
                    .add(new ValidationResultInfo(fieldName,
                            ErrorLevel.ERROR,
                            errMsg));
        }
    }

    /**
     * Validation method for Length.
     *
     * @param fieldValue contains field values
     * @param fieldName name of the field
     * @param minLength minimum required length for field
     * @param maxLength maximum required length for field
     * @param errors
     */
    @Timed(name = "validateLength")
    public static void validateLength(String fieldValue,
            String fieldName,
            int minLength,
            int maxLength,
            List<ValidationResultInfo> errors) {
        if (fieldValue != null) {
            int fieldLength = fieldValue.length();
            if (minLength > fieldLength) {
                errors.add(new ValidationResultInfo(fieldName,
                        ErrorLevel.ERROR,
                        fieldName + " must have more than "
                        + minLength + " characters"));
            } else if (fieldLength > maxLength) {
                errors
                        .add(new ValidationResultInfo(fieldName,
                                ErrorLevel.ERROR,
                                fieldName + " must have less than "
                                + maxLength + " characters"));
            }
        }
    }

    /**
     * Check if errors contains WARN thresh hold Error.
     *
     * @param errors list of errors
     * @param errorLevel
     * @return true if it contains WARN thresh hold Error.
     */
    @Timed(name = "containsErrors")
    public static boolean containsErrors(
            List<ValidationResultInfo> errors,
            ErrorLevel errorLevel) {
        return ValidationResultInfo
                .hasValidationErrors(errors,
                        errorLevel,
                        new ArrayList<>());
    }

}
