package es.cide.programacio;

import javax.swing.*;
import java.awt.*;

public class MenuAjustes extends JPanel {

    public MenuAjustes(CardLayout cardLayout, JPanel contenedor, JFrame frame) {
        setLayout(new GridBagLayout());
        setBackground(new Color(20, 20, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;

        JButton btnTitulo = crearBoton(Recursos.BOTO_INSTRUCCIONS_IMG);
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(btnTitulo, gbc);

        // Checkbox pantalla completa
        JCheckBox chkPantallaCompleta = new JCheckBox("Pantalla completa");
        chkPantallaCompleta.setFont(new Font("Arial", Font.PLAIN, 22));
        chkPantallaCompleta.setForeground(Color.WHITE);
        chkPantallaCompleta.setBackground(new Color(20, 20, 40));
        chkPantallaCompleta.setFocusPainted(false);
        chkPantallaCompleta.setSelected(frame.getExtendedState() == JFrame.MAXIMIZED_BOTH);
        chkPantallaCompleta.addActionListener(e -> {
            if (chkPantallaCompleta.isSelected()) {
                frame.dispose();
                frame.setUndecorated(true);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setVisible(true);
            } else {
                frame.dispose();
                frame.setUndecorated(false);
                frame.setExtendedState(JFrame.NORMAL);
                frame.setSize(1920, 1080);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(chkPantallaCompleta, gbc);

        JButton btnAtras = crearBoton(Recursos.BOTO_ATRAS_IMG);
        btnAtras.addActionListener(e -> cardLayout.show(contenedor, "menu"));
        gbc.gridy = 2;
        gbc.insets = new Insets(30, 0, 0, 0);
        add(btnAtras, gbc);
    }

    private JButton crearBoton(Image img) {
        JButton btn = new JButton(" ");
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setIcon(new ImageIcon(img));

        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);

        return btn;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (Recursos.FONDO_MENU != null) {
            g.drawImage(Recursos.FONDO_MENU, 0, 0, getWidth(), getHeight(), this);
        }
    }
}