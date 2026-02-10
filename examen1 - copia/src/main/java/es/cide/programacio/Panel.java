package es.cide.programacio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentListener;

import org.w3c.dom.Text;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.*;
import java.awt.event.AdjustmentListener;
import java.awt.event.FocusListener;
import java.sql.Date;

import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.event.FocusListener;
import java.util.EventListener;
import java.io.ObjectStreamException;
import java.io.Serial;

public class Panel extends JFrame {

    Hotel hotelBlau = new Hotel("Hotel Blau", 3);
    Hotel hotelIlla = new Hotel("Hotel Illa", 10);
    Hotel hotelVall = new Hotel("Hotel Vall", 60);
    Hotel hotelSelecc = new Hotel(" ", 0);

    JLabel textVocal = new JLabel();
    JLabel labelGrid7 = new JLabel(
            " ");
    JTextField textGrid1 = new JTextField();

    String nom = "Luke Skywalker";
    String dni = "44444444A";
    String nomHotel = "Hotel Illa";

    JSlider sliderGrid5 = new JSlider(1, 15, 9);

    int numNits;

    public Panel() {

        // ***VENTANA***/
        // titol
        this.setTitle("Reserver d'Hotel");
        // tamany
        this.setSize(800, 800);
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

        // cream 7 subrids
        JPanel subGrid1 = new JPanel(new GridLayout(1, 2));
        JPanel subGrid2 = new JPanel(new GridLayout(1, 2));
        JPanel subGrid3 = new JPanel(new GridLayout(1, 2));
        JPanel subGrid4 = new JPanel(new GridLayout(1, 2));
        JPanel subGrid5 = new JPanel(new GridLayout(1, 1));
        JPanel subGrid6 = new JPanel(new GridLayout(1, 1));
        JPanel subGrid7 = new JPanel(new GridLayout(1, 1));

        // ***CONFIGURAM ELS SUBRIDS***/
        configureGrid1(subGrid1);
        configureGrid2(subGrid2, emptyBorder);
        configureGrid3(subGrid3);

        configureGrid4(subGrid4);
        configureGrid5(subGrid5);
        configureGrid6(subGrid6);
        configureGrid7(subGrid7);

        // afegim els subgrids al grid "pare"
        grid.add(subGrid1);
        grid.add(subGrid2);
        grid.add(subGrid3);
        grid.add(subGrid4);
        grid.add(subGrid5);
        grid.add(subGrid6);
        grid.add(subGrid7);

        // afegim el grid pare a la finestra
        this.add(grid);

        // feim visible el Jframe
        this.setVisible(true);
        this.setResizable(false);

    }

    private void configureGrid7(JPanel g) {

        g.add(labelGrid7);

    }

    JLabel textFicha = new JLabel();

    private void configureGrid6(JPanel g) {
        /*
         * textFicha.setText(" "); // Espacio inicial
         * g.add(textFicha);
         */
        JButton botoGrid6 = new JButton("Guardar reserva");

        botoGrid6.addActionListener(e -> mostrarFicha());

        g.add(botoGrid6);

    }

    private void configureGrid5(JPanel g) {

        sliderGrid5.setMajorTickSpacing(1);
        sliderGrid5.setMinorTickSpacing(1);
        sliderGrid5.setPaintTicks(true);
        sliderGrid5.setPaintLabels(true);
        sliderGrid5.setFont(new Font("SansSerif", Font.PLAIN, 10));

        numNits = sliderGrid5.getValue();

        sliderGrid5.addChangeListener(e -> cambiarNits(sliderGrid5.getValue()));

        g.add(sliderGrid5);
        /*
         * JButton mostraButton = new JButton("Mostrar Ficha:");
         * mostraButton.addActionListener(e -> mostrarFicha());
         * g.add(mostraButton);
         */
    }

    // JLabel hotelSelec = new JLabel();

    private void configureGrid4(JPanel g) {
        JLabel labelGrid4 = new JLabel("Hotel: ");

        JComboBox<String> comboBoxGrid4 = new JComboBox<>(
                new String[] { hotelIlla.getNom(), hotelBlau.getNom(), hotelVall.getNom() });

        comboBoxGrid4.addActionListener(e -> cambiarSlider(comboBoxGrid4.getSelectedItem().toString()));

        g.add(labelGrid4);
        g.add(comboBoxGrid4);

    }

    String data = "07/03/2025";

    private void configureGrid3(JPanel g) {

        JLabel labelGrid3 = new JLabel("Data Check-in: (dd/MM/yyyy)");
        JTextField textGrid3 = new JTextField(data);
        textGrid3.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarLabel(textGrid3.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarLabel(textGrid3.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarLabel(textGrid3.getText());
            }

            // Método auxiliar para no repetir código
            private void actualizarLabel(String s) {
                if (s.equals("")) {
                    errorData(1);
                } else if (!esDataAnterior(s)) {
                    errorData(2);

                }

            }
        });

