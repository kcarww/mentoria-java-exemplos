package com.sollaris.controller;

import com.sollaris.application.*;
import com.sollaris.controller.dto.AlunoDTO;
import com.sollaris.domain.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    private final CriarAlunoUseCase criarAlunoUseCase;
    private final ListarAlunosUseCase listarAlunosUseCase;
    private final BuscarAlunoPorIdUseCase buscarAlunoPorIdUseCase;
    private final AtualizarAlunoUseCase atualizarAlunoUseCase;
    private final ExcluirAlunoUseCase excluirAlunoUseCase;

    @Autowired
    public AlunoController(
            CriarAlunoUseCase criarAlunoUseCase,
            ListarAlunosUseCase listarAlunosUseCase,
            BuscarAlunoPorIdUseCase buscarAlunoPorIdUseCase,
            AtualizarAlunoUseCase atualizarAlunoUseCase,
            ExcluirAlunoUseCase excluirAlunoUseCase
    ) {
        this.criarAlunoUseCase = criarAlunoUseCase;
        this.listarAlunosUseCase = listarAlunosUseCase;
        this.buscarAlunoPorIdUseCase = buscarAlunoPorIdUseCase;
        this.atualizarAlunoUseCase = atualizarAlunoUseCase;
        this.excluirAlunoUseCase = excluirAlunoUseCase;
    }

    @PostMapping
    public Aluno criarAluno(@RequestBody AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.nome);
        aluno.setEmail(alunoDTO.email);
        aluno.setTelefone(alunoDTO.telefone);

        return criarAlunoUseCase.execute(aluno);
    }

    @GetMapping
    public List<Aluno> listarAlunos() {
        return listarAlunosUseCase.execute();
    }

    @GetMapping("/{id}")
    public Aluno buscarAlunoPorId(@PathVariable String id) {
        return buscarAlunoPorIdUseCase.execute(id);
    }

    @PutMapping("/{id}")
    public Aluno atualizarAluno(@PathVariable String id, @RequestBody AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.nome);
        aluno.setEmail(alunoDTO.email);
        aluno.setTelefone(alunoDTO.telefone);

        return atualizarAlunoUseCase.execute(id, aluno);
    }

    @DeleteMapping("/{id}")
    public void excluirAluno(@PathVariable String id) {
        excluirAlunoUseCase.execute(id);
    }

}
