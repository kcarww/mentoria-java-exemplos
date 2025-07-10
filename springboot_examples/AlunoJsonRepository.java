package com.sollaris.admin.catalogo.infrastructure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlunoJsonRepository implements IRepository<Aluno>{
    private final File file;
    private final ObjectMapper mapper;
    private List<Aluno> alunos;

    public AlunoJsonRepository(String filePath) {
        this.file = new File(filePath);
        this.mapper = new ObjectMapper();
        this.alunos = loadAlunos();
    }

    private List<Aluno> loadAlunos() {
        try {
            if (!file.exists()) {
                return new ArrayList<>();
            }

            return mapper.readValue(file, new TypeReference<List<Aluno>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveAlunos() {
        try {
            mapper.writeValue(file, alunos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Aluno save(Aluno aluno) {
        alunos.add(aluno);
        saveAlunos();
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
        return List.of();
    }

    @Override
    public void update(Aluno entity) {

    }
}
