package es.cide.programacio;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import java.awt.*;

public class Main {
    static int valor = 0;

    public static void main(String[] args) {
        JFrame finestra = new JFrame("Exemple de Layout");
        finestra.setSize(500, 300);
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JLabel label = new JLabel("adasdas");
        label.setText(Integer.toString(valor));
        JTextField text = new JTextField("text");

        text.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarLabel();
            }

            // Se ejecuta cuando borras texto
            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarLabel();
            }

            // Se ejecuta cuando cambia el estilo (poco común en JTextField simple)
            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarLabel();
            }

            // Método auxiliar para no repetir código
            private void actualizarLabel() {
                label.setText(text.getText());
            }

        });

        panel.add(label);
        panel.add(text);
        finestra.add(panel);

        finestra.setVisible(true);

    }
}