package es.cide.programacio;

class CalculadoraLogica {

    public CalculadoraLogica() {

    }

    public void registrarAccion(String valor) {

        // operaciones aritmeticas
        if (valor.equals("+") || valor.equals("-") || valor.equals("*") || valor.equals("/")) {
            if (operant1 == 0) {
                textAreaHistorial.setText(resultat + " " + valor + " ");
            } else {
                resultat += operant1;
                textAreaHistorial.setText(textAreaHistorial.getText() + " " + valor + " ");
                textAreaResultat.setText(String.valueOf(resultat));

            }
            operant1 = 0;

            operacio = valor.charAt(0);
        }
        // el resto
        else {
            switch (valor) {
                case ".":
                    break;

                case "=":
                    if ((operacio == '/') && (operant1 == 0)) {
                        textAreaHistorial.setText("No se puede dividir por 0");
                        textAreaResultat.setText("No se puede dividir por 0");
                        resultat = 0;
                    } else {
                        resultat = operar(resultat, operant1, operacio);
                        textAreaHistorial.setText(textAreaHistorial.getText() + " = ");

                        textAreaResultat.setText(String.valueOf(resultat));

                    }
                    operant1 = 0;

                    break;
                case " ":
                    operacio = ' ';
                    operant1 = 0;
                    resultat = 0;
                    textAreaResultat.setText("0");
                    textAreaHistorial.setText("0");
                    break;
                default:
                    // numeros
                    if (resultat != 0) {
                        operant1 *= 10;
                        operant1 += Integer.parseInt(valor);
                        textAreaHistorial.setText(
                                String.valueOf(textAreaHistorial.getText() + df.format(Integer.parseInt(valor))));

                    } else {
                        operant1 *= 10;
                        operant1 += Integer.parseInt(valor);
                        textAreaHistorial.setText(String.valueOf(df.format(operant1)));
                    }

                    textAreaResultat.setText(String.valueOf(df.format(operant1)));
                    break;
            }
        }

    }

    private double operar(double a, double b, char operacio) {
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

}