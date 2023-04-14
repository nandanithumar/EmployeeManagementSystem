package com.egovja.tatransform.licencingmanagement.common.dto.iface;

/**
 * A common interface pattern for service entities.This interface is applied to
 * entities without a primary identifier but with type and meta information.
 */
public interface TypeEntity extends HasType, HasMeta {
}
