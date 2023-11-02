package com.github.gustavoflor.springfields.entrypoint.web;

import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.stream.Stream;

import static java.util.Objects.nonNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchQuery extends Query {

    private String fullName;
    private String cpf;
    private String email;

    @AssertTrue(message = "Only one filter should be used, choose between [fullName, cpf, email]")
    public boolean hasOnlyOneFilter() {
        final var filters = Stream.of(nonNull(fullName), nonNull(cpf), nonNull(email))
            .filter(it -> it)
            .count();
        return filters == 1;
    }

}
