package br.edu.ifpa.laboratorio.view;

import br.edu.ifpa.laboratorio.dao.AlunoDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaListaAlunos extends JFrame {
    private JTable tabela;
    private DefaultTableModel modelo;
    private AlunoDAO alunoDAO = new AlunoDAO();

    public TelaListaAlunos() {
        setTitle("Visualização de Alunos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Tabela de Alunos
        modelo = new DefaultTableModel(new Object[]{"ID", "Nome", "Matrícula"}, 0);
        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        // Painel de Botões
        JPanel pBotoes = new JPanel();
        JButton btnAvancar = new JButton("Ir para Gestão de Equipamentos →");
        btnAvancar.setBackground(new Color(0, 184, 148));
        btnAvancar.setForeground(Color.WHITE);
        pBotoes.add(btnAvancar);
        add(pBotoes, BorderLayout.SOUTH);

        // Ação para avançar no fluxo
        btnAvancar.addActionListener(e -> {
            this.dispose();
            new TelaLaboratorio().setVisible(true);
        });

        carregarDados();
        setLocationRelativeTo(null);
    }

    private void carregarDados() {
        modelo.setRowCount(0);
        List<Object[]> dados = alunoDAO.listarAlunos();
        for (Object[] linha : dados) {
            modelo.addRow(linha);
        }
    }
}