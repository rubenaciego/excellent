package dades;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ControladorDades {

    public void guardaArxiu(String nom, String text) throws IOException {
        Files.writeString(Paths.get(nom), text, StandardOpenOption.CREATE);
    }

    public String llegeixArxiu(String nom) throws IOException {
        return Files.readString(Paths.get(nom), StandardCharsets.UTF_8);
    }
}
