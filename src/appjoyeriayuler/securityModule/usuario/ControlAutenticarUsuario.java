package appjoyeriayuler.securityModule.usuario;

import appjoyeriayuler.model.usuario.UsuarioDAO;
import appjoyeriayuler.securityModule.MenuPrincipal;
import appjoyeriayuler.shared.MensajeSistema;
import java.util.List;
import javax.swing.JFrame;

public class ControlAutenticarUsuario {
    private final UsuarioDAO dao = new UsuarioDAO();

    public List<String> verificarUsuario(String usuario, String password) {
        boolean resultado = dao.validarUsuario(usuario);
        if (dao.huboError()) return null;

        if (resultado) {
            resultado = dao.validarPassword(usuario, password);
            if (dao.huboError()) return null;

            if (resultado) {
                resultado = dao.validarEstado(usuario);
                if (dao.huboError()) return null;

                if (resultado) {
                    int idUsuario = dao.obtenerIdPorUsuario(usuario);
                    if (dao.huboError()) return null;

                    if (idUsuario == -1) {
                        MensajeSistema.mostrarAdvertencia("Error al obtener ID del usuario", "");
                        return null;
                    }

                    List<String> privilegios = dao.obtenerPrivilegios(idUsuario);
                    if (dao.huboError()) return null;

                    if (privilegios != null && !privilegios.isEmpty()) {
                        return privilegios;
                    } else {
                        MensajeSistema.mostrarAdvertencia("El usuario no tiene privilegios asignados.", "Consulte con el administrador.");
                        return null;
                    }
                } else {
                    MensajeSistema.mostrarAdvertencia("El usuario está deshabilitado.", "Contacte al administrador.");
                }
            } else {
                MensajeSistema.mostrarAdvertencia("El password es incorrecto", "");
            }
        } else {
            MensajeSistema.mostrarAdvertencia("Usuario no encontrado", "");
        }

        return null;
    }

    public void cerrarSesion(JFrame ventanaActual) {
        if (MensajeSistema.mostrarConfirmacion("¿Está seguro que desea cerrar sesión?", "Confirmar salida")) {
            ventanaActual.dispose();
            new FormAutenticarUsuario().setVisible(true);
            MensajeSistema.mostrarInfo("Sesión cerrada correctamente");
        }
    }

    public int obtenerIdPorUsuario(String usuario) {
        return dao.obtenerIdPorUsuario(usuario);
    }

    public static boolean validarSesion(JFrame ventanaActual) {
        if (FormAutenticarUsuario.sesionIdUsuario == -1) {
            MensajeSistema.mostrarAdvertencia("Debe iniciar sesión primero.", "");
            ventanaActual.dispose();
            new FormAutenticarUsuario().setVisible(true);
            return false;
        }
        return true;
    }

    public static boolean validarPrivilegio(JFrame ventana, String nombrePrivilegio) {
        int idUsuario = FormAutenticarUsuario.sesionIdUsuario;

        UsuarioDAO dao = new UsuarioDAO();
        List<String> privilegios = dao.obtenerPrivilegios(idUsuario);

        if (dao.huboError()) return false;

        if (!privilegios.contains(nombrePrivilegio)) {
            MensajeSistema.mostrarAdvertencia("El usuario no tiene privilegios para esta interfaz.", "");
            ventana.dispose();
            new MenuPrincipal(privilegios).setVisible(true); // redirigir al menú
            return false;
        }

        return true;
    }
}
