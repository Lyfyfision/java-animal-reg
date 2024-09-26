package org.animalreg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Rule {
    private String name;
    private String condition;
}
