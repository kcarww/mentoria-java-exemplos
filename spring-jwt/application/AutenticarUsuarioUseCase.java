package com.sollaris.application;

import com.sollaris.domain.Usuario;
import com.sollaris.infrastructure.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AutenticarUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Usuario execute(String login, String senhaEmTextoClaro) {
        Usuario usuario = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("Credenciais inválidas"));
        boolean ok = passwordEncoder.matches(senhaEmTextoClaro, usuario.getSenha());
        if (!ok) {
            throw new IllegalArgumentException("Credenciais inválidas");
        }
        return usuario;
    }
}