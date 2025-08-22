package com.sollaris.application;

import com.sollaris.domain.Aluno;
import com.sollaris.infrastructure.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriarAlunoUseCase {
    private final AlunoRepository alunoRepository;

    @Autowired
    public CriarAlunoUseCase(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno execute(Aluno aluno) {
         return alunoRepository.save(aluno);
    }
}
