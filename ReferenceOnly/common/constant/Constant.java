/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.constant;

import com.egovja.tatransform.licencingmanagement.common.dto.info.ContextInfo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * This Constant class contains all the constant variable of this project
 *
 * @author Hiren Morzariya
 * @since 2021-2-11
 */
public final class Constant {
    

    private Constant() {
    }

    public static final String CONTEXT_PATH = "${app.context.path}";

    public static final String EMPTY = "";
    public static final String SPACE = " ";
    public static final String UNDERSCORE = "_";
    public static final String PDF_FILE_EXTENSION = ".pdf";

    public static final String ACTIVITI_GROUP_PREFIX = "GROUP_";

    public static final String JAMAICA_MOBILE_PRIFIX = "1";

    // Constants for validating for
    public static final String CREATE_VALIDATION = "create.validation";
    public static final String UPDATE_VALIDATION = "update.validation";
    public static final String DELETE_VALIDATION = "delete.validation";
    public static final String ACTIVATE_VALIDATION = "activate.validation";
    public static final String GENERATE_VALIDATION = "generate.validation";
    public static final String CAN_CREATE_VALIDATION = "can.create.validation";
    public static final String CAN_UPDATE_VALIDATION = "can.update.validation";
    public static final String APPROVE_VALIDATION = "approve.validation";
    public static final String FEE_VALIDATION = "fee.validation";
    public static final String ROUTE_VALIDATION = "route.validation";
    public static final String TIME_TABLE_VALIDATION = "time.table.validation";
    public static final String IS_RENEWABLE_VALIDATION = "is.renewable.validation";
    public static final String CANCEL_PAYMENT_VALIDATION = "cancel.payment.validation";

    //customer Registration
    public static final String TRN_VALIDATION = "trn.validation";
    public static final String UPDATE_ADDRESS_VALIDATION = "update.address.validation";
    public static final String OTP_VALIDATION = "otp.validation";
    public static final String REGISTRATION_VALIDATION = "registration.validation";

    //forgot Password
    public static final String EMAIL_VALIDATION = "email.validation";
    public static final String TOKEN_VALIDATION = "token.validation";
    public static final String FORGOT_PASSWORD_VALIDATION = "forgotpassword.validation";

    public static final String OTP_STATE_ACTIVE = "jm.org.ta.state.otpinfo.active";
    public static final String OTP_STATE_INACTIVE = "jm.org.ta.state.otpinfo.inactive";
    public static final String OTP_STATE_SEND_FAIL = "jm.org.ta.state.otpinfo.send.fail";

    public static final String SYSTEM_USER_TYPE = "SYSTEM_USER";
    public static final String CUSTOMER_TYPE = "CUSTOMER";

    public static final String ANONYMOUS_USER = "anonymousUser";
    public static final String ANONYMOUS_USER_NAME = "ANONYMOUS_USER";
    public static final String DEFAULT_AUDIT_NAME = "SYSTEM_USER";

    public static final String SYSTEM_USER = "SYSTEM_USER:";
    public static final String CUSTOMER = "CUSTOMER:";
    public static final String LICENCING_MANAGEMENT_APPLICATION = "Licencing Management Application";

    public static final String DOT = ".";

    public static final String TYPE = "type";

    public static final String STATE = "state";

    public static final Integer MAX_IDS_CAN_RETRIVED = 50;

    public static final String TYPE_ID_PRIFIX = "jm.org.ta.type.";

    public static final String STATE_ID_PRIFIX = "jm.org.ta.state.";

    public static final String ALLOWED_CHARS_IN_NAMES = "[[A-Z][a-z][0-9][/][\\s][_][@][#][(][)][.][,]['][-][*][`][/][&]]*";

    public static final String ALLOWED_CHARS_IN_NAMES_APPLICATION = "[[A-Z][a-z][0-9][/][\\s][(][)][.][,]['][-][`][&]]*";

    public static final String ALLOWED_CHARS_IN_CAPTCHA_STRING = "[A-Za-z1-9]+";

    public static final String ALLOWED_CHARS_IN_NAMES_ERR_MESSAGE = "[\"/\",\"&\",\"_\",\".\",\",\",\"-\",\"@\",\"#\",\"*\",\"`\",\"'\",\"(\",\")\",\"/\"]";

    public static final Pageable LAST_CRETED_TYPE_RECORD_PAGE = PageRequest.of(0, 1, Sort.by("code").descending());

