package com.trabalho.interfaces;

import com.trabalho.controllers.CinemaController;
import com.trabalho.controllers.EnderecoController;
import com.trabalho.models.Cinema;
import com.trabalho.models.Endereco;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InserirCinema extends JFrame {
    private JTextField nomeCinemaField;
    private JTable enderecoTable;
    private JButton salvarButton;
    private JButton voltarButton;
    private CinemaController cinemaController;
    private EnderecoController enderecoController;

    public InserirCinema() {
        cinemaController = new CinemaController();
        enderecoController = new EnderecoController();

        // Inicializa os componentes
        initComponents();
    }

    private void initComponents() {
        // Definindo o painel superior
        JPanel panelTop = new JPanel();
        panelTop.setBackground(new Color(51, 51, 51)); // Cor escura para o cabeçalho
        JLabel titleLabel = new JLabel("Inserir Cinema");
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
        JLabel nomeCinemaLabel = new JLabel("Nome do Cinema:");
        nomeCinemaLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelForm.add(nomeCinemaLabel, gbc);

        nomeCinemaField = new JTextField(20); // Campo maior
        nomeCinemaField.setFont(fieldFont);
        gbc.gridx = 1;
        panelForm.add(nomeCinemaField, gbc);

        // Tabela de endereços
        JLabel enderecoLabel = new JLabel("Selecione o Endereço:");
        enderecoLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelForm.add(enderecoLabel, gbc);

        // Criando a tabela de endereços
        String[] colunas = {"ID", "Rua", "Cidade", "Bairro", "UF"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        enderecoTable = new JTable(model);
        enderecoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        enderecoTable.setFont(fieldFont);
        enderecoTable.setRowHeight(30); // Maior altura para as linhas
        enderecoTable.setPreferredScrollableViewportSize(new Dimension(600, 180)); // Aumentando a largura da tabela

        // Preencher a tabela com os dados de endereços
        for (Endereco endereco : enderecoController.listarTodosRegistros()) {
            model.addRow(new Object[]{
                endereco.getIdEndereco(), endereco.getRua(), endereco.getCidade(), endereco.getBairro(), endereco.getUf()
            });
        }

        JScrollPane scrollPane = new JScrollPane(enderecoTable);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelForm.add(scrollPane, gbc);

        // Botões de ação
        salvarButton = new JButton("Salvar");
        salvarButton.setBackground(new Color(72, 133, 237)); // Cor azul
        salvarButton.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Fonte maior
        salvarButton.setForeground(Color.WHITE);
        salvarButton.setFocusPainted(false);
        salvarButton.setPreferredSize(new Dimension(120, 45)); // Botão maior

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelForm.add(salvarButton, gbc);

        voltarButton = new JButton("Voltar");
        voltarButton.setBackground(new Color(204, 204, 204)); // Cor cinza claro
        voltarButton.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Fonte maior
        voltarButton.setFocusPainted(false);
        voltarButton.setPreferredSize(new Dimension(120, 45)); // Botão maior

        gbc.gridy = 4;
        panelForm.add(voltarButton, gbc);

        // Configuração da janela
        setTitle("Inserir Cinema");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);  // Tamanho maior para acomodar todos os componentes
        setLocationRelativeTo(null);  // Centraliza a tela
        setLayout(new BorderLayout());

        // Adicionando os painéis
        add(panelTop, BorderLayout.NORTH);
        add(panelForm, BorderLayout.CENTER);

        // Ação do botão "Salvar"
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarCinema();
            }
        });

        // Ação do botão "Voltar"
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarTelaAnterior();
            }
        });
    }

    private void salvarCinema() {
        String nomeCinema = nomeCinemaField.getText();
        int selectedRow = enderecoTable.getSelectedRow();

        if (nomeCinema.isEmpty() || selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtendo os dados do endereço selecionado na tabela
        Endereco enderecoSelecionado = enderecoController.listarTodosRegistros().get(selectedRow);
        Cinema novoCinema = new Cinema(nomeCinema, enderecoSelecionado);

        if (cinemaController.inserirRegistro(novoCinema)) {
            JOptionPane.showMessageDialog(this, "Cinema inserido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            nomeCinemaField.setText("");  // Limpar campo
            enderecoTable.clearSelection();  // Limpar a seleção da tabela
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao inserir Cinema.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void voltarTelaAnterior() {
        dispose(); // Fecha a janela atual
        InserirRegistro inserirRegistro = new InserirRegistro();
        inserirRegistro.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InserirCinema().setVisible(true);
            }
        });
    }
}
