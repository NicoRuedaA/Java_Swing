package es.cide.programacio;

import javax.swing.*;
import java.awt.*;

public class MenuInstrucciones extends JPanel {

    public MenuInstrucciones(CardLayout cardLayout, JPanel contenedor) {
        setLayout(new GridBagLayout());
        setBackground(new Color(20, 20, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;

        JButton btnTitulo = crearBoton(Recursos.BOTO_INSTRUCCIONS_IMG);
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 30, 0);
        add(btnTitulo, gbc);

        gbc.insets = new Insets(5, 0, 5, 0);

        String[] lineas = {
                "Jugador 1: W / S para mover",
                "Jugador 2: ↑ / ↓ para mover",
                "La pelota rebota en los jugadores",
                "Si la pelota sale por tu lado, el rival anota un punto",
                "Pulsa ESC para pausar"
        };

        for (int i = 0; i < lineas.length; i++) {
            JLabel linea = new JLabel(lineas[i], SwingConstants.CENTER);
            linea.setFont(new Font("Arial", Font.PLAIN, 20));
            linea.setForeground(Color.LIGHT_GRAY);
            gbc.gridy = i + 1;
            add(linea, gbc);
        }

        JButton btnAtras = crearBoton(Recursos.BOTO_ATRAS_IMG);
        btnAtras.addActionListener(e -> cardLayout.show(contenedor, "menu"));
        gbc.gridy = lineas.length + 1;
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