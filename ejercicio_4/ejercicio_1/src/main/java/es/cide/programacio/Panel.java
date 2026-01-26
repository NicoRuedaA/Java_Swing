package es.cide.programacio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JFrame {
    static JLabel label;
    int num = 12;

    public static void main(String[] args) {
        // cream un objete tipus ServerAdminPanel
        Panel finestra = new Panel();
    }

    // constructor tipo ServerAdminPanel
    public Panel() {

        this.setSize(800, 600);
        // comportament
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // posicio
        this.setLocationRelativeTo(null);

        JPanel grid = new JPanel(new GridLayout(2, 1));

        label = new JLabel(Integer.toString(num));
        JButton buton = new JButton("Botón");

        label.setFont(new Font("Arial", Font.BOLD, 15));

        buton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                num++;
                // TODO Auto-generated method stub
                label.setText(Integer.toString(num));

            }

        });

        grid.add(buton);
        grid.add(label);

        this.add(grid);
        this.setVisible(true);
    }
}