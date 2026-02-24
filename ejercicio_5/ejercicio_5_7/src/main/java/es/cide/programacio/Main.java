package es.cide.programacio;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    public static void main(String[] args) {

        JFrame finestra = new JFrame("Exemple de Layout");
        finestra.setSize(500, 300);
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 1));

        JLabel label = new JLabel();
        ButtonGroup grup = new ButtonGroup();
        JRadioButton opcio1 = new JRadioButton("Windows");
        JRadioButton opcio2 = new JRadioButton("Linux");
        JRadioButton opcio3 = new JRadioButton("Macs");

        grup.add(opcio1);
        grup.add(opcio2);
        grup.add(opcio3);

        panel.add(opcio1);
        panel.add(opcio2);
        panel.add(opcio3);
        panel.add(label);

        try {
            ImageIcon a = new ImageIcon(new URL("https://i.blogs.es/c1de2e/windows-7/450_1000.webp"));
            ImageIcon b = new ImageIcon(new URL(
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQVdzVxi12efuVQzzCMkf7uJgaVcQ0UYTvAXg&s"));
            ImageIcon c = new ImageIcon(new URL(
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRL6gupesisX_67dopOkh4WkfElRs2Vqx02bA&s"));

            opcio1.addActionListener(e -> label.setIcon(a));
            opcio2.addActionListener(e -> label.setIcon(b));
            opcio3.addActionListener(e -> label.setIcon(c));

        } catch (MalformedURLException e) {
            label.setText("Error al cargar imágenes");
        }
        panel.add(new JScrollPane(label));

        finestra.add(panel);

        finestra.setVisible(true);

    }

}
