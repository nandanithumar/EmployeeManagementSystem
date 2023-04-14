package com.egovja.tatransform.licencingmanagement.common.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Audited
@MappedSuperclass
public class SerialStringIdDescriptionEntity extends DescriptionEntity {

    @Id
    @GeneratedValue(generator = "common_gen")
    @GenericGenerator(
            name = "common_gen",
            parameters = {
                    @Parameter(name = "prefix", value = "A"),
                    @Parameter(name = "formatString", value = "%03d")
            },
            strategy = "com.egovja.tatransform.licencingmanagement.common.utils.CommonGenericIdGenerator"
    )
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SerialStringIdDescriptionEntity{"
                + "id='" + id + '\''
                + "} " + super.toString();
    }
}
