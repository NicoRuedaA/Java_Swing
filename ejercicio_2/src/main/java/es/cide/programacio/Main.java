package es.cide.programacio;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame finestra = new JFrame("Exemple de Layout");
        finestra.setSize(500, 300);
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestra.setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout());
        panelSuperior.add(new JButton("Botó 1"));
        panelSuperior.add(new JButton("Botó 2"));
        panelSuperior.add(new JButton("Botó 3"));

        JPanel panelCentral = new JPanel();

        panelCentral.setLayout(new FlowLayout());
        panelCentral.add(new JLabel("Etiqueta A1"));
        panelCentral.add(new JTextField("Text 1"));
        panelCentral.add(new JLabel("Etiqueta A2"));
        panelCentral.add(new JTextField("Text 2"));

        finestra.add(panelSuperior, BorderLayout.NORTH);
        finestra.add(panelCentral, BorderLayout.CENTER);

        finestra.setVisible(true);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(finestra);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}