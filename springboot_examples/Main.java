package com.sollaris.admin.catalogo.infrastructure;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AlunoMysqlRespository repository = new AlunoMysqlRespository();
//    AlunoJsonRepository jsonRepository = new AlunoJsonRepository("alunos.json");
        SaveAlunoUseCase useCase = new SaveAlunoUseCase(repository);
        FindAlunoUseCase findUseCase = new FindAlunoUseCase(repository);
        DeleteAlunoUseCase deleteUseCase = new DeleteAlunoUseCase(repository);

//        Aluno aluno = useCase.execute("1", "João", "aaa@aaa.com", "999999999");
//        Aluno aluno = findUseCase.execute("3");
//        System.out.println(aluno);
//        deleteUseCase.execute("1");


        ListAlunoUseCase listUseCase = new ListAlunoUseCase(repository);
        List<Aluno> alunos = listUseCase.execute();
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }
}