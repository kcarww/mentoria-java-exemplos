package com.sollaris.application;

import com.sollaris.domain.Usuario;
import com.sollaris.infrastructure.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarUsuarioPorEmailUseCase {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public BuscarUsuarioPorEmailUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario execute(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }
}