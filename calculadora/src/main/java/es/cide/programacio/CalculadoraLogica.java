package es.cide.programacio;

import java.text.DecimalFormat;

public class CalculadoraLogica {

    double resultat;
    char operacio;

    // constructor
    public CalculadoraLogica() {
        resultat = 0;
        operacio = ' ';

    }

    // leemos la operacion y devolvemos el resultado
    public double operar(double a, double b, char operacio) {
        double resultat = 0;
        switch (operacio) {

            case '-':
                resultat = a - b;
                break;
            case '*':
                resultat = a * b;
                break;
            case '/':
                resultat = a / b;
                break;
            case '+':
                resultat = a + b;
                break;
            default:
                resultat = b;
                break;
        }
        return resultat;
    }

    // get del resultado
    public double getResultat() {
        return this.resultat;
    }

}
