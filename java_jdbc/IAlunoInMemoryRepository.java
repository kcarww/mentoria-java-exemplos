package com.sollaris.admin.catalogo.infrastructure;

import java.util.ArrayList;
import java.util.List;

public class IAlunoInMemoryRepository implements  IRepository<Aluno>  {
    private ArrayList<Aluno> alunos = new ArrayList<>();

    @Override
    public Aluno save(Aluno aluno) {
        alunos.add(aluno);
        return aluno;
    }

    @Override
    public Aluno findById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<Aluno> findAll() {
        return alunos;
    }

    @Override
    public void update(Aluno entity) {

    }
}
