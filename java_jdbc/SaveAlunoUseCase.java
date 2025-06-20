package com.sollaris.admin.catalogo.infrastructure;

public class SaveAlunoUseCase {
    private final IRepository<Aluno> repository;

    public SaveAlunoUseCase(IRepository<Aluno> repository) {
        this.repository = repository;
    }

    public Aluno execute(String id, String nome, String email, String telefone) {
        Aluno aluno = new Aluno(id, nome, email, telefone);
        return repository.save(aluno);
    }
}
