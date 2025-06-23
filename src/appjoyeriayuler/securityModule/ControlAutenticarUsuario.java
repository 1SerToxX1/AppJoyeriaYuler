package appjoyeriayuler.securityModule;

import java.util.List;
import appjoyeriayuler.model.UsuarioDAO;
import appjoyeriayuler.shared.MensajeSistema;

public class ControlAutenticarUsuario {
    private final UsuarioDAO dao = new UsuarioDAO();

    public List<String> verificarUsuario(String usuario, String password) {
        boolean resultado = dao.existeUsuario(usuario);
        if (resultado) {
            resultado = dao.passwordCorrecta(usuario, password);
            if (resultado) {
                resultado = dao.usuarioEstaActivo(usuario);
                if (resultado) {
                    return dao.obtenerPrivilegios(usuario);
                } else {
                    MensajeSistema.mostrarAdvertencia("El usuario está deshabilitado", "Contacte al administrador.");
                }
            } else {
                MensajeSistema.mostrarAdvertencia("El password es incorrecto", "");
            }
        } else {
            MensajeSistema.mostrarAdvertencia("Usuario no encontrado", "");
        }
        return null;
    }
}
