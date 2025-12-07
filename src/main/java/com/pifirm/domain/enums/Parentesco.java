package com.pifirm.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Parentesco {
    PADRE("Padre"),
    MADRE("Madre"),
    HIJO("Hijo"),
    CONYUGE("Cónyuge"),
    HERMANO("Hermano"),
    OTRO("Otro");

    private final String label;

    Parentesco(String label) { this.label = label; }

    @JsonValue
    public String getLabel() { return label; }

    @Override
    public String toString() { return label; }

    @JsonCreator
    public static Parentesco fromValue(String value) {
        if (value == null) return null;
        String normalized = value.trim();
        for (Parentesco p : values()) {
            if (p.label.equalsIgnoreCase(normalized)) return p;
        }
        throw new IllegalArgumentException("Unknown Parentesco: " + value);
    }
}
