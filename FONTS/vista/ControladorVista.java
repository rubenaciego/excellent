package vista;

import domini.ControladorDomini;
import domini.ExcepcioDomini;

public class ControladorVista {
    private final ControladorDomini controladorDomini;
    private final MainWindow window;

    public ControladorVista(ControladorDomini controladorDomini) {
        this.controladorDomini = controladorDomini;
        window = new MainWindow(this);
    }

    public void afegeixFull()
    {
        String[] message = {"OPERACIO_DOCUMENT,AFEGEIX_FULL"};

        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        int i = controladorDomini.getNumFulls() - 1;
        window.afegeixFull(controladorDomini.getNumFiles(i), controladorDomini.getNumCols(i));
    }

    public void esborraFull(int index)
    {
        // construeix missatge a controlador domini
        window.esborraFull(index);
    }

    public void creaDocument(String nom)
    {
        String[] message = {"OPERACIO_DOCUMENT,CREA_DOCUMENT", nom};

        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void carregaDocument(String nom)
    {
        String[] message = {"OPERACIO_DOCUMENT,CARREGA_DOCUMENT", nom};

        try {
            controladorDomini.executaOperacio(message);
        } catch (ExcepcioDomini e) {
            window.errorMessage(e.getMessage());
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < controladorDomini.getNumFulls(); ++i)
        {
            int files = controladorDomini.getNumFiles(i);
            int cols = controladorDomini.getNumCols(i);
            window.afegeixFull(files, cols);
            window.setEntradesFull(i, controladorDomini.getEntrades(i, 0, 0, files, cols));
        }

        window.setFocusedFull(0);
    }
}
