package com.pifirm.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum NivelEducativo {
    PRIMARIA("Primaria"),
    SECUNDARIA("Secundaria"),
    TECNICO("Técnico"),
    UNIVERSITARIO("Universitario"),
    POSGRADO("Posgrado");

    private final String label;

    NivelEducativo(String label) { this.label = label; }

    @JsonValue
    public String getLabel() { return label; }

    @Override
    public String toString() { return label; }

    @JsonCreator
    public static NivelEducativo fromValue(String value) {
        if (value == null) return null;
        String normalized = value.trim();
        for (NivelEducativo n : values()) {
            if (n.label.equalsIgnoreCase(normalized)) return n;
        }
        throw new IllegalArgumentException("Unknown NivelEducativo: " + value);
    }
}
