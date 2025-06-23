package appjoyeriayuler.shared;

import javax.swing.JOptionPane;

public class MensajeSistema {
    public static void mostrarAdvertencia(String mensaje, String sugerencia) {
        JOptionPane.showMessageDialog(null, mensaje + "\n" + sugerencia, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    public static void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void mostrarInfo(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
}
