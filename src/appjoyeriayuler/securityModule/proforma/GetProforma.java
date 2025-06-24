package appjoyeriayuler.securityModule.proforma;

import appjoyeriayuler.model.producto.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class GetProforma {

    private final ControlProforma control = new ControlProforma();
    private List<Producto> listaProductos = new ArrayList<>();

    // Cargar los combos iniciales
    public void cargarCombos(JComboBox<String> cboTipoJoya, JComboBox<String> cboMaterialJoya) {
        cboTipoJoya.removeAllItems();
        cboTipoJoya.addItem("Seleccionar...");
        List<String> tipos = control.obtenerTipoJoyas();
        if (tipos != null) {
            for (String tipo : tipos) {
                cboTipoJoya.addItem(tipo);
            }
        }

        cboMaterialJoya.removeAllItems();
        cboMaterialJoya.addItem("Seleccionar...");
        List<String> materiales = control.obtenerMateriales();
        if (materiales != null) {
            for (String material : materiales) {
                cboMaterialJoya.addItem(material);
            }
        }
    }

    // Validar que material y tipo estén bien seleccionados y cargar productos
    public void validarYObtenerProductos(String material, String tipo,
                                         JComboBox<String> cboProducto,
                                         JTextField txtPrecio,
                                         JTextField txtStock) {
        cboProducto.removeAllItems();
        txtPrecio.setText("");
        txtStock.setText("");

        if (material == null || tipo == null ||
            material.equals("Seleccionar...") || tipo.equals("Seleccionar...")) {
            cboProducto.setEnabled(false);
            return;
        }

        listaProductos = control.obtenerListaProductos(material, tipo);

        if (listaProductos == null || listaProductos.isEmpty()) {
            cboProducto.setEnabled(false);
            return;
        }

        cboProducto.addItem("Seleccionar...");
        for (Producto p : listaProductos) {
            cboProducto.addItem(p.getNombre());
        }

        // Ocultar "Seleccionar..." cuando el combo esté cerrado
        cboProducto.setRenderer(new javax.swing.plaf.basic.BasicComboBoxRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if ("Seleccionar...".equals(value)) {
                    setText(index == -1 ? " " : "Seleccionar...");
                } else {
                    setText((value != null) ? value.toString() : "");
                }

                return this;
            }
        });

        cboProducto.setEnabled(true);
        control.setListaProductos(listaProductos);  // sincroniza con el controlador si es necesario
    }

    // Al seleccionar un producto, mostrar su precio y stock
    public void cargarDatosProductoSeleccionado(JComboBox<String> cboProducto,
                                                JTextField txtPrecio,
                                                JTextField txtStock) {
        String seleccionado = (String) cboProducto.getSelectedItem();

        if (seleccionado == null || seleccionado.equals("Seleccionar...")) {
            txtPrecio.setText("");
            txtStock.setText("");
            return;
        }

        for (Producto p : listaProductos) {
            if (p.getNombre().equals(seleccionado)) {
                txtPrecio.setText(String.valueOf(p.getPrecio()));
                txtStock.setText(String.valueOf(p.getStock()));
                return;
            }
        }

        txtPrecio.setText("");
        txtStock.setText("");
    }
    
    public List<Producto> getListaProductos() {
    return listaProductos;
}

}
