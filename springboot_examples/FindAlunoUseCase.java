package com.sollaris.admin.catalogo.infrastructure;

public class FindAlunoUseCase {
    private final IRepository<Aluno> repository;

    public FindAlunoUseCase(IRepository<Aluno> repository) {
        this.repository = repository;
    }

    public Aluno execute(String id) {
        Aluno aluno = repository.findById(id);
        if (aluno == null) {
            throw new RuntimeException("Aluno not found with id: " + id);
        }
        return aluno;
    }
}
