package es.cide.programacio;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JPanel {

    public MenuPrincipal(CardLayout cardLayout, JPanel contenedor, JFrame frame) {

        setLayout(new GridBagLayout());
        setBackground(new Color(20, 20, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        gbc.insets = new Insets(10, 0, 10, 0);

        JButton btnComenzar = crearBoton(Recursos.BOTO_COMENCAR_IMG);
        btnComenzar.addActionListener(e -> {
            Pong pong = new Pong(cardLayout, contenedor);
            contenedor.add(pong, "pong");
            cardLayout.show(contenedor, "pong");
            pong.requestFocusInWindow();
            pong.iniciar();
        });
        gbc.gridy = 1;
        add(btnComenzar, gbc);

        JButton btnInstrucciones = crearBoton(Recursos.BOTO_INSTRUCCIONS_IMG);
        btnInstrucciones.setBackground(getBackground());
        btnInstrucciones.addActionListener(e -> cardLayout.show(contenedor, "instrucciones"));
        gbc.gridy = 2;
        add(btnInstrucciones, gbc);

        JButton btnAjustes = crearBoton(Recursos.BOTO_AJUSTES_IMG);
        btnAjustes.addActionListener(e -> cardLayout.show(contenedor, "ajustes"));
        gbc.gridy = 3;
        add(btnAjustes, gbc);

        JButton btnSalir = crearBoton(Recursos.BOOT_SALIR_IMG);
        btnSalir.addActionListener(e -> System.exit(0));
        gbc.gridy = 4;
        add(btnSalir, gbc);
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