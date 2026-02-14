package es.cide.programacio;

import javax.swing.border.TitledBorder;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.awt.KeyboardFocusManager;
import java.awt.KeyEventDispatcher;
import javax.swing.JButton;
import javax.swing.JTextField;

class CalculadoraListener {

    // instancias que las otras clases
    CalculadoraLogica calculadoraLogica;
    CalculadoraVisual calculadoraVisual;

    // botones
    JButton boto1;
    JButton boto2;
    JButton boto3;
    JButton boto4;
    JButton boto5;
    JButton boto6;
    JButton boto7;
    JButton boto8;
    JButton boto9;
    JButton boto0;
    JButton botoClear;
    JButton botoIgual;
    JButton botoSumar;
    JButton botoRestar;
    JButton botoMultiplicar;
    JButton botoDividir;
    JTextField textAreaHistorial;
    JTextField textAreaResultat;

    // numeros que operan
    double operant1;
    double operant2;
    double resultat;
    char operacio;

    // limite de decimales para casos de 3.3333....
    DecimalFormat df;

    public CalculadoraListener(CalculadoraVisual vista, CalculadoraLogica logica) {

        // igualamos las instancias
        this.calculadoraVisual = vista;
        this.calculadoraLogica = logica;

        operant1 = 0;
        operant2 = 0;

        // inicializamos componentes
        inicializarComponentes();

        // declaramos el formado de nuestra calculadora

        df = new DecimalFormat("####.##");

        // registramos los action listener
        boto1.addActionListener(e -> registrarAccion("1"));
        boto2.addActionListener(e -> registrarAccion("2"));
        boto3.addActionListener(e -> registrarAccion("3"));
        boto4.addActionListener(e -> registrarAccion("4"));
        boto5.addActionListener(e -> registrarAccion("5"));
        boto6.addActionListener(e -> registrarAccion("6"));
        boto7.addActionListener(e -> registrarAccion("7"));
        boto8.addActionListener(e -> registrarAccion("8"));
        boto9.addActionListener(e -> registrarAccion("9"));
        boto0.addActionListener(e -> registrarAccion("0"));

        botoDividir.addActionListener(e -> registrarAccion("/"));
        botoMultiplicar.addActionListener(e -> registrarAccion("*"));
        botoRestar.addActionListener(e -> registrarAccion("-"));
        botoClear.addActionListener(e -> registrarAccion(" "));
        botoSumar.addActionListener(e -> registrarAccion("+"));
        botoIgual.addActionListener(e -> registrarAccion("="));

        // añadimos el action listener del teclado
        listenKey();

    }

    private void inicializarComponentes() {

        boto1 = calculadoraVisual.getButton("boto1");
        boto2 = calculadoraVisual.getButton("boto2");
        boto3 = calculadoraVisual.getButton("boto3");
        boto4 = calculadoraVisual.getButton("boto4");
        boto5 = calculadoraVisual.getButton("boto5");
        boto6 = calculadoraVisual.getButton("boto6");
        boto7 = calculadoraVisual.getButton("boto7");
        boto8 = calculadoraVisual.getButton("boto8");
        boto9 = calculadoraVisual.getButton("boto9");
        boto0 = calculadoraVisual.getButton("boto0");

        botoClear = calculadoraVisual.getButton("botoClear");
        botoIgual = calculadoraVisual.getButton("botoIgual");
        botoSumar = calculadoraVisual.getButton("botoSumar");
        botoRestar = calculadoraVisual.getButton("botoRestar");
        botoMultiplicar = calculadoraVisual.getButton("botoMultiplicar");
        botoDividir = calculadoraVisual.getButton("botoDividir");

        textAreaHistorial = calculadoraVisual.getTextArea("textAreaHistorial");
        textAreaResultat = calculadoraVisual.getTextArea("textAreaResultat");
    }

    private void listenKey() {
        // keyboard focus listener
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
                new KeyEventDispatcher() {

                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        // si pulsamos una tecla
                        if (e.getID() == KeyEvent.KEY_PRESSED) {
                            // guardamos la tecla pulsada en un char
                            char c = e.getKeyChar();
                            // guardamos el codigo de la tecla pulsada en un int
                            int codigo = e.getKeyCode();
                            // si la tecla es un digito
                            if (Character.isDigit(c)) {
                                switch (c) {
                                    case '1':
                                        boto1.doClick();
                                        break;
                                    case '2':
                                        boto2.doClick();
                                        break;
                                    case '3':
                                        boto3.doClick();
                                        break;
                                    case '4':
                                        boto4.doClick();
                                        break;
                                    case '5':
                                        boto5.doClick();
                                        break;
                                    case '6':
                                        boto6.doClick();
                                        break;
                                    case '7':
                                        boto7.doClick();
                                        break;
                                    case '8':
                                        boto8.doClick();
                                        break;
                                    case '9':
                                        boto9.doClick();
                                        break;
                                    case '0':
                                        boto0.doClick();
                                        break;
                                }
                                // si no, si es un "no numero"
                            } else {
                                // si la tecla es enter
                                if (codigo == KeyEvent.VK_ENTER) {
                                    // registrarAccion("=");
                                    botoIgual.doClick();
                                    // si la tecla es el boton de borrar
                                } else if (codigo == KeyEvent.VK_BACK_SPACE) {
                                    // registrarAccion(" ");
                                    botoClear.doClick();
                                    // si es un boton de +, -, , /
                                } else if (c == '+' || c == '-' || c == ' ' || c == '/') {
                                    // registrarAccion(String.valueOf(c));
                                    if (c == '+') {
                                        botoSumar.doClick();
                                    } else if (c == '-') {
                                        botoRestar.doClick();
                                    } else if (c == ' ') {
                                        botoMultiplicar.doClick();
                                    } else if (c == '/') {
                                        botoDividir.doClick();

                                    }
                                }
                            }
                        }
                        return false;
                    }
                });
    }

    public void registrarAccion(String valor) {
        // si es un numero
        if (Character.isDigit(valor.charAt(0))) {
            // Multiplicamos por 10 para desplazar el numero
            operant1 = (operant1 * 10) + Double.parseDouble(valor);
            // aplicamos el formato decimal
            textAreaResultat.setText(df.format(operant1));
            return;
        }

        // si no, si es operacion
        if (valor.equals("+") || valor.equals("-") || valor.equals("*") || valor.equals("/")) {
            if (resultat == 0) {
                resultat = operant1;
            } else if (operant1 != 0) {
                // si hay un numero
                resultat = calculadoraLogica.operar(resultat, operant1, operacio);
            }

            operacio = valor.charAt(0);
            textAreaHistorial.setText(df.format(resultat) + " " + operacio + " ");
            textAreaResultat.setText(df.format(resultat));
            operant1 = 0;
        }

        // igual y limpiar
        else {
            switch (valor) {
                case "=":
                    if (operacio == '/' && operant1 == 0) {
                        textAreaResultat.setText("Error: No se puede dividir entre 0");
                    } else {
                        resultat = calculadoraLogica.operar(resultat, operant1, operacio);
                        textAreaHistorial.setText(textAreaHistorial.getText() + df.format(operant1) + " = ");
                        textAreaResultat.setText(df.format(resultat));
                    }
                    operant1 = 0;
                    break;

                case " ":
                    operant1 = 0;
                    resultat = 0;
                    operacio = ' ';
                    textAreaResultat.setText("0");
                    textAreaHistorial.setText("");
                    break;
            }
        }
    }
}