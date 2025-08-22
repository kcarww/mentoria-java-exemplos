package com.sollaris.application;

import com.sollaris.domain.Aluno;
import com.sollaris.infrastructure.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtualizarAlunoUseCase {

    private final AlunoRepository alunoRepository;

    @Autowired
    public AtualizarAlunoUseCase(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno execute(String id, Aluno dados) {
        Aluno existente = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno com id " + id + " não encontrado"));

        // Atualiza apenas campos permitidos
        existente.setNome(dados.getNome());
        existente.setEmail(dados.getEmail());
        existente.setTelefone(dados.getTelefone());

        return alunoRepository.save(existente);
    }
}
