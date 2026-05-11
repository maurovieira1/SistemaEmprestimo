package br.edu.ifpa.laboratorio.view;

import br.edu.ifpa.laboratorio.dao.EquipamentoDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaLaboratorio extends JFrame {
    private JTable tabela;
    private DefaultTableModel modelo;
    private EquipamentoDAO dao = new EquipamentoDAO();
    private JTextField txtNovoItem = new JTextField(20);

    public TelaLaboratorio() {
        // Configurações Básicas
        setTitle("Gestão de Inventário - Laboratório");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // --- PAINEL SUPERIOR (CADASTRO DE EQUIPAMENTO) ---
        JPanel pTopo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pTopo.setBorder(BorderFactory.createTitledBorder("Adicionar Novo Equipamento"));

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBackground(new Color(45, 52, 54));
        btnAdicionar.setForeground(Color.WHITE);

        pTopo.add(new JLabel("Nome do Item:"));
        pTopo.add(txtNovoItem);
        pTopo.add(btnAdicionar);
        add(pTopo, BorderLayout.NORTH);

        // --- PAINEL CENTRAL (TABELA) ---
        // Criamos o modelo para que apenas a coluna "Qtd" (índice 2) seja editável
        modelo = new DefaultTableModel(new Object[]{"ID", "Equipamento", "Qtd", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2; // Permite editar apenas a quantidade
            }
        };

        tabela = new JTable(modelo);
        tabela.setRowHeight(25);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        // EVENTO: Salva no banco automaticamente ao editar a quantidade na tabela
        modelo.addTableModelListener(e -> {
            if (e.getType() == javax.swing.event.TableModelEvent.UPDATE) {
                int linha = e.getFirstRow();
                int coluna = e.getColumn();
                if (coluna == 2) { // Se a coluna alterada foi a de Quantidade
                    try {
                        int id = (int) modelo.getValueAt(linha, 0);
                        int novaQtd = Integer.parseInt(modelo.getValueAt(linha, 2).toString());
                        dao.atualizarQuantidade(id, novaQtd); // Atualiza no MySQL
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Por favor, insira um número válido na quantidade.");
                        carregarDados(); // Recarrega para desfazer a digitação errada
                    }
                }
            }
        });

        // --- PAINEL INFERIOR (AÇÕES) ---
        JPanel pBaixo = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton btnEmprestar = new JButton("Marcar Emprestado");
        JButton btnDisponivel = new JButton("Marcar Disponível");
        JButton btnExcluir = new JButton("Excluir Item");
        JButton btnAvancar = new JButton("Finalizar Empréstimo →");

        // Estilização
        btnExcluir.setForeground(Color.RED);
        btnAvancar.setBackground(new Color(0, 184, 148));
        btnAvancar.setForeground(Color.WHITE);
        btnAvancar.setFont(new Font("Arial", Font.BOLD, 12));

        pBaixo.add(btnEmprestar);
        pBaixo.add(btnDisponivel);
        pBaixo.add(btnExcluir);
        pBaixo.add(new JSeparator(SwingConstants.VERTICAL));
        pBaixo.add(btnAvancar);

        add(pBaixo, BorderLayout.SOUTH);

        // --- LÓGICA DOS BOTÕES ---

        // Adicionar novo item
        btnAdicionar.addActionListener(e -> {
            String nome = txtNovoItem.getText().trim();
            if (!nome.isEmpty()) {
                dao.salvar(nome);
                txtNovoItem.setText("");
                carregarDados();
            }
        });

        // Mudar para Emprestado
        btnEmprestar.addActionListener(e -> mudarStatus(0));

        // Mudar para Disponível
        btnDisponivel.addActionListener(e -> mudarStatus(1));

        // Excluir item permanentemente
        btnExcluir.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha != -1) {
                int id = (int) modelo.getValueAt(linha, 0);
                String nome = modelo.getValueAt(linha, 1).toString();

                int confirm = JOptionPane.showConfirmDialog(this,
                        "Deseja realmente excluir o item: " + nome + "?",
                        "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    dao.excluir(id);
                    carregarDados();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um item na tabela para excluir.");
            }
        });

        // Avançar no Fluxo
        btnAvancar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Fluxo concluído! Equipamentos e Aluno registrados.");
            // Aqui você poderia abrir a próxima tela, como a de recibo ou histórico
        });

        // Inicialização
        carregarDados();
        setLocationRelativeTo(null);
    }

    private void mudarStatus(int status) {
        int linha = tabela.getSelectedRow();
        if (linha != -1) {
            int id = (int) modelo.getValueAt(linha, 0);
            dao.atualizarStatus(id, status);
            carregarDados();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item primeiro!");
        }
    }

    private void carregarDados() {
        modelo.setRowCount(0); // Limpa a tabela
        List<Object[]> dados = dao.listarTudo(); // Busca no Banco
        for (Object[] linha : dados) {
            modelo.addRow(linha);
        }
    }
}