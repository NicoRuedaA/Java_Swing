package es.cide.programacio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.DecimalFormat;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import com.formdev.flatlaf.extras.FlatSVGIcon;

public class CalculadoraVisual extends JFrame {

    FlatSVGIcon icono1 = new FlatSVGIcon("icons/1.svg");
    FlatSVGIcon icono2 = new FlatSVGIcon("icons/2.svg");
    FlatSVGIcon icono3 = new FlatSVGIcon("icons/3.svg");
    FlatSVGIcon icono4 = new FlatSVGIcon("icons/4.svg");
    FlatSVGIcon icono5 = new FlatSVGIcon("icons/5.svg");
    FlatSVGIcon icono6 = new FlatSVGIcon("icons/6.svg");
    FlatSVGIcon icono7 = new FlatSVGIcon("icons/7.svg");
    FlatSVGIcon icono8 = new FlatSVGIcon("icons/8.svg");
    FlatSVGIcon icono9 = new FlatSVGIcon("icons/9.svg");

    JButton boto1 = new JButton("1", icono1);
    JButton boto2 = new JButton("2", icono2);
    JButton boto3 = new JButton("3", icono3);
    JButton boto4 = new JButton("4", icono4);
    JButton boto5 = new JButton("5", icono5);
    JButton boto6 = new JButton("6", icono6);
    JButton boto7 = new JButton("7", icono7);
    JButton boto8 = new JButton("8", icono8);
    JButton boto9 = new JButton("9", icono9);
    JButton boto0 = new JButton("0");
    JButton botoClear = new JButton("Clear");
    JButton botoIgual = new JButton("=");
    JButton botoSumar = new JButton("+");
    JButton botoRestar = new JButton("-");
    JButton botoMultiplicar = new JButton("*");
    JButton botoDividir = new JButton("/");

    DecimalFormat df = new DecimalFormat("####.##");

    JTextArea textAreaResultat;
    JTextArea textAreaHistorial;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 800;
    private static final int ROWS = 6;
    private static final int COLS = 1;

    double operant1 = 0;
    double operant2 = 0;
    double resultat = 0;

    char operacio = ' ';

