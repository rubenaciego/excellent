package domini;
import java.lang.*;
import java.time.LocalDate;

//falta el compare
public class CelaRef extends Cela
{
    private Cela celaReferenciada;

    public CelaRef(String inputUsuari, Cela celaRef)
    {
        super.setInputUsuari(inputUsuari);
        celaReferenciada = celaRef;
        tipusCela = TipusCela.REFERENCIAL;
    }

    public void setRef(String inputUsuari, Cela celaRef)
    {
        super.setInputUsuari(inputUsuari);
        celaReferenciada = celaRef;
        tipusCela = TipusCela.REFERENCIAL;
    }

    @Override
    public Double getNum()
    {
        return celaReferenciada.getNum();
    }

    @Override
    public LocalDate getData()
    {
        return celaReferenciada.getData();
    }

    @Override
    public String getText()
    {
        return celaReferenciada.getText();
    }

    @Override
    public int compare(Cela c)
    {
        return celaReferenciada.compare(c);
    }

    @Override
    protected int compareType(Cela c)
    {
        // error
        return 0;
    }
}
