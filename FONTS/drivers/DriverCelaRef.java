package drivers;
import domini.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DriverCelaRef {

    static CelaRef celaRef;

    public static void TestConstructor(String inputUsuari, Cela c) {
        celaRef = new CelaRef(inputUsuari, c);
    }

    public static void TestGetInputUsuari() {
        System.out.println(celaRef.getInputUsuari());
    }

    public static void TestSetInputUsuari(String inputUsuari) {
        celaRef.setInputUsuari(inputUsuari);
    }

    public static void TestGetTipusCela() {
        System.out.println(celaRef.getTipusCela());
    }

    public static void TestSetRef(String inputUsuari, Cela c) {
        celaRef.setRef(c);
    }

    public static void TestGetNum() {
        System.out.println(celaRef.getNum());
    }

    public static void TestGetData() {
        System.out.println(celaRef.getData());
    }

    public static void TestGetText() {
        System.out.println(celaRef.getText());
    }

    public static void TestCompare(String inputUsuari, Cela cela) {
        CelaRef c = new CelaRef(inputUsuari, cela);
        System.out.println(celaRef.compare(c));
    }

    public static void TestCopy() {
        CelaRef c = (CelaRef) celaRef.copy();
        System.out.println(celaRef.getInputUsuari() + ", " + c.getInputUsuari());
        System.out.println(celaRef.getRef().getInputUsuari() + ", " + c.getRef().getInputUsuari());
        System.out.println(celaRef + ", " + c);
    }

    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        System.out.println("Introdueix un String com a inputUsuari i una altra String per crear una CelaText");
        String input = entry.next();
        String textInput = entry.next();
        TestConstructor(input, new CelaText(textInput));

        System.out.println("Executant getInputUsuari()");
        TestGetInputUsuari();

        System.out.println("Introdueix un String per provar setInputUsuari");
        input = entry.next();

        System.out.println("Executant setInputUsuari()");
        TestSetInputUsuari(input);

        System.out.println("Executant getTipusCela()");
        TestGetTipusCela();

        System.out.println("Introdueix un String i una altra String i un double per provar setRef");
        input = entry.next();
        textInput = entry.next();
        double valor = entry.nextDouble();
        TestSetRef(input, new CelaNum(textInput, valor));

        System.out.println("Executant getNum()");
        TestGetNum();

        System.out.println("Executant getText()");
        TestGetText();

        System.out.println("Executant getData()");
        TestGetData();

        System.out.println("Introdueix un String i un altre String i una nova data en format dd/mm/yyyy per crear" +
                " una altra CelaNum i provar el metode compare");
        input = entry.next();
        textInput = entry.next();
        String data = entry.next();
        TestCompare(input, new CelaData(textInput, LocalDate.parse(data, formatter)));

        System.out.println("Executant copy() i imprimint els atributs i els objectes, per veure que els atributs son" +
                "els mateixos pero els objectes no");
        TestCopy();
    }
}