/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.master.repository;

import com.egovja.tatransform.licencingmanagement.master.model.TypeEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This repository is for making queries on the Type model.
 *
 * @author akshar
 * @since 2021-2-17
 */
@Repository
public interface TypeRepository extends JpaRepository<TypeEntity, String> {
    
    @Query("SELECT DISTINCT entity FROM TypeEntity entity \n"
            + " WHERE entity.id IN (:ids)")
    public List<TypeEntity> findTypesByIds(@Param("ids") List<String> ids);

    @Query("SELECT DISTINCT type,state.name FROM TypeEntity type \n"
            + " LEFT JOIN StateEntity state ON type.stateId = state.id \n"
            + " WHERE type.refObjectUri = :refObjectUri"
    )
    public Page<Object[]> findByRefObjectUri(
            @Param("refObjectUri") String refObjectUri,
            Pageable pageable);

    @Query("SELECT DISTINCT type,state.name FROM TypeEntity type \n"
            + " LEFT JOIN StateEntity state ON type.stateId = state.id \n"
            + " WHERE type.refObjectUri = :refObjectUri \n"
            + " AND (:name IS NULL OR UPPER(type.name) LIKE CONCAT('%',UPPER(:name),'%')) \n"
            + " AND (:stateId IS NULL OR type.stateId = :stateId)"
            + " AND (:code IS NULL OR type.code = :code)"
    )
    public Page<Object[]> findByRefObjectUriAndNameContainingIgnoreCase(
            @Param("stateId") String stateId,
            @Param("code") Integer code,
            @Param("refObjectUri") String refObjectUri,
            @Param("name") String name,
            Pageable pageable);

}
