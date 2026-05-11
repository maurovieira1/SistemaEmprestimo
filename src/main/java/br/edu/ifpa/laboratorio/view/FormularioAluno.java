package br.edu.ifpa.laboratorio.view;

import br.edu.ifpa.laboratorio.dao.AlunoDAO;
import br.edu.ifpa.laboratorio.model.Aluno;
import javax.swing.*;
import java.awt.*;

public class FormularioAluno extends JFrame {
    private JTextField txtNome = new JTextField(20);
    private JTextField txtMatricula = new JTextField(20);
    private JButton btnSalvar = new JButton("Salvar e Ver Alunos");
    private AlunoDAO alunoDAO = new AlunoDAO();

    public FormularioAluno() {
        setTitle("Cadastro de Aluno");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel(" Nome:")); add(txtNome);
        add(new JLabel(" Matrícula:")); add(txtMatricula);
        add(new JLabel("")); add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String matricula = txtMatricula.getText().trim();

            if (!nome.isEmpty() && !matricula.isEmpty()) {
                Aluno a = new Aluno();
                a.setNome(nome);
                a.setMatricula(matricula);
                alunoDAO.salvar(a);

                JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!");
                this.dispose(); // Fecha o formulário
                new TelaListaAlunos().setVisible(true); // Abre a visualização da tabela
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            }
        });
        setLocationRelativeTo(null);
    }
}