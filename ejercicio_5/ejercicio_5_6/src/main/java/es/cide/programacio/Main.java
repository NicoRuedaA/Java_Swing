package es.cide.programacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Main {

    public static void main(String[] args) {
        JFrame finestra = new JFrame("Exemple de Layout");
        finestra.setSize(500, 300);
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 1));

        JTextField text = new JTextField();
        JButton boto = new JButton("añadir");
        DefaultListModel<String> model = new DefaultListModel<>();
        JList lista = new JList<>(model);

        boto.addActionListener(e -> annadir(model, text));

        panel.add(text);
        panel.add(boto);
        panel.add(lista);
        panel.add(new JScrollPane(lista));

        finestra.add(panel);

        finestra.setVisible(true);

    }

    public static void annadir(DefaultListModel<String> l, JTextField t) {
        String cont = t.getText();
        if (!cont.isEmpty()) {
            l.addElement(cont);
            t.setText(" ");
            t.requestFocus();
        }
    }

}
