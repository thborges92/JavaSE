package APP;

import SERVICES.UsuarioService;
import VIEW.Menu;

public class Main {

    public static void main(String[] args) {

        UsuarioService.logon();
        Menu.inicio();

    }

}
