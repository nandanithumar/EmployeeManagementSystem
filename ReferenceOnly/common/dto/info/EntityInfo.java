/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.info;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.Entity;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * This class is provides implementation for HasEffectiveDates.
 *
 * @author Hiren Morzariya
 * @since 2021/02/19
 */
public class EntityInfo extends TypeStateEntityInfo implements Entity, Serializable {

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
     * Constructs a new EntityInfo.
     */
    public EntityInfo() {
    }

    /**
     * Constructs a new EntityInfo from another Entity.
     *
     * @param entity the Entity to copy
     */
    public EntityInfo(Entity entity) {
        super(entity);
        if (entity != null) {
            this.name = entity.getName();
            if (entity.getDescription() != null) {
                this.description = new RichTextInfo(entity.getDescription());
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
