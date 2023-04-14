/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.master.dto.info;

import com.egovja.tatransform.licencingmanagement.common.dto.info.TemporalStateEntityInfo;
import com.egovja.tatransform.licencingmanagement.master.dto.infc.Type;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * This info is for type DTO that contains all the type model's data.
 *
 * @author Hiren Morzariya
 * @since 2021-2-17
 */
public class TypeInfo
        extends TemporalStateEntityInfo
        implements Type, Serializable {

    @ApiModelProperty(notes = "The icon for type",
            allowEmptyValue = true,
            example = "fa-clock",
            dataType = "String",
            required = false)
    private String icon;

    @ApiModelProperty(notes = "The refObjectUri",
            allowEmptyValue = false,
            example = "ViaPointInfo",
            dataType = "String",
            required = true)
    private String refObjectUri;

    @ApiModelProperty(notes = "The code",
            allowEmptyValue = false,
            example = "1",
            dataType = "Integer",
            required = false,
            readOnly = true)
    private Integer code;

    public TypeInfo() {
    }

    /**
     * This is copy ConStructure that make deep copy of the Type.
     *
     * @param type
     */
    public TypeInfo(Type type) {
        super(type);
        if (type != null) {
            this.refObjectUri = type.getRefObjectUri();
            this.icon = type.getIcon();
            this.code = type.getCode();
        }
    }

    @Override
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String getRefObjectUri() {
        return refObjectUri;
    }

    public void setRefObjUri(String refObjUri) {
        this.refObjectUri = refObjUri;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "TypeInfo{"
                + "id=" + this.getId()
                + ", name=" + this.getName()
                + ", icon=" + icon
                + ", code=" + code
                + ", refObjectUri=" + refObjectUri
                + ", effectiveDate" + this.getEffectiveDate()
                + ", expirationDate" + this.getExpirationDate()
                + ", stateId=" + this.getStateId()
                + ", meta=" + this.getMeta()
                + ", description=" + this.getDescription()
                + '}';
    }

}
