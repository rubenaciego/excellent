package domini;

import java.time.*;
import java.lang.*;
import java.util.ArrayList;

/**
 * @class Document
 * @brief Representa un document, un conjunt de fulls
 */
public class Document
{
    private final String nom;
    private LocalDateTime dataModificacio;
    private ArrayList<Full> fulls;

    private final int DEFAULT_FILES = 100;
    private final int DEFAULT_COLS = 100;

    /**
     * @param nom Nom del document
     * @brief Constructora de document, crea un document buit, sense fulls
     */
    public Document(String nom)
    {
        this.nom = nom;
        dataModificacio = LocalDateTime.now();
    }

    public String getNom()
    {
        return nom;
    }

    public LocalDateTime getDataModificacio()
    {
        return dataModificacio;
    }

    public int getNumFulls()
    {
        return fulls.size();
    }

    public void setDataModificacio(LocalDateTime dataModificacio)
    {
        this.dataModificacio = dataModificacio;
    }

    /**
     * @brief Desa el document al disc
     */
    public void desa()
    {
        dataModificacio = LocalDateTime.now();
        // mock
    }

    /**
     * @brief Afegeix un nou full al final
     */
    public void afegeixFull()
    {
        fulls.add(new Full(DEFAULT_FILES, DEFAULT_COLS));
    }

    /**
     * @param idFull Índex del full que es vol eliminar
     * @brief Elimina un full a la posició especificada
     */
    public void eliminaFull(int idFull)
    {
        if (idFull < 0 || idFull >= fulls.size())
            throw new ExcepcioIndexFull(idFull, fulls.size());

        fulls.remove(idFull);
    }

    /**
     * @param idFull Índex del full
     * @return Full en la posició especificada
     * @brief Obté full a la posició especificada
     */
    public Full getFull(int idFull)
    {
        if (idFull < 0 || idFull >= fulls.size())
            throw new ExcepcioIndexFull(idFull, fulls.size());

        return fulls.get(idFull);
    }
}
