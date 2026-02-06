package es.cide.programacio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.w3c.dom.Text;

import java.awt.*;
import java.awt.event.AdjustmentListener;
import java.awt.event.FocusListener;

public class ServerAdminPanel extends JFrame {

    public static void main(String[] args) {
        // cream un objete tipus ServerAdminPanel
        ServerAdminPanel finestra = new ServerAdminPanel();
    }

    // constructor tipo ServerAdminPanel
    public ServerAdminPanel() {
        // ***VENTANA***/
        // titol
        this.setTitle("Ficha de Película");
        // tamany
        this.setSize(400, 400);
        // comportament
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // posicio
        this.setLocationRelativeTo(null);

        // ***GRID***/
        // cream un grid.
        JPanel grid = new JPanel(new GridLayout(9, 1, 15, 15));
        // ceamos empty border
        EmptyBorder emptyBorder = new EmptyBorder(10, 10, 10, 10);
        // afegim empty border
        grid.setBorder(emptyBorder);

        // cream 4 subrids
        JPanel subGridTopLeft = new JPanel(new GridLayout(1, 2));
        JPanel subGridTopRight = new JPanel(new GridLayout(1, 2));
        JPanel subGridBotLeft = new JPanel(new GridLayout(1, 2));
        JPanel subGridBotRight = new JPanel(new GridLayout(1, 2));
        JPanel a = new JPanel(new GridLayout(1, 1));
        JPanel b = new JPanel(new GridLayout(1, 1));
        JPanel c = new JPanel(new GridLayout(2, 1));
        JPanel d = new JPanel(new GridLayout(1, 1));
        JPanel e = new JPanel(new GridLayout(1, 1));

        // ***SUBGRID TOP LEFT***/
        configureGrid1(subGridTopLeft);
        // ***SUBGRID TOP RIGHT***/
        configureGrid2(subGridTopRight, emptyBorder);
        // ***SUBGRID BOT LEFT***/
        configureGrid3(subGridBotLeft);
        // ***SUBGRID BOT RIGHT***/
        configureGrid4(subGridBotRight);
        configureGrid5(a);
        configureGrid6(b);
        configureGrid7(c);
        configureGrid8(d);
        configureGrid9(e);

        // ***GRID PADRE***/
        // afegim els subgrids al grid "pare"
        grid.add(subGridTopLeft);
        grid.add(subGridTopRight);
        grid.add(subGridBotLeft);
        grid.add(subGridBotRight);
        grid.add(a);
        grid.add(b);
        grid.add(c);
        grid.add(d);
        grid.add(e);

        // afegim el grid a la finestra
        this.add(grid);

        // feim visible el Jframe
        this.setVisible(true);
        this.setResizable(false);
        // importam un tema
        // si limportam l'efecte del slider no funcionara
        // this.setTheme("javax.swing.plaf.nimbus.NimbusLookAndFeel");

    }

    JLabel textVocal = new JLabel();

    private void configureGrid9(JPanel g) {

        // Assignam texte al Jlabel
        textVocal.setText("Número de vocales: ");

        g.add(textVocal);
    }

    private void configureGrid8(JPanel g) {
        JButton contarButton = new JButton("Contar Vocales:");
        contarButton.addActionListener(e -> contarVocales());
        g.add(contarButton);
    }

    int numeroVocales;

    public void contarVocales() {
        numeroVocales = 0;

        for (int i = 0; i < titulo.length(); i++) {

            if (titulo.charAt(i) == (vocalAcontar.charAt(0))) {
                System.out.println((vocalAcontar.charAt(0)));
                numeroVocales++;
            }
        }

        textVocal.setText("Número de vocales: " + numeroVocales);

    }

    String vocalAcontar;

