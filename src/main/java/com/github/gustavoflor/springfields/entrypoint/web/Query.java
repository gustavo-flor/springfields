package com.github.gustavoflor.springfields.entrypoint.web;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.github.gustavoflor.springfields.core.User;
import com.github.gustavoflor.springfields.core.UserField;
import com.github.gustavoflor.springfields.entrypoint.web.payload.UserPayload;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Query {

    private Set<String> fields = new HashSet<>();

    @NotEmpty
    public Set<UserField> getFields() {
        return fields.stream()
            .map(UserField::of)
            .collect(Collectors.toSet());
    }

    public MappingJacksonValue filter(final User user) {
        return getMappingJacksonValue(UserPayload.of(user));
    }

    public MappingJacksonValue filter(final List<User> users) {
        final var userPayloads = users.stream()
            .map(UserPayload::of)
            .toList();
        return getMappingJacksonValue(userPayloads);
    }

    private MappingJacksonValue getMappingJacksonValue(final Object value) {
        final var mappingJacksonValue = new MappingJacksonValue(value);
        mappingJacksonValue.setFilters(getFilterProvider());
        return mappingJacksonValue;
    }

    private SimpleFilterProvider getFilterProvider() {
        final var filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter(UserPayload.class.getSimpleName(), getPropertyFilter());
        return filterProvider;
    }

    private SimpleBeanPropertyFilter getPropertyFilter() {
        final var properties = getFields()
            .stream()
            .map(UserField::getPropertyName)
            .toArray(String[]::new);
        return SimpleBeanPropertyFilter.filterOutAllExcept(properties);
    }

}
