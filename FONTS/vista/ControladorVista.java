package vista;

import domini.ControladorDomini;

public class ControladorVista {
    private final ControladorDomini controladorDomini;

    public ControladorVista(ControladorDomini controladorDomini) {
        this.controladorDomini = controladorDomini;

        MainWindow window = new MainWindow();
    }
}
