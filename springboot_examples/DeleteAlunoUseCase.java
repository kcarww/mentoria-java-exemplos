package com.sollaris.admin.catalogo.infrastructure;

public class DeleteAlunoUseCase {
    private final IRepository<Aluno> repository;

    public DeleteAlunoUseCase(IRepository<Aluno> repository) {
        this.repository = repository;
    }

    public void execute(String id) {
        Aluno aluno = repository.findById(id);
        if (aluno == null) {
            throw new RuntimeException("Aluno não encontrado com ID: " + id);
        }
        repository.delete(id);
    }
}
