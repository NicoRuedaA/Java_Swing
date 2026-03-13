package es.cide.programacio.examen;

import javax.swing.*;
import java.awt.*;

public class Joc {

    // constants del tamany de la finestra
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            // Crea la finestra principal
            // titol
            JFrame frame = new JFrame("“El corredor de Kessel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(WIDTH, HEIGHT); // Mida de la finestra
            frame.setLocationRelativeTo(null); // Centra la finestra
            frame.setVisible(true); // Fa que la finestra sigui visible
            frame.setResizable(false); // finestra estatic, no pots modificar el tamant

            // Contenedor principal con CardLayout
            CardLayout cardLayout = new CardLayout();
            JPanel contenedor = new JPanel(cardLayout);

            // Añadir pantallas
            contenedor.add(new MenuPrincipal(cardLayout, contenedor, frame), "menu");

            // Mostrar menú principal al inicio
            cardLayout.show(contenedor, "menu");

            frame.add(contenedor);
            frame.setVisible(true);
        });
    }
}