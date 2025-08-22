package com.sollaris.application;

import com.sollaris.infrastructure.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcluirUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public ExcluirUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void execute(String id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário com id " + id + " não encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}