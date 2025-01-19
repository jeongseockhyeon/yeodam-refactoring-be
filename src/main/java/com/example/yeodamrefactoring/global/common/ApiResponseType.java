package com.example.yeodamrefactoring.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiResponseType {

    SUCCESS(200, "success"),

    UNAUTHORIZED(401, "unauthorized"),
    FORBIDDEN(400, "forbidden"),
    NOT_FOUND(404, "not_found"),
    METHOD_NOT_ALLOWED(405, "method_not_allowed"),

    NOT_VALID_RESPONSE(409, "not_valid_response"),
    ;

    private final int code;
    private final String message;
}
