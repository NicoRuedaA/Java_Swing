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
        JComboBox combo = new JComboBox<String>();
        combo.addItem("dr.");
        combo.addItem("dra.");
        JTextArea text = new JTextArea();
        JButton boto = new JButton("Saluda");
        JOptionPane opp = new JOptionPane();

        boto.addActionListener(e -> saluda(combo, text));

        panel.add(label);
        panel.add(combo);
        panel.add(boto);
        panel.add(text);

        finestra.add(panel);

        finestra.setVisible(true);

    }

    public static void saluda(JComboBox<String> com, JTextArea te) {
        String text = (String) com.getSelectedItem();
        String nom = te.getText();

        JOptionPane.showMessageDialog(null, text + " " + nom);

    }

}
