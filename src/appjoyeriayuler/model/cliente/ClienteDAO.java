/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appjoyeriayuler.model.cliente;

import appjoyeriayuler.model.Conexion;
import java.sql.*;

public class ClienteDAO extends Conexion {

    private boolean error = false;

public boolean huboError() {
    return error;
}
public Cliente obtenerClientePorDNI(String dni) {
    Cliente cliente = null;
    String sql = "SELECT * FROM clientes WHERE dni = ?";
    Connection conn = null;

    try {
        conn = conectarBD();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, dni);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            cliente = new Cliente();
            cliente.setIdCliente(rs.getInt("idCliente"));
            cliente.setNombre(rs.getString("cliente"));
            cliente.setDni(rs.getString("dni"));
            cliente.setRuc(rs.getString("ruc"));
            cliente.setTelefono(rs.getString("telefono"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        desconectarBD(conn);
    }

    return cliente;
}


}
