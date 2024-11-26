package com.trabalho.interfaces;

import com.trabalho.controllers.CinemaController;
import com.trabalho.controllers.EnderecoController;
import com.trabalho.models.Cinema;
import com.trabalho.models.Endereco;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CinemaInsercaoTela extends JFrame {
    private JTextField nomeCinemaField;
    private JComboBox<Endereco> enderecoComboBox;
    private JButton salvarButton;
    private CinemaController cinemaController;
    private EnderecoController enderecoController;

    public CinemaInsercaoTela() {
        cinemaController = new CinemaController();
        enderecoController = new EnderecoController();
        
        // Definindo título e layout
        setTitle("Inserir Cinema");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Definir o painel de fundo
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245));  // Fundo claro

        // Fonte personalizada
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 12);

        // Adicionando Label e Campo para o Nome do Cinema
        JLabel nomeCinemaLabel = new JLabel("Nome do Cinema:");
        nomeCinemaLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nomeCinemaLabel, gbc);
        
        nomeCinemaField = new JTextField(20);
        nomeCinemaField.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(nomeCinemaField, gbc);

        // Adicionando Label e ComboBox para Endereço
        JLabel enderecoLabel = new JLabel("Selecione o Endereço:");
        enderecoLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(enderecoLabel, gbc);

        enderecoComboBox = new JComboBox<>();
        for (Endereco endereco : enderecoController.listarTodosRegistros()) {
            enderecoComboBox.addItem(endereco);
        }
        enderecoComboBox.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(enderecoComboBox, gbc);

        // Botão Salvar
        salvarButton = new JButton("Salvar");
        salvarButton.setFont(new Font("Arial", Font.BOLD, 14));
        salvarButton.setBackground(new Color(72, 133, 237));  // Cor azul
        salvarButton.setForeground(Color.WHITE);
        salvarButton.setFocusPainted(false);  // Remove o foco ao clicar
        salvarButton.setPreferredSize(new Dimension(100, 40));
        
        // Adicionando o botão no layout
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(salvarButton, gbc);

        // Configuração da janela
        add(panel);
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centraliza a tela
        
        // Ação do botão
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarCinema();
            }
        });
    }

    private void salvarCinema() {
        String nomeCinema = nomeCinemaField.getText();
        Endereco enderecoSelecionado = (Endereco) enderecoComboBox.getSelectedItem();

        if (nomeCinema.isEmpty() || enderecoSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cinema novoCinema = new Cinema(nomeCinema, enderecoSelecionado);
        if (cinemaController.inserirRegistro(novoCinema)) {
            JOptionPane.showMessageDialog(this, "Cinema inserido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            nomeCinemaField.setText("");  // Limpar campo
            enderecoComboBox.setSelectedIndex(0);  // Resetar combo box
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao inserir Cinema.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CinemaInsercaoTela().setVisible(true);
            }
        });
    }
}
