package es.cide.programacio;

import javax.swing.*;
import java.awt.*;

public class Main {
    static int valor = 0;

    public static void main(String[] args) {
        JFrame finestra = new JFrame("Exemple de Layout");
        finestra.setSize(500, 300);
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JButton botoVermell = new JButton("Vermell");
        JButton botoBlau = new JButton("Blau");
        JButton botoVerd = new JButton("Verd");

        botoVermell.addActionListener(e -> modColor(botoVermell.getText(), panel));
        botoBlau.addActionListener(e -> modColor(botoBlau.getText(), panel));
        botoVerd.addActionListener(e -> modColor(botoVerd.getText(), panel));

        panel.add(botoVermell);
        panel.add(botoBlau);
        panel.add(botoVerd);
        finestra.add(panel);

        finestra.setVisible(true);

    }

    public static void modColor(String s, JPanel panel) {
        if (s.equals("Vermell")) {
            panel.setBackground(Color.red);
        } else if (s.equals("Blau")) {
            panel.setBackground(Color.BLUE);
        } else {
            panel.setBackground(Color.green);
        }
    }
}