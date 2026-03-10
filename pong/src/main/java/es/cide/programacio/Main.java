package es.cide.programacio;

import javax.swing.*;
import java.awt.*;

public class Main {

    private final static int HEIGHT = 1080;
    private final static int WIDTH = 1920;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ping Pong");
            frame.setSize(WIDTH, HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            // Contenedor principal con CardLayout
            CardLayout cardLayout = new CardLayout();
            JPanel contenedor = new JPanel(cardLayout);

            // Añadir pantallas
            contenedor.add(new MenuPrincipal(cardLayout, contenedor, frame), "menu");
            contenedor.add(new MenuInstrucciones(cardLayout, contenedor), "instrucciones");
            contenedor.add(new MenuAjustes(cardLayout, contenedor, frame), "ajustes");

            // Mostrar menú principal al inicio
            cardLayout.show(contenedor, "menu");

            frame.add(contenedor);
            frame.setVisible(true);
        });
    }
}