package domini;
import java.time.*;
import java.lang.*;
import java.util.HashMap;

/*
 * @class Document
 * @brief Representa un document, un conjunt de fulls
 */
public class Document
{
    String nom;
    LocalDateTime dataModificacio;
    HashMap<Integer, Full> fulls;
    int lastFull = 0;

    public Document(String nom)
    {
        this.nom = nom;
    }

    public LocalDateTime getDataModificacio()
    {
        return dataModificacio;
    }

    public void setDataModificacio(LocalDateTime dataModificacio)
    {
        this.dataModificacio = dataModificacio;
    }

    public void desa()
    {
        // mock
    }

    public void afegeixFull()
    {
        fulls.put(lastFull, new Full(lastFull));
        ++lastFull;
    }

    public void eliminaFull(int idFull)
    {
        fulls.remove(idFull);
    }

    public Full obteFull(int idFull)
    {
        return fulls.get(idFull);
    }
}
