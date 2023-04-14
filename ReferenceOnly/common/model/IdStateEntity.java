/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.model;

import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * This model used in model those contains UUID as id.
 *
 * @author sudip
 * @since 2021-3-23
 */
@Audited
@MappedSuperclass
public class IdStateEntity extends DescriptionStateEntity {

    @Id
    @Column(name = "id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
