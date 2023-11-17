package com.github.gustavoflor.springfields.entrypoint.web.payload;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class DataPayload<D> {

    private final D data;

}
