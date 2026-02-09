package es.cide.progamacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Main {
    static int valor = 0;

    public static void main(String[] args) {
        JFrame finestra = new JFrame("Exemple de Layout");
        finestra.setSize(500, 300);
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JButton boto = new JButton("Vermell");
        ;
        JCheckBox check = new JCheckBox("boto");
        boto.setEnabled(false);

        check.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // boto.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    boto.setEnabled(true);
                } else {
                    boto.setEnabled(false);
                }
            }
        });

        panel.add(boto);
        panel.add(check);
        finestra.add(panel);

        finestra.setVisible(true);

    }

}