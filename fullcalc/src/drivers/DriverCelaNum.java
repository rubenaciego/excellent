package drivers;
import domini.Cela;
import domini.CelaNum;
import java.util.Scanner;
import java.time.LocalDate;

public class DriverCelaNum {

    static CelaNum celaNum;

    public static void TestConstructor(String inputUsuari, double valorInput) {
        celaNum = new CelaNum(inputUsuari, valorInput);
    }

    public static void TestGetInputUsuari() {
        System.out.println(celaNum.getInputUsuari());
    }

    public static void TestSetInputUsuari(String inputUsuari) {
        celaNum.setInputUsuari(inputUsuari);
    }

    public static void TestGetTipusCela() {
        System.out.println(celaNum.getTipusCela());
    }

    public static void TestSetNum(String inputUsuari, double valorInput) {
        celaNum.setNum(inputUsuari, valorInput);
    }

    public static void TestGetNum() {
        System.out.println(celaNum.getNum());
    }

    public static void TestGetData() {
        System.out.println(celaNum.getData());
    }

    public static void TestGetText() {
        System.out.println(celaNum.getText());
    }

    public static void TestCompare(String inputUsuari, double valorInput) {
        CelaNum c = new CelaNum(inputUsuari, valorInput);
        System.out.println(celaNum.compare(c));
    }

    public static void TestCopy() {
        Cela c = celaNum.copy();
        System.out.println(celaNum.getInputUsuari() + ", " + c.getInputUsuari());
        System.out.println(celaNum.getNum() + ", " + c.getNum());
        System.out.println(celaNum + ", " + c);
    }

    public static void main(String[] args) {
        Scanner entry = new Scanner(System.in);

        System.out.println("Introdueix un String i un double per incialitzar la CelaNum");
        String input = entry.next();
        double valor = entry.nextDouble();
        TestConstructor(input, valor);
        celaNum = new CelaNum(input, valor);

        System.out.println("Executant getInputUsuari()");
        TestGetInputUsuari();

        System.out.println("Introdueix un String per provar setInputUsuari");
        input = entry.next();

        System.out.println("Executant setInputUsuari()");
        TestSetInputUsuari(input);

        System.out.println("Executant getTipusCela()");
        TestGetTipusCela();

        System.out.println("Introdueix un String i un double per provar setNum");
        input = entry.next();
        valor = entry.nextDouble();
        TestSetNum(input, valor);

        System.out.println("Executant getNum()");
        TestGetNum();

        System.out.println("Executant getText()");
        TestGetText();

        System.out.println("Executant getData()");
        TestGetData();

        System.out.println("Introdueix un String i un double per crear una altra CelaNum i provar compare");
        input = entry.next();
        valor = entry.nextDouble();
        TestCompare(input, valor);

        System.out.println("Executant copy() i imprimint els atributs i els objectes, per veure que els atributs son" +
                "els mateixos pero els objectes no");
        TestCopy();
    }
}
