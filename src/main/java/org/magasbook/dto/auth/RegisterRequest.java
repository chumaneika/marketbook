package org.magasbook.dto.auth;

public record RegisterRequest(
        String numberPhone,
        String password

        // todo в дальнейшем создать разграничения между созданием и авторизацией пользователя
) {}
