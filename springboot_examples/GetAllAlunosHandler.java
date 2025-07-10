package com.sollaris.admin.catalogo.infrastructure;

import java.util.List;

public class GetAllAlunosHandler {
    private final AlunoMysqlRespository repository = new AlunoMysqlRespository();
    private final ListAlunoUseCase listAlunoUseCase = new ListAlunoUseCase(repository);

    public List<Aluno> handle() {
        List<Aluno> alunos = listAlunoUseCase.execute();
        if (alunos == null || alunos.isEmpty()) {
            throw new IllegalArgumentException("Nenhum aluno encontrado");
        }
        return alunos;
    }
}
