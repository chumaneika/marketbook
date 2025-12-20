package org.magasbook.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // 1. Получаем заголовок Authorization
        String authHeader = request.getHeader("Authorization");

        // 2. Если заголовка нет или он не Bearer — пропускаем запрос
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Извлекаем JWT из заголовка
        String jwt = authHeader.substring(7);

        // 4. Проверяем токен и извлекаем subject
        String numberPhone;
        try {
            numberPhone = jwtService.getNumberPhoneFromToken(jwt);
        } catch (Exception e) {
            // токен битый / истёкший / подделан
            filterChain.doFilter(request, response);
            return;
        }

        // 5. Если пользователь ещё не аутентифицирован
        if (SecurityContextHolder.getContext().getAuthentication() == null) {

            // 6. Загружаем пользователя из БД
            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(numberPhone);

            // 7. Валидируем токен (подпись, срок жизни)
            if (jwtService.validateJwtToken(jwt)) {

                // 8. Создаём Authentication
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                // 9. Добавляем детали запроса
                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // 10. Кладём Authentication в SecurityContext
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        }

        // 11. Передаём управление дальше
        filterChain.doFilter(request, response);
    }
}
