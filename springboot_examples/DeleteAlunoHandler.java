package com.sollaris.admin.catalogo.infrastructure;

public class DeleteAlunoHandler {
    private final AlunoMysqlRespository repository = new AlunoMysqlRespository();
    private final DeleteAlunoUseCase useCase = new DeleteAlunoUseCase(repository);

    public void handle(String id) {
        Aluno aluno = repository.findById(id);
        if (aluno == null) {
            throw new RuntimeException("Aluno não encontrado com ID: " + id);
        }

        useCase.execute(id);
    }
}
