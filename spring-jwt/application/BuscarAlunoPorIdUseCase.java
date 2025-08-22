package com.sollaris.application;

import com.sollaris.domain.Aluno;
import com.sollaris.infrastructure.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarAlunoPorIdUseCase {

    private final AlunoRepository alunoRepository;

    @Autowired
    public BuscarAlunoPorIdUseCase(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno execute(String id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno com id " + id + " não encontrado"));
    }
}
