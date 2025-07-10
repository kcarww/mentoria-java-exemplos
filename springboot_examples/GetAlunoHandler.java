package com.sollaris.admin.catalogo.infrastructure;

public class GetAlunoHandler {
    AlunoMysqlRespository repository = new AlunoMysqlRespository();
    FindAlunoUseCase useCase = new FindAlunoUseCase(repository);

    public Aluno handle(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID é obrigatório");
        }

        Aluno aluno = useCase.execute(id);
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não encontrado");
        }

        return aluno;
    }
}
