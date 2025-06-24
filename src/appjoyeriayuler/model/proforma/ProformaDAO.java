package appjoyeriayuler.model.proforma;

import appjoyeriayuler.model.Conexion;
import appjoyeriayuler.model.producto.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProformaDAO extends Conexion {

    private boolean huboError = false;

    public boolean huboError() {
        return huboError;
    }

    public void resetearError() {
        this.huboError = false;
    }

    public ArrayList<String> obtenerMateriales() {
        resetearError();
        ArrayList<String> lista = new ArrayList<>();
        Connection conn = null;
        try {
            conn = conectarBD();
            String sql = "SELECT material FROM tipomaterial";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(rs.getString("material"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            huboError = true;
        } finally {
            desconectarBD(conn);
        }

        return lista;
    }

    public ArrayList<String> obtenerTipoJoyas() {
        resetearError();
        ArrayList<String> lista = new ArrayList<>();
        Connection conn = null;
        try {
            conn = conectarBD();
            String sql = "SELECT joya FROM tipojoya";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(rs.getString("joya"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            huboError = true;
        } finally {
            desconectarBD(conn);
        }

        return lista;
    }

    public List<Producto> obtenerProductosPorMaterialYTipo(String material, String tipoJoya) {
    resetearError();
    List<Producto> productos = new ArrayList<>();

    String sql = "SELECT producto, stock, precio FROM productos p " +
                 "JOIN tipomaterial tm ON p.idTipoMaterial = tm.idMaterial " +
                 "JOIN tipojoya tj ON p.idTipoJoya = tj.idTipoJoya " +
                 "WHERE tm.material = ? AND tj.joya = ?";

    Connection conn = null;
    try {
        conn = conectarBD();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, material);
        stmt.setString(2, tipoJoya);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String nombre = rs.getString("producto");
            int stock = rs.getInt("stock");
            double precio = rs.getDouble("precio");
            productos.add(new Producto(nombre, stock, precio));
        }
    } catch (SQLException e) {
        e.printStackTrace();
        huboError = true;
    } finally {
        desconectarBD(conn);  // <- Aquí está tu error corregido
    }

    return productos;
}



}
