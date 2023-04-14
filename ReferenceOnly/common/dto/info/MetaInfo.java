/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.dto.info;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.Meta;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * This info is for meta DTO that contains
 * createdAt,createdBy,updatedAt,updatedBy and version.
 *
 * @author Hiren Morzariya
 * @since 2021-2-11
 */
public class MetaInfo implements Meta, Serializable {

    @ApiModelProperty(notes = "The creation time of the model data",
            allowEmptyValue = true,
            example = "2014-02-28",
            dataType = "Date",
            required = false,
            readOnly = true)
    private Date createdAt;

    @ApiModelProperty(notes = "The id of the creator",
            allowEmptyValue = true,
            example = "1",
            dataType = "String/Integer",
            required = false,
            readOnly = true)
    private String createdBy;

    @ApiModelProperty(notes = "The last updation time of the model data",
            allowEmptyValue = true,
            example = "2014-02-28",
            dataType = "Date",
            required = false,
            readOnly = true)
    private Date updatedAt;

    @ApiModelProperty(notes = "The id of the updator",
            allowEmptyValue = true,
            example = "1",
            dataType = "String/Integer",
            required = false,
            readOnly = true)
    private String updatedBy;

    @ApiModelProperty(notes = "The version of the model data",
            allowEmptyValue = false,
            example = "1",
            dataType = "Long",
            required = true,
            readOnly = true)
    private Long version;

    public MetaInfo() {
    }

    /**
     * This is copy ConStructure that make deep copy of the meta.
     *
     * @param meta meta
     */
    public MetaInfo(Meta meta) {
        if (meta != null) {
            if (meta.getCreatedAt() != null) {
                this.createdAt = new Date(meta.getCreatedAt().getTime());
            }
            if (meta.getUpdatedAt() != null) {
                this.updatedAt = new Date(meta.getUpdatedAt().getTime());
            }
            this.createdBy = meta.getCreatedBy();
            this.updatedBy = meta.getUpdatedBy();
            this.version = meta.getVersion();
        }
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt != null
                ? new Date(createdAt.getTime()) : null;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt != null
                ? new Date(updatedAt.getTime()) : null;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public Long getVersion() {
        return this.version;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt != null
                ? new Date(createdAt.getTime()) : null;
    }

    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }

    @Override
    public Date getUpdatedAt() {
        return updatedAt != null
                ? new Date(updatedAt.getTime()) : null;
    }

    @Override
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    @Override
    public String toString() {
        return "MetaInfo{"
                + "createdAt=" + createdAt
                + ", createdBy=" + createdBy
                + ", updatedAt=" + updatedAt
                + ", updatedBy=" + updatedBy
                + ", version=" + version
                + '}';
    }
}
