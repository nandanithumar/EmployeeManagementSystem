/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.master.repository;

import com.egovja.tatransform.licencingmanagement.master.model.StateEntity;
import com.egovja.tatransform.licencingmanagement.master.model.TypeEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This repository is for making queries on the State model.
 *
 * @author akshay
 * @since 2021-02-25
 */
@Repository
public interface StateRepository extends JpaRepository<StateEntity, String> {

    @Query("SELECT DISTINCT entity FROM StateEntity entity \n"
            + " WHERE entity.id IN (:ids)")
    public List<StateEntity> findStatesByIds(@Param("ids") List<String> ids);

    public Page<StateEntity> findByRefObjectUri(String refObjectUri,
            Pageable pageable);

    public Page<StateEntity> findByRefObjectUriAndNameContainingIgnoreCase(
            String refObjectUri,
            String name,
            Pageable pageable);

}
