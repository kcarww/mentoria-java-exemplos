package com.sollaris.admin.catalogo.infrastructure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoMysqlRespository implements IRepository<Aluno> {

    @Override
    public Aluno save(Aluno aluno) {
        String sql = "INSERT INTO alunos (id, nome, email, telefone) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, aluno.getId());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getTelefone());
            stmt.executeUpdate();
            return aluno;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir: " + e);
        }

    }

    @Override
    public Aluno findById(String id) {
        String sql = "SELECT * FROM alunos WHERE id = ?";
        try {
            Connection conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getString("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEmail(rs.getString("email"));
                aluno.setTelefone(rs.getString("telefone"));
                return aluno;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar aluno: " + e);
        }
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM alunos WHERE id = ?";
        try {
            Connection conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar aluno: " + e);
        }
    }

    @Override
    public List<Aluno> findAll() {
        String sql = "SELECT * FROM alunos";
        try {
            Connection conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Aluno> alunos = new ArrayList<>();
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getString("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEmail(rs.getString("email"));
                aluno.setTelefone(rs.getString("telefone"));
                alunos.add(aluno);
            }
            return alunos;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos os alunos: " + e);
        }
    }

    @Override
    public void update(Aluno aluno) {
        
    }
}
