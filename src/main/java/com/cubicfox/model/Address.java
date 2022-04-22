package com.cubicfox.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Address {
    private long id;
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;
}
