package es.cide.programacio;

import javax.swing.border.TitledBorder;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.awt.KeyboardFocusManager;
import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JTextArea;

class CalculadoraListener {

    CalculadoraLogica calculadoraLogica;

    CalculadoraVisual calculadoraVisual;

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
    JTextArea textAreaHistorial;
    JTextArea textAreaResultat;

    double operant1;
    double operant2;

    DecimalFormat df;

    double resultat;
    char operacio;

    private void inicializarComponentes() {

        calculadoraLogica = new CalculadoraLogica();

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

    public CalculadoraListener(CalculadoraVisual vista, CalculadoraLogica logica) {

        this.calculadoraVisual = vista;
        this.calculadoraLogica = logica;

        operant1 = 0;
        operant2 = 0;

        inicializarComponentes();

        df = new DecimalFormat("####.##");

        boto7.addActionListener(e -> registrarAccion("7"));

        boto8.addActionListener(e -> registrarAccion("8"));
        boto9.addActionListener(e -> registrarAccion("9"));
        botoDividir.addActionListener(e -> registrarAccion("/"));
        boto4.addActionListener(e -> registrarAccion("4"));

        boto5.addActionListener(e -> registrarAccion("5"));
        boto6.addActionListener(e -> registrarAccion("6"));
        botoMultiplicar.addActionListener(e -> registrarAccion("  "));
        boto1.addActionListener(e -> registrarAccion("1"));

        boto2.addActionListener(e -> registrarAccion("2"));
        boto3.addActionListener(e -> registrarAccion("3"));
        botoRestar.addActionListener(e -> registrarAccion("-"));
        botoClear.addActionListener(e -> registrarAccion(" "));
        botoSumar.addActionListener(e -> registrarAccion("+"));

        botoIgual.addActionListener(e -> registrarAccion("="));

        boto0.addActionListener(e -> registrarAccion("0"));

        listenKey();

    }

    private void listenKey() {
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
                                // registrarAccion(String.valueOf(c));
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
        // 1. GESTIÓN DE NÚMEROS (Concatenación)
        if (Character.isDigit(valor.charAt(0))) {
            // Multiplicamos por 10 para desplazar el dígito a la izquierda y sumamos el
            // nuevo
            operant1 = (operant1 * 10) + Double.parseDouble(valor);

            textAreaResultat.setText(df.format(operant1));
            return; // Salimos para no procesar el resto como operación
        }

        // 2. GESTIÓN DE OPERACIONES (+, -, *, /)
        if (valor.equals("+") || valor.equals("-") || valor.equals("*") || valor.equals("/")) {
            if (resultat == 0) {
                resultat = operant1; // Primer número introducido
            } else if (operant1 != 0) {
                // Si ya hay un número pendiente, lo operamos antes de cambiar al nuevo signo
                resultat = calculadoraLogica.operar(resultat, operant1, operacio);
            }

            operacio = valor.charAt(0);
            textAreaHistorial.setText(df.format(resultat) + " " + operacio + " ");
            textAreaResultat.setText(df.format(resultat));
            operant1 = 0; // Reset para el siguiente número
        }

        // 3. CASOS ESPECIALES (C, =)
        else {
            switch (valor) {
                case "=":
                    if (operacio == '/' && operant1 == 0) {
                        textAreaResultat.setText("Error: Div/0");
                    } else {
                        resultat = calculadoraLogica.operar(resultat, operant1, operacio);
                        textAreaHistorial.setText(textAreaHistorial.getText() + df.format(operant1) + " = ");
                        textAreaResultat.setText(df.format(resultat));
                    }
                    operant1 = 0; // Importante para permitir una nueva operación tras el igual
                    break;

                case " ": // Asegúrate de que este String coincida con el nombre del botón
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