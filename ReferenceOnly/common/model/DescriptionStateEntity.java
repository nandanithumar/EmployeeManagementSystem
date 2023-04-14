/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.model;

import com.egovja.tatransform.licencingmanagement.common.constant.Constant;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * This model used in model those contains meta, plain and formatted
 * description.
 *
 * @author sudip
 * @since 2021-3-23
 */
@Audited
@MappedSuperclass
public class DescriptionStateEntity extends StateEntity {

    @Column(name = "description_plain")
    private String descriptionPlain;

    @Column(name = "description_formatted")
    private String descriptionFormatted;

    @Column(name = "name")
    private String name;

    public String getDescriptionPlain() {
        return descriptionPlain;
    }

    public void setDescriptionPlain(String descriptionPlain) {
        this.descriptionPlain = descriptionPlain;
    }

    public String getDescriptionFormatted() {
        return descriptionFormatted;
    }

    public void setDescriptionFormatted(String descriptionFormatted) {
        this.descriptionFormatted = descriptionFormatted;
    }

    public String getName() {
        if (name == null) {
            name = Constant.EMPTY;
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
