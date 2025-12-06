package com.pifirm.web.response;

public record GenericResponse(
        int status,
        String message
) {
}
