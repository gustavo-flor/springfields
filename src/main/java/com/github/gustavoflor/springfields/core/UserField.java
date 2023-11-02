package com.github.gustavoflor.springfields.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum UserField {

    ID("id"),
    FULL_NAME("fullName"),
    CPF("cpf"),
    EMAIL("email");

    private final String propertyName;

    @JsonValue
    public String getPropertyName() {
        return propertyName;
    }

    @JsonCreator
    public static UserField of(final String propertyName) {
        return Arrays.stream(values())
            .filter(it -> it.getPropertyName().equals(propertyName))
            .findFirst()
            .orElse(null);
    }

}
