package com.sollaris.admin.catalogo.infrastructure;

import java.util.ArrayList;

public class AlunoRepository implements  Repository<Aluno> {
    ArrayList<Aluno> alunos = new ArrayList<>();

    @Override
    public Aluno save(Aluno aluno) {
        alunos.add(aluno);
        return aluno;
    }

    @Override
    public Aluno update(Aluno aluno) {
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getId().equals(aluno.getId())) {
                alunos.set(i, aluno);
                return aluno;
            }
        }
        return null;
    }

    @Override
    public Aluno findById(String id) {
        for (Aluno aluno : alunos) {
            if (aluno.getId().equals(id)) {
                return aluno;
            }
        }
        return null;
    }

    @Override
    public void deleteById(String id) {
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getId().equals(id)) {
                alunos.remove(i);
                return;
            }
        }
    }
}
