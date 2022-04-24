package domini;

import java.lang.*;
import java.util.Objects;

public class ResultatParserFull {
    private int idFull;
    private int filaOrigen;
    private int filaDesti;
    private int columnaOrigen;
    private int columnaDesti;
    private int midaFila;
    private int midaColumna;
    private int digitsTruncar;
    private int filaColEliminar;
    private int columnaOrdenacio;
    private OperacioFull tipusOperacioFull;
    private OperacioAritmetica tipusOperacioAritmetica;
    private OperacioEstadistica tipusOperacioEstadistica;
    private ConversioUnitats tipusConversioUnitats;
    private CriteriOrdenacio tipusCriteriOrdenacio;
    private String stringCercada;
    private String stringRemplacadora;
    private ResultatParserCela resultatParserCela;


    public int getIdFull() {
        return idFull;
    }

    public void setIdFull(int idFull) {
        this.idFull = idFull;
    }

    public int getFilaOrigen() {
        return filaOrigen;
    }

    public void setFilaOrigen(int filaOrigen) {
        this.filaOrigen = filaOrigen;
    }

    public int getFilaDesti() {
        return filaDesti;
    }

    public void setFilaDesti(int filaDesti) {
        this.filaDesti = filaDesti;
    }

    public int getColumnaOrigen() {
        return columnaOrigen;
    }

    public void setColumnaOrigen(int columnaOrigen) {
        this.columnaOrigen = columnaOrigen;
    }

    public int getColumnaDesti() {
        return columnaDesti;
    }

    public void setColumnaDesti(int columnaDesti) {
        this.columnaDesti = columnaDesti;
    }

    public int getMidaFila() {
        return midaFila;
    }

    public void setMidaFila(int midaFila) {
        this.midaFila = midaFila;
    }

    public int getMidaColumna() {
        return midaColumna;
    }

    public void setMidaColumna(int midaColumna) {
        this.midaColumna = midaColumna;
    }

    public OperacioFull getTipusOpFull() {
        return tipusOperacioFull;
    }

    public void setTipusOpFull(OperacioFull tipusOperacioFull) {
        this.tipusOperacioFull = tipusOperacioFull;
    }

    public OperacioAritmetica getTipusOpAritmetica() {
        return tipusOperacioAritmetica;
    }

    public void setTipusOpAritmetica(OperacioAritmetica tipusOperacioAritmetica) {
        this.tipusOperacioAritmetica = tipusOperacioAritmetica;
    }

    public OperacioEstadistica getTipusOpEstadistica() {
        return tipusOperacioEstadistica;
    }

    public void setTipusOpEstadistica(OperacioEstadistica tipusOperacioEstadistica) {
        this.tipusOperacioEstadistica = tipusOperacioEstadistica;
    }

    public ConversioUnitats getTipusConversioUnitats() {
        return tipusConversioUnitats;
    }

    public void setTipusConversioUnitats(ConversioUnitats tipusConversioUnitats) {
        this.tipusConversioUnitats = tipusConversioUnitats;
    }

    public CriteriOrdenacio getTipusCriteriOrdenacio() {
        return tipusCriteriOrdenacio;
    }

    public void setTipusCriteriOrdenacio(CriteriOrdenacio tipusCriteriOrdenacio) {
        this.tipusCriteriOrdenacio = tipusCriteriOrdenacio;
    }

    public String getStringCercada() {
        return stringCercada;
    }

    public void setStringCercada(String stringCercada) {
        this.stringCercada = stringCercada;
    }

    public String getStringRemplacadora() {
        return stringRemplacadora;
    }

    public void setStringRemplacadora(String stringRemplacadora) {
        this.stringRemplacadora = stringRemplacadora;
    }

    public int getDigitsTruncar() {
        return digitsTruncar;
    }

    public void setDigitsTruncar(int digitsTruncar) {
        this.digitsTruncar = digitsTruncar;
    }

    public int getFilaColEliminar() {
        return filaColEliminar;
    }

    public void setFilaColEliminar(int filaColEliminar) {
        this.filaColEliminar = filaColEliminar;
    }

    public int getColumnaOrdenacio() {
        return columnaOrdenacio;
    }

    public void setColumnaOrdenacio(int columnaOrdenacio) {
        this.columnaOrdenacio = columnaOrdenacio;
    }

    public ResultatParserCela getResultatParserCela() {
        return resultatParserCela;
    }

    public void setResultatParserCela(ResultatParserCela resultatParserCela) {
        this.resultatParserCela = resultatParserCela;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof ResultatParserFull)) {
            return false;
        }

        ResultatParserFull c = (ResultatParserFull) o;

        return (idFull == c.idFull && filaOrigen == c.filaOrigen && filaDesti == c.filaDesti &&
                columnaOrigen == c.columnaOrigen && columnaDesti == c.columnaDesti
                && midaFila == c.midaFila && midaColumna == c.midaColumna &&
                digitsTruncar == c.digitsTruncar && filaColEliminar == c.filaColEliminar
                && columnaOrdenacio == c.columnaOrdenacio &&
                Objects.equals(String.valueOf(tipusOperacioFull),
                        String.valueOf(c.tipusOperacioFull)) &&
                Objects.equals(String.valueOf(tipusOperacioAritmetica),
                        String.valueOf(c.tipusOperacioAritmetica)) &&
                Objects.equals(String.valueOf(tipusOperacioEstadistica),
                        String.valueOf(c.tipusOperacioEstadistica)) &&
                Objects.equals(String.valueOf(tipusConversioUnitats),
                        String.valueOf(c.tipusConversioUnitats)) &&
                Objects.equals(String.valueOf(tipusCriteriOrdenacio),
                        String.valueOf(c.tipusCriteriOrdenacio)) &&
                Objects.equals(stringCercada, c.stringCercada) && Objects.equals(stringRemplacadora, c.stringRemplacadora) &&
                Objects.equals(resultatParserCela, c.resultatParserCela));
    }
}
