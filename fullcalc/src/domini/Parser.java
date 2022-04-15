package domini;

import java.lang.*;

enum opAritmetica {
    valorAbsolut, incrementar, decrementar, exponencial, cosinus, sinus,
    cosinusHiperbolic, sinusHiperbolic, tangentHiperbolic;
}

enum opEstadistica {
    mitjana, mediana, variancia, covariancia, desviacioEstandard,
    coeficientPearson;
}

enum opFull {
    extreuHoroscop, extreuAny, extreuDia, extreuDiaSetmana,
    executaOperacioAritmeticaUnaria, executaFuncioEstadistica,
    truncaNumero, converteixUnitats, extreuLongitudText,
    cercaOcurrencies, converteixMajuscules, converteixMinuscules,
    transposa, reemplaca, ordena, modificaCela,
    afegeixColumna, afegeixFila, eliminaColumna, eliminaFila,
    mouBloc, copiaBloc, buidaBloc;
}

enum opDocument {
    creaDocument, carregaDocument, desaDocument, afegeixFull, eliminaFull;
}

enum criteriOrdenacio {
    ascendent, descendent;
}

enum conversioUnitats {
    radGraus, grausRad, kmMilla, millaKm, kgLliura, lliuraKg, litreGalo,
    galoLitre, celsiusKelvin, kelvinCelsius, kelvinFahrenheit,
    fahrenheitKelvin, fahrenheitCelsius, celsiuFahrenheit, km2Hecatarea,
    hectareaKm2, kmhMillah, millahKmh;
}

public class Parser {
    /**
     * @brief Fa el parseig d'una operació provinent de la capa de presentació
     * * /pre opFull és un array de tres strings on el primer conté el codi
     * d'operació fila d'origen, fila de destí, columna d'origen, columna de
     * destí, mida fila, mida columna separat per comes (,); el segon string
     * conté l'inputUsuari en cas que l'operació sigui modificaCela o la
     * stringCercada en cas que l'operació sigui cercaOcurrencies o
     * reemplaça;  el tercer string conté la stringReemplaçadora en cas que
     * l'operació sigui reemplaça
     * /post retorna les dades d'opFull estructurades en un objecte de
     * ResultatParserFull
     */
    public ResultatParserFull parseOpFull(String[] opSenseParsejar) {
        String[] splitted = opSenseParsejar[0].split(",");
        ResultatParserFull resultat = new ResultatParserFull();

        resultat.filaOrigen = Integer.parseInt(splitted[1]);
        resultat.filaDesti = Integer.parseInt(splitted[2]);
        resultat.columnaOrigen = Integer.parseInt(splitted[3]);
        resultat.columnaDesti = Integer.parseInt(splitted[4]);
        resultat.midaFila = Integer.parseInt(splitted[5]);
        resultat.midaColumna = Integer.parseInt(splitted[6]);
        int codiOp = Integer.parseInt(splitted[0]);
        if (codiOp >= 50 && codiOp < 80) {
            resultat.tipusOpFull = opFull.valueOf(String.valueOf(codiOp-50));
        }
        return resultat;
    }

    //public ResultatParserDocument parseOpDocument (String opDocument)

    //public ResultatParserInput parseOpInputUsuari (String opInputUsuari)
}
