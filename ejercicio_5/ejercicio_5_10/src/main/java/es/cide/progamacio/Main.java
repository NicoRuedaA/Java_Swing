package es.cide.progamacio;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    public static void main(String[] args) {

        JFrame finestra = new JFrame("Exemple de Layout");
        finestra.setSize(500, 300);
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 1));

        JLabel label = new JLabel();

        panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                // e.getX() y e.getY() nos dan las coordenadas
                label.setText("X: " + e.getX() + " | Y: " + e.getY());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // También actualizamos si está arrastrando
                label.setText("Arrossegant - X: " + e.getX() + " | Y: " + e.getY());
            }
        });

        panel.add(label);

        finestra.add(panel);

        finestra.setVisible(true);

    }

    public static void saluda(JComboBox<String> com, JTextArea te) {
        String text = (String) com.getSelectedItem();
        String nom = te.getText();

        JOptionPane.showMessageDialog(null, text + " " + nom);

    }

}
