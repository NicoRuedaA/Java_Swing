package es.cide.programacio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class CalculadoraVisual extends JFrame {

    JTextArea textAreaResultat;
    JTextArea textAreaHistorial;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 800;
    private static final int ROWS = 6;
    private static final int COLS = 1;

    int operant1 = 0;
    int operant2 = 0;

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
        JPanel fila3 = new JPanel(new GridLayout(1, 5));
        JPanel fila4 = new JPanel(new GridLayout(1, 5));
        JPanel fila5 = new JPanel(new GridLayout(1, 5));
        JPanel fila6 = new JPanel(new GridLayout(1, 5));

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
        JButton botoPunt = new JButton(".");
        JButton boto0 = new JButton("0");
        JButton botoComa = new JButton(",");
        JButton botoPerCent = new JButton("%");
        JButton botoDolar = new JButton("$");

        botoPunt.addActionListener(e -> registrarAccion("."));

        boto0.addActionListener(e -> registrarAccion("0"));

        botoComa.addActionListener(e -> registrarAccion(","));

        botoPerCent.addActionListener(e -> registrarAccion("%"));

        botoDolar.addActionListener(e -> registrarAccion("$"));

        g.add(botoPunt);
        g.add(boto0);
        g.add(botoComa);
        g.add(botoPerCent);
        g.add(botoDolar);

    }

    private void configureFila5(JPanel g) {
        JButton boto1 = new JButton("1");
        JButton boto2 = new JButton("2");
        JButton boto3 = new JButton("3");
        JButton botoIgual = new JButton("=");
        JButton botoMes = new JButton("+");

        boto1.addActionListener(e -> registrarAccion("1"));

        boto2.addActionListener(e -> registrarAccion("2"));
        boto3.addActionListener(e -> registrarAccion("3"));

        botoIgual.addActionListener(e -> registrarAccion("="));

        botoMes.addActionListener(e -> registrarAccion("+"));

        g.add(boto1);
        g.add(boto2);
        g.add(boto3);
        g.add(botoIgual);
        g.add(botoMes);
    }

    private void configureFila4(JPanel g) {
        JButton boto4 = new JButton("4");
        JButton boto5 = new JButton("5");
        JButton boto6 = new JButton("6");
        JButton botoRestar = new JButton("-");
        JButton botoMultiplicar = new JButton("*");

        boto4.addActionListener(e -> registrarAccion("4"));

        boto5.addActionListener(e -> registrarAccion("5"));
        boto6.addActionListener(e -> registrarAccion("6"));

        botoRestar.addActionListener(e -> registrarAccion("-"));

        botoMultiplicar
                .addActionListener(e -> registrarAccion("*"));

        g.add(boto4);
        g.add(boto5);
        g.add(boto6);
        g.add(botoRestar);
        g.add(botoMultiplicar);
    }

    private void configureFila3(JPanel g) {
        JButton boto7 = new JButton("7");
        JButton boto8 = new JButton("8");
        JButton boto9 = new JButton("9");
        JButton botoDividir = new JButton("/");
        JButton botoClear = new JButton("Clear");

        boto7.getText();

        boto7.addActionListener(e -> registrarAccion("7"));

        boto8.addActionListener(e -> registrarAccion("8"));
        boto9.addActionListener(e -> registrarAccion("9"));

        botoDividir.addActionListener(e -> registrarAccion("/"));

        botoClear.addActionListener(e -> registrarAccion(""));

        g.add(boto7);
        g.add(boto8);
        g.add(boto9);
        g.add(botoDividir);
        g.add(botoClear);
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
        try {
            UIManager.setLookAndFeel(s);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrarAccion(String valor) {

        if (valor.equals("")) {
            textAreaHistorial.setText(" ");
        }

        else if ((valor.equals("*")) || (valor.equals("*"))) {
            textAreaHistorial.setText(" ");
        }

        else {

            textAreaHistorial.setText(textAreaHistorial.getText() + " " + valor + " ");
        }

        operant1 = Integer.parseInt(valor);
        textAreaResultat.setText(String.valueOf(operant1));
    }

}
