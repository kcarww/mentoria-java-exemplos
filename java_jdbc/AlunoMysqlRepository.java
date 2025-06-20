package com.sollaris.admin.catalogo.infrastructure;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AlunoMysqlRepository implements IRepository<Aluno> {

    @Override
    public Aluno save(Aluno aluno) {
        String sql = "INSERT INTO alunos (id, nome, email, telefone) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String id = UUID.randomUUID().toString();
            aluno.setId(id);

            stmt.setString(1, id);
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getTelefone());

            stmt.executeUpdate();
            return aluno;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar aluno", e);
        }
    }

    @Override
    public Aluno findById(String id) {
        String sql = "SELECT * FROM alunos WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Aluno(
                        rs.getString("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone")
                );
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar aluno", e);
        }
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM alunos WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar aluno", e);
        }
    }

    @Override
    public List<Aluno> findAll() {
        String sql = "SELECT * FROM alunos";
        List<Aluno> alunos = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                alunos.add(new Aluno(
                        rs.getString("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone")
                ));
            }
            return alunos;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar alunos", e);
        }
    }

    @Override
    public void update(Aluno aluno) {
        String sql = "UPDATE alunos SET nome = ?, email = ?, telefone = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getTelefone());
            stmt.setString(4, aluno.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar aluno", e);
        }
    }
}
