/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.info;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.Context;
import io.swagger.annotations.ApiParam;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * This info is for context DTO who has Request Information, Date and LoggeIn
 * UserId.
 *
 * @author Hiren Morzariya
 * @since 2021-2-11
 */
public class ContextInfo extends User implements Context, Serializable {

    @ApiParam(
            value = "currentDate of the context",
            hidden = true
    )
    private Date currentDate;

    @ApiParam(
            value = "remoteAddress of the context",
            hidden = true
    )
    private String remoteAddress;

    @ApiParam(
            value = "locale of the context",
            hidden = true
    )
    private LocaleInfo locale;

    @ApiParam(
            value = "accessToken of the context",
            hidden = true
    )
    private String accessToken;

    @ApiParam(
            value = "isSystemUser of the context",
            hidden = true
    )
    private Boolean isSystemUser;

    @ApiParam(
            value = "email of the context",
            hidden = true
    )
    private String email;

    @ApiParam(
            value = "trn of the context",
            hidden = true
    )
    private Integer trn;

    @ApiParam(
            value = "userId of the context",
            hidden = true
    )
    private String userId;

    @ApiParam(
            value = "customerId of the context",
            hidden = true
    )
    private Long customerId;

    @ApiParam(
            value = "isHigherAccess of the context",
            hidden = true
    )
    private Boolean isHigherAccess;

    @ApiParam(
            value = "officeId of the context",
            hidden = true
    )
    private String officeId;

    @ApiParam(
            value = "regionId of the context",
            hidden = true
    )
    private String regionId;

    @ApiParam(
            value = "firstName of the context",
            hidden = true
    )
    private String firstName;

    @ApiParam(
            value = "lastName of the context",
            hidden = true
    )
    private String lastName;

    public ContextInfo() {
        super("username", "password", new ArrayList<>());
    }

    public ContextInfo(
            String firstName,
            String lastName,
            Boolean isHigherAccess,
            String officeId,
            String regionId,
            String email,
            Boolean isSystemUser,
            String userId,
            String username,
            String password,
            boolean enabled,
            boolean accountNonExpired,
            boolean credentialsNonExpired,
            boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
        super(username,
                password,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities);
        this.isHigherAccess = isHigherAccess;
        this.officeId = officeId;
        this.regionId = regionId;
        this.email = email;
        this.isSystemUser = isSystemUser;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ContextInfo(
            String firstName,
            String lastName,
            String email,
            Integer trn,
            Boolean isSystemUser,
            Long customerId,
            String username,
            String password,
            boolean enabled,
            boolean accountNonExpired,
            boolean credentialsNonExpired,
            boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
        super(username,
                password,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities);
        this.email = email;
        this.trn = trn;
        this.isSystemUser = isSystemUser;
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public Date getCurrentDate() {
        return this.currentDate != null
                ? new Date(currentDate.getTime())
                : Calendar.getInstance().getTime();
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate != null
                ? new Date(currentDate.getTime()) : null;
    }

    public void setIsSystemUser(Boolean isSystemUser) {
        this.isSystemUser = isSystemUser;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Integer getTrn() {
        return trn;
    }

    public void setTrn(Integer trn) {
        this.trn = trn;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String getRemoteAddress() {
        return this.remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    @Override
    public LocaleInfo getLocale() {
        return locale;
    }

    public void setLocale(LocaleInfo locale) {
        this.locale = locale;
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        if (accessToken != null && accessToken.length() > 0) {
            accessToken = accessToken.split(" ")[1].trim();
        }
        this.accessToken = accessToken;
    }

    @Override
    public Boolean getIsSystemUser() {
        if (isSystemUser == null) {
            isSystemUser = false;
        }
        return isSystemUser;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return super.isCredentialsNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return super.isAccountNonLocked();
    }

    @Override
    public boolean isAccountNonExpired() {
        return super.isAccountNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return super.getAuthorities();
    }

    public Boolean getIsHigherAccess() {
        if (isHigherAccess == null) {
            isHigherAccess = false;
        }
        return isHigherAccess;
    }

    public void setIsHigherAccess(Boolean isHigherAccess) {
        this.isHigherAccess = isHigherAccess;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "ContextInfo{"
                + "currentDate=" + currentDate
                + ", remoteAddress=" + remoteAddress
                + ", locale=" + locale
                + ", accessToken=" + accessToken
                + ", isSystemUser=" + isSystemUser
                + ", email=" + email
                + ", trn=" + trn
                + ", userId=" + userId
                + ", customerId=" + customerId
                + ", isHigherAccess=" + isHigherAccess
                + ", officeId=" + officeId
                + ", regionId=" + regionId
                + ", firstName=" + firstName
                + ", lastName=" + lastName
                + '}';
    }
}
