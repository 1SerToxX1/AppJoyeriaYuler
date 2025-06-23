package appjoyeriayuler.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends Conexion {

    public boolean existeUsuario(String usuario) {
        String sql = "SELECT usuario FROM usuarios WHERE usuario = ?";
        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            desconectarBD(conn);
        }
    }

    public boolean passwordCorrecta(String usuario, String contraseña) {
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
            e.printStackTrace();
            return false;
        } finally {
            desconectarBD(conn);
        }
    }

    public boolean usuarioEstaActivo(String usuario) {
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
            e.printStackTrace();
            return false;
        } finally {
            desconectarBD(conn);
        }
    }

    public List<String> obtenerPrivilegios(String usuario) {
        List<String> lista = new ArrayList<>();
        String sql = """
            SELECT p.label AS Privilegio
            FROM privilegiosusuario pu
            INNER JOIN usuarios u ON pu.idUsuario = u.idUsuarios
            INNER JOIN privilegio p ON pu.idPrivilegio = p.idPrivilegio
            WHERE u.usuario = ?
        """;
        Connection conn = null;
        try {
            conn = conectarBD();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("Privilegio"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectarBD(conn);
        }
        return lista;
    }
}
