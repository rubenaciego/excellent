package util;

public class Utilitats {

    private Utilitats() {}

    /**
     * @param text Text a convertir a número codificat en base 26 usant caràcters A-Z
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
}
