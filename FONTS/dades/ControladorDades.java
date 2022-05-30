package dades;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Contolador de la capa de dades i persistència
 */
public class ControladorDades {

    /**
     * Guarda un arxiu a memòria
     * @param nom nom de l'arxiu a guardar
     * @param text contingut de l'arxiu a guardar
     * @throws IOException excepció si no es pot emmagatzemar amb èxit en
     * memòria
     */
    public void guardaArxiu(String nom, String text) throws IOException {
        Files.writeString(Paths.get(nom), text, StandardOpenOption.CREATE);
    }

    /**
     * Llegeix un arxiu de memòria
     * @param nom nom de l'arxiu a llegir
     * @return contingut de l'arxiu a llegir
     * @throws IOException si no es pot llegir amb èxit la memòria
     */
    public String llegeixArxiu(String nom) throws IOException {
        return Files.readString(Paths.get(nom), StandardCharsets.UTF_8);
    }
}
