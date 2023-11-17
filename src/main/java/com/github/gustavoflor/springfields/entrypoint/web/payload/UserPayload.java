package com.github.gustavoflor.springfields.entrypoint.web.payload;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.github.gustavoflor.springfields.core.User;
import lombok.Builder;

@JsonFilter("UserPayload")
@Builder
public record UserPayload(Long id,
                          String fullName,
                          String cpf,
                          String email) {

    public static UserPayload of(final User user) {
        return UserPayload.builder()
            .id(user.id())
            .fullName(user.fullName())
            .cpf(user.cpf())
            .email(user.email())
            .build();
    }

    public UserDataPayload wrap() {
        return new UserDataPayload(this);
    }

}
