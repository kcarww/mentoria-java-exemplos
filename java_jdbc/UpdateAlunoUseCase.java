package com.sollaris.admin.catalogo.infrastructure;

public class UpdateAlunoUseCase {
    private final IRepository<Aluno> repository;

    public UpdateAlunoUseCase(IRepository<Aluno> repository) {
        this.repository = repository;
    }

    public Aluno execute(String id, String nome, String email, String telefone) {
        Aluno alunoExistente = repository.findById(id);
        if (alunoExistente == null) {
            throw new RuntimeException("Aluno com ID " + id + " não encontrado");
        }

        alunoExistente.setNome(nome);
        alunoExistente.setEmail(email);
        alunoExistente.setTelefone(telefone);

        repository.update(alunoExistente);
        return alunoExistente;
    }
}
