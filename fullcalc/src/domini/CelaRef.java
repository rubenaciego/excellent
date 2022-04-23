package domini;
import java.awt.dnd.InvalidDnDOperationException;
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

    public Cela getRef()
    {
        return celaReferenciada;
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
    public CelaRef copy() {
        CelaRef nova = new CelaRef(new String(inputUsuari), celaReferenciada);
        return nova;
    }

    @Override
    public int compare(Cela c)
    {
        return celaReferenciada.compare(c);
    }

    @Override
    protected int compareType(Cela c)
    {
        throw new UnsupportedOperationException("compareType de CelaRef mai no pot ser cridat");
    }
}
