package appjoyeriayuler.securityModule;

import java.util.List;
import appjoyeriayuler.model.UsuarioDAO;
import appjoyeriayuler.shared.MensajeSistema;

public class ControlAutenticarUsuario {
    private final UsuarioDAO dao = new UsuarioDAO();

    public List<String> verificarUsuario(String login, String password) {
        boolean resultado = dao.existeUsuario(login);
        if (resultado) {
            resultado = dao.passwordCorrecta(login, password);
            if (resultado) {
                resultado = dao.usuarioEstaActivo(login);
                if (resultado) {
                    return dao.obtenerPrivilegios(login);
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
