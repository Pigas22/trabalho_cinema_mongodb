package com.trabalho.interfaces;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author samuel
 */
public class MenuPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        botaoRelatorio = new javax.swing.JButton();  // Renomeado
        botaoInserirRegistro = new javax.swing.JButton();  // Renomeado
        botaoAlterarRegistro = new javax.swing.JButton();  // Renomeado
        botaoRemoverRegistro = new javax.swing.JButton();  // Renomeado

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Painel superior
        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));  // Fonte em negrito para destaque
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));  // Texto branco
        jLabel1.setText("MENU PRINCIPAL");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(181, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(179, 179, 179))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(21, 21, 21))
        );

        // Painel inferior (onde ficam os botões)
        jPanel1.setBackground(new java.awt.Color(245, 245, 245)); // Cor de fundo suave

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // Fonte moderna
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));  // Texto escuro

        // Botão "Relatório"
        botaoRelatorio.setText("Relatório");
        botaoRelatorio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // Fonte do botão
        botaoRelatorio.setBackground(new java.awt.Color(72, 133, 237));  // Cor azul
        botaoRelatorio.setForeground(Color.WHITE);  // Texto branco
        botaoRelatorio.setFocusPainted(false); // Remove o contorno do foco
        botaoRelatorio.setPreferredSize(new java.awt.Dimension(150, 40));
        botaoRelatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Lógica para o botão Relatório
            }
        });

        // Botão "Inserir Registro"
        botaoInserirRegistro.setText("Inserir Registro");
        botaoInserirRegistro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // Fonte do botão
        botaoInserirRegistro.setBackground(new java.awt.Color(72, 133, 237)); // Cor azul
        botaoInserirRegistro.setForeground(Color.WHITE); // Texto branco
        botaoInserirRegistro.setFocusPainted(false); // Remove o contorno do foco
        botaoInserirRegistro.setPreferredSize(new java.awt.Dimension(150, 40));
        botaoInserirRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoInserirRegistroActionPerformed(evt);  // Abre a tela InserirRegistro
            }
        });

        // Botão "Alterar Registro"
        botaoAlterarRegistro.setText("Alterar Registro");
        botaoAlterarRegistro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // Fonte do botão
        botaoAlterarRegistro.setBackground(new java.awt.Color(72, 133, 237)); // Cor azul
        botaoAlterarRegistro.setForeground(Color.WHITE); // Texto branco
        botaoAlterarRegistro.setFocusPainted(false); // Remove o contorno do foco
        botaoAlterarRegistro.setPreferredSize(new java.awt.Dimension(150, 40));

        // Botão "Remover Registro"
        botaoRemoverRegistro.setText("Remover Registro");
        botaoRemoverRegistro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // Fonte do botão
        botaoRemoverRegistro.setBackground(new java.awt.Color(72, 133, 237)); // Cor azul
        botaoRemoverRegistro.setForeground(Color.WHITE); // Texto branco
        botaoRemoverRegistro.setFocusPainted(false); // Remove o contorno do foco
        botaoRemoverRegistro.setPreferredSize(new java.awt.Dimension(150, 40));
        botaoRemoverRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRemoverRegistroActionPerformed(evt);  // Lógica para Remover Registro
            }
        });

        // Layout dos botões no painel inferior
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botaoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoInserirRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoAlterarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoRemoverRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(botaoRelatorio)
                .addGap(18, 18, 18)
                .addComponent(botaoInserirRegistro)
                .addGap(18, 18, 18)
                .addComponent(botaoAlterarRegistro)
                .addGap(18, 18, 18)
                .addComponent(botaoRemoverRegistro)
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        // Layout geral da tela
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoInserirRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoInserirRegistroActionPerformed
        // Ao clicar no botão "Inserir Registro", abrir a tela InserirRegistro
        this.dispose();  // Fecha a tela atual (MenuPrincipal)
        
        // Cria e exibe a tela InserirRegistro
        InserirRegistro inserirRegistro = new InserirRegistro();
        inserirRegistro.setVisible(true);
    }//GEN-LAST:event_botaoInserirRegistroActionPerformed

    private void botaoRemoverRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoRemoverRegistroActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoRelatorio;  // Renomeado
    private javax.swing.JButton botaoInserirRegistro;  // Renomeado
    private javax.swing.JButton botaoAlterarRegistro;  // Renomeado
    private javax.swing.JButton botaoRemoverRegistro;  // Renomeado
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
