package com.github.gustavoflor.springfields.entrypoint.web.controller;

import com.github.gustavoflor.springfields.core.UserService;
import com.github.gustavoflor.springfields.entrypoint.web.Query;
import com.github.gustavoflor.springfields.entrypoint.web.SearchQuery;
import com.github.gustavoflor.springfields.entrypoint.web.payload.UserDataPayload;
import com.github.gustavoflor.springfields.entrypoint.web.payload.UserPayload;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Operation(
        responses = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserPayload.class)))
            )
        }
    )
    public MappingJacksonValue findAll(@Valid SearchQuery query) {
        final var users = userService.findAll();
        return query.filter(users);
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @Operation(
        responses = {
            @ApiResponse(
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = UserDataPayload.class))
            )
        }
    )
    public MappingJacksonValue findById(@PathVariable Long id, @Valid Query query) {
        final var user = userService.findById(id);
        return query.filter(user);
    }

}
