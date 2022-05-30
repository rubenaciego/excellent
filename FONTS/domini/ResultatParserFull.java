package domini;

import java.lang.*;
import java.util.Objects;


/**
 * Resultat de la decodificació en el parser d'una operació sobre un full.
 */
public class ResultatParserFull {
    /**
     * id del full sobre el que aplicar l'operació, si escau
     */
    private int idFull;
    /**
     * Fila de l'inici del bloc d'origen sobre el que aplicar l'operació, si
     * escau
     */
    private int filaOrigen;
    /**
     * Fila de l'inici del bloc de destí sobre el que aplicar l'operació, si
     * escau
     */
    private int filaDesti;
    /**
     * Columna de l'inici del bloc d'origen sobre el que aplicar l'operació, si
     * escau
     */
    private int columnaOrigen;
    /**
     * Columna de l'inici del bloc de destí sobre el que aplicar l'operació, si
     * escau
     */
    private int columnaDesti;
    /**
     * Nombre de files del bloc sobre el que aplicar l'operació, si ecau
     */
    private int midaFila;
    /**
     * Nombre de columnes del bloc sobre el que aplicar l'operació, si ecau
     */
    private int midaColumna;
    /**
     * Nombre de dígits a truncar, si escau
     */
    private int digitsTruncar;
    /**
     * Nombre de la fila o columna a eliminar, si escau
     */
    private int filaColEliminar;
    /**
     * Nombre de la columna respecte la qual ordenar, si escau
     */
    private int columnaOrdenacio;
    /**
     * Tipus de l'operació a executar
     */
    private OperacioFull tipusOperacioFull;
    /**
     * Tipus de l'operació aritmètica a executar, si escau
     */
    private OperacioAritmetica tipusOperacioAritmetica;
    /**
     * Tipus de l'operació estadística a executar, si escau
     */private OperacioEstadistica tipusOperacioEstadistica;
    /**
     * Conversió d'unitats a executar, si escau
     */
     private ConversioUnitats tipusConversioUnitats;
    /**
     * Criteri d'ordenació a utilitzar, si escau
     */
     private CriteriOrdenacio tipusCriteriOrdenacio;
    /**
     * String a cercar, si escau
     */
     private String stringCercada;
    /**
     * String per la qual reemplaçar la cercada, si escau
     */
     private String stringRemplacadora;
    /**
     * Resultat del parser sobre una cel·la, si escau
     */
     private ResultatParserCela resultatParserCela;


    /**
     * Getter del id del full
     * @return id del full
     */
    public int getIdFull() {
        return idFull;
    }

    public void setIdFull(int idFull) {
        this.idFull = idFull;
    }

    /**
     * Getter de la fila d'inici del bloc d'origen
     * @return fila d'inici del bloc d'origen
     */
    public int getFilaOrigen() {
        return filaOrigen;
    }

    /**
     * Setter de la fila d'inici del bloc d'origen
     * @param filaOrigen la fila d'inici del bloc d'origen
     */
    public void setFilaOrigen(int filaOrigen) {
        this.filaOrigen = filaOrigen;
    }

    /**
     * Getter de la fila d'inici del bloc de destí
     * @return fila d'inici del bloc de destí
     */
    public int getFilaDesti() {
        return filaDesti;
    }

    /**
     * Setter de la fila d'inici del bloc de destí
     * @param filaDesti la fila d'inici del bloc de destí
     */
    public void setFilaDesti(int filaDesti) {
        this.filaDesti = filaDesti;
    }

    /**
     * Getter de la columna d'inici del bloc d'origen
     * @return columna d'inici del bloc d'origen
     */
    public int getColumnaOrigen() {
        return columnaOrigen;
    }

    /**
     * Setter de la columna d'inici del bloc d'origen
     * @param columnaOrigen la columna d'inici del bloc d'origen
     */
    public void setColumnaOrigen(int columnaOrigen) {
        this.columnaOrigen = columnaOrigen;
    }

    /**
     * Getter de la columna d'inici del bloc de destí
     * @return columna d'inici del bloc de destí
     */public int getColumnaDesti() {
        return columnaDesti;
    }

    /**
     * Setter de la fila d'inici del bloc de destí
     * @param columnaDesti la fila d'inici del bloc de destí
     */
    public void setColumnaDesti(int columnaDesti) {
        this.columnaDesti = columnaDesti;
    }

    /**
     * Getter del nombre de files del bloc
     * @return nombre de files del bloc
     */
    public int getMidaFila() {
        return midaFila;
    }

    /**
     * Setter del nombre de files del bloc
     * @param midaFila nombre de files del bloc
     */
    public void setMidaFila(int midaFila) {
        this.midaFila = midaFila;
    }

    /**
     * Getter del nombre de columnes del bloc
     * @return nombre de columnes del bloc
     */
    public int getMidaColumna() {
        return midaColumna;
    }

    /**
     * Setter del nombre de columnes del bloc
     * @param midaColumna nombre de columnes del bloc
     */
    public void setMidaColumna(int midaColumna) {
        this.midaColumna = midaColumna;
    }

    /**
     * Getter del tipus de l'operació a executar
     * @return tipus de l'operació a executar
     */
    public OperacioFull getTipusOpFull() {
        return tipusOperacioFull;
    }

