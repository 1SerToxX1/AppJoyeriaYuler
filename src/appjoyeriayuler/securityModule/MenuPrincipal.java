
package appjoyeriayuler.securityModule;
import appjoyeriayuler.securityModule.usuario.ControlAutenticarUsuario;
import appjoyeriayuler.shared.MensajeSistema;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MenuPrincipal extends javax.swing.JFrame {


   public MenuPrincipal(List<String> privilegios) {
    if (!ControlAutenticarUsuario.validarSesion(this)) {
        return;
    }

    // Validar privilegio específico para acceder al menú
    if (!ControlAutenticarUsuario.validarPrivilegio(this, "Emitir comprobante")) {
        return; // Se redirige o muestra advertencia desde el método
    }

    initComponents();
    setLocationRelativeTo(null);
    setResizable(false);
    mostrarPrivilegios(privilegios);
}

   
   private void mostrarPrivilegios(List<String> privilegios) {
    jPanelMenu.removeAll(); // Limpia si ya hay botones anteriores
    jPanelMenu.setLayout(new GridLayout(0, 2, 10, 10)); // 2 columnas con espacios

    for (String p : privilegios) {
        JButton boton = new JButton(p);
        boton.setPreferredSize(new Dimension(150, 40)); // Tamaño opcional
        boton.addActionListener(e -> abrirFormulario(p));
        jPanelMenu.add(boton);
    }

    jPanelMenu.revalidate();
    jPanelMenu.repaint();
}
   
   private void abrirFormulario(String privilegio) {
    switch (privilegio.toLowerCase()) {
        case "registrar cliente":
            new FormRegistrarCliente().setVisible(true);
            break;
        case "editar cliente":
            new FormEditarCliente().setVisible(true);
            break;
        case "emitir comprobante":
            new FormEmitirComprobante().setVisible(true);
            break;
        case "emitir proforma":
            new FormEmitirProforma().setVisible(true);
            break;
        case "atender comprobante":
            new FormAtenderComprobante().setVisible(true);
            break;
        default:
            MensajeSistema.mostrarAdvertencia("Función aún no implementada", "");
            break;
    }
}


   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMenuPrincipal = new javax.swing.JPanel();
        lblMenuPrincipal = new javax.swing.JLabel();
        jPanelMenu = new javax.swing.JPanel();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelMenuPrincipal.setPreferredSize(new java.awt.Dimension(632, 525));

        lblMenuPrincipal.setFont(new java.awt.Font("Comic Sans MS", 0, 36)); // NOI18N
        lblMenuPrincipal.setText("Menú principal");

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 593, Short.MAX_VALUE)
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
        );

        btnLogout.setText("Cerrar sesión");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMenuPrincipalLayout = new javax.swing.GroupLayout(jPanelMenuPrincipal);
        jPanelMenuPrincipal.setLayout(jPanelMenuPrincipalLayout);
        jPanelMenuPrincipalLayout.setHorizontalGroup(
            jPanelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuPrincipalLayout.createSequentialGroup()
                .addGroup(jPanelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelMenuPrincipalLayout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(lblMenuPrincipal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogout))
                    .addGroup(jPanelMenuPrincipalLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanelMenuPrincipalLayout.setVerticalGroup(
            jPanelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuPrincipalLayout.createSequentialGroup()
                .addGroup(jPanelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMenuPrincipalLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lblMenuPrincipal))
                    .addGroup(jPanelMenuPrincipalLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenuPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenuPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        ControlAutenticarUsuario control = new ControlAutenticarUsuario();
        control.cerrarSesion(this);
    }//GEN-LAST:event_btnLogoutActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelMenuPrincipal;
    private javax.swing.JLabel lblMenuPrincipal;
    // End of variables declaration//GEN-END:variables
}
