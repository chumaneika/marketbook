package org.magasbook.dto.security;

import lombok.Data;

@Data
public class UserCredentialsDto {
    private String numberPhone;
    private String password;
}
