package com.sollaris.admin.catalogo.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonAlunoRepository implements Repository<Aluno> {
    private final File file;
    private final ObjectMapper mapper;
    private List<Aluno> alunos;


    public JsonAlunoRepository(String filePath) {
        this.file = new File(filePath);
        this.mapper = new ObjectMapper();
        this.alunos = loadFromFile();
    }

    private List<Aluno> loadFromFile() {
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

    private void saveToFile() {
        try {
            mapper.writeValue(file, alunos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Aluno save(Aluno aluno) {
        alunos.add(aluno);
        saveToFile();
        return aluno;
    }

    @Override
    public Aluno update(Aluno aluno) {
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getId().equals(aluno.getId())) {
                alunos.set(i, aluno);
                saveToFile();
                return aluno;
            }
        }
        return null;
    }

    @Override
    public Aluno findById(String id) {
        return alunos.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteById(String id) {
        alunos.removeIf(a -> a.getId().equals(id));
        saveToFile();
    }
}
