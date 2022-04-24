package drivers;
import domini.Cela;
import domini.CelaText;

import java.util.Scanner;

public class DriverCelaText {

    static CelaText celaText;

    public static void TestConstructor1(String inputUsuari) {
        celaText = new CelaText(inputUsuari);
    }

    public static void TestConstructor2(String inputUsuari, String text) {
        celaText = new CelaText(inputUsuari, text);
    }

    public static void TestGetInputUsuari() {
        System.out.println(celaText.getInputUsuari());
    }

    public static void TestSetInputUsuari(String inputUsuari) {
        celaText.setInputUsuari(inputUsuari);
    }

    public static void TestGetTipusCela() {
        System.out.println(celaText.getTipusCela());
    }

    public static void TestSetText(String inputUsuari, String text) {
        celaText.setText(inputUsuari, text);
    }

    public static void TestGetNum() {
        System.out.println(celaText.getNum());
    }

    public static void TestGetData() {
        System.out.println(celaText.getData());
    }

    public static void TestGetText() {
        System.out.println(celaText.getText());
    }

    public static void TestCompare(String inputUsuari, String text) {
        CelaText c = new CelaText(inputUsuari, text);
        System.out.println(celaText.compare(c));
    }

    public static void TestCopy() {
        Cela c = celaText.copy();
        System.out.println(celaText.getInputUsuari() + ", " + c.getInputUsuari());
        System.out.println(celaText.getText() + ", " + c.getText());
        System.out.println(celaText + ", " + c);
    }

    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);

        System.out.println("Introdueix un String per inicialitzar la CelaText amb un sol argument");
        String input = entry.next();
        TestConstructor1(input);

        System.out.println("Introdueix dues String per inicialitzar la CelaText amb dos parametres");
        input = entry.next();
        String text = entry.next();
        TestConstructor2(input, text);

        System.out.println("Executant getInputUsuari()");
        TestGetInputUsuari();

        System.out.println("Introdueix un String per provar setInputUsuari");
        input = entry.next();

        System.out.println("Executant setInputUsuari()");
        TestSetInputUsuari(input);

        System.out.println("Executant getTipusCela()");
        TestGetTipusCela();

        System.out.println("Introdueix dues String per provar setText");
        input = entry.next();
        text = entry.next();
        TestSetText(input, text);

        System.out.println("Executant getNum()");
        TestGetNum();

        System.out.println("Executant getText()");
        TestGetText();

        System.out.println("Executant getData()");
        TestGetData();

        System.out.println("Introdueix dues String per crear una altra CelaText i provar compare");
        input = entry.next();
        text = entry.next();
        TestCompare(input, text);

        System.out.println("Executant copy() i imprimint els atributs i els objectes, per veure que els atributs son" +
                "els mateixos pero els objectes no");
        TestCopy();
    }
}