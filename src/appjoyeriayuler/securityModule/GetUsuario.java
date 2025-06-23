package appjoyeriayuler.securityModule;

import java.util.List;
import appjoyeriayuler.shared.MensajeSistema;

public class GetUsuario {

    public void autenticar(String usuario, String password, FormAutenticarUsuario formAutenticarUsuario) {
        // Validar campos vacíos
        if (usuario == null || password == null || 
            usuario.trim().isEmpty() || password.trim().isEmpty()) {

            MensajeSistema.mostrarAdvertencia("Rellenar datos de usuario y/o contraseña válidos", "");
            return;
        }

        // Proceso de autenticación
        ControlAutenticarUsuario control = new ControlAutenticarUsuario();
        List<String> privilegios = control.verificarUsuario(usuario, password);

        if (privilegios != null) {
            new MenuPrincipal(privilegios).setVisible(true);
            formAutenticarUsuario.dispose(); // Cierra la ventana de login correctamente
        }
    }
}
