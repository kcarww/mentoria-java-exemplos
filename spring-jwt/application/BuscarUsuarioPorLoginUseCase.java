package com.sollaris.application;

import com.sollaris.domain.Usuario;
import com.sollaris.infrastructure.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarUsuarioPorLoginUseCase {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public BuscarUsuarioPorLoginUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario execute(String login) {
        return usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }
}