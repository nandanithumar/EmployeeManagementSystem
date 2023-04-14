/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.master.dto.infc;

import com.egovja.tatransform.licencingmanagement.common.dto.iface.TemporalTypeStatelessEntity;

/**
 * This Interface is provides contract for State.
 *
 * @author hiren
 * @since 2021-02-25
 */
public interface State extends TemporalTypeStatelessEntity {

    public String getRefObjectUri();

    public boolean getIsInitialState();

}
