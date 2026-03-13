package es.cide.programacio.examen;

import javax.swing.*;

import java.awt.*;

public class MenuPrincipal extends JPanel {

    // img al boto "comencar"
    Image botoComencar = Recursos.BOTO_COMENCAR_IMG;

    // constructor
    public MenuPrincipal(CardLayout cardLayout, JPanel contenedor, JFrame frame) {

        // layout tipo grid bag
        setLayout(new GridBagLayout());
        // color de fondo
        setBackground(Color.BLACK);

        // configuram el grid bag
        GridBagConstraints gbc = new GridBagConstraints();

        // boto per comencar
        JButton btnComenzar = crearBoton(botoComencar);
        // action listener
        btnComenzar.addActionListener(e -> {
            // contenidor amb la classe del joc
            JocPanel joc = new JocPanel(cardLayout, contenedor);
            contenedor.add(joc, "joc");
            cardLayout.show(contenedor, "joc");
            // focus
            joc.requestFocusInWindow();
        });
        gbc.gridy = 1;
        add(btnComenzar, gbc);

    }

    // metode per crear buton
    private JButton crearBoton(Image img) {
        // construcotr
        JButton btn = new JButton(" ");
        // ma al tenir el rato damunt
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // image
        btn.setIcon(new ImageIcon(img));

        // comportament
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);

        return btn;
    }

    // pintar
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}