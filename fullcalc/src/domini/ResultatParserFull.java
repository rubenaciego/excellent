package domini;

import java.lang.*;


public class ResultatParserFull {
    private int idFull;
    private int filaOrigen;
    private int filaDesti;
    private int columnaOrigen;
    private int columnaDesti;
    private int midaFila;
    private int midaColumna;
    private OpFull tipusOpFull;
    private OpAritmetica tipusOpAritmetica;
    private OpEstadistica tipusOpEstadistica;
    private ConversioUnitats tipusConversioUnitats;
    private CriteriOrdenacio tipusCriteriOrdenacio;
    private String stringCercada;
    private String stringRemplacadora;
    private ContingutCelaModificada celaModificada;

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

    public OpFull getTipusOpFull() {
        return tipusOpFull;
    }

    public void setTipusOpFull(OpFull tipusOpFull) {
        this.tipusOpFull = tipusOpFull;
    }

    public OpAritmetica getTipusOpAritmetica() {
        return tipusOpAritmetica;
    }

    public void setTipusOpAritmetica(OpAritmetica tipusOpAritmetica) {
        this.tipusOpAritmetica = tipusOpAritmetica;
    }

    public OpEstadistica getTipusOpEstadistica() {
        return tipusOpEstadistica;
    }

    public void setTipusOpEstadistica(OpEstadistica tipusOpEstadistica) {
        this.tipusOpEstadistica = tipusOpEstadistica;
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

    public ContingutCelaModificada getCelaModificada() {
        return celaModificada;
    }

    public void setCelaModificada(ContingutCelaModificada celaModificada) {
        this.celaModificada = celaModificada;
    }
}
