package appjoyeriayuler.securityModule;

import java.util.List;
import appjoyeriayuler.shared.MensajeSistema;

public class GetUsuario {

    public void autenticar(String usuario, String password) {
        if (usuario == null || password == null || 
        usuario.trim().isEmpty() || password.trim().isEmpty()) {
        
        MensajeSistema.mostrarAdvertencia("Rellenar datos de usuario y/o contraseña", "");
        return;
    }

    ControlAutenticarUsuario control = new ControlAutenticarUsuario();
    List<String> privilegios = control.verificarUsuario(usuario, password);

    if (privilegios != null) {
        new MenuPrincipal(privilegios).setVisible(true);
    }
    }
}
