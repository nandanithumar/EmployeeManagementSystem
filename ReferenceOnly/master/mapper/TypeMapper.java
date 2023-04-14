/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.master.mapper;

import com.egovja.tatransform.licencingmanagement.common.dto.info.MetaInfo;
import com.egovja.tatransform.licencingmanagement.common.dto.info.RichTextInfo;
import com.egovja.tatransform.licencingmanagement.master.dto.info.TypeInfo;
import com.egovja.tatransform.licencingmanagement.master.model.TypeEntity;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This mapper is used for conversion between type and TypeInfo.
 *
 * @author akshar
 * @since 2021-2-17
 */
public final class TypeMapper {

    private TypeMapper() {
    }

    /**
     * Convert Type to TypeInfo.
     *
     * @param type type
     * @return TypeInfo converted from Type
     */
    public static TypeInfo convertTypeToTypeInfo(TypeEntity type) {

        TypeInfo typeInfo = new TypeInfo();
        MetaInfo meta = new MetaInfo();
        RichTextInfo description = new RichTextInfo();

        description.setFormatted(type.getDescriptionFormatted());
        description.setPlain(type.getDescriptionPlain());

        meta.setCreatedAt(type.getCreatedAt());
        meta.setCreatedBy(type.getCreatedBy());
        meta.setUpdatedAt(type.getUpdatedAt());
        meta.setUpdatedBy(type.getUpdatedBy());
        meta.setVersion(type.getVersion());

        typeInfo.setId(type.getId());
        typeInfo.setCode(type.getCode());
        typeInfo.setName(type.getName());
        typeInfo.setRefObjUri(type.getRefObjectUri());
        typeInfo.setEffectiveDate(type.getEffectiveDate());
        typeInfo.setExpirationDate(type.getExpirationDate());
        typeInfo.setIcon(type.getIcon());
        typeInfo.setDescription(description);
        typeInfo.setMeta(meta);
        typeInfo.setStateId(type.getStateId());

        return typeInfo;
    }

    /**
     * Convert list of Type Object to list of TypeInfo.
     *
     * @param typeObjects list of type objects
     * @return List of TypeInfo converted from List of Type Object
     */
    public static List<TypeInfo> convertTypeObjectsToTypeInfos(List<Object[]> typeObjects) {
        return typeObjects.stream()
                .map(o -> convertTypeToTypeInfo((TypeEntity) o[0]))
                .collect(Collectors.toList());
    }

    /**
     * Convert list of Types to list of TypeInfo.
     *
     * @param types list of type Entity objects
     * @return List of TypeInfo converted from List of Type Object
     */
    public static List<TypeInfo> convertTypesToTypeInfos(List<TypeEntity> types) {
        return types.stream()
                .map(s -> convertTypeToTypeInfo(s))
                .collect(Collectors.toList());
    }

    /**
     * Convert TypeInfo to Type.
     *
     * @param typeInfo typeInfo
     * @return Type converted from TypeInfo
     */
    public static TypeEntity convertTypeInfoToType(TypeInfo typeInfo) {

        TypeEntity type = new TypeEntity();

        type.setId(typeInfo.getId());
        type.setCode(typeInfo.getCode());
        type.setName(typeInfo.getName());
        type.setRefObjectUri(typeInfo.getRefObjectUri());
        type.setStateId(typeInfo.getStateId());

        if (typeInfo.getIcon() != null) {
            type.setIcon(typeInfo.getIcon());
        }

        if (typeInfo.getDescription() != null) {
            type.setDescriptionFormatted(typeInfo.getDescription().getFormatted());
            type.setDescriptionPlain(typeInfo.getDescription().getPlain());
        }

        type.setEffectiveDate(typeInfo.getEffectiveDate());
        type.setExpirationDate(typeInfo.getExpirationDate());

        type.setVersion(typeInfo.getMeta().getVersion());
        return type;
    }
}