    /**
     * Setter del tipus de l'operació a executar
     * @param tipusOperacioFull tipus de l'operació a executar
     */
    public void setTipusOpFull(OperacioFull tipusOperacioFull) {
        this.tipusOperacioFull = tipusOperacioFull;
    }

    /**
     * Getter del tipus de l'operació aritmètica a executar
     * @return tipus de l'operació aritmètica a executar
     */
    public OperacioAritmetica getTipusOpAritmetica() {
        return tipusOperacioAritmetica;
    }

    /**
     * Setter del tipus de l'operació aritmètica a executar
     * @param tipusOperacioAritmetica  tipus de l'operació aritmètica a executar
     */
    public void setTipusOpAritmetica(OperacioAritmetica tipusOperacioAritmetica) {
        this.tipusOperacioAritmetica = tipusOperacioAritmetica;
    }

    /**
     * Getter del tipus de l'operació estadística a executar
     * @return tipus de l'operació estadística a executar
     */
    public OperacioEstadistica getTipusOpEstadistica() {
        return tipusOperacioEstadistica;
    }

    /**
     * Setter del tipus de l'operació estadística a executar
     * @param tipusOperacioEstadistica  tipus de l'operació estadística a executar
     */
    public void setTipusOpEstadistica(OperacioEstadistica tipusOperacioEstadistica) {
        this.tipusOperacioEstadistica = tipusOperacioEstadistica;
    }

    /**
     * Getter del tipus de la conversió d'unitats a executar
     * @return tipus de la conversió d'unitats a executar
     */
    public ConversioUnitats getTipusConversioUnitats() {
        return tipusConversioUnitats;
    }

    /**
     * Setter del tipus de la conversió d'unitats a executar
     * @param tipusConversioUnitats  tipus de la conversió d'unitats a executar
     */
    public void setTipusConversioUnitats(ConversioUnitats tipusConversioUnitats) {
        this.tipusConversioUnitats = tipusConversioUnitats;
    }

    /**
     * Getter del criteri d'ordenació a utilitzar
     * @return criteri d'ordenació a utilitzar
     */
    public CriteriOrdenacio getTipusCriteriOrdenacio() {
        return tipusCriteriOrdenacio;
    }

    /**
     * Setter del criteri d'ordenació a utilitzar
     * @param tipusCriteriOrdenacio  criteri d'ordenació a utilitzar
     */
    public void setTipusCriteriOrdenacio(CriteriOrdenacio tipusCriteriOrdenacio) {
        this.tipusCriteriOrdenacio = tipusCriteriOrdenacio;
    }

    /**
     * Getter de l'string a cercar
     * @return string a cercar
     */
    public String getStringCercada() {
        return stringCercada;
    }

    /**
     * Setter de l'string a cercar
     * @param stringCercada  string a cercar
     */
    public void setStringCercada(String stringCercada) {
        this.stringCercada = stringCercada;
    }

    /**
     * Getter de l'string pel qual reemplaçar l'string a cercar
     * @return string pel qual reemplaçar l'string a cercar
     */
    public String getStringRemplacadora() {
        return stringRemplacadora;
    }

    /**
     * Setter de l'string pel qual reemplaçar l'string a cercar
     * @param stringRemplacadora  string pel qual reemplaçar l'string a cercar
     */
    public void setStringRemplacadora(String stringRemplacadora) {
        this.stringRemplacadora = stringRemplacadora;
    }

    /**
     * Getter del nombre de dígits a truncar
     * @return nombre de dígits a truncar
     */
    public int getDigitsTruncar() {
        return digitsTruncar;
    }

    /**
     * Setter del nombre de dígits a truncar
     * @param digitsTruncar  nombre de dígits a truncar
     */
    public void setDigitsTruncar(int digitsTruncar) {
        this.digitsTruncar = digitsTruncar;
    }

    /**
     * Getter del nombre de la fila o columna a eliminar
     * @return nombre de la fila o columna a eliminar
     */
    public int getFilaColEliminar() {
        return filaColEliminar;
    }

    /**
     * Setter del nombre de la fila o columna a eliminar
     * @param filaColEliminar  nombre de la fila o columna a eliminar
     */
    public void setFilaColEliminar(int filaColEliminar) {
        this.filaColEliminar = filaColEliminar;
    }

    /**
     * Getter del nombre de la columna respecte la qual ordenar
     * @return nombre de la columna respecte la qual ordenar
     */
    public int getColumnaOrdenacio() {
        return columnaOrdenacio;
    }

    /**
     * Setter del nombre de la columna respecte la qual ordenar
     * @param columnaOrdenacio  nombre de la columna respecte la qual ordenar
     */
    public void setColumnaOrdenacio(int columnaOrdenacio) {
        this.columnaOrdenacio = columnaOrdenacio;
    }

    /**
     * Getter del resultat del parsejat d'una cel·la
     * @return resultat del parsejat d'una cel·la
     */
    public ResultatParserCela getResultatParserCela() {
        return resultatParserCela;
    }

    /**
     * Setter del resultat del parsejat d'una cel·la
     * @param resultatParserCela  resultat del parsejat d'una cel·la
     */
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
