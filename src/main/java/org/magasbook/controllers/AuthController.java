package org.magasbook.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.magasbook.config.JwtService;
import org.magasbook.dto.auth.LoginRequest;
import org.magasbook.dto.userdto.UserCreateDTO;
import org.magasbook.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.magasbook.dto.security.JwtAuthenticationDto;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
///auth/register
//
///auth/login
//
///auth/refresh

    /**
     * Логин → аутентификация → JWT
     */
    @PostMapping("/login")
    public JwtAuthenticationDto login(@RequestBody LoginRequest request) {

        // 1. Передаём логин и пароль в Spring Security
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.numberPhone(),
                                request.password()
                        )
                );

        // 2. Если дошли сюда — пользователь аутентифицирован
        String numberPhone = authentication.getName();

        // 3. Генерируем access + refresh token
        return jwtService.generateAuthToken(numberPhone);
    }

    /**
     * Регистрация пользователя
     */
    @PostMapping("/register")
    public void register(@Valid @RequestBody UserCreateDTO request) {
        userService.createUser(request);
    }

    /**
     * Обновление access token
     */
    @PostMapping("/refresh")
    public JwtAuthenticationDto refresh(
            @RequestParam String numberPhone,
            @RequestParam String refreshToken
    ) {
        return jwtService.refreshBaseToken(numberPhone, refreshToken);
    }
}
