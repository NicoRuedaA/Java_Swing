package es.cide.programacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

// Classe que representa un panell on es dibuixa un cercle que rebota
public class CercleRebotant extends JPanel implements ActionListener {
    private int xPilota = 10, yPilota = 10; // Coordenades inicials del cercle
    private double dxPilota = 2, dyPilota = 2; // Velocitat del moviment en X i Y
    private double xRectangle1 = 50, yRectangle1 = 50;
    private double xRectangleSize1 = 50, yRectangleSize1 = 50;
    private double xRectangle2 = 150, yRectangle2 = 50;
    private double xRectangleSize2 = 50, yRectangleSize2 = 50;
    private double dyRectangle2 = 0.25, dyRectangle1 = 0.25;
    private final int RADI = 20; // Radi del cercle
    private final int DELAY = 10; // Retard del temporitzador en mil·lisegons
    private Timer timer; // Temporitzador per controlar l'animació

    // Constructor que inicialitza el panell i inicia el temporitzador
    public CercleRebotant() {
        setBackground(Color.WHITE); // Defineix el color de fons del panell
        timer = new Timer(DELAY, this); // Crea el temporitzador amb retard especificat
        timer.start(); // Inicia el temporitzador
    }

    // Mètode per dibuixar el cercle dins del panell
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; // Conversió a Graphics2D per millorar el dibuix
        g2d.setColor(Color.RED); // Defineix el color del cercle
        g2d.fillOval(xPilota, yPilota, RADI * 2, RADI * 2); // Dibuixa el cercle amb les coordenades i el radi

        Graphics2D r1 = (Graphics2D) g;
        r1.setColor(Color.green);
        r1.fillRect((int) xRectangle1, (int) yRectangle1, (int) xRectangleSize1, (int) yRectangleSize1);

        Graphics2D r2 = (Graphics2D) g;
        r2.setColor(Color.blue);
        r2.fillRect((int) xRectangle2, (int) yRectangle2, (int) xRectangleSize2, (int) yRectangleSize2);

        /*
         * Graphics2D r2 = (Graphics2D) g;
         * r1.setColor(Color.blue);
         * r1.fillRect(50, 100, 50, 50);
         */

    }

    // Mètode que s'executa a cada tic del temporitzador per moure el cercle
    @Override
    public void actionPerformed(ActionEvent e) {
        // Comprova si el cercle toca les vores horitzontals
        if (xPilota + 2 * RADI >= getWidth() || xPilota <= 0) {
            dxPilota = -dxPilota; // Inverteix la direcció horitzontal
        }
        // Comprova si el cercle toca les vores verticals
        if (yPilota + 2 * RADI >= getHeight() || yPilota <= 0) {
            dyPilota = -dyPilota; // Inverteix la direcció vertical
        }

        // Comprova si el cercle toca les vores horitzontals
        /*
         * if (yPilota >= yRectangle1 + yRectangleSize1) {
         * dyPilota = -dyPilota;
         * }
         */
        if (yPilota >= yRectangle1 - yRectangleSize1) {
            if (xPilota <= xRectangle1) {
                dyPilota = -dyPilota;
                System.out.println("toca");
            }

            // Actualitza la posició del cercle

        }
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {

                int c = e.getKeyCode();
                System.out.println(c);

                switch (c) {
                    case 87:
                        yRectangle1 -= dyRectangle1;

                        break;
                    case 83:

                        yRectangle1 += dyRectangle1;

                        break;

                    case 40:

                        yRectangle2 += dyRectangle2;

                        break;
                    case 38:

                        yRectangle2 -= dyRectangle2;

                        break;
                    default:
                        break;
                }
                return false;

            }
        });
        xPilota += dxPilota;
        yPilota += dyPilota;
        repaint(); // Redibuixa el panell per actualitzar la posició del cercle
    }

    // Mètode principal per iniciar l'aplicació
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Cercle Rebotant"); // Crea la finestra
            CercleRebotant panel = new CercleRebotant(); // Crea una instància del panell
            frame.add(panel); // Afegeix el panell a la finestra
            frame.setSize(400, 300); // Defineix la mida de la finestra
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configura el tancament de la finestra
            frame.setLocationRelativeTo(null); // Centra la finestra a la pantalla
            frame.setVisible(true); // Mostra la finestra
        });
    }
}