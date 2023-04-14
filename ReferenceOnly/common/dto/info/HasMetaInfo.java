/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.info;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.HasMeta;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * This class is provides implementation for HasMeta.
 *
 * @author Hiren Morzariya
 * @since 2021-2-19
 */
public class HasMetaInfo implements HasMeta, Serializable {

    @ApiModelProperty(notes = "The meta of the model",
            allowEmptyValue = true,
            example = "{\n"
            + "\t\"createdAt\":2014-02-28 ,\n"
            + "\t\"createdBy\":\"userId\" ,\n"
            + "\t\"updatedAt\":2014-02-28 ,\n"
            + "\t\"updatedBy\":\"userId\" ,\n"
            + "\t\"version\":1\"\n"
            + "}",
            dataType = "MetaInfo",
            required = false,
            readOnly = true)
    private MetaInfo meta;

    public HasMetaInfo() {
    }

    /**
     * This is copy ConStructure that make deep copy of the hasMeta.
     *
     * @param hasMeta hasMeta
     */
    public HasMetaInfo(HasMeta hasMeta) {
        if (hasMeta != null) {
            this.meta = new MetaInfo(hasMeta.getMeta());
        }
    }

    @Override
    public MetaInfo getMeta() {
        if (this.meta == null) {
            this.meta = new MetaInfo();
        }
        return this.meta;
    }

    public void setMeta(MetaInfo meta) {
        this.meta = meta;
    }

}
