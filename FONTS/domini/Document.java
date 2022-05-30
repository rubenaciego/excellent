package domini;

import java.time.*;
import java.lang.*;
import java.util.ArrayList;

/**
 * Classe que representa un document, un conjunt de fulls
 */
public class Document {
    /**
     * Nom del document
     */
    private String nom;
    /**
     * Data de l'últim cop que s'ha desat el document
     */
    private LocalDateTime dataModificacio;
    /**
     * Conjunt dels Full que conté el document
     */
    private final ArrayList<Full> fulls;
    /**
     * Valor predeterminat del número de files d'un full
     */
    private static final int DEFAULT_FILES = 100;
    /**
     * Valor predeterminat del número de columnes d'un full
     */
    private static final int DEFAULT_COLS = 100;

    /**
     * Constructora principal del document
     * @param nom nom del nou document
     */
    public Document(String nom) {
        this.nom = nom;
        fulls = new ArrayList<Full>();
        dataModificacio = LocalDateTime.now();
    }

    /**
     * Setter del nom
     * @param nom nou nom del Document
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter del nom
     * @return el nom del Document
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter de dataModificacio
     * @return la data de l'última vegada que s'ha desat el document
     */
    public LocalDateTime getDataModificacio() {
        return dataModificacio;
    }

    /**
     * Setter de dataModificació
     * @param dataModificacio la nova data d'última modificació
     */
    public void setDataModificacio(LocalDateTime dataModificacio) {
        this.dataModificacio = dataModificacio;
    }

    /**
     * Getter del nombre total de fulls del document
     * @return el nombre de fulls del document
     */
    public int getNumFulls() {
        return fulls.size();
    }

    /**
     * Afegeix un nou full al final
     */
    public void afegeixFull() {
        fulls.add(new Full(DEFAULT_FILES, DEFAULT_COLS));
    }

    /**
     * Afegeix un nou full al final
     * @param files nombre de files del full
     * @param cols nombre de columnes del full
     */
    public void afegeixFull(int files, int cols) {
        fulls.add(new Full(files, cols));
    }

    /**
     * Elimina un full a la posició especificada
     * @param idFull índex del full que es vol eliminar
     * @throws ExcepcioIndexFull si el full amb índex idFull no existeix
     */
    public void eliminaFull(int idFull) {
        if (idFull < 0 || idFull >= fulls.size())
            throw new ExcepcioIndexFull(idFull, fulls.size());

        fulls.remove(idFull);
    }

    /**
     * Obté full a la posició especificada
     * @param idFull índex del full
     * @return Full en la posició especificada
     * @throws ExcepcioIndexFull si el full amb índex idFull no existeix
     */
    public Full getFull(int idFull) {
        if (idFull < 0 || idFull >= fulls.size())
            throw new ExcepcioIndexFull(idFull, fulls.size());

        return fulls.get(idFull);
    }
}
