/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.info;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.TypeStatelessEntity;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * This class is provides implementation for TypeStatelessEntity.
 *
 * @author Hiren Morzariya
 * @since 2021/02/19
 */
public class TypeStatelessEntityInfo
        extends HasMetaInfo
        implements TypeStatelessEntity, Serializable {

    @ApiModelProperty(notes = "The name of the model",
            allowEmptyValue = false,
            example = "Active",
            dataType = "String",
            required = true)
    private String name;

    @ApiModelProperty(notes = "The description of the model",
            allowEmptyValue = true,
            example = "{\n\t\"plain\":\"plain text\" ,\n\t\"formatted\":\"<b>formatted text</b>\n\"}",
            dataType = "RichTextInfo",
            required = false)
    private RichTextInfo description;

    /**
     * Constructs a new EntityInfo.
     */
    public TypeStatelessEntityInfo() {
    }

    /**
     * Constructs a new EntityInfo from another Entity.
     *
     * @param typeStatelessEntity the TypeStatelessEntity to copy
     */
    public TypeStatelessEntityInfo(TypeStatelessEntity typeStatelessEntity) {
        super(typeStatelessEntity);
        if (typeStatelessEntity != null) {
            this.name = typeStatelessEntity.getName();
            if (typeStatelessEntity.getDescription() != null) {
                this.description = new RichTextInfo(typeStatelessEntity.getDescription());
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public RichTextInfo getDescription() {
        if (this.description == null) {
            this.description = new RichTextInfo();
        }
        return description;
    }

    public void setDescription(RichTextInfo description) {
        this.description = description;
    }

}
