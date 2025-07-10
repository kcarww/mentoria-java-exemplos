package com.sollaris.admin.catalogo.infrastructure;

public class CreateAlunoHandler {
    AlunoMysqlRespository repository = new AlunoMysqlRespository();
    SaveAlunoUseCase useCase = new SaveAlunoUseCase(repository);

    public Aluno handle(Aluno aluno) {
        if (aluno.getNome() == null || aluno.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        return useCase.execute(aluno.getId(),
                                 aluno.getNome(),
                                 aluno.getEmail(),
                                 aluno.getTelefone());
    }
}
