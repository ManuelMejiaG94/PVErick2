/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views.Product;

import BDPuntoVentaManuel.MODEL.Catcategoria;
import BDPuntoVentaManuel.MODEL.Producto;
import com.mysql.jdbc.StringUtils;
import java.awt.event.ItemEvent;
import java.util.Vector;

/**
 *
 * @author manuel
 */
public class Products_New_Update extends javax.swing.JPanel {

    /**
     * Creates new form Products_New
     */
    public Products_New_Update() {
        initComponents();
    }

    public void Open_Windows_New(boolean open) {
        this.btnProcess.setText("Registrar");
        this.Clean_Windows();
        this.Charge_Default_Data();

        this.setVisible(open);
    }

    public void Open_Windows_Update(Producto _product) {
        this.btnProcess.setText("Modificar");
        this.Clean_Windows();
        this.Charge_Default_Data();

        this.Charge_Product_Modific(_product);

        this.setVisible(true);
    }

    private void Charge_Product_Modific(Producto _product) {
        product = _product;

        this.txtCodigo.setText(product.getStrClave());
        this.txtCodigo.setEnabled(false);

        this.txtName.setText(product.getStrNombre());
        this.txtStock.setText(product.getDobCantidad().toString());
        this.txtPrecent.setText(product.getStrPresentacion());
        this.txtPC.setText(product.getDobPrecioCompra().toString());
        this.txtPV.setText(product.getDobPrecioVenta().toString());
        this.cmbCategories.setModel(Products_Start.ProductsProcess.getModelCategorias());

        this.cmbCategories.setSelectedItem(product.getIdCategoria());
        this.cmbCategories.setEnabled(false);
    }

    public void Charge_Default_Data() {
        this.cmbCategories.setModel(Products_Start.ProductsProcess.getModelCategorias());
    }

    private void Clean_Windows() {
        this.txtStock.setText(null);
        this.txtName.setText(null);
        this.txtPC.setText(null);
        this.txtPV.setText(null);
        this.txtCodigo.setText(null);

        this.lbError1.setVisible(false);
        this.lbError2.setVisible(false);
        this.lbError3.setVisible(false);
        this.lbError4.setVisible(false);
        this.lbError5.setVisible(false);
        this.lbError6.setVisible(false);
        this.lbError7.setVisible(false);

        this.ErrorsMessage.setVisible(false);
    }

    private boolean Validate_Data() {
        this.ListErros = new Vector();

        boolean Result = true;
        if (StringUtils.isNullOrEmpty(this.txtCodigo.getText())) {
            this.lbError1.setVisible(true);
            Result = false;
        }
        if (StringUtils.isNullOrEmpty(this.txtName.getText())) {
            this.lbError2.setVisible(true);
            Result = false;
        }
        if (StringUtils.isNullOrEmpty(this.txtStock.getText())) {
            this.lbError3.setVisible(true);
            Result = false;
        }
        if (StringUtils.isNullOrEmpty(this.txtPrecent.getText())) {
            this.lbError4.setVisible(true);
            Result = false;
        }
        if (StringUtils.isNullOrEmpty(this.txtPC.getText())) {
            this.lbError5.setVisible(true);
            Result = false;
        }
        if (StringUtils.isNullOrEmpty(this.txtPV.getText())) {
            this.lbError6.setVisible(true);
            Result = false;
        }

        if (!Result) {
            String error = "Los campos marcados con * son obligatorios";
            this.ListErros.add(error);
        }

        if (this.cmbCategories.getSelectedIndex() <= 0) {
            String error = "Es necesario indicar una categoria";

            this.ListErros.add(error);
            this.lbError7.setVisible(true);
            Result = false;
        }

        return Result;
    }

    private void ErrorsPaint() {
        this.ErrorsMessage.setListData(this.ListErros);
        this.ErrorsMessage.setVisible(true);
        this.VisitErrors = true;

    }

