package br.com.foursys.locadora.view;

/**
 * Classe responsável pela interface do menu
 *
 * @author jgil
 * @since 05/03/2020
 * @version 0.1
 */
public class MenuView extends javax.swing.JFrame {

    public MenuView() {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtVendedor = new javax.swing.JButton();
        jbtClientes = new javax.swing.JButton();
        jbtFilmes = new javax.swing.JButton();
        jbtLocacao = new javax.swing.JButton();
        jbtSair = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiCliente = new javax.swing.JMenuItem();
        jmiVendedor = new javax.swing.JMenuItem();
        jmiFilme = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Sistema de Controle Locadora");

        jbtVendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/foursys/locadora/img/funcionario.png"))); // NOI18N
        jbtVendedor.setText("Vendedor");
        jbtVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtVendedorActionPerformed(evt);
            }
        });

        jbtClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/foursys/locadora/img/clientes.png"))); // NOI18N
        jbtClientes.setText("Clientes");
        jbtClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtClientesActionPerformed(evt);
            }
        });

        jbtFilmes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/foursys/locadora/img/filme.png"))); // NOI18N
        jbtFilmes.setText("Filmes");
        jbtFilmes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFilmesActionPerformed(evt);
            }
        });

        jbtLocacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/foursys/locadora/img/locação.png"))); // NOI18N
        jbtLocacao.setText("Locação");
        jbtLocacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtLocacaoActionPerformed(evt);
            }
        });

        jbtSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/foursys/locadora/img/sair.png"))); // NOI18N
        jbtSair.setText("Sair");
        jbtSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSairActionPerformed(evt);
            }
        });

        jMenu1.setText("Cadastro");

        jmiCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jmiCliente.setText("Cliente");
        jmiCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiClienteActionPerformed(evt);
            }
        });
        jMenu1.add(jmiCliente);

        jmiVendedor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jmiVendedor.setText("Vendedor");
        jmiVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiVendedorActionPerformed(evt);
            }
        });
        jMenu1.add(jmiVendedor);

        jmiFilme.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jmiFilme.setText("Filme");
        jmiFilme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFilmeActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFilme);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        jMenuItem1.setText("Locação");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Sistema");

        jmiSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jmiSair.setText("Sair");
        jmiSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSairActionPerformed(evt);
            }
        });
        jMenu2.add(jmiSair);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbtClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtFilmes, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtFilmes, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtSair, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(212, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiClienteActionPerformed
        new ClienteView();
    }//GEN-LAST:event_jmiClienteActionPerformed

    private void jmiFilmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFilmeActionPerformed
        new FilmeView();
    }//GEN-LAST:event_jmiFilmeActionPerformed

    private void jmiVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiVendedorActionPerformed
        new VendedorView();
    }//GEN-LAST:event_jmiVendedorActionPerformed

    private void jbtClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtClientesActionPerformed
        new ClienteView();
    }//GEN-LAST:event_jbtClientesActionPerformed

    private void jbtVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtVendedorActionPerformed
        new VendedorView();
    }//GEN-LAST:event_jbtVendedorActionPerformed

    private void jbtFilmesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFilmesActionPerformed
        new FilmeView();
    }//GEN-LAST:event_jbtFilmesActionPerformed

    private void jbtLocacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtLocacaoActionPerformed
        new LocacaoView();
    }//GEN-LAST:event_jbtLocacaoActionPerformed

    private void jbtSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jbtSairActionPerformed

    private void jmiSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jmiSairActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new LocacaoView();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JButton jbtClientes;
    private javax.swing.JButton jbtFilmes;
    private javax.swing.JButton jbtLocacao;
    private javax.swing.JButton jbtSair;
    private javax.swing.JButton jbtVendedor;
    private javax.swing.JMenuItem jmiCliente;
    private javax.swing.JMenuItem jmiFilme;
    private javax.swing.JMenuItem jmiSair;
    private javax.swing.JMenuItem jmiVendedor;
    // End of variables declaration//GEN-END:variables
}
