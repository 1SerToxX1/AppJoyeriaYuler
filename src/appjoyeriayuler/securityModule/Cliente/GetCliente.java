/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appjoyeriayuler.securityModule.Cliente;

import appjoyeriayuler.model.cliente.Cliente;
import appjoyeriayuler.shared.MensajeSistema;
import javax.swing.JTextField;

public class GetCliente {

    private final ControlCliente control = new ControlCliente();

    public void validarDNIPorEnter(String dni, JTextField txtCliente) {
    if (dni == null || dni.trim().isEmpty()) {
        MensajeSistema.mostrarAdvertencia("Debe ingresar un DNI.", "");
        return;
    }

    if (!dni.matches("\\d+")) {
        MensajeSistema.mostrarAdvertencia("El DNI debe tener datos numéricos.", "");
        return;
    }

    if (dni.length() != 8) {
        MensajeSistema.mostrarAdvertencia("El DNI solo debe contener 8 dígitos.", "");
        return;
    }

    Cliente cliente = control.buscarClientePorDNI(dni);
    if (cliente == null) {
        MensajeSistema.mostrarAdvertencia("El DNI no se encuentra registrado.", "");
        return;
    }

    txtCliente.setText(cliente.getNombre());
}

}