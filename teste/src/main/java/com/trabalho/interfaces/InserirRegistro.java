package com.trabalho.interfaces;

public class InserirRegistro extends javax.swing.JFrame {

    /**
     * Creates new form InserirRegistro
     */
    public InserirRegistro() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        labelTitle = new javax.swing.JLabel();
        panelContent = new javax.swing.JPanel();
        labelInstruction = new javax.swing.JLabel();
        buttonCinema = new javax.swing.JButton();
        buttonEndereco = new javax.swing.JButton();
        buttonFilme = new javax.swing.JButton();
        buttonSessao = new javax.swing.JButton();
        buttonVenda = new javax.swing.JButton();
        buttonVoltar = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelHeader.setBackground(new java.awt.Color(51, 51, 51));

        labelTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelTitle.setForeground(new java.awt.Color(255, 255, 255));
        labelTitle.setText("INSERIR REGISTRO");

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap(171, Short.MAX_VALUE)
                .addComponent(labelTitle)
                .addGap(179, 179, 179))
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(labelTitle)
                .addGap(21, 21, 21))
        );

        panelContent.setBackground(new java.awt.Color(255, 102, 0));

        labelInstruction.setBackground(new java.awt.Color(255, 255, 255));
        labelInstruction.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelInstruction.setForeground(new java.awt.Color(255, 255, 255));

        buttonCinema.setText("CINEMA");
        buttonCinema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCinemaActionPerformed(evt);
            }
        });

        buttonEndereco.setText("ENDEREÇO");
        buttonEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEnderecoActionPerformed(evt);
            }
        });

        buttonFilme.setText("FILME");
        buttonFilme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFilmeActionPerformed(evt);
            }
        });

        buttonSessao.setText("SESSÃO");
        buttonSessao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSessaoActionPerformed(evt);
            }
        });

        buttonVenda.setText("VENDA");
        buttonVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVendaActionPerformed(evt);
            }
        });

        buttonVoltar.setText("VOLTAR");
        buttonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(labelInstruction)
                        .addGap(82, 82, 82)
                        .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(buttonEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                                .addComponent(buttonFilme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonCinema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonSessao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonVoltar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonVoltar)
                        .addGap(142, 142, 142)
                        .addComponent(labelInstruction))
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(buttonCinema)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEndereco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonFilme)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSessao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonVenda)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void buttonSessaoActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO: Adicionar lógica para Sessão
    }                                           

    private void buttonVendaActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO: Adicionar lógica para Venda
    }                                           

    private void buttonEnderecoActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO: Adicionar lógica para Endereço
    }                                             

    private void buttonCinemaActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO: Adicionar lógica para Cinema
    }                                            

    private void buttonFilmeActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // Navegar para a tela de Inserir Filme
        this.dispose();
        InserirFilme inserirFilme = new InserirFilme();
        inserirFilme.setVisible(true);
    }                                           

    private void buttonVoltarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // Voltar para o Menu Principal
        this.dispose();
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
    }                                            

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(InserirRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InserirRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InserirRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InserirRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InserirRegistro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton buttonCinema;
    private javax.swing.JButton buttonEndereco;
    private javax.swing.JButton buttonFilme;
    private javax.swing.JButton buttonSessao;
    private javax.swing.JButton buttonVenda;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JLabel labelInstruction;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelContent;
    private javax.swing.JToggleButton buttonVoltar;
    // End of variables declaration                   
}
