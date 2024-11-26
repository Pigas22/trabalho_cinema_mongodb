package com.trabalho.interfaces;

import com.trabalho.controllers.CinemaController;
import com.trabalho.controllers.FilmeController;
import com.trabalho.controllers.SessaoController;
import com.trabalho.models.Cinema;
import com.trabalho.models.Filme;
import com.trabalho.models.Sessao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class InserirSessao extends JFrame {

    private JTextField jTextFieldHorario;
    private JComboBox<Cinema> jComboBoxCinema;
    private JComboBox<Filme> jComboBoxFilme;
    private JTextField jTextFieldQtdAssentos;
    private JButton jButtonInserir;
    private JButton jButtonVoltar;
    private SessaoController sessaoController;
    private CinemaController cinemaController;
    private FilmeController filmeController;

    public InserirSessao() {
        cinemaController = new CinemaController();
        filmeController = new FilmeController();
        sessaoController = new SessaoController();

        initComponents();
    }

    private void initComponents() {
        // Painel superior (Cabeçalho)
        JPanel panelTop = new JPanel();
        panelTop.setBackground(new Color(51, 51, 51)); // Cor escura para o cabeçalho
        JLabel titleLabel = new JLabel("Inserir Sessão");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24)); // Fonte maior para o título
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelTop.add(titleLabel);

        // Painel principal para o formulário
        JPanel panelForm = new JPanel();
        panelForm.setBackground(new Color(245, 245, 245)); // Fundo claro
        panelForm.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // Menor espaçamento entre os componentes

        // Fontes e tamanhos
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 16); // Fonte maior
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14); // Fonte maior para os campos

        // Adicionando campos e labels
        JLabel jLabelHorario = new JLabel("Horário da Sessão:");
        jLabelHorario.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelForm.add(jLabelHorario, gbc);

        jTextFieldHorario = new JTextField(20); // Campo maior
        jTextFieldHorario.setFont(fieldFont);
        gbc.gridx = 1;
        panelForm.add(jTextFieldHorario, gbc);

        JLabel jLabelCinema = new JLabel("Cinema:");
        jLabelCinema.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelForm.add(jLabelCinema, gbc);

        jComboBoxCinema = new JComboBox<>();
        for (Cinema cinema : cinemaController.listarTodosRegistros()) {
            jComboBoxCinema.addItem(cinema);
        }
        jComboBoxCinema.setFont(fieldFont);
        gbc.gridx = 1;
        panelForm.add(jComboBoxCinema, gbc);

        JLabel jLabelFilme = new JLabel("Filme:");
        jLabelFilme.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelForm.add(jLabelFilme, gbc);

        jComboBoxFilme = new JComboBox<>();
        for (Filme filme : filmeController.listarTodosRegistros()) {
            jComboBoxFilme.addItem(filme);
        }
        jComboBoxFilme.setFont(fieldFont);
        gbc.gridx = 1;
        panelForm.add(jComboBoxFilme, gbc);

        JLabel jLabelQtdAssentos = new JLabel("Quantidade de Assentos:");
        jLabelQtdAssentos.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelForm.add(jLabelQtdAssentos, gbc);

        jTextFieldQtdAssentos = new JTextField(20);
        jTextFieldQtdAssentos.setFont(fieldFont);
        gbc.gridx = 1;
        panelForm.add(jTextFieldQtdAssentos, gbc);

        // Botões de ação
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245)); // Fundo claro
        jButtonInserir = new JButton("Inserir Sessão");
        jButtonInserir.setBackground(new Color(72, 133, 237)); // Cor azul
        jButtonInserir.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Fonte maior
        jButtonInserir.setForeground(Color.WHITE);
        jButtonInserir.setFocusPainted(false);
        jButtonInserir.setPreferredSize(new Dimension(150, 45)); // Botão maior

        jButtonVoltar = new JButton("Voltar");
        jButtonVoltar.setBackground(new Color(204, 204, 204)); // Cor cinza claro
        jButtonVoltar.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Fonte maior
        jButtonVoltar.setFocusPainted(false);
        jButtonVoltar.setPreferredSize(new Dimension(150, 45)); // Botão maior

        buttonPanel.add(jButtonInserir);
        buttonPanel.add(jButtonVoltar);

        // Configuração da janela
        setTitle("Inserir Sessão");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500); // Tamanho maior para acomodar todos os componentes
        setLocationRelativeTo(null); // Centraliza a tela
        setLayout(new BorderLayout());

        // Adicionando os painéis à janela
        add(panelTop, BorderLayout.NORTH);
        add(panelForm, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Ação do botão "Inserir Sessão"
        jButtonInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inserirSessao();
            }
        });

        // Ação do botão "Voltar"
        jButtonVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarTelaAnterior();
            }
        });
    }

    private void inserirSessao() {
        try {
            // Obtém os valores dos campos
            String horarioStr = jTextFieldHorario.getText();
            Cinema cinemaSelecionado = (Cinema) jComboBoxCinema.getSelectedItem();
            Filme filmeSelecionado = (Filme) jComboBoxFilme.getSelectedItem();
            int qtdAssentos = Integer.parseInt(jTextFieldQtdAssentos.getText());

            // Valida os campos
            if (horarioStr.isEmpty() || cinemaSelecionado == null || filmeSelecionado == null || qtdAssentos <= 0) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Converte o horário para Timestamp
            Timestamp horario = Timestamp.valueOf(horarioStr);

            // Cria um novo objeto Sessao
            Sessao sessao = new Sessao(horario, cinemaSelecionado, filmeSelecionado, qtdAssentos);

            // Chama o método de inserção no controller
            boolean sucesso = sessaoController.inserirRegistro(sessao);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Sessão inserida com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao inserir a sessão.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        jTextFieldHorario.setText("");
        jComboBoxCinema.setSelectedIndex(0);
        jComboBoxFilme.setSelectedIndex(0);
        jTextFieldQtdAssentos.setText("");
    }

    private void voltarTelaAnterior() {
        // Lógica para voltar à tela anterior
        dispose(); // Fecha a janela atual

        InserirRegistro inserirRegistro = new InserirRegistro();
        inserirRegistro.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InserirSessao().setVisible(true);
            }
        });
    }
}
