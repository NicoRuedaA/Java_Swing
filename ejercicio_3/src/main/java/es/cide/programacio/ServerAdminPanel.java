package es.cide.programacio;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.text.JTextComponent;

import java.awt.*;

public class ServerAdminPanel extends JFrame {

    public ServerAdminPanel() {
        // ***VENTANA***/
        // titol
        this.setTitle("Tauler d'administració - Nico Rueda");
        // tamany
        this.setSize(800, 600);
        // comportament
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // posicio
        this.setLocationRelativeTo(null);

        // ***GRID***/
        // creamos un grid.
        JPanel grid = new JPanel(new GridLayout(2, 2, 15, 15));
        // ceamos y añadimos empty border
        EmptyBorder emptyBorder = new EmptyBorder(10, 10, 10, 10);
        grid.setBorder(emptyBorder);

        // creamos los subgrids
        JPanel subGridTopLeft = new JPanel(new GridLayout(2, 1));
        JPanel subGridTopRight = new JPanel(new GridLayout(3, 1, 0, 10));
        JPanel subGridBotLeft = new JPanel(new GridLayout(4, 1));
        JPanel subGridBotRight = new JPanel(new GridLayout(1, 1));

        // ***SUBGRID TOP LEFT***/
        configureGrid1(subGridTopLeft);
        // ***SUBGRID TOP RIGHT***/
        configureGrid2(subGridTopRight, emptyBorder);
        // ***SUBGRID BOT LEFT***/
        configureGrid3(subGridBotLeft);
        // ***SUBGRID BOT RIGHT***/
        configureGrid4(subGridBotRight);

        // ***GRID PADRE***/
        // añadimos los subgrids al grid ""padre""
        grid.add(subGridTopLeft);
        grid.add(subGridTopRight);
        grid.add(subGridBotLeft);
        grid.add(subGridBotRight);

        // ***AÑADIMOS EL GRID A LA VENTANA***/
        this.add(grid);

        // hacemos visible el jFRame
        this.setVisible(true);
        // importamos un tema
        this.setTheme("javax.swing.plaf.nimbus.NimbusLookAndFeel");

    }

    private void configureGrid4(JPanel g) {
        // cream titol
        setTitledBorder("Logs del Servidor en Viu", g);
        g.setLayout(new BorderLayout());
        JTextArea textAreaBotttomRight = new JTextArea();
        textAreaBotttomRight.setText("[Info] Servei iniciat.\n" +
                "[Warning] Connexió lenta IP 192.\n" +
                "[INFO] Base de dades connectada.\n" +
                "[ERROR] Fallada en el modul d'avaluacio (intent1).\n" +
                "...");
        textAreaBotttomRight.setEditable(false);
        textAreaBotttomRight.setFont((new Font("Monospaced", Font.PLAIN, 12)));

        // SCROLL VERTICAL
        textAreaBotttomRight.setLineWrap(true);
        textAreaBotttomRight.setWrapStyleWord(true);

        JScrollPane scrollPaneBottomRight = new JScrollPane(textAreaBotttomRight);

        g.add(scrollPaneBottomRight, BorderLayout.CENTER);
    }

    private void configureGrid3(JPanel g) {
        // cream titol
        setTitledBorder("Paràmetres de Càrrega", g);
        JLabel textBotLeft1 = new JLabel();
        textBotLeft1.setText("Límit de Connexions (0-500):");
        g.add(textBotLeft1);

        JSlider sliderBotLeft1 = new JSlider(0, 500, 250);
        // numeros cada 50
        sliderBotLeft1.setMajorTickSpacing(50);
        // raya cada 10
        sliderBotLeft1.setMinorTickSpacing(10);
        // dibujamos las rayas debajo del slider
        sliderBotLeft1.setPaintTicks(true);

        // dibujamos los numeros debajo del slider
        sliderBotLeft1.setPaintLabels(true);
        g.add(sliderBotLeft1);

        JPanel subGrid = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField textBotLeft3 = new JTextField(35);
        textBotLeft3.setText("60");
        subGrid.add(textBotLeft3);

        g.add(subGrid);
    }

    private void configureGrid2(JPanel g, EmptyBorder b) {
        // cream titol
        TitledBorder titulo = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1),
                "Accions Ràpides",
                TitledBorder.LEFT, TitledBorder.TOP);
        g.setBorder(titulo);
        // creamos 4 botones
        JButton bottoTopRight1 = new JButton("▶ Iniciar Servei");
        JButton bottoTopRight2 = new JButton("■ Aturar Servei");
        JButton bottoTopRight3 = new JButton("↻ Reiniciar");
        // añadimos los 4 botones
        g.add(bottoTopRight1);
        g.add(bottoTopRight2);
        g.add(bottoTopRight3);
        b = new EmptyBorder(50, 100, 100, 100);
        g.setBorder(BorderFactory.createCompoundBorder(titulo, b));
    }

    private void configureGrid1(JPanel g) {
        // cream titol
        setTitledBorder("Estat del Sistema", g);
        // creamos lineas de texto
        JLabel textTopLeft1 = new JLabel();
        textTopLeft1.setText("CPU: 15% Ús");
        JLabel textTopLeft2 = new JLabel();
        textTopLeft2.setText("RAM: 4GB / 16GB");
        // centramos las fuentes y las ponemos en negrita
        setFontCentered(textTopLeft1);
        setFontCentered(textTopLeft2);
        // añadimos lineas de texto
        g.add(textTopLeft1);
        g.add(textTopLeft2);
    }

    private void setFontCentered(JLabel text) {
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setFont(new Font("Arial", Font.BOLD, 18));
    }

    private void setTitledBorder(String s, JPanel panel) {
        TitledBorder titulo = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1), s,
                TitledBorder.LEFT, TitledBorder.TOP);
        panel.setBorder(titulo);
    }

    private void setTheme(String s) {
        try {
            UIManager.setLookAndFeel(s);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServerAdminPanel finestra = new ServerAdminPanel();
    }

}
