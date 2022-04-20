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
    String nom;
    LocalDateTime dataModificacio;
    ArrayList<Full> fulls;

    /**
     * @brief Constructora de document, crea un document buit, sense fulls
     * @param nom Nom del document
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

    public void setDataModificacio(LocalDateTime dataModificacio)
    {
        this.dataModificacio = dataModificacio;
    }

    /**
     * @brief Desa el document al disc
     */
    public void desa()
    {
        // mock
    }

    /**
     * @brief Afegeix un nou full al final
     */
    public void afegeixFull()
    {
        fulls.add(new Full(0));
    }

    /**
     * @brief Elimina un full a la posició especificada
     * @param idFull Índex del full que es vol eliminar
     */
    public void eliminaFull(int idFull)
    {
        fulls.remove(idFull);
    }

    /**
     * @brief Obté full a la posició especificada
     * @param idFull Índex del full
     * @return Full en la posició especificada
     */
    public Full obteFull(int idFull)
    {
        return fulls.get(idFull);
    }
}