    private void Clear_Erros() {
        this.lbError1.setVisible(false);
        this.lbError2.setVisible(false);
        this.lbError3.setVisible(false);
        this.lbError4.setVisible(false);
        this.lbError5.setVisible(false);
        this.lbError6.setVisible(false);
        this.lbError7.setVisible(false);

        this.ErrorsMessage.setVisible(false);
    }

    private void PaintErrorExistenProduct() {

        String error = "El producto con el codigo dado ya exite en tu repletorio";
        this.ListErros.add(error);
        this.lbError1.setVisible(true);

        this.ErrorsPaint();
    }

    private Producto GetProductoNew() {
        product = new Producto();
        product.setStrClave(this.txtCodigo.getText().trim());
        product.setStrNombre(this.txtName.getText().trim());
        product.setStrPresentacion(this.txtPrecent.getText().trim());
        product.setDobCantidad(Double.parseDouble(this.txtStock.getText().trim()));
        product.setDobPrecioCompra(Double.parseDouble(this.txtPC.getText().trim()));
        product.setDobPrecioVenta(Double.parseDouble(this.txtPV.getText().trim()));
        product.setIdCategoria(this.categoriaProduct);
        product.setBitEstatus(true);

        return product;
    }

    private Producto GetProductoForUpdate() {

        product.setStrClave(this.txtCodigo.getText().trim());
        product.setStrNombre(this.txtName.getText().trim());
        product.setStrPresentacion(this.txtPrecent.getText().trim());
        product.setDobCantidad(Double.parseDouble(this.txtStock.getText().trim()));
        product.setDobPrecioCompra(Double.parseDouble(this.txtPC.getText().trim()));
        product.setDobPrecioVenta(Double.parseDouble(this.txtPV.getText().trim()));
        product.setIdCategoria(this.categoriaProduct);
        product.setBitEstatus(true);

        return product;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        cmbCategories = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtPrecent = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ErrorsMessage = new javax.swing.JList();
        txtPC = new javax.swing.JTextField();
        txtPV = new javax.swing.JTextField();
        btnProcess = new javax.swing.JButton();
        lbError5 = new javax.swing.JLabel();
        lbError6 = new javax.swing.JLabel();
        lbError7 = new javax.swing.JLabel();
        lbError1 = new javax.swing.JLabel();
        lbError2 = new javax.swing.JLabel();
        lbError3 = new javax.swing.JLabel();
        lbError4 = new javax.swing.JLabel();

        jLabel2.setText("Codigo del producto");

        jLabel3.setText("Nombre del producto");

        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNameKeyTyped(evt);
            }
        });

        txtStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockKeyTyped(evt);
            }
        });

        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        cmbCategories.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCategoriesItemStateChanged(evt);
            }
        });

        jLabel1.setText("Message");

        jLabel4.setText("Cantidad a registrar");

        jLabel5.setText("Presentacion (lts,mts,kg)");

        jLabel6.setText("Precio de compra");

        jLabel7.setText("Precio de venta");

        jLabel8.setText("Asignar categoria");

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        txtPrecent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecentKeyTyped(evt);
            }
        });

        jScrollPane1.setViewportView(ErrorsMessage);

        txtPC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPCKeyTyped(evt);
            }
        });

        txtPV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPVKeyTyped(evt);
            }
        });

        btnProcess.setText("Processo");
        btnProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessActionPerformed(evt);
            }
        });

        lbError5.setForeground(new java.awt.Color(255, 0, 0));
        lbError5.setText("*");

        lbError6.setForeground(new java.awt.Color(255, 0, 0));
        lbError6.setText("*");

        lbError7.setForeground(new java.awt.Color(255, 0, 0));
        lbError7.setText("*");

        lbError1.setForeground(new java.awt.Color(255, 0, 0));
        lbError1.setText("*");

        lbError2.setForeground(new java.awt.Color(255, 0, 0));
        lbError2.setText("*");

        lbError3.setForeground(new java.awt.Color(255, 0, 0));
        lbError3.setText("*");

        lbError4.setForeground(new java.awt.Color(255, 0, 0));
        lbError4.setText("*");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbError1)
                            .addComponent(lbError2)
                            .addComponent(lbError3)
                            .addComponent(lbError4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtCodigo)
                                .addComponent(txtName)
                                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtPrecent, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbError6))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbError7))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbError5)))
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPC)
                                    .addComponent(txtPV)
                                    .addComponent(cmbCategories, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                                .addComponent(btnProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(239, 239, 239)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbError5)
                    .addComponent(lbError1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbError6)
                    .addComponent(lbError2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cmbCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbError7)
                    .addComponent(lbError3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPrecent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProcess)
                    .addComponent(btnCancel)
                    .addComponent(lbError4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessActionPerformed
        if (this.VisitErrors) {
            this.Clear_Erros();
        }
        if (btnProcess.getText().equals("Registrar")) {
            if (this.Validate_Data()) {
                String Codigo = txtCodigo.getText().trim();
                Producto item = Products_Start.ProductsProcess.GetProductByCode(Codigo);
                if (item == null) {
                    Products_Start.ProductsProcess.SaveProduct(this.GetProductoNew());
                    this.Clean_Windows();
                    this.setVisible(false);

                    Products_Start.viewDefault.ResetDataTable();
                    Products_Start.viewDefault.setVisible(true);
                } else {
                    this.PaintErrorExistenProduct();
                }
            } else {

                this.ErrorsPaint();
            }
        }
        if (btnProcess.getText().equals("Modificar")) {
            String result;
            if (this.Validate_Data()) {
                result = Products_Start.ProductsProcess.EditProduct(GetProductoForUpdate());
                if (result == null) {
                    this.setVisible(false);
                    Products_Start.viewDefault.ResetDataTable();
                    Products_Start.viewDefault.setVisible(true);
                } else {
                    this.ListErros.add(result);
                }

            } else {
                this.ErrorsPaint();
            }
        }
    }//GEN-LAST:event_btnProcessActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        if (txtCodigo.getText().length()== 15)
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtStockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyTyped
           char caracter = evt.getKeyChar();

        // Verificar si la tecla pulsada no es un digito
        if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
            evt.consume();  // ignorar el evento de teclado
        }
        if (txtStock.getText().length()== 10)
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtStockKeyTyped

    private void txtNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyTyped
        if (txtName.getText().length()== 25)
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtNameKeyTyped

    private void txtPCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPCKeyTyped
        if(!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.')
        {
            evt.consume();
        }
        if(evt.getKeyChar()=='.' && txtPC.getText().contains("."))
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtPCKeyTyped

    private void txtPrecentKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecentKeyTyped
        if (txtPrecent.getText().length()== 25)
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecentKeyTyped

    private void txtPVKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPVKeyTyped
        if(!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.')
        {
            evt.consume();
        }
        if(evt.getKeyChar()=='.' && txtPV.getText().contains("."))
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtPVKeyTyped

    private void cmbCategoriesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCategoriesItemStateChanged
         if(evt.getStateChange()==ItemEvent.SELECTED)
        {
            this.categoriaProduct=(Catcategoria) this.cmbCategories.getSelectedItem();
        }
    }//GEN-LAST:event_cmbCategoriesItemStateChanged

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.setVisible(false);
                    
        Products_Start.viewDefault.ResetDataTable();
        Products_Start.viewDefault.setVisible(true);
    }//GEN-LAST:event_btnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList ErrorsMessage;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnProcess;
    private javax.swing.JComboBox cmbCategories;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbError1;
    private javax.swing.JLabel lbError2;
    private javax.swing.JLabel lbError3;
    private javax.swing.JLabel lbError4;
    private javax.swing.JLabel lbError5;
    private javax.swing.JLabel lbError6;
    private javax.swing.JLabel lbError7;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPC;
    private javax.swing.JTextField txtPV;
    private javax.swing.JTextField txtPrecent;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables

    //Variables
    Vector ListErros;
    private boolean VisitErrors=false;
    private Catcategoria categoriaProduct;
    private int idProduct;
    private Producto product;
    
}
