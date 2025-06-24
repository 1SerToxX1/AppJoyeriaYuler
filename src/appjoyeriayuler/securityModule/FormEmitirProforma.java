/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package appjoyeriayuler.securityModule;

import appjoyeriayuler.model.producto.Producto;
import appjoyeriayuler.model.proforma.ProformaDAO;
import appjoyeriayuler.securityModule.Cliente.ControlCliente;
import appjoyeriayuler.securityModule.Cliente.GetCliente;
import appjoyeriayuler.securityModule.proforma.ControlProforma;
import appjoyeriayuler.securityModule.proforma.GetProforma;
import appjoyeriayuler.securityModule.usuario.ControlAutenticarUsuario;
import appjoyeriayuler.shared.MensajeSistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author carlo
 */
public class FormEmitirProforma extends javax.swing.JFrame {
private javax.swing.table.DefaultTableModel modeloTabla;

    private final GetCliente getCliente = new GetCliente();
    private final GetProforma getProforma = new GetProforma();
    private final ControlCliente controlCliente = new ControlCliente();
    private List<Producto> listaProductos = new ArrayList<>();

    
    public FormEmitirProforma() {
        // Validaciones de sesión y privilegios
        if (!ControlAutenticarUsuario.validarSesion(this)) return;
        if (!ControlAutenticarUsuario.validarPrivilegio(this, "Emitir proforma")) return;

        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);

        // Cargar combo de tipo de joya (único visible al inicio)
        getProforma.cargarCombos(cboTipoJoya, cboMaterialJoya);
        cboMaterialJoya.setEnabled(false);
        cboProducto.setEnabled(false);

        // Configurar restricción de DNI (solo números) y limpieza de cliente
        controlCliente.configurarRestriccionDNI(txtDNI);
        controlCliente.configurarLimpiezaClientePorDNI(txtDNI, txtCliente);

