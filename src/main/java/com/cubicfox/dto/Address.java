package com.cubicfox.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Address {
    private final String street;
    private final String suite;
    private final String city;
    private final String zipcode;
    private final Geo geo;
}
