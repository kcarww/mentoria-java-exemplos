package com.sollaris.controller;

import com.sollaris.application.AutenticarUsuarioUseCase;
import com.sollaris.application.CadastrarUsuarioUseCase;
import com.sollaris.controller.dto.CadastroUsuarioDTO;
import com.sollaris.controller.dto.LoginDTO;
import com.sollaris.controller.dto.TokenResponseDTO;
import com.sollaris.domain.Usuario;
import com.sollaris.infrastructure.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;
    private final AutenticarUsuarioUseCase autenticarUsuarioUseCase;
    private final JwtService jwtService;

    @Autowired
    public AuthController(CadastrarUsuarioUseCase cadastrarUsuarioUseCase,
                          AutenticarUsuarioUseCase autenticarUsuarioUseCase,
                          JwtService jwtService) {
        this.cadastrarUsuarioUseCase = cadastrarUsuarioUseCase;
        this.autenticarUsuarioUseCase = autenticarUsuarioUseCase;
        this.jwtService = jwtService;
    }

    @PostMapping("/cadastrar")
    public TokenResponseDTO cadastrar(@Valid @RequestBody CadastroUsuarioDTO dto) {
        Usuario u = new Usuario();
        u.setLogin(dto.login);
        u.setEmail(dto.email);
        u.setSenha(dto.senha);

        Usuario salvo = cadastrarUsuarioUseCase.execute(u);

        String token = jwtService.generateToken(
                salvo.getLogin(),
                Map.of("login", salvo.getLogin(), "email", salvo.getEmail())
        );
        return new TokenResponseDTO(token, salvo.getLogin());
    }

    @PostMapping("/login")
    public TokenResponseDTO login(@Valid @RequestBody LoginDTO dto) {
        Usuario usuario = autenticarUsuarioUseCase.execute(dto.login, dto.senha);
        String token = jwtService.generateToken(
                usuario.getLogin(),
                Map.of("login", usuario.getLogin(), "email", usuario.getEmail())
        );
        return new TokenResponseDTO(token, usuario.getLogin());
    }
}