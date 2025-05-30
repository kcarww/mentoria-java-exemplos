package com.sollaris.admin.catalogo.infrastructure;

public class CreateAlunoUseCase {
    private final AlunoRepository alunoRepository;

    public CreateAlunoUseCase(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno execute(String id, String nome, String email) {
        Aluno aluno = new Aluno(id, nome, email);
        return alunoRepository.save(aluno);
    }
}
