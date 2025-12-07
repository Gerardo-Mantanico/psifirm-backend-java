package com.pifirm.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Genero {
    FEMENINO("Femenino"),
    MASCULINO("Masculino"),
    NO_BINARIO("No binario"),
    PREFIERO_NO_ESPECIFICAR("Prefiero no especificar");

    private final String label;

    Genero(String label) { this.label = label; }

    @JsonValue
    public String getLabel() { return label; }

    @Override
    public String toString() { return label; }

    @JsonCreator
    public static Genero fromValue(String value) {
        if (value == null) return null;
        String normalized = value.trim();
        for (Genero g : values()) {
            if (g.label.equalsIgnoreCase(normalized)) return g;
        }
        throw new IllegalArgumentException("Unknown Genero: " + value);
    }
}
