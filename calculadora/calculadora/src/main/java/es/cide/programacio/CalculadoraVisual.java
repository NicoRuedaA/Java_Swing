package es.cide.programacio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.DecimalFormat;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

public class CalculadoraVisual extends JFrame {

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
        // ***VENTANA***/
        // titol
        this.setTitle("Tauler d'administració - Nico Rueda");
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
        this.setResizable(false);

    }

    private void configureFila6(JPanel g) {

        JButton botoClear = new JButton("Clear");
        JButton boto0 = new JButton("0");
        JButton botoIgual = new JButton("=");
        JButton botoSumar = new JButton("+");
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
        JButton boto1 = new JButton("1");
        JButton boto2 = new JButton("2");
        JButton boto3 = new JButton("3");
        JButton botoRestar = new JButton("-");

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
        JButton boto4 = new JButton("4");
        JButton boto5 = new JButton("5");
        JButton boto6 = new JButton("6");
        JButton botoMultiplicar = new JButton("*");

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
        JButton boto7 = new JButton("7");
        JButton boto8 = new JButton("8");
        JButton boto9 = new JButton("9");
        JButton botoDividir = new JButton("/");

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
        UIManager.setLookAndFeel(themeClass);

        // 2. Método optimizado de FlatLaf para refrescar toda la app
        FlatLaf.updateUI();

        // 3. Opcional: Si tienes ventanas abiertas, esto asegura que se repinten
        SwingUtilities.updateComponentTreeUI(this);
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
                    resultat = operar(resultat, operant1, operacio);
                    textAreaHistorial.setText(textAreaHistorial.getText() + " = ");
                    // + textAreaHistorial.getText() + operant1 + " = " + resultat + " "
                    textAreaResultat.setText(String.valueOf(resultat));
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
