package org.example;

import br.edu.ifpa.laboratorio.view.FormularioAluno;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        // Define o visual do sistema para parecer com o Windows/Sistema Operacional nativo
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Inicia o fluxo pela tela de cadastro de alunos
        SwingUtilities.invokeLater(() -> {
            FormularioAluno telaInicial = new FormularioAluno();
            telaInicial.setVisible(true);
        });
    }
}