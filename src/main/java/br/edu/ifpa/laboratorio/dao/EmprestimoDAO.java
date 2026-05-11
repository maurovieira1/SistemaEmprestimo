package br.edu.ifpa.laboratorio.dao;

import br.edu.ifpa.laboratorio.database.ConexaoMySQL;
import java.sql.*;

public class EmprestimoDAO {
    public void realizarEmprestimo(int idAluno, int idEquipamento) {
        String sqlEmprestimo = "INSERT INTO emprestimo (id_aluno, id_equipamento, status) VALUES (?, ?, 'ATIVO')";
        String sqlUpdateEquipamento = "UPDATE equipamento SET disponivel = FALSE WHERE id = ?";

        try (Connection conn = ConexaoMySQL.getConexao()) {
            conn.setAutoCommit(false); // Inicia uma transação segura

            try (PreparedStatement stmtEmp = conn.prepareStatement(sqlEmprestimo);
                 PreparedStatement stmtEquip = conn.prepareStatement(sqlUpdateEquipamento)) {

                stmtEmp.setInt(1, idAluno);
                stmtEmp.setInt(2, idEquipamento);
                stmtEmp.executeUpdate();

                stmtEquip.setInt(1, idEquipamento);
                stmtEquip.executeUpdate();

                conn.commit(); // Salva as duas operações
                System.out.println("Empréstimo realizado e equipamento reservado!");
            } catch (SQLException e) {
                conn.rollback(); // Se der erro em um, cancela tudo
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Erro no empréstimo: " + e.getMessage());
        }
    }
}