    private void configureGrid7(JPanel g) {
        JLabel text = new JLabel("Selecciona una vocal:");
        JComboBox<Character> comboBox = new JComboBox<>();

        comboBox.addItem('a');
        comboBox.addItem('e');
        comboBox.addItem('i');
        comboBox.addItem('o');
        comboBox.addItem('u');

        // botoClear.addActionListener(e -> registrarAccion(" "));
        comboBox.addActionListener(e -> cambiarVocal(comboBox.getSelectedItem().toString()));

        g.add(text);
        g.add(comboBox);
    }

    public void cambiarVocal(String a) {
        vocalAcontar = a;
    }

    JLabel textFicha = new JLabel();

    private void configureGrid6(JPanel g) {

        // Pel·lícula: [Títol] | Gènere: [Gènere] | Any: [Any]

        // Assignam texte al Jlabel

        g.add(textFicha);
    }

    private void configureGrid5(JPanel g) {
        JButton mostraButton = new JButton("Mostrar Ficha:");
        mostraButton.addActionListener(e -> mostrarFicha());
        g.add(mostraButton);
    }

    public void mostrarFicha() {
        textFicha.setText("Película: " + titulo + " Género: " + genero + " Año: " + anno);
        annoSeleccionadoText.setText(anno);
    }

    int annoSeleccionado = 2000;
    JLabel annoSeleccionadoText = new JLabel();

    private void configureGrid4(JPanel g) {

        // cream Jlabel

        JLabel textTopLeft2 = new JLabel();

        // Assignam texte al Jlabel
        textTopLeft2.setText("Año seleccionado:");
        annoSeleccionadoText.setText("2000");

        // afegim els JLabel al JaPanel
        g.add(textTopLeft2);
        g.add(annoSeleccionadoText);

    }

    String anno;

    private void configureGrid3(JPanel g) {

        // cream JLabel
        JLabel textBotLeft1 = new JLabel();
        // cream text
        textBotLeft1.setText("Año de estreno:");
        // afegim borde al text

        // cream un Sliders
        JSlider sliderBotLeft1 = new JSlider(1900, 2025);

        sliderBotLeft1.setMajorTickSpacing(25);

        sliderBotLeft1.setMinorTickSpacing(5);
        // dibuixam ses retxes
        sliderBotLeft1.setPaintTicks(true);
        // dibuixam els numeros
        sliderBotLeft1.setPaintLabels(true);

        sliderBotLeft1.setFont(new Font("Monospaced", 0, 10));
        sliderBotLeft1.addChangeListener(e -> cambiarAnno(sliderBotLeft1.getValue()));

        // sliderBotLeft1.addAdjustmentListener(e -> cambiarString(" "));

        // afegim els components
        g.add(textBotLeft1);
        g.add(sliderBotLeft1);

    }

    public void cambiarAnno(int s) {
        anno = Integer.toString(s);
    }

    String genero;

    private void configureGrid2(JPanel g, EmptyBorder b) {
        // cream Jlabel
        JLabel textTopLeft1 = new JLabel();
        JComboBox<String> comboBox = new JComboBox<>();

        // Assignam texte al Jlabel
        textTopLeft1.setText("Género:");

        comboBox.addItem("Acción");
        comboBox.addItem("Comedia");
        comboBox.addItem("Drama");
        comboBox.addItem("Terror");
        comboBox.addItem("Ciencia Ficción");

        comboBox.addActionListener(e -> cambiarGenero(comboBox.getSelectedItem().toString()));

        // afegim els JLabel al JaPanel
        g.add(textTopLeft1);
        g.add(comboBox);
    }

    public void cambiarGenero(String s) {
        genero = s;
    }

    JTextArea textTitulo = new JTextArea();
    String titulo = "Star Wars: A new hope";

    private void configureGrid1(JPanel g) {
        // cream Jlabel

        JLabel textArea = new JLabel();

        // Assignam texte al Jlabel
        textArea.setText("Títol de la película:");
        textTitulo.setText(
                "Star Wars: A new hope");

        // textTitulo.addFocusListener(null);

        // afegim els JLabel al JaPanel
        g.add(textArea);
        g.add(textTitulo);

    }

    public void cambiarTitulo(String s) {
        titulo = s;
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

}
