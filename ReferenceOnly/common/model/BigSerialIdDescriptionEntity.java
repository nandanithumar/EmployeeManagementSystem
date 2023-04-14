package com.egovja.tatransform.licencingmanagement.common.model;

import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Audited
@MappedSuperclass
public class BigSerialIdDescriptionEntity extends DescriptionEntity {

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

    @Override
    public String toString() {
        return "SerialStringIdDescriptionEntity{"
                + "id='" + id + '\''
                + "} " + super.toString();
    }
}
