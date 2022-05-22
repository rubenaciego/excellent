package util;

import javafx.util.Pair;

public class Utilitats {

    private Utilitats() {}

    /**
     * @param text Text a convertir a número codificat en "base 26" usant caràcters A-Z
     * @return El valor numèric o -1 en cas que no sigui vàlid
     */
    public static int convertirBase26(String text) {
        final int base = 'Z' - 'A' + 1;
        int res = 0;

        for (int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') return -1;
            res = base * res  + (int)(c - 'A') + 1;
        }

        return res - 1;
    }

    /**
     * @param num Número no negatiu a convertir a "base 26" usant caràcters A-Z
     * @return El text amb caràcters A-Z que representa el valor o un text buit si l'entrada
     * és negativa
     */
    public static String convertirABase26(int num) {
        if (num < 0) return "";
        final int base = 'Z' - 'A' + 1;
        StringBuilder sb = new StringBuilder();

        boolean first = true;

        do {
            if (!first) --num;
            int d = num % base;
            sb.append((char)('A' + d));
            num /= base;
            first = false;
        } while (num != 0);

        sb.reverse();
        return sb.toString();
    }

    /**
     * @param text Converteix un text que representa dos números, un en format "base 26" amb A-Z i
     *             l'altre en decimal a un parell format pel valor numèric dels dos nombres
     * @return Un pair amb els dos nombres i null si l'entrada no és correcte
     */
    public static Pair<Integer, Integer> convertirAIndexs(String text) {
        if (text.matches("^[A-Z]+[1-9][0-9]*$")) {
            int index = -1;
            char c;

            do {
                ++index;
                c = text.charAt(index);
            } while (c >= 'A' && c <= 'Z');

            String fila = text.substring(index);
            String col = text.substring(0, index);

            return new Pair<Integer, Integer>(Integer.parseInt(fila) - 1, Utilitats.convertirBase26(col));
        }
        else return null;
    }

    /**
     * @param f Fila
     * @param c Columna
     * @return Converteix la fila i columna a la representació en "base 26" amb A-Z seguida d'un número decimal
     */
    public static String convertirATextCela(int f, int c) {
        return convertirABase26(c) + Integer.toString(f + 1);
    }
}
