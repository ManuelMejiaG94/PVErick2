/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views.Product;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 *
 * @author manuel
 */
public class Products_Start extends javax.swing.JInternalFrame {

    /**
     * Creates new form Products_Start
     */
    public Products_Start() {
        initComponents();
        this.btnInabilit.setVisible(false);
        this.Start_Windows();
    }
    private void Start_Windows()
    {
        this.Start_Connections();
        this.Close_Views();
        this.Open_COnfigVindowsDefault();
    }

    
    private void Start_Connections()
    {
        viewProduct=new Products_New_Update();
        viewSelect=new Products_Select_Innabilit();
        viewDefault=new Products_Default();
        
        pnContainer.setLayout(Layout);
        GridBagConstraints cons= new GridBagConstraints();
        
        cons.gridx=0;
        cons.gridy=0;
        pnContainer.add(viewProduct,cons);
        
        cons.gridx=0;
        cons.gridy=0;
        pnContainer.add(viewSelect,cons);

        pnContainer.add(viewDefault,cons);

    }
    
    private void Open_COnfigVindowsDefault()
    {
         viewProduct.setVisible(false);
        viewSelect.setVisible(false);
        viewDefault.setVisible(true);
    }
    
    private void Close_Views()
    {
        viewProduct.setVisible(false);
        viewSelect.setVisible(true);
        this.viewDefault.setVisible(false);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();
        pnContainer = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnNew = new javax.swing.JMenuItem();
        btnUpdate = new javax.swing.JMenuItem();
        btnInabilit = new javax.swing.JMenuItem();

        btnExit.setText("Salir");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExit)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnContainerLayout = new javax.swing.GroupLayout(pnContainer);
        pnContainer.setLayout(pnContainerLayout);
        pnContainerLayout.setHorizontalGroup(
            pnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnContainerLayout.setVerticalGroup(
            pnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        jLabel1.setText("Title");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(427, 427, 427)
                .addComponent(jLabel1)
                .addContainerGap(489, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jMenu1.setText("Producto");

        btnNew.setText("Nuevo");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jMenu1.add(btnNew);

        btnUpdate.setText("Modificar");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jMenu1.add(btnUpdate);

        btnInabilit.setText("Innabilitar");
        btnInabilit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInabilitActionPerformed(evt);
            }
        });
        jMenu1.add(btnInabilit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        viewProduct.Open_Windows_New(true);
        viewSelect.setVisible(false);
        viewDefault.setVisible(false);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        viewProduct.setVisible(false);
        viewSelect.Open_Update();
        viewDefault.setVisible(false);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnInabilitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInabilitActionPerformed
        viewProduct.setVisible(false);
        viewSelect.setVisible(true);
        viewDefault.setVisible(false);
    }//GEN-LAST:event_btnInabilitActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JMenuItem btnInabilit;
    private javax.swing.JMenuItem btnNew;
    private javax.swing.JMenuItem btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel pnContainer;
    // End of variables declaration//GEN-END:variables


    //Vistas del proceso
    public static Products_New_Update viewProduct;
    public static Products_Select_Innabilit viewSelect;
    public static Products_Default viewDefault;
    
    //Variables
    GridBagLayout Layout=new GridBagLayout();
    
    //Variable de los procesos de la vista
    public static Products ProductsProcess;

}
