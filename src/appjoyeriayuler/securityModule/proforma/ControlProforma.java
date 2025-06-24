package appjoyeriayuler.securityModule.proforma;

import appjoyeriayuler.model.producto.Producto;
import appjoyeriayuler.model.proforma.ProformaDAO;
import appjoyeriayuler.shared.MensajeSistema;
import java.util.ArrayList;
import java.util.List;

public class ControlProforma {

    private final ProformaDAO dao = new ProformaDAO();
    private List<Producto> listaProductos = new ArrayList<>();

    // Obtener tipos de joyas
    public List<String> obtenerTipoJoyas() {
        List<String> tipos = dao.obtenerTipoJoyas();
        if (dao.huboError() || tipos == null || tipos.isEmpty()) {
            MensajeSistema.mostrarError("No se pudieron cargar los tipos de joya desde la base de datos.");
            return null;
        }
        return tipos;
    }

    // Obtener materiales
    public List<String> obtenerMateriales() {
        List<String> materiales = dao.obtenerMateriales();
        if (dao.huboError() || materiales == null || materiales.isEmpty()) {
            MensajeSistema.mostrarError("No se pudieron cargar los materiales desde la base de datos.");
            return null;
        }
        return materiales;
    }

    // Obtener productos por material y tipo de joya
    public List<Producto> obtenerProductos(String material, String tipo) {
        List<Producto> productos = dao.obtenerProductosPorMaterialYTipo(material, tipo);
        if (dao.huboError() || productos == null || productos.isEmpty()) {
            MensajeSistema.mostrarError("No se pudieron cargar los productos desde la base de datos.");
            return null;
        }
        return productos;
    }

    // Para ser reutilizados en GetProforma
    public List<Producto> obtenerListaProductos(String material, String tipo) {
        return dao.obtenerProductosPorMaterialYTipo(material, tipo);
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> productos) {
        this.listaProductos = productos;
    }
}
