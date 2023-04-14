package com.egovja.tatransform.licencingmanagement.common.model;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.HasIdAndMeta;

import javax.persistence.*;

@MappedSuperclass
public class IdMetaEntity extends MetaEntity {
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
