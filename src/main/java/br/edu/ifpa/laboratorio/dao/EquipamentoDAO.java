package br.edu.ifpa.laboratorio.dao;

import br.edu.ifpa.laboratorio.database.ConexaoMySQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoDAO {

    public List<Object[]> listarTudo() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT id, nome, disponivel, quantidade FROM equipamento";
        try (Connection conn = ConexaoMySQL.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Object[]{
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("quantidade"),
                        rs.getInt("disponivel") == 1 ? "DISPONÍVEL" : "EMPRESTADO"
                });
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao listar: " + ex.getMessage());
        }
        return lista;
    }

    public void salvar(String nome) {
        String sql = "INSERT INTO equipamento (nome, disponivel, quantidade) VALUES (?, 1, 1)";
        try (Connection conn = ConexaoMySQL.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public void atualizarQuantidade(int id, int novaQtd) {
        String sql = "UPDATE equipamento SET quantidade = ? WHERE id = ?";
        try (Connection conn = ConexaoMySQL.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, novaQtd);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public void atualizarStatus(int id, int status) {
        String sql = "UPDATE equipamento SET disponivel = ? WHERE id = ?";
        try (Connection conn = ConexaoMySQL.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM equipamento WHERE id = ?";
        try (Connection conn = ConexaoMySQL.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
}