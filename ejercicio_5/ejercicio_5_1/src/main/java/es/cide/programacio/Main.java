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
        JLabel label = new JLabel(" ");

        label.setText(Integer.toString(valor));
        JButton boto = new JButton("Botó 1");

        boto.addActionListener(e -> suma(label));

        panel.add(label);
        panel.add(boto);
        finestra.add(panel);

        finestra.setVisible(true);

    }

    public static void suma(JLabel l) {
        valor++;
        l.setText(Integer.toString(valor));
    }
}