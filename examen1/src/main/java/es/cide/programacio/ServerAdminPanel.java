package es.cide.programacio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.w3c.dom.Text;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.*;
import java.awt.event.AdjustmentListener;
import java.awt.event.FocusListener;

public class ServerAdminPanel extends JFrame {

    public static void main(String[] args) {
        // cream un objete tipus ServerAdminPanel
        FlatLightLaf.setup();
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
        JPanel grid = new JPanel();
        grid.setLayout(new BoxLayout(grid, BoxLayout.Y_AXIS));
        grid.setBorder(new EmptyBorder(15, 15, 15, 15));
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
        textVocal.setText("Número de vocales: 0");
        g.add(textVocal);
    }

    private void configureGrid8(JPanel g) {
        JButton contarButton = new JButton("Contar Vocales");
        contarButton.addActionListener(e -> contarVocales());
        g.add(contarButton);
    }

    int numeroVocales;

    public void contarVocales() {
        numeroVocales = 0;
        // ACTUALIZACIÓN: Leemos el texto actual del JTextArea antes de contar
        titulo = textTitulo.getText().toLowerCase();

        for (int i = 0; i < titulo.length(); i++) {
            if (titulo.charAt(i) == (vocalAcontar.charAt(0))) {
                numeroVocales++;
            }
        }
        textVocal.setText("Número de vocales: " + numeroVocales);
    }

    String vocalAcontar = "a";

    private void configureGrid7(JPanel g) {
        JLabel text = new JLabel("Selecciona una vocal: ");
        Character[] vocales = { 'a', 'e', 'i', 'o', 'u' };
        JComboBox<Character> comboBox = new JComboBox<>(vocales);

        comboBox.addActionListener(e -> cambiarVocal(comboBox.getSelectedItem().toString()));

        g.add(text);
        g.add(comboBox);
    }

    public void cambiarVocal(String a) {
        vocalAcontar = a;
    }

    JLabel textFicha = new JLabel();

    private void configureGrid6(JPanel g) {
        textFicha.setText(" "); // Espacio inicial
        g.add(textFicha);
    }

    private void configureGrid5(JPanel g) {
        JButton mostraButton = new JButton("Mostrar Ficha:");
        mostraButton.addActionListener(e -> mostrarFicha());
        g.add(mostraButton);
    }

    public void mostrarFicha() {
        titulo = textTitulo.getText();
        textFicha.setText("Película: " + titulo + " | Género: " + genero + " | Año: " + anno);
    }

    JLabel annoSeleccionadoText = new JLabel();

    private void configureGrid4(JPanel g) {
        JLabel textTopLeft2 = new JLabel("Año seleccionado: ");
        annoSeleccionadoText.setText("2000");
        g.add(textTopLeft2);
        g.add(annoSeleccionadoText);

    }

    String anno = "2000";

    private void configureGrid3(JPanel g) {
        JLabel textBotLeft1 = new JLabel("Año de estreno: ");
        JSlider slider = new JSlider(1900, 2025, 2000);

        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setFont(new Font("SansSerif", Font.PLAIN, 10));

        slider.addChangeListener(e -> cambiarAnno(slider.getValue()));

        g.add(textBotLeft1);
        g.add(slider);

    }

    public void cambiarAnno(int s) {
        anno = Integer.toString(s);
        annoSeleccionadoText.setText(anno);
    }

    String genero = "Acción";

    private void configureGrid2(JPanel g, EmptyBorder b) {
        // cream Jlabel
        JLabel textTopLeft1 = new JLabel("Género: ");
        JComboBox<String> comboBox = new JComboBox<>(
                new String[] { "Acción", "Comedia", "Drama", "Terror", "Ciencia Ficción" });

        comboBox.addActionListener(e -> cambiarGenero(comboBox.getSelectedItem().toString()));

        g.add(textTopLeft1);
        g.add(comboBox);
    }

    public void cambiarGenero(String s) {
        genero = s;
    }

    JTextArea textTitulo = new JTextArea();
    String titulo = "Star Wars: A new hope";

    private void configureGrid1(JPanel g) {
        JLabel textArea = new JLabel("Título de la película: ");
        textTitulo.setText(titulo);

        // Un pequeño borde para que parezca un campo de texto
        textTitulo.setBorder(UIManager.getBorder("TextField.border"));

        g.add(textArea);
        g.add(textTitulo);

    }

    public void cambiarTitulo(String s) {
        titulo = s;
    }

}
