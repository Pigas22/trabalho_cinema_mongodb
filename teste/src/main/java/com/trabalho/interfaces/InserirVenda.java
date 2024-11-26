package com.trabalho.interfaces;

import com.trabalho.controllers.ClienteController;
import com.trabalho.controllers.SessaoController;
import com.trabalho.controllers.VendaController;
import com.trabalho.models.Cliente;
import com.trabalho.models.Sessao;
import com.trabalho.models.Venda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InserirVenda extends JFrame {

    private JComboBox<Cliente> jComboBoxCliente;
    private JComboBox<Sessao> jComboBoxSessao;
    private JTextField jTextFieldAssento;
    private JTextField jTextFieldFormaPagamento;
    private JButton jButtonInserir;
    private JButton jButtonVoltar;
    private VendaController vendaController;
    private ClienteController clienteController;
    private SessaoController sessaoController;

    public InserirVenda() {
        clienteController = new ClienteController();
        sessaoController = new SessaoController();
        vendaController = new VendaController();

        initComponents();
    }

    private void initComponents() {
        // Painel superior (Cabeçalho)
        JPanel panelTop = new JPanel();
        panelTop.setBackground(new Color(51, 51, 51)); // Cor escura para o cabeçalho
        JLabel titleLabel = new JLabel("Inserir Venda");
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
        JLabel jLabelCliente = new JLabel("Cliente:");
        jLabelCliente.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelForm.add(jLabelCliente, gbc);

        jComboBoxCliente = new JComboBox<>();
        for (Cliente cliente : clienteController.listarTodosRegistros()) {
            jComboBoxCliente.addItem(cliente);
        }
        jComboBoxCliente.setFont(fieldFont);
        gbc.gridx = 1;
        panelForm.add(jComboBoxCliente, gbc);

        JLabel jLabelSessao = new JLabel("Sessão:");
        jLabelSessao.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelForm.add(jLabelSessao, gbc);

        jComboBoxSessao = new JComboBox<>();
        for (Sessao sessao : sessaoController.listarTodosRegistros()) {
            jComboBoxSessao.addItem(sessao);
        }
        jComboBoxSessao.setFont(fieldFont);
        gbc.gridx = 1;
        panelForm.add(jComboBoxSessao, gbc);

        JLabel jLabelAssento = new JLabel("Assento:");
        jLabelAssento.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelForm.add(jLabelAssento, gbc);

        jTextFieldAssento = new JTextField(20);
        jTextFieldAssento.setFont(fieldFont);
        gbc.gridx = 1;
        panelForm.add(jTextFieldAssento, gbc);

        JLabel jLabelFormaPagamento = new JLabel("Forma de Pagamento:");
        jLabelFormaPagamento.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelForm.add(jLabelFormaPagamento, gbc);

        jTextFieldFormaPagamento = new JTextField(20);
        jTextFieldFormaPagamento.setFont(fieldFont);
        gbc.gridx = 1;
        panelForm.add(jTextFieldFormaPagamento, gbc);

        // Botões de ação
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245)); // Fundo claro
        jButtonInserir = new JButton("Inserir Venda");
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
        setTitle("Inserir Venda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500); // Tamanho maior para acomodar todos os componentes
        setLocationRelativeTo(null); // Centraliza a tela
        setLayout(new BorderLayout());

        // Adicionando os painéis à janela
        add(panelTop, BorderLayout.NORTH);
        add(panelForm, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Ação do botão "Inserir Venda"
        jButtonInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inserirVenda();
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

    private void inserirVenda() {
        try {
            // Obtém os valores dos campos
            Cliente clienteSelecionado = (Cliente) jComboBoxCliente.getSelectedItem();
            Sessao sessaoSelecionada = (Sessao) jComboBoxSessao.getSelectedItem();
            int assento = Integer.parseInt(jTextFieldAssento.getText());
            String formaPagamento = jTextFieldFormaPagamento.getText();

            // Valida os campos
            if (clienteSelecionado == null || sessaoSelecionada == null || assento <= 0 || formaPagamento.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cria um novo objeto Venda
            Venda venda = new Venda(clienteSelecionado, assento, formaPagamento, sessaoSelecionada);

            // Chama o método de inserção no controller
            boolean sucesso = vendaController.inserirRegistro(venda);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Venda inserida com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao inserir a venda.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        jComboBoxCliente.setSelectedIndex(0);
        jComboBoxSessao.setSelectedIndex(0);
        jTextFieldAssento.setText("");
        jTextFieldFormaPagamento.setText("");
    }

    private void voltarTelaAnterior() {
        // Lógica para voltar à tela anterior
        dispose(); // Fecha a janela atual
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InserirVenda().setVisible(true);
            }
        });
    }
}