    public static final Pageable FULL_PAGE = PageRequest.of(0, Integer.MAX_VALUE);
    public static final Pageable SINGLE_VALUE_PAGE = PageRequest.of(0, 1);
    public static final Pageable TWO_VALUE_PAGE = PageRequest.of(0, 2);

    public static final Map<String, String> COMMON_FUZZY_TEXT = new HashMap<>();

    public static final Map<String, String> ROUTE_STATE = new HashMap<>();
    public static final String CANT_PARSE_PRINCIPAL_ID = "Can't parse PrincipalId from Context";

    public static final String VERSION_MISMATCH = "Version misMatch";

    public static final String NOT_FOUND = " not found";

    public static final String OPERATION_FAILED = "Operation failed";
    public static final String VALIDATION_ERROR = "Error(s) occurred validating";
    public static final String PARISH_ID = "parishId";
    public static final String PARISH_ID_DOES_NOT_EXIST = "The id supplied for the parish does not exists";
    public static final String ASSOCIATION_COMPANY_INFO_NAME = "AssociationCompanyInfo";
    public static final String ASSOCIATION_LICENCE_TYPE = "associatedLicenceType";
    public static final String STATE_DOES_NOT_EXIST = "The id supplied for the state does not exists";
    public static final String TYPE_DOES_NOT_EXIST = "The id supplied for the type does not existss";
    public static final String ID_FOR_UPADATE_NOT_EXIST = "The id supplied to the update does not exists";
    public static final String INVALID_VALIDATION_TYPE_KEY = "Invalid validationTypeKey";
    public static final String NO_ID_ON_CREATE = "No id should be supplied on a create";
    public static final String NO_META_ON_CREATE = "no meta data should be supplied on a create";
    public static final String META_VERSION = "meta.version";
    public static final String STARTED_UPDATING = " started updating, you might want to refresh your page.";
    public static final String MUST_PROVIDED = " must be provided";
    public static final String ID_MISSING = "id is missing";

    public static final String ALL_NUMBERS_FROM_0_TO_9 = "0123456789";

    //Trn Service Messages
    public static final String MISSING_TAX_REGISTRATION_NUMBER = "Missing Tax Registration Number";
    public static final String MISSING_DOB = "Mising date of birth";

    //Validation Service Messages
    public static final String VALIDATION_SUCCESSFUL = "VALIDATION_SUCCESSFUL";
    public static final String VALIDATION_FAILED = "VALIDATION_FAILED";

    public static final String PPV = "PPV";

    public static final ContextInfo SYSTEM_CONTEXT_INFO = new ContextInfo();

    static {
        SYSTEM_CONTEXT_INFO.setEmail(Constant.LICENCING_MANAGEMENT_APPLICATION);

        COMMON_FUZZY_TEXT.put("saint", "st");
        COMMON_FUZZY_TEXT.put("road", "rd");
        COMMON_FUZZY_TEXT.put("avenue", "ave");
        COMMON_FUZZY_TEXT.put("square", "sq");
        COMMON_FUZZY_TEXT.put("station", "stn");
        COMMON_FUZZY_TEXT.put("place", "pl");
        COMMON_FUZZY_TEXT.put("route", "rt");

        ROUTE_STATE.put("open", "jm.org.ta.state.routeinfo.open");
        ROUTE_STATE.put("draft", "jm.org.ta.state.routeinfo.draft");

    }

    public static final String AUTHORIZATION = "Authorization";

    public static final DateTimeFormatter LOCAL_DATE_DD_MM_YYYY
            = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static final String DATE_FORMATE_STRING = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMATE_STRING = "yyyy-MM-dd hh:mm a";
    public static final String TIME_FORMAT_24_HOURS_STRING = "HH:mm";
    public static final String TIME_FORMAT_12_HOURS_STRING = "hh:mm a";

    public static final String TRUE_STRING = "true";
    public static final String FALSE_STRING = "false";

    public static final DateTimeFormatter LOCAL_DATE_YYYY_MM_DD
            = DateTimeFormatter.ofPattern(DATE_FORMATE_STRING);

    public static final DateTimeFormatter LOCAL_DATE_FORMATE
            = DateTimeFormatter.ofPattern(DATE_FORMATE_STRING);
    public static final DateTimeFormatter LOCAL_DATE_YYYYMMDD
            = DateTimeFormatter.ofPattern("yyyyMMdd");

}
