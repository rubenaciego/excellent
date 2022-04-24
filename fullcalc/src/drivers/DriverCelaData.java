package drivers;
import domini.Cela;
import domini.CelaData;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DriverCelaData {

    static CelaData celaData;

    public static void TestConstructor(String inputUsuari, LocalDate data) {
        celaData = new CelaData(inputUsuari, data);
    }

    public static void TestGetInputUsuari() {
        System.out.println(celaData.getInputUsuari());
    }

    public static void TestSetInputUsuari(String inputUsuari) {
        celaData.setInputUsuari(inputUsuari);
    }

    public static void TestGetTipusCela() {
        System.out.println(celaData.getTipusCela());
    }

    public static void TestSetData(String inputUsuari, LocalDate data) {
        celaData.setData(inputUsuari, data);
    }

    public static void TestGetNum() {
        System.out.println(celaData.getNum());
    }

    public static void TestGetData() {
        LocalDate novaData = celaData.getData();
        System.out.println(novaData.getDayOfMonth() + "/" + novaData.getMonthValue() + "/" + novaData.getYear());
    }

    public static void TestGetText() {
        System.out.println(celaData.getText());
    }

    public static void TestCompare(String inputUsuari, LocalDate data) {
        CelaData c = new CelaData(inputUsuari, data);
        System.out.println(celaData.compare(c));
    }

    public static void TestCopy() {
        Cela c = celaData.copy();
        System.out.println(celaData.getInputUsuari() + ", " + c.getInputUsuari());
        System.out.println(celaData.getData() + ", " + c.getData());
        System.out.println(celaData + ", " + c);
    }

    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        System.out.println("Introdueix un String i una data en format dd/mm/yyyy per inicialitzar la CelaData");
        String input = entry.next();
        String data = entry.next();
        TestConstructor(input, LocalDate.parse(data, formatter));

        System.out.println("Executant getInputUsuari()");
        TestGetInputUsuari();

        System.out.println("Introdueix un String per provar setInputUsuari");
        input = entry.next();

        System.out.println("Executant setInputUsuari()");
        TestSetInputUsuari(input);

        System.out.println("Executant getTipusCela()");
        TestGetTipusCela();

        System.out.println("Introdueix un String i una data en format dd/mm/yyyy per provar setData");
        input = entry.next();
        data = entry.next();
        TestSetData(input, LocalDate.parse(data, formatter));

        System.out.println("Executant getNum()");
        TestGetNum();

        System.out.println("Executant getText()");
        TestGetText();

        System.out.println("Executant getData()");
        TestGetData();

        System.out.println("Introdueix un String i una nova data en format dd/mm/yyyy per crear una altra CelaData" +
                "i provar el metode compare");
        input = entry.next();
        data = entry.next();
        TestCompare(input, LocalDate.parse(data, formatter));

        System.out.println("Executant copy() i imprimint els atributs i els objectes, per veure que els atributs son" +
                "els mateixos pero els objectes no");
        TestCopy();
    }
}