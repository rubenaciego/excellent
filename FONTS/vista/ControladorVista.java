package vista;

import domini.ControladorDomini;
import domini.ExcepcioDomini;

public class ControladorVista {
    private final ControladorDomini controladorDomini;
    private final MainWindow window;
    private final WindowSecundaria secundaria;

    public ControladorVista(ControladorDomini controladorDomini) {
        this.controladorDomini = controladorDomini;
        window = new MainWindow(this);
        secundaria = new WindowSecundaria();
    }

    public void afegeixFull()
    {
        // construeix missatge a controlador domini
        int i = controladorDomini.getNumFulls() - 1;
        window.afegeixFull(controladorDomini.getNumFiles(i), controladorDomini.getNumCols(i));
    }

    public void esborraFull(int index)
    {
        // construeix missatge a controlador domini
        window.esborraFull(index);
    }

    public void carregaDocument(String nom)
    {
        String[] message = {"OPERACIO_DOCUMENT,CARREGA_DOCUMENT", nom};
        ExcepcioDomini.TipusError error = controladorDomini.executaOperacio(message);

        if (error != ExcepcioDomini.TipusError.NO_ERROR)
        {
            System.out.println("Error desde presentació");
        }

        for (int i = 0; i < controladorDomini.getNumFulls(); ++i)
        {
            int files = controladorDomini.getNumFiles(i);
            int cols = controladorDomini.getNumCols(i);
            window.afegeixFull(files, cols);
            window.setEntradesFull(i, controladorDomini.getEntrades(i, 0, 0, files, cols));
        }

        window.focusFull(0);
    }
}
