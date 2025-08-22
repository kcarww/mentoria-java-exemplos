package com.sollaris.infrastructure.security;

import com.sollaris.application.BuscarUsuarioPorLoginUseCase;
import com.sollaris.domain.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final BuscarUsuarioPorLoginUseCase buscarUsuarioPorLoginUseCase;

    public JwtAuthenticationFilter(JwtService jwtService, BuscarUsuarioPorLoginUseCase buscarUsuarioPorLoginUseCase) {
        this.jwtService = jwtService;
        this.buscarUsuarioPorLoginUseCase = buscarUsuarioPorLoginUseCase;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                String login = jwtService.getSubject(token);
                Usuario usuario = buscarUsuarioPorLoginUseCase.execute(login);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                usuario.getLogin(), null, Collections.emptyList());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                // Token inválido/expirado ou usuário não encontrado: segue sem autenticar
            }
        }
        filterChain.doFilter(request, response);
    }
}