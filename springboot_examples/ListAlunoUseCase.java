package com.sollaris.admin.catalogo.infrastructure;

import java.util.List;

public class ListAlunoUseCase {
    private final IRepository<Aluno> repository;

    public ListAlunoUseCase(IRepository<Aluno> repository) {
        this.repository = repository;
    }

    public List<Aluno> execute() {
        return repository.findAll();
    }
}
