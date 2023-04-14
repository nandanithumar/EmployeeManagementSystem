/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.info;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.TypeEntity;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 *
 * @author akshar
 */
public class TypeEntityInfo extends HasMetaInfo implements TypeEntity, Serializable {

    @ApiModelProperty(notes = "The id of type model",
            allowEmptyValue = false,
            example = "jm.org.ta.type.viapointinfo.terminal",
            dataType = "String",
            required = true,
            reference = "type(id)")
    private String typeId;

    @ApiModelProperty(notes = "The name of the model",
            allowEmptyValue = false,
            example = "Active",
            dataType = "String",
            required = true)
    private String name;

    @ApiModelProperty(notes = "The description of the model",
            allowEmptyValue = true,
            example = "{\n"
            + "\t\"plain\":\"plain text\" ,\n"
            + "\t\"formatted\":\"<b>formatted text</b>\"\n"
            + "}",
            dataType = "RichTextInfo",
            required = false)
    private RichTextInfo description;

    /**
     * Constructs a new TypeEntityInfo.
     */
    public TypeEntityInfo() {
    }

    /**
     * Constructs a new TypeEntityInfo from another TypeEntity.
     *
     * @param typeEntity
     */
    public TypeEntityInfo(TypeEntityInfo typeEntity) {
        super(typeEntity);
        if (typeEntity != null) {
            this.typeId = typeEntity.getTypeId();
            this.name = typeEntity.getName();
            this.description = typeEntity.getDescription();

        }
    }

    @Override
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public RichTextInfo getDescription() {
        if (this.description == null) {
            this.description = new RichTextInfo();
        }
        return description;
    }

    public void setDescription(RichTextInfo description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
