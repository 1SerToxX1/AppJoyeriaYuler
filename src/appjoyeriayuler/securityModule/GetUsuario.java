package appjoyeriayuler.securityModule;

import java.util.List;

public class GetUsuario {
    public void autenticar(String login, String password) {
        ControlAutenticarUsuario control = new ControlAutenticarUsuario();
        List<String> privilegios = control.verificarUsuario(login, password);

        if (privilegios != null) {
            new MenuPrincipal(privilegios).setVisible(true);
        }
    }
}
