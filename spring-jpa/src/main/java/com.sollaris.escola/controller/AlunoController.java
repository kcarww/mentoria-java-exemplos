package com.sollaris.escola.controller;

import com.sollaris.escola.controller.dto.AlunoDTO;
import com.sollaris.escola.domain.Aluno;
import com.sollaris.escola.application.CriarAlunoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private final CriarAlunoUseCase criarAlunoUseCase;

    @Autowired
    public AlunoController(CriarAlunoUseCase criarAlunoUseCase) {
        this.criarAlunoUseCase = criarAlunoUseCase;
    }

    @PostMapping
    public Aluno criarAluno(@RequestBody AlunoDTO dto) {
        System.out.println(dto);
        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome);
        aluno.setEmail(dto.email);
        aluno.setTelefone(dto.telefone);
        return criarAlunoUseCase.execute(aluno);
    }
}