    public CalculadoraVisual() {

        textAreaResultat = new JTextArea();
        textAreaHistorial = new JTextArea();

        textAreaHistorial.setBackground(new Color(100, 100, 100));
        textAreaResultat.setBackground(new Color(100, 100, 100));
        // ***VENTANA***/
        // titol
        this.setTitle("Calculadora - Nico Rueda");
        // tamany
        this.setSize(WIDTH, HEIGHT);
        // comportament
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // posicio
        this.setLocationRelativeTo(null);

        // ***GRID***/
        // cream un grid.
        JPanel grid = new JPanel(new GridLayout(ROWS, COLS, 15, 15));
        // ceamos empty border
        EmptyBorder emptyBorder = new EmptyBorder(10, 10, 10, 10);
        // afegim empty border
        grid.setBorder(emptyBorder);

        // cream 4 subrids
        JPanel fila1 = new JPanel(new GridLayout(1, 1));
        JPanel fila2 = new JPanel(new GridLayout(1, 1));
        JPanel fila3 = new JPanel(new GridLayout(1, 4));
        JPanel fila4 = new JPanel(new GridLayout(1, 4));
        JPanel fila5 = new JPanel(new GridLayout(1, 4));
        JPanel fila6 = new JPanel(new GridLayout(1, 4));

        configureFila1(fila1);
        configureFila2(fila2, textAreaResultat);
        configureFila3(fila3);
        configureFila4(fila4);
        configureFila5(fila5);
        configureFila6(fila6);

        // ***GRID PADRE***/
        // afegim els subgrids al grid "pare"
        grid.add(fila1);
        grid.add(fila2);
        grid.add(fila3);
        grid.add(fila4);
        grid.add(fila5);
        grid.add(fila6);

        this.setFont((new Font("Monospaced", Font.PLAIN, 10)));
        // afegim el grid a la finestra
        this.add(grid);

        // feim visible el Jframe
        this.setVisible(true);
        // this.setResizable(false);

        try {
            // Cargamos la imagen desde la carpeta de recursos
            ImageIcon icono = new ImageIcon(getClass().getResource("/icono.png"));
            this.setIconImage(icono.getImage());
        } catch (Exception e) {
            System.out.println("No se pudo cargar el icono: " + e.getMessage());
        }

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
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
                            // si es un boton de +, -, *, /
                        } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                            // registrarAccion(String.valueOf(c));
                            if (c == '+') {
                                botoSumar.doClick();
                            } else if (c == '-') {
                                botoRestar.doClick();
                            } else if (c == '*') {
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

    private void configureFila6(JPanel g) {

        botoClear.addActionListener(e -> registrarAccion(" "));
        botoSumar.addActionListener(e -> registrarAccion("+"));

        botoIgual.addActionListener(e -> registrarAccion("="));

        boto0.addActionListener(e -> registrarAccion("0"));
        g.add(botoClear);
        g.add(boto0);

        g.add(botoIgual);

        g.add(botoSumar);

    }

    private void configureFila5(JPanel g) {
        boto1.setFont(new Font("Arial", Font.PLAIN, 0));

        boto2.setFont(new Font("Arial", Font.PLAIN, 0));

        boto3.setFont(new Font("Arial", Font.PLAIN, 0));

        boto1.addActionListener(e -> registrarAccion("1"));

        boto2.addActionListener(e -> registrarAccion("2"));
        boto3.addActionListener(e -> registrarAccion("3"));
        botoRestar.addActionListener(e -> registrarAccion("-"));

        g.add(boto1);
        g.add(boto2);
        g.add(boto3);
        g.add(botoRestar);

    }

    private void configureFila4(JPanel g) {

        boto4.setFont(new Font("Arial", Font.PLAIN, 0));

        boto5.setFont(new Font("Arial", Font.PLAIN, 0));

        boto6.setFont(new Font("Arial", Font.PLAIN, 0));

        boto4.addActionListener(e -> registrarAccion("4"));

        boto5.addActionListener(e -> registrarAccion("5"));
        boto6.addActionListener(e -> registrarAccion("6"));
        botoMultiplicar.addActionListener(e -> registrarAccion("*"));

        g.add(boto4);
        g.add(boto5);
        g.add(boto6);
        g.add(botoMultiplicar);

    }

    private void configureFila3(JPanel g) {

        boto7.setFont(new Font("Arial", Font.PLAIN, 0));

        boto8.setFont(new Font("Arial", Font.PLAIN, 0));

        boto9.setFont(new Font("Arial", Font.PLAIN, 0));

        boto7.addActionListener(e -> registrarAccion("7"));

        boto8.addActionListener(e -> registrarAccion("8"));
        boto9.addActionListener(e -> registrarAccion("9"));
        botoDividir.addActionListener(e -> registrarAccion("/"));

        g.add(boto7);
        g.add(boto8);
        g.add(boto9);
        g.add(botoDividir);

    }

    private void configureFila2(JPanel g, JTextArea text) {
        // cream titol-border
        // set text del text area
        text.setText("0");
        // el text area no es editable
        text.setEditable(false);
        // establim sa font

        // numero de columnes i files
        text.setRows(1);
        text.setColumns(40);
        g.add(text);
    }

    private void configureFila1(JPanel g) {

        // set text del text area
        textAreaHistorial.setText("");
        // el text area no es editable
        textAreaHistorial.setEditable(false);
        // establim sa font

        // numero de columnes i files
        textAreaHistorial.setRows(1);
        textAreaHistorial.setColumns(40);
        g.add(textAreaHistorial);
    }

    private void setTitledBorder(String s, JPanel panel) {
        // cream un titol amb borde de tipus linea de color negre amb amplada 1
        TitledBorder titulo = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1), s,
                TitledBorder.LEFT, TitledBorder.TOP);
        // aplicam el borde
        panel.setBorder(titulo);

    }

    private void setTheme(String s) {
        // importam el tema amb try catch per prevenir errors
        /*
         * UIManager.setLookAndFeel(themeClass);
         * 
         * // 2. Método optimizado de FlatLaf para refrescar toda la app
         * FlatLaf.updateUI();
         * 
         * // 3. Opcional: Si tienes ventanas abiertas, esto asegura que se repinten
         * SwingUtilities.updateComponentTreeUI(this);
         */
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
