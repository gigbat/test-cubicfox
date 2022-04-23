package com.cubicfox.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Company extends GenericModel {
    private String name;
    private String catchPhrase;
    private String bs;
}
