package es.cide.programacio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.text.DecimalFormat;

import com.formdev.flatlaf.extras.FlatSVGIcon;

public class CalculadoraVisual extends JFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 800;
    private static final int ROWS = 6;
    private static final int COLS = 1;

    FlatSVGIcon icono1;
    FlatSVGIcon icono2;
    FlatSVGIcon icono3;
    FlatSVGIcon icono4;
    FlatSVGIcon icono5;
    FlatSVGIcon icono6;
    FlatSVGIcon icono7;
    FlatSVGIcon icono8;
    FlatSVGIcon icono9;

    protected JButton boto1;
    protected JButton boto2;
    protected JButton boto3;
    protected JButton boto4;
    protected JButton boto5;
    protected JButton boto6;
    protected JButton boto7;
    protected JButton boto8;
    protected JButton boto9;
    protected JButton boto0;

    protected JButton botoClear;
    protected JButton botoIgual;
    protected JButton botoSumar;
    protected JButton botoRestar;
    protected JButton botoMultiplicar;
    protected JButton botoDividir;

    protected JTextArea textAreaResultat;
    protected JTextArea textAreaHistorial;

    private void cargarImagenes() {
        icono1 = new FlatSVGIcon("icons/1.svg");
        icono2 = new FlatSVGIcon("icons/2.svg");
        icono3 = new FlatSVGIcon("icons/3.svg");
        icono4 = new FlatSVGIcon("icons/4.svg");
        icono5 = new FlatSVGIcon("icons/5.svg");
        icono6 = new FlatSVGIcon("icons/6.svg");
        icono7 = new FlatSVGIcon("icons/7.svg");
        icono8 = new FlatSVGIcon("icons/8.svg");
        icono9 = new FlatSVGIcon("icons/9.svg");
    }

    private void inicializarBotones() {

        boto1 = new JButton("1", icono1);
        boto2 = new JButton("2", icono2);
        boto3 = new JButton("3", icono3);
        boto4 = new JButton("4", icono4);
        boto5 = new JButton("5", icono5);
        boto6 = new JButton("6", icono6);
        boto7 = new JButton("7", icono7);
        boto8 = new JButton("8", icono8);
        boto9 = new JButton("9", icono9);
        boto0 = new JButton("0");
        botoClear = new JButton("Clear");
        botoIgual = new JButton("=");
        botoSumar = new JButton("+");
        botoRestar = new JButton("-");
        botoMultiplicar = new JButton("*");
        botoDividir = new JButton("/");

        boto1.setName("boto1");
        boto2.setName("boto2");
        boto3.setName("boto3");
        boto4.setName("boto4");
        boto5.setName("boto5");
        boto6.setName("boto6");
        boto7.setName("boto7");
        boto8.setName("boto8");
        boto9.setName("boto9");
        boto0.setName("boto0");

        botoClear.setName("botoClear");
        botoIgual.setName("botoIgual");
        botoSumar.setName("botoSumar");
        botoRestar.setName("botoRestar");
        botoMultiplicar.setName("botoMultiplicar");
        botoDividir.setName("botoDividir");
    }

    public CalculadoraVisual() {
        cargarImagenes();
        inicializarBotones();

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
        // cream un grid. //6 //1
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

    }

    private void configureFila6(JPanel g) {

        g.add(botoClear);
        g.add(boto0);

        g.add(botoIgual);

        g.add(botoSumar);

    }

    private void configureFila5(JPanel g) {
        boto1.setFont(new Font("Arial", Font.PLAIN, 0));

        boto2.setFont(new Font("Arial", Font.PLAIN, 0));

        boto3.setFont(new Font("Arial", Font.PLAIN, 0));

        g.add(boto1);
        g.add(boto2);
        g.add(boto3);
        g.add(botoRestar);

    }

    private void configureFila4(JPanel g) {

        boto4.setFont(new Font("Arial", Font.PLAIN, 0));

        boto5.setFont(new Font("Arial", Font.PLAIN, 0));

        boto6.setFont(new Font("Arial", Font.PLAIN, 0));

        g.add(boto4);
        g.add(boto5);
        g.add(boto6);
        g.add(botoMultiplicar);

    }

    private void configureFila3(JPanel g) {

        boto7.setFont(new Font("Arial", Font.PLAIN, 0));

        boto8.setFont(new Font("Arial", Font.PLAIN, 0));

        boto9.setFont(new Font("Arial", Font.PLAIN, 0));

        g.add(boto7);
        g.add(boto8);
        g.add(boto9);
        g.add(botoDividir);

    }

    private void configureFila2(JPanel g, JTextArea text) {
        // set text del text area
        text.setText("0");
        // el text area no es editable
        text.setEditable(false);
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

    // busqueda On ya que los botones son pocoas y hacer mapas está feo
    public JButton getButton(String name) {
        return findButton(this.getContentPane(), name);
    }

    private JButton findButton(Container container, String name) {
        for (Component c : container.getComponents()) {
            if (name.equals(c.getName()))
                return (JButton) c;
            if (c instanceof Container) {
                JButton found = findButton((Container) c, name);
                if (found != null)
                    return found;
            }
        }
        return null;
    }

    public JTextArea getTextArea(String s) {
        for (Component c : this.getComponents()) {
            if (c instanceof JTextArea && s.equals(c.getName())) {
                return (JTextArea) c;
            }
        }
        return null;
    }

}
