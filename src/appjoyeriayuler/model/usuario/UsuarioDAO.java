package appjoyeriayuler.model.usuario;

import appjoyeriayuler.model.Conexion;
import appjoyeriayuler.shared.MensajeSistema;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends Conexion {

    private boolean huboError = false;

    public boolean huboError() {
        return huboError;
    }

    public void resetearError() {
        this.huboError = false;
    }

    public boolean validarUsuario(String usuario) {
        resetearError();
        String sql = "SELECT usuario FROM usuarios WHERE usuario = ?";
        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            MensajeSistema.mostrarError("Error en BD");
            e.printStackTrace();
            huboError = true;
            return false;
        } finally {
            desconectarBD(conn);
        }
    }

    public boolean validarPassword(String usuario, String contraseña) {
        resetearError();
        String sql = "SELECT usuario FROM usuarios WHERE usuario = ? AND contraseña = ?";
        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, contraseña);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            MensajeSistema.mostrarError("Error en BD");
            e.printStackTrace();
            huboError = true;
            return false;
        } finally {
            desconectarBD(conn);
        }
    }

    public boolean validarEstado(String usuario) {
        resetearError();
        String sql = """
            SELECT u.usuario 
            FROM usuarios u
            INNER JOIN estado e ON u.idEstado = e.idEstado
            WHERE u.usuario = ? AND e.estado = 'activo'
        """;
        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            MensajeSistema.mostrarError("Error en BD");
            e.printStackTrace();
            huboError = true;
            return false;
        } finally {
            desconectarBD(conn);
        }
    }

    public List<String> obtenerPrivilegios(int idUsuario) {
        resetearError();
        List<String> privilegios = new ArrayList<>();
        String sql = """
            SELECT p.label AS privilegio
            FROM privilegiosUsuario pu
            JOIN privilegio p ON pu.idPrivilegio = p.idPrivilegio
            WHERE pu.idUsuario = ?
        """;

        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                privilegios.add(rs.getString("privilegio"));
            }
        } catch (SQLException e) {
            MensajeSistema.mostrarError("Error en BD");
            e.printStackTrace();
            huboError = true;
        } finally {
            desconectarBD(conn);
        }

        return privilegios;
    }

    public int obtenerIdPorUsuario(String usuario) {
        resetearError();
        String sql = "SELECT idUsuarios FROM usuarios WHERE usuario = ?";
        Connection conn = null;

        try {
            conn = conectarBD();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("idUsuarios");
            }
        } catch (SQLException e) {
            MensajeSistema.mostrarError("Error en BD");
            e.printStackTrace();
            huboError = true;
        } finally {
            desconectarBD(conn);
        }

        return -1;
    }
}
