package com.cubicfox.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Geo extends GenericModel {
    private double lat;
    private double lng;
}
