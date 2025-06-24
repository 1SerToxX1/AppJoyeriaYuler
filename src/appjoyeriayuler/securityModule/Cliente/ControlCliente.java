package appjoyeriayuler.securityModule.Cliente;

import appjoyeriayuler.model.cliente.Cliente;
import appjoyeriayuler.model.cliente.ClienteDAO;
import appjoyeriayuler.shared.MensajeSistema;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ControlCliente {
    private final ClienteDAO dao = new ClienteDAO();

    public Cliente buscarClientePorDNI(String dni) {
        return dao.obtenerClientePorDNI(dni);
    }

    public boolean validarDNI(String dni, JTextField txtCliente) {
        Cliente cliente = dao.obtenerClientePorDNI(dni);
        if (dao.huboError()) {
            MensajeSistema.mostrarError("Error al consultar el cliente.");
            return false;
        }

        if (cliente != null) {
            txtCliente.setText(cliente.getNombre());
            return true;
        } else {
            MensajeSistema.mostrarAdvertencia("DNI no encontrado.", "");
            txtCliente.setText("");
            return false;
        }
    }
    
    public void configurarRestriccionDNI(JTextField txtDNI) {
    txtDNI.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isDigit(c) && !Character.isISOControl(c)) {
                e.consume();
                MensajeSistema.mostrarAdvertencia("El DNI debe tener datos numéricos.", "");
            }
        }
    });
}

public void configurarLimpiezaClientePorDNI(JTextField txtDNI, JTextField txtCliente) {
    txtDNI.getDocument().addDocumentListener(new DocumentListener() {

        private void manejarCambio() {
            String texto = txtDNI.getText().trim();

            if (texto.length() < 8) {
                // IMPORTANTE: usamos invokeLater para evitar el IllegalStateException
                SwingUtilities.invokeLater(() -> {
                    txtCliente.setText("");
                });
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            manejarCambio();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            manejarCambio();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            manejarCambio();
        }
    });
}




}










