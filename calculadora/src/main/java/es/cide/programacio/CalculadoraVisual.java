package es.cide.programacio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;

public class CalculadoraVisual extends JFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 800;
    private static final int ROWS = 6;
    private static final int COLS = 1;

    // *** ICONOS ***/
    FlatSVGIcon icono1;
    FlatSVGIcon icono2;
    FlatSVGIcon icono3;
    FlatSVGIcon icono4;
    FlatSVGIcon icono5;
    FlatSVGIcon icono6;
    FlatSVGIcon icono7;
    FlatSVGIcon icono8;
    FlatSVGIcon icono9;

    // *** BOTONES ***/
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

    // *** TEXT ***/
    JTextField textResultat;
    JTextField textHistorial;

    JPanel grid;
    JPanel fila1;
    JPanel fila2;
    JPanel fila3;
    JPanel fila4;
    JPanel fila5;
    JPanel fila6;

    public CalculadoraVisual() {
        // cargamos las imagenes
        cargarImagenes();
        // inicializamos los botones
        inicializarBotones();

        // ***VENTANA***/
        // titulo
        this.setTitle("Calculadora - Nico Rueda");
        // tamaño
        this.setSize(WIDTH, HEIGHT);
        // comportamiento
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // posicion
        this.setLocationRelativeTo(null);

        // ***GRID***/
        // creamos un grid de 6 gilas
        grid = new JPanel(new GridLayout(ROWS, COLS, 15, 15));
        // ceamos empty border
        EmptyBorder emptyBorder = new EmptyBorder(10, 10, 10, 10);
        // añadimos el borde
        grid.setBorder(emptyBorder);

        // creamos los subgrids
        fila1 = new JPanel(new GridLayout(1, 1));
        fila2 = new JPanel(new GridLayout(1, 1));
        fila3 = new JPanel(new GridLayout(1, 4));
        fila4 = new JPanel(new GridLayout(1, 4));
        fila5 = new JPanel(new GridLayout(1, 4));
        fila6 = new JPanel(new GridLayout(1, 4));

        // inicializamos los subrids
        inicializarFila1(fila1);
        inicializarFila2(fila2);
        inicializarFila3(fila3);
        inicializarFila4(fila4);
        inicializarFila5(fila5);
        inicializarFila6(fila6);

        // ***GRID PADRE***/
        // añadimos los subrids al padre
        grid.add(fila1);
        grid.add(fila2);
        grid.add(fila3);
        grid.add(fila4);
        grid.add(fila5);
        grid.add(fila6);

        // añadimos el grid padre a la ventana
        this.add(grid);

        // hacemos la ventana visible
        this.setVisible(true);
        // this.setResizable(false);

        // cambiamos el icono de la aplicacion (no me lo pidoó nadie)
        ImageIcon icono = new ImageIcon(getClass().getResource("/icono.png"));
        this.setIconImage(icono.getImage());

    }

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

        // le asignamso un texto a los botones para saber que hacer en su action
        // listener
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

        // el texto no es visible con el size 0
        boto1.setFont(new Font("Arial", Font.PLAIN, 0));
        boto2.setFont(new Font("Arial", Font.PLAIN, 0));
        boto3.setFont(new Font("Arial", Font.PLAIN, 0));
        boto4.setFont(new Font("Arial", Font.PLAIN, 0));
        boto5.setFont(new Font("Arial", Font.PLAIN, 0));
        boto6.setFont(new Font("Arial", Font.PLAIN, 0));
        boto7.setFont(new Font("Arial", Font.PLAIN, 0));
        boto8.setFont(new Font("Arial", Font.PLAIN, 0));
        boto9.setFont(new Font("Arial", Font.PLAIN, 0));

        // les asignamos un nombre para el get
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

        // hacemos que los botones no sean focusables para no tener problemas con el
        // enter

        boto1.setFocusable(false);
        boto2.setFocusable(false);
        boto3.setFocusable(false);
        boto4.setFocusable(false);
        boto5.setFocusable(false);
        boto6.setFocusable(false);
        boto7.setFocusable(false);
        boto8.setFocusable(false);
        boto9.setFocusable(false);
        boto0.setFocusable(false);
        botoClear.setFocusable(false);
        botoIgual.setFocusable(false);
        botoSumar.setFocusable(false);
        botoRestar.setFocusable(false);
        botoMultiplicar.setFocusable(false);
        botoDividir.setFocusable(false);

        textResultat = new JTextField();
        textHistorial = new JTextField();
        // les asignamos un nombre para el get
        textResultat.setName("textAreaResultat");
        textHistorial.setName("textAreaHistorial");

        textHistorial.setBackground(new Color(100, 100, 100));
        textResultat.setBackground(new Color(100, 100, 100));
    }

    private void inicializarFila6(JPanel g) {

        g.add(botoClear);
        g.add(boto0);
        g.add(botoIgual);
        g.add(botoSumar);

    }

    private void inicializarFila5(JPanel g) {

        g.add(boto1);
        g.add(boto2);
        g.add(boto3);
        g.add(botoRestar);

    }

    private void inicializarFila4(JPanel g) {

        g.add(boto4);
        g.add(boto5);
        g.add(boto6);
        g.add(botoMultiplicar);

    }

    private void inicializarFila3(JPanel g) {

        g.add(boto7);
        g.add(boto8);
        g.add(boto9);
        g.add(botoDividir);

    }

    private void inicializarFila2(JPanel g) {

        textResultat.setText("0");
        // el text no es editable
        textResultat.setEditable(false);

        textResultat.setColumns(40);
        // fuente
        textResultat.setFont(new Font("Arial", Font.PLAIN, 45));
        g.add(textResultat);
    }

    private void inicializarFila1(JPanel g) {

        textHistorial.setText("");
        // el text area no es editable
        textHistorial.setEditable(false);

        textHistorial.setColumns(40);
        // fuente
        textHistorial.setFont(new Font("Arial", Font.PLAIN, 45));
        g.add(textHistorial);
    }

    // busqueda ya que los botones son pocos y hacer mapas está feo
    public JButton getButton(String s) {
        JButton boton = null;

        // guardamos las filas en un array
        Component[] filas = grid.getComponents();
        int i = 0;

        // recorremos las filas del grid
        while (i < filas.length && boton == null) {
            if (filas[i] instanceof Container) {
                // guardamos los componentes de la fila en un array
                Component[] componentesFila = ((Container) filas[i]).getComponents();
                int j = 0;

                // recorremos cada componente de la fila
                while (j < componentesFila.length && boton == null) {
                    Component c = componentesFila[j];

                    if (s.equals(c.getName())) {
                        boton = (JButton) c;
                    }
                    j++;
                }
            }
            i++;
        }

        return boton;

    }

    public JTextField getTextArea(String s) {
        JTextField texto = null;

        // guardamos las filas del grid en un array
        Component[] filas = grid.getComponents();

        int i = 0;
        // recorremos cada fila del grid
        while (i < filas.length && texto == null) {
            if (filas[i] instanceof Container) {
                Component[] componentesFila = ((Container) filas[i]).getComponents();
                int j = 0;

                // recorremos cad acomponente del grid
                while (j < componentesFila.length && texto == null) {
                    Component c = componentesFila[j];

                    // miramos si el nombre es el que buscamos
                    if (s.equals(c.getName())) {
                        texto = (JTextField) c;
                    }
                    j++;
                }
            }
            i++;
        }

        return texto;
    }

}