        // Inicializar modelo de tabla y bloquear edición
        modeloTabla = new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new Object[]{"N°", "Producto", "Tipo de Joya", "Tipo de Material", "Cantidad", "Precio Unitario"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // ❌ No se puede editar ninguna celda
            }
        };

        tblListaProducto.setModel(modeloTabla);
        tblListaProducto.getTableHeader().setReorderingAllowed(false);
        tblListaProducto.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        tblListaProducto.getSelectionModel().addListSelectionListener(e -> {
            int fila = tblListaProducto.getSelectedRow();
            if (fila >= 0) {
                String productoNombre = tblListaProducto.getValueAt(fila, 1).toString();
                String tipoJoya = tblListaProducto.getValueAt(fila, 2).toString();
                String material = tblListaProducto.getValueAt(fila, 3).toString();
                String cantidad = tblListaProducto.getValueAt(fila, 4).toString();
                String precio = tblListaProducto.getValueAt(fila, 5).toString();

                for (int i = 0; i < cboProducto.getItemCount(); i++) {
                    if (cboProducto.getItemAt(i).equals(productoNombre)) {
                        cboProducto.setSelectedIndex(i);
                        break;
                    }
                }

                cboTipoJoya.setSelectedItem(tipoJoya);
                cboMaterialJoya.setSelectedItem(material);
                txtCantidad.setText(cantidad);
                txtPrecio.setText(precio);

                for (Producto p : getProforma.getListaProductos()) {
                    if (p.getNombre().equals(productoNombre)) {
                        txtStock.setText(String.valueOf(p.getStock()));
                        break;
                    }
                }
            }
        });

        // Listeners para limpiar combos al seleccionar "Seleccionar..."
        cboTipoJoya.addActionListener(e -> {
            String tipoSeleccionado = (String) cboTipoJoya.getSelectedItem();
            if ("Seleccionar...".equals(tipoSeleccionado)) {
                cboMaterialJoya.removeAllItems();
                cboMaterialJoya.setEnabled(false);

                cboProducto.removeAllItems();
                cboProducto.setEnabled(false);

                txtPrecio.setText("");
                txtStock.setText("");
            }
        });

        cboMaterialJoya.addActionListener(e -> {
            String materialSeleccionado = (String) cboMaterialJoya.getSelectedItem();
            if ("Seleccionar...".equals(materialSeleccionado)) {
                cboProducto.removeAllItems();
                cboProducto.setEnabled(false);

                txtPrecio.setText("");
                txtStock.setText("");
            }
        });

        cboProducto.addActionListener(e -> {
            String productoSeleccionado = (String) cboProducto.getSelectedItem();
            if ("Seleccionar...".equals(productoSeleccionado)) {
                txtPrecio.setText("");
                txtStock.setText("");
            }
        });
    }





    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelEmitirProforma = new javax.swing.JPanel();
        jPanelMenu = new javax.swing.JPanel();
        lblDNI = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        lblCliente = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        lblTipoJoya = new javax.swing.JLabel();
        lblMaterialJoya = new javax.swing.JLabel();
        cboMaterialJoya = new javax.swing.JComboBox<>();
        cboTipoJoya = new javax.swing.JComboBox<>();
        lblProducto = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        lblStock = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        cboProducto = new javax.swing.JComboBox<>();
        jPanelTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListaProducto = new javax.swing.JTable();
        jPanelButtons = new javax.swing.JPanel();
        btnAgregarProducto = new javax.swing.JButton();
        btnEditarProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        btnRegistrarProducto = new javax.swing.JButton();
        btnVolverMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelEmitirProforma.setBackground(new java.awt.Color(255, 255, 255));

        jPanelMenu.setBackground(new java.awt.Color(255, 255, 255));

        lblDNI.setBackground(new java.awt.Color(0, 0, 0));
        lblDNI.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDNI.setForeground(new java.awt.Color(0, 0, 0));
        lblDNI.setText("DNI:");

        txtDNI.setForeground(new java.awt.Color(0, 0, 0));
        txtDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIActionPerformed(evt);
            }
        });

        lblCliente.setBackground(new java.awt.Color(0, 0, 0));
        lblCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCliente.setForeground(new java.awt.Color(0, 0, 0));
        lblCliente.setText("Cliente:");

        txtCliente.setForeground(new java.awt.Color(0, 0, 0));
        txtCliente.setEnabled(false);

        lblTipoJoya.setBackground(new java.awt.Color(0, 0, 0));
        lblTipoJoya.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTipoJoya.setForeground(new java.awt.Color(0, 0, 0));
        lblTipoJoya.setText("Tipo de joya:");

        lblMaterialJoya.setBackground(new java.awt.Color(0, 0, 0));
        lblMaterialJoya.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMaterialJoya.setForeground(new java.awt.Color(0, 0, 0));
        lblMaterialJoya.setText("Material de joya:");

        cboMaterialJoya.setForeground(new java.awt.Color(0, 0, 0));
        cboMaterialJoya.setEnabled(false);
        cboMaterialJoya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaterialJoyaActionPerformed(evt);
            }
        });

        cboTipoJoya.setForeground(new java.awt.Color(0, 0, 0));
        cboTipoJoya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoJoyaActionPerformed(evt);
            }
        });

        lblProducto.setBackground(new java.awt.Color(0, 0, 0));
        lblProducto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblProducto.setForeground(new java.awt.Color(0, 0, 0));
        lblProducto.setText("Producto:");

        lblCantidad.setBackground(new java.awt.Color(0, 0, 0));
        lblCantidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCantidad.setForeground(new java.awt.Color(0, 0, 0));
        lblCantidad.setText("Cantidad:");

        txtCantidad.setForeground(new java.awt.Color(0, 0, 0));

        lblPrecio.setBackground(new java.awt.Color(0, 0, 0));
        lblPrecio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPrecio.setForeground(new java.awt.Color(0, 0, 0));
        lblPrecio.setText("Precio");

        txtPrecio.setForeground(new java.awt.Color(0, 0, 0));
        txtPrecio.setEnabled(false);
        txtPrecio.setPreferredSize(new java.awt.Dimension(80, 25));

        lblStock.setBackground(new java.awt.Color(0, 0, 0));
        lblStock.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblStock.setForeground(new java.awt.Color(0, 0, 0));
        lblStock.setText("Stock:");

        txtStock.setForeground(new java.awt.Color(0, 0, 0));
        txtStock.setEnabled(false);
        txtStock.setPreferredSize(new java.awt.Dimension(80, 25));
        txtStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockActionPerformed(evt);
            }
        });

        cboProducto.setForeground(new java.awt.Color(0, 0, 0));
        cboProducto.setEnabled(false);
        cboProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCliente)
                    .addComponent(lblDNI))
                .addGap(18, 18, 18)
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDNI)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaterialJoya)
                    .addComponent(lblTipoJoya))
                .addGap(18, 18, 18)
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboMaterialJoya, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboTipoJoya, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelMenuLayout.createSequentialGroup()
                        .addComponent(lblProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelMenuLayout.createSequentialGroup()
                        .addComponent(lblCantidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPrecio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblStock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuLayout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDNI)
                    .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoJoya)
                    .addComponent(cboTipoJoya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProducto)
                    .addComponent(cboProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCliente)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaterialJoya)
                    .addComponent(cboMaterialJoya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantidad)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecio)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStock)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        tblListaProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "N°", "Producto", "Tipo de Joya", "Tipo de Material", "Cantidad", "Precio Unitario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblListaProducto);

        javax.swing.GroupLayout jPanelTableLayout = new javax.swing.GroupLayout(jPanelTable);
        jPanelTable.setLayout(jPanelTableLayout);
        jPanelTableLayout.setHorizontalGroup(
            jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanelTableLayout.setVerticalGroup(
            jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAgregarProducto.setText("Agregar");
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        btnEditarProducto.setText("Editar");
        btnEditarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProductoActionPerformed(evt);
            }
        });

        btnEliminarProducto.setText("Desh");
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });

        btnRegistrarProducto.setText("Confirm");
        btnRegistrarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarProductoActionPerformed(evt);
            }
        });

        btnVolverMenu.setText("Volver al menú principal");
        btnVolverMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelButtonsLayout = new javax.swing.GroupLayout(jPanelButtons);
        jPanelButtons.setLayout(jPanelButtonsLayout);
        jPanelButtonsLayout.setHorizontalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonsLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnVolverMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelButtonsLayout.createSequentialGroup()
                        .addGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEliminarProducto)
                            .addComponent(btnAgregarProducto))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEditarProducto)
                            .addComponent(btnRegistrarProducto))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanelButtonsLayout.setVerticalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelButtonsLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgregarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEliminarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnVolverMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout jPanelEmitirProformaLayout = new javax.swing.GroupLayout(jPanelEmitirProforma);
        jPanelEmitirProforma.setLayout(jPanelEmitirProformaLayout);
        jPanelEmitirProformaLayout.setHorizontalGroup(
            jPanelEmitirProformaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEmitirProformaLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanelEmitirProformaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelEmitirProformaLayout.createSequentialGroup()
                        .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(jPanelEmitirProformaLayout.createSequentialGroup()
                        .addComponent(jPanelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))))
        );
        jPanelEmitirProformaLayout.setVerticalGroup(
            jPanelEmitirProformaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEmitirProformaLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelEmitirProformaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelEmitirProforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelEmitirProforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockActionPerformed

    private void cboMaterialJoyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaterialJoyaActionPerformed
        String material = (String) cboMaterialJoya.getSelectedItem();
    String tipo = (String) cboTipoJoya.getSelectedItem();

    getProforma.validarYObtenerProductos(material, tipo, cboProducto, txtPrecio, txtStock);

    }//GEN-LAST:event_cboMaterialJoyaActionPerformed

    private void cboTipoJoyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoJoyaActionPerformed
        cboProducto.removeAllItems();
    cboProducto.setEnabled(false);
    txtPrecio.setText("");
    txtStock.setText("");
    cboMaterialJoya.setEnabled(true);
    }//GEN-LAST:event_cboTipoJoyaActionPerformed

    private void txtDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDNIActionPerformed
        String dni = txtDNI.getText().trim();

    // Solo validar cliente, no tocar combos
    boolean valido = controlCliente.validarDNI(dni, txtCliente);

    if (!valido) {
        txtCliente.setText("");
        // NO tocar cboTipoJoya, cboMaterialJoya ni cboProducto
    }
    }//GEN-LAST:event_txtDNIActionPerformed

    private void cboProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboProductoActionPerformed
        getProforma.cargarDatosProductoSeleccionado(cboProducto, txtPrecio, txtStock);
    }//GEN-LAST:event_cboProductoActionPerformed

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
         int selectedIndex = cboProducto.getSelectedIndex();

    if (selectedIndex <= 0) {
        MensajeSistema.mostrarAdvertencia("Debe seleccionar un producto válido.", "Seleccione un producto de la lista.");
        return;
    }

    String tipoJoya = (String) cboTipoJoya.getSelectedItem();
    String materialJoya = (String) cboMaterialJoya.getSelectedItem();
    String cantidadStr = txtCantidad.getText().trim();
    String precioStr = txtPrecio.getText().trim();

    if (cantidadStr.isEmpty() || !cantidadStr.matches("\\d+")) {
        MensajeSistema.mostrarAdvertencia("Cantidad inválida.", "Ingrese un número entero positivo.");
        return;
    }

    int cantidad = Integer.parseInt(cantidadStr);
    double precioUnitario = Double.parseDouble(precioStr);

    // Usar lista interna de GetProforma
    Producto seleccionado = getProforma.getListaProductos().get(selectedIndex - 1); // -1 por "Seleccionar..."

    int nro = modeloTabla.getRowCount() + 1;
    modeloTabla.addRow(new Object[]{
        nro,
        seleccionado.getNombre(),
        tipoJoya,
        materialJoya,
        cantidad,
        precioUnitario
    });

    // Limpiar campos
    cboProducto.setSelectedIndex(0);
    txtCantidad.setText("");
    txtPrecio.setText("");
    txtStock.setText("");
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void btnEditarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProductoActionPerformed
        int fila = tblListaProducto.getSelectedRow();
    if (fila == -1) {
        MensajeSistema.mostrarAdvertencia("Debe seleccionar una fila para editar.", "");
        return;
    }

    String producto = (String) cboProducto.getSelectedItem();
    String tipoJoya = (String) cboTipoJoya.getSelectedItem();
    String material = (String) cboMaterialJoya.getSelectedItem();
    String cantidad = txtCantidad.getText();
    String precio = txtPrecio.getText();

    tblListaProducto.setValueAt(producto, fila, 1);
    tblListaProducto.setValueAt(tipoJoya, fila, 2);
    tblListaProducto.setValueAt(material, fila, 3);
    tblListaProducto.setValueAt(cantidad, fila, 4);
    tblListaProducto.setValueAt(precio, fila, 5);
    }//GEN-LAST:event_btnEditarProductoActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        int fila = tblListaProducto.getSelectedRow();
    if (fila == -1) {
        MensajeSistema.mostrarAdvertencia("Debe seleccionar una fila para eliminar.", "");
        return;
    }

    int confirmar = javax.swing.JOptionPane.showConfirmDialog(
        this, "¿Deseas eliminar esta fila?", "Confirmar", javax.swing.JOptionPane.YES_NO_OPTION
    );

    if (confirmar == javax.swing.JOptionPane.YES_OPTION) {
        modeloTabla.removeRow(fila);
        // Recalcular numeración:
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            modeloTabla.setValueAt(i + 1, i, 0);
        }
    }
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnRegistrarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarProductoActionPerformed

    private void btnVolverMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolverMenuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormEmitirProforma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormEmitirProforma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormEmitirProforma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormEmitirProforma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormEmitirProforma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnEditarProducto;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnRegistrarProducto;
    private javax.swing.JButton btnVolverMenu;
    private javax.swing.JComboBox<String> cboMaterialJoya;
    private javax.swing.JComboBox<String> cboProducto;
    private javax.swing.JComboBox<String> cboTipoJoya;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JPanel jPanelEmitirProforma;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblMaterialJoya;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblTipoJoya;
    private javax.swing.JTable tblListaProducto;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
