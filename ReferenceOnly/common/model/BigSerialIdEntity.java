package com.egovja.tatransform.licencingmanagement.common.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * This model used in model those, which contains big serial as id, typeId and meta.
 */
@Audited
@MappedSuperclass
public class BigSerialIdEntity extends DescriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
