package com.cubicfox.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Geo {
    private long id;
    private double lat;
    private double lng;
}
