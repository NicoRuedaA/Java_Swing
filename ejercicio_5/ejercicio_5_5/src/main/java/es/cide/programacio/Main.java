package es.cide.programacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Main {
    static int valor1;
    static int valor2;
    static int valor3;

    public static void main(String[] args) {
        JFrame finestra = new JFrame("Exemple de Layout");
        finestra.setSize(500, 300);
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 1));

        JTextField op1 = new JTextField();
        JTextField op2 = new JTextField();
        JTextField resultat = new JTextField();

        JButton boto = new JButton("Sumar");

        boto.addActionListener(e -> sumar(op1, op2, resultat));

        panel.add(boto);
        panel.add(op1);
        panel.add(op2);
        panel.add(resultat);
        finestra.add(panel);

        finestra.setVisible(true);

    }

    public static void sumar(JTextField o1, JTextField o2, JTextField text) {
        try {
            valor1 = Integer.parseInt(o1.getText());
            valor2 = Integer.parseInt(o2.getText());

            valor3 = valor1 + valor2;

            text.setText(Integer.toString(valor3));
        } catch (NumberFormatException e) {
            text.setText("ERROR");
        }
    }
}
