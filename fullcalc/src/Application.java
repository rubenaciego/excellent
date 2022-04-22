import domini.*;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

public class Application
{
    public static void main(String[] args)
    {
        System.out.println("Hello world!");
        Locale l = new Locale("ca", "ES");
        DayOfWeek d = DayOfWeek.MONDAY;
        System.out.println(d.getDisplayName(TextStyle.FULL_STANDALONE, l));

        System.out.println(OperacioEstadistica.COEFICIENT_PEARSON.compareTo(OperacioEstadistica.COVARIANCIA));
    }
}
