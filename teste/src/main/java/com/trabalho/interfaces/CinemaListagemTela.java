package com.trabalho.interfaces;

import com.trabalho.controllers.CinemaController;
import com.trabalho.models.Cinema;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CinemaListagemTela extends JFrame {
    private JTable tabelaCinema;
    private CinemaController cinemaController;

    public CinemaListagemTela() {
        cinemaController = new CinemaController();

        setTitle("Lista de Cinemas");
        setLayout(new BorderLayout());

        // Criar e configurar a tabela
        String[] colunas = {"ID Cinema", "Nome Cinema", "Endereço"};
        List<Cinema> cinemas = cinemaController.listarTodosRegistros();

        Object[][] dados = new Object[cinemas.size()][3];
        for (int i = 0; i < cinemas.size(); i++) {
            Cinema cinema = cinemas.get(i);
            dados[i][0] = cinema.getIdCinema();
            dados[i][1] = cinema.getNomeCinema();
            dados[i][2] = cinema.getEndereco().getRua();  // Exemplo de como pegar o nome da rua
        }

        tabelaCinema = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(tabelaCinema);
        add(scrollPane, BorderLayout.CENTER);

        // Configurações da janela
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CinemaListagemTela().setVisible(true);
            }
        });
    }
}
