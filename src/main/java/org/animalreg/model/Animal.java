package org.animalreg.model;

import java.util.List;

public record Animal(List<String> properties) {
    public String getWeight() {
        return properties.get(0);
    }

    public String getHeight() {
        return properties.get(1);
    }

    public String getType() {
        return properties.get(2);
    }
}
