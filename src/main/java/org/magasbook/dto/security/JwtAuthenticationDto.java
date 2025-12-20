package org.magasbook.dto.security;

import lombok.Data;

@Data
public class JwtAuthenticationDto {

    private String token;
    private String refreshToken;

}
