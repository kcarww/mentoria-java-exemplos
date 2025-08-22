package com.sollaris.application;

import com.sollaris.domain.Aluno;
import com.sollaris.infrastructure.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarAlunosUseCase {

    private final AlunoRepository alunoRepository;

    @Autowired
    public ListarAlunosUseCase(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> execute() {
        return alunoRepository.findAll();
    }
}