        /*
         * textGrid3.addFocusListener(new FocusListener() {
         * 
         * @Override
         * public void focusGained(FocusEvent e) {
         * 
         * }
         * 
         * @Override
         * public void focusLost(FocusEvent e) {
         * 
         * }
         * });
         */

        g.add(labelGrid3);
        g.add(textGrid3);
    }

    private void configureGrid2(JPanel g, EmptyBorder b) {
        // cream Jlabel
        JLabel label1Grid2 = new JLabel("DNI: ");
        JPanel panelGrid2 = new JPanel(new GridLayout(1, 2));

        JTextField textGrid2 = new JTextField(dni);
        JLabel label2Grid2 = new JLabel(" ");

        textGrid2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarLabel(textGrid2.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarLabel(textGrid2.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarLabel(textGrid2.getText());
            }

            // Método auxiliar para no repetir código
            private void actualizarLabel(String s) {
                if (!validarDNI(s)) {
                    label2Grid2.setText("Error");

                } else
                    label2Grid2.setText("OK");
            }

        });

        panelGrid2.add(textGrid2);
        panelGrid2.add(label2Grid2);

        g.add(label1Grid2);
        g.add(panelGrid2);
        // g.add(label2Grid2);

    }

    private void configureGrid1(JPanel g) {
        JLabel labelGrid1 = new JLabel("Nom: ");
        textGrid1.setText(nom);

        // petit borde
        textGrid1.setBorder(UIManager.getBorder("TextField.border"));

        g.add(labelGrid1);
        g.add(textGrid1);

    }

    public void cambiarTitulo(String s) {
        nom = s;
    }

    public void cambiarNits(int s) {
        System.out.println(s);
        System.out.println(hotelSelecc.getNits());
        if (s >= hotelSelecc.getNits())
            mostrarAdv();
        else
            limpGrid7();
        numNits = s;

    }

    public void cambiarSlider(String s) {
        if (s.equals("Hotel Illa")) {
            hotelSelecc = hotelIlla;
        } else if (s.equals("Hotel Blau")) {
            hotelSelecc = hotelBlau;
        } else
            hotelSelecc = hotelVall;

        sliderGrid5.setMaximum(hotelSelecc.getNits());
    }

    public void cambiarGenero(String s) {
        dni = s;
    }

    public void mostrarFicha() {
        labelGrid7.setText("Reserva: " + nom + ", " + dni + ", " + data + ", " + nomHotel + ", " + numNits + " nits");
    }

    public void mostrarAdv() {
        labelGrid7.setText("Estada máxima a aquest hotel");
    }

    public void limpGrid7() {
        labelGrid7.setText(" ");
    }

    public static boolean validarDNI(String dni) {
        // Comprova si el format del DNI és correcte:
        // - Ha de tenir exactament 8 dígits seguits d'una lletra (majúscula o
        // minúscula).
        if (!dni.matches("\\d{8}[A-HJ-NP-TV-Za-hj-np-tv-z]"))
            return false;

        // Conjunt de lletres possibles per a la validació del DNI.
        String lletres = "TRWAGMYFPDXBNJZSQVHLCKE";

        // Converteix els primers 8 caràcters (els dígits) a un número enter.
        int numero = Integer.parseInt(dni.substring(0, 8));

        // Converteix la lletra proporcionada a majúscula per evitar errors de
        // comparació.
        char lletraDNI = Character.toUpperCase(dni.charAt(8));

        // Comprova si la lletra calculada a partir del número coincideix amb la lletra
        // proporcionada.
        return lletres.charAt(numero % 23) == lletraDNI;
    }

    public static boolean esDataAnterior(String dataString) {
        try {
            // Defineix el format de la data esperada
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Converteix el String a un objecte LocalDate
            LocalDate dataEntrada = LocalDate.parse(dataString, formatter);

            // Obté la data actual
            LocalDate dataActual = LocalDate.now();

            // Compara si la data entrada és anterior a la data actual
            return dataEntrada.isBefore(dataActual);
        } catch (DateTimeParseException e) {
            // Retorna fals en cas d'error en el format de la data
            System.out.println("Error: Format de data invàlid. Utilitza dd/MM/aaaa.");
            return false;
        }
    }

    public void errorData(int x) {
        if (x == 1) {
            labelGrid7.setText("no pot estar buida");

        } else if (x == 2) {
            labelGrid7.setText("la data de check-in no pot ésser anterior al dia d’avui");

        }
    }

}
