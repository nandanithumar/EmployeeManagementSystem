package com.egovja.tatransform.licencingmanagement.common.dto.info;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.BigSerialIdEntity;
import io.swagger.annotations.ApiModelProperty;

public class BigSerialIdEntityInfo extends EntityInfo implements BigSerialIdEntity {

    @ApiModelProperty(notes = "The unique id of the model",
            allowEmptyValue = false,
            example = "100",
            dataType = "String/Integer",
            required = false)
    private String id;

    /**
     * Constructs a new IdTypeInfo.
     */
    public BigSerialIdEntityInfo() {
    }

    /**
     * Constructs a new IdTypeInfo from another IdType.
     *
     * @param onlyBigSerialIdTypeEntityInfo the OnlyBigSerialTypeEntity to copy
     */
    public BigSerialIdEntityInfo(BigSerialIdEntityInfo onlyBigSerialIdTypeEntityInfo) {
        super(onlyBigSerialIdTypeEntityInfo);
        if (onlyBigSerialIdTypeEntityInfo != null) {
            this.setId(onlyBigSerialIdTypeEntityInfo.getId());
        }
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
