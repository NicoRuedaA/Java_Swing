package es.cide.programacio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ServerAdminPanel extends JFrame {

    public static void main(String[] args) {
        // cream un objete tipus ServerAdminPanel
        ServerAdminPanel finestra = new ServerAdminPanel();
    }

    // constructor tipo ServerAdminPanel
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
        // cream un grid.
        JPanel grid = new JPanel(new GridLayout(2, 2, 15, 15));
        // ceamos empty border
        EmptyBorder emptyBorder = new EmptyBorder(10, 10, 10, 10);
        // afegim empty border
        grid.setBorder(emptyBorder);

        // cream 4 subrids
        JPanel subGridTopLeft = new JPanel(new GridLayout(2, 1));
        JPanel subGridTopRight = new JPanel(new GridLayout(3, 1, 0, 10));
        JPanel subGridBotLeft = new JPanel(new GridLayout(4, 1, 1000, 0));
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
        // afegim els subgrids al grid "pare"
        grid.add(subGridTopLeft);
        grid.add(subGridTopRight);
        grid.add(subGridBotLeft);
        grid.add(subGridBotRight);

        // afegim el grid a la finestra
        this.add(grid);

        // feim visible el Jframe
        this.setVisible(true);
        // importam un tema
        // si limportam l'efecte del slider no funcionara
        // this.setTheme("javax.swing.plaf.nimbus.NimbusLookAndFeel");

    }

    private void configureGrid4(JPanel g) {
        // cream titol
        setTitledBorder("Logs del Servidor en Viu", g);
        // cream text area
        JTextArea textAreaBotttomRight = new JTextArea();
        // set text del text area
        textAreaBotttomRight.setText("[Info] Servei iniciat.\n" +
                "[Warning] Connexió lenta IP 192.\n" +
                "[INFO] Base de dades connectada.\n" +
                "[ERROR] Fallada en el modul d'avaluacio (intent 1).\n" +
                "...");
        // el text area no es editable
        textAreaBotttomRight.setEditable(false);
        // establim sa font
        textAreaBotttomRight.setFont((new Font("Monospaced", Font.PLAIN, 10)));
        // numero de columnes i files
        textAreaBotttomRight.setRows(20);
        textAreaBotttomRight.setColumns(40);
        // cream un JScroll panel. Afegim el Text area
        JScrollPane scrollPaneBottomRight = new JScrollPane(textAreaBotttomRight);
        // afegim el scroll panel
        g.add(scrollPaneBottomRight, BorderLayout.CENTER);
    }

    private void configureGrid3(JPanel g) {

        // cream titol
        setTitledBorder("Paràmetres de Càrrega", g);

        // cream JLabel
        JLabel textBotLeft1 = new JLabel();
        // cream text
        textBotLeft1.setText("Límit de Connexions (0-500):");
        // afegim borde al text

        // cream un Sliders
        JSlider sliderBotLeft1 = new JSlider(0, 500, 250);
        // numeros cada 50
        sliderBotLeft1.setMajorTickSpacing(50);
        // retxa cada 10
        sliderBotLeft1.setMinorTickSpacing(10);
        // dibuixam ses retxes
        sliderBotLeft1.setPaintTicks(true);
        // dibuixam els numeros
        sliderBotLeft1.setPaintLabels(true);

        // Cream JLabel
        JLabel textBotLeft2 = new JLabel();
        // titol
        textBotLeft2.setText("Timeout (segons)");
        // borde

        // cream JPanel
        JPanel subGrid = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // cram text
        JTextField textBotLeft3 = new JTextField(30);
        // establim text
        textBotLeft3.setText("60");
        // afegim text
        subGrid.add(textBotLeft3);

        // cream epmty border
        EmptyBorder empty = new EmptyBorder(10, 10, 10, 10);
        // afegim borde
        sliderBotLeft1.setBorder(empty);
        textBotLeft1.setBorder(empty);
        textBotLeft2.setBorder(empty);
        textBotLeft3.setBorder(empty);

        // moficam ses fonts
        textBotLeft1.setFont(new Font("Arial", Font.BOLD, 12));
        textBotLeft2.setFont(new Font("Arial", Font.BOLD, 12));

        // afegim els components
        g.add(textBotLeft1);
        g.add(sliderBotLeft1);
        g.add(textBotLeft2);
        g.add(subGrid);
    }

    private void configureGrid2(JPanel g, EmptyBorder b) {
        // cream titol-border
        TitledBorder titulo = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1),
                "Accions Ràpides",
                TitledBorder.LEFT, TitledBorder.TOP);
        // aplicam el border
        g.setBorder(titulo);
        // creamos 4 botones

        // cream 3 botons
        JButton bottoTopRight1 = new JButton("▶ Iniciar Servei");
        JButton bottoTopRight2 = new JButton("■ Aturar Servei");
        JButton bottoTopRight3 = new JButton("↻ Reiniciar");
        // els afegim
        g.add(bottoTopRight1);
        g.add(bottoTopRight2);
        g.add(bottoTopRight3);
        // cream un empty border
        b = new EmptyBorder(50, 100, 50, 100);
        // afegim el border compost del "titol-border" i el empty border
        g.setBorder(BorderFactory.createCompoundBorder(titulo, b));
    }

    private void configureGrid1(JPanel g) {

        // titol
        setTitledBorder("Estat del Sistema", g);
        // cream Jlabel
        JLabel textTopLeft1 = new JLabel();
        JLabel textTopLeft2 = new JLabel();

        // Assignam texte al Jlabel
        textTopLeft1.setText("CPU: 15% Ús");
        textTopLeft2.setText("RAM: 4GB / 16GB");

        // cream una font
        Font font = new Font("Arial", Font.BOLD, 18);
        // assignam la font
        textTopLeft1.setFont(font);
        textTopLeft2.setFont(font);

        // centram horizontalment
        textTopLeft1.setHorizontalAlignment(JLabel.CENTER);
        textTopLeft2.setHorizontalAlignment(JLabel.CENTER);
        // ""juntam"" visualment els Jlabel
        textTopLeft1.setVerticalAlignment(JLabel.BOTTOM);
        textTopLeft2.setVerticalAlignment(JLabel.TOP);

        // cream un empty border
        EmptyBorder empty = new EmptyBorder(10, 10, 10, 10);
        // assignam el empty border
        textTopLeft1.setBorder(empty);
        textTopLeft2.setBorder(empty);

        // afegim els JLabel al JaPanel
        g.add(textTopLeft1, BorderLayout.SOUTH);
        g.add(textTopLeft2, BorderLayout.NORTH);

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

}
