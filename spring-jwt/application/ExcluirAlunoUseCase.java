package com.sollaris.application;

import com.sollaris.infrastructure.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcluirAlunoUseCase {

    private final AlunoRepository alunoRepository;

    @Autowired
    public ExcluirAlunoUseCase(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public void execute(String id) {
        if (!alunoRepository.existsById(id)) {
            throw new EntityNotFoundException("Aluno com id " + id + " não encontrado");
        }
        alunoRepository.deleteById(id);
    }
}
