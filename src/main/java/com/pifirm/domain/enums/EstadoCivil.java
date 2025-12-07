package com.pifirm.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EstadoCivil {
    SOLTERO("Soltero"),
    CASADO("Casado"),
    DIVORCIADO("Divorciado"),
    VIUDO("Viudo"),
    UNION_LIBRE("Unión libre");

    private final String label;

    EstadoCivil(String label) { this.label = label; }

    @JsonValue
    public String getLabel() { return label; }

    @Override
    public String toString() { return label; }

    @JsonCreator
    public static EstadoCivil fromValue(String value) {
        if (value == null) return null;
        String normalized = value.trim();
        for (EstadoCivil e : values()) {
            if (e.label.equalsIgnoreCase(normalized)) return e;
        }
        throw new IllegalArgumentException("Unknown EstadoCivil: " + value);
    }
}
