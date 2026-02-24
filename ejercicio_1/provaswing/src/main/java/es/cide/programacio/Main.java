package es.cide.programacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame finestra = new JFrame("Exemple Dialeg");
        finestra.setSize(400, 200);
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestra.setLayout(new FlowLayout());

        JButton botoObrir = new JButton("Obrir dialeg");
        finestra.add(botoObrir);

        botoObrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDialeg(finestra);
            }
        });
        finestra.setVisible(true);
    }

    private static void mostrarDialeg(JFrame parent) {
        JDialog dialeg = new JDialog(parent, "Missatge Important", true);
        dialeg.setSize(300, 150);
        dialeg.setLayout(new BorderLayout());

        // afegim un missatge
        JLabel etiqueta = new JLabel("Aixo es un dialeg!", JLabel.CENTER);
        dialeg.add(etiqueta, BorderLayout.CENTER);

        // boto per tancar el dialeg
        JButton botoTncar = new JButton("D'acord");
        botoTncar.addActionListener(e -> dialeg.dispose());
        dialeg.add(botoTncar, BorderLayout.SOUTH);

        // fem visible el dialeg
        dialeg.setLocationRelativeTo(parent);
        dialeg.setVisible(true);

    }
}