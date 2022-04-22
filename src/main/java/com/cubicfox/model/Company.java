package com.cubicfox.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Company {
    private long id;
    private String name;
    private String catchPhrase;
    private String bs;
}
