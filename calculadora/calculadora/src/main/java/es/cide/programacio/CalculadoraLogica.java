package es.cide.programacio;

import java.text.DecimalFormat;

public class CalculadoraLogica {

    double resultat;
    char operacio;

    public CalculadoraLogica() {
        resultat = 0;
        operacio = ' ';

    }

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

            default:
                resultat = a + b;
                break;
        }
        return resultat;
    }

    public double getResultat() {
        return this.resultat;
    }

}
