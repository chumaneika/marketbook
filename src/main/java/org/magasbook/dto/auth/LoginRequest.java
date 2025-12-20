package org.magasbook.dto.auth;

public record LoginRequest(
        String numberPhone,
        String password
) {}
