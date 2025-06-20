package com.sollaris.admin.catalogo.infrastructure;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        final var inMemoryrepository = new IAlunoInMemoryRepository();
        AlunoMysqlRepository repository = new AlunoMysqlRepository();

        // Criar aluno
        Aluno aluno = new Aluno("2", "João Gomes", "joaoneres@email.com", "11999999999");
        aluno = repository.save(aluno);
        System.out.println("Aluno salvo: " + aluno);

        // Buscar todos
        ListAlunosUseCase listAlunosUseCase = new ListAlunosUseCase(repository);
        List<Aluno> alunos = listAlunosUseCase.execute();
        alunos.forEach(System.out::println);
    }
}
