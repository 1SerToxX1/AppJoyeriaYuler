package appjoyeriayuler.securityModule.usuario;

import appjoyeriayuler.securityModule.MenuPrincipal;
import java.util.List;
import appjoyeriayuler.shared.MensajeSistema;

public class GetUsuario {

    public void validarTexto(String usuario, String password, FormAutenticarUsuario formAutenticarUsuario) {
        if (usuario == null || password == null || 
            usuario.trim().isEmpty() || password.trim().isEmpty()) {

            MensajeSistema.mostrarAdvertencia("Rellenar datos de usuario y/o contraseña válidos", "");
            return;
        }
        validarBoton(usuario, password, formAutenticarUsuario);
    }

    public void validarBoton(String usuario, String password, FormAutenticarUsuario formAutenticarUsuario) {
    ControlAutenticarUsuario control = new ControlAutenticarUsuario();
    List<String> privilegios = control.verificarUsuario(usuario, password);

    if (privilegios != null) {
        int idUsuario = control.obtenerIdPorUsuario(usuario);
        FormAutenticarUsuario.sesionIdUsuario = idUsuario;

        new MenuPrincipal(privilegios).setVisible(true);
        formAutenticarUsuario.dispose();
    }
}

}
