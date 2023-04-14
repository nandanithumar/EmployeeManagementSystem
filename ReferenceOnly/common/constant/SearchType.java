
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egovja.tatransform.licencingmanagement.common.constant;

/**
 * Type of the search.
 *
 * @author sudip
 * @since 2021-02-15
 */
public enum SearchType {
    STARTING("STARTING"),
    ENDING("ENDING"),
    CONTAINING("CONTAINING"),
    FETCH("FETCH"),
    EXACTLY("EXACTLY");

    private final String name;

    private SearchType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
