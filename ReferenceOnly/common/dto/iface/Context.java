/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.iface;

import com.egovja.tatransform.licencingmanagement.common.dto.info.LocaleInfo;
import java.util.Date;

/**
 * This Interface is provides contract for ContextInfo.
 *
 * @author Hiren Morzariya
 * @since 2021-2-11
 */
public interface Context {

    /**
     * The current date in this context.This date is used to instruct the
     * provider to perform operations as if this date were the current date.
     *
     * @return Current Date
     */
    public Date getCurrentDate();

    /**
     * The remote IP address of the machine of the user
     *
     * @return Remote IP Address
     */
    public String getRemoteAddress();

    /**
     * The Principal Email of the logged In user.
     *
     * @return Principal Email
     */
    public String getEmail();
    
    /**
     * The Principal User FirstName of the logged In user.
     *
     * @return Principal FirstName
     */
    public String getFirstName();
    
    /**
     * The Principal User LastName of the logged In user.
     *
     * @return Principal LastName
     */
    public String getLastName();
    
    /**
     * The Principal IsHigherAccess of the logged In user.
     *
     * @return Principal IsHigherAccess
     */
    public Boolean getIsHigherAccess();
    
    /**
     * The Principal RegionId of the logged In user.
     *
     * @return Principal RegionId
     */
    public String getRegionId();
    
    /**
     * The Principal OfficeId of the logged In user.
     *
     * @return Principal OfficeId
     */
    public String getOfficeId();
    
    /**
     * The Principal Trn of the logged In user.
     *
     * @return Principal Trn
     */
    public Integer getTrn();
    
    /**
     * The Principal UserId of the logged In user.
     *
     * @return Principal UserId
     */
    public String getUserId();
    
    /**
     * The Principal CustomerId of the logged In user.
     *
     * @return Principal CustomerId
     */
    public Long getCustomerId();

    /**
     * The locale information requested by the user.
     *
     * @return Locale
     */
    public LocaleInfo getLocale();

    /**
     * The AccessToken of the loggedIn user.
     *
     * @return AccessToken
     */
    public String getAccessToken();

    /**
     * The check loggedIn user is SystemUser.
     *
     * @return IsSystemUser
     */
    public Boolean getIsSystemUser();
}
