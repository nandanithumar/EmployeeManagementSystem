package com.egovja.tatransform.licencingmanagement.common.dto.info;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.HasIdAndMeta;
import io.swagger.annotations.ApiModelProperty;

public class IdMetaEntityInfo extends  HasMetaInfo implements HasIdAndMeta {
    @ApiModelProperty(notes = "The unique id of the model",
            allowEmptyValue = false,
            example = "jm.org.ta.state.viapointinfo.active",
            dataType = "String/Integer",
            required = false)
    private String id;

    /**
     * Constructs a new IdMetaEntityInfo.
     */
    public IdMetaEntityInfo() {
    }

    /**
     * Constructs a new IdMetaEntityInfo from another IdType.
     *
     * @param idMetaEntityInfo the IdMetaEntityInfo to copy
     */
    public IdMetaEntityInfo(IdMetaEntityInfo idMetaEntityInfo) {
        super(idMetaEntityInfo);
        if (idMetaEntityInfo != null) {
            this.id = idMetaEntityInfo.getId();
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
