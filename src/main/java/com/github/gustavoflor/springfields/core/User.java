package com.github.gustavoflor.springfields.core;

public record User(Long id,
                   String fullName,
                   String cpf,
                   String email) {
}
