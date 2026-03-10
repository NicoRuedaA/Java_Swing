package es.cide.programacio;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import java.io.IOException;
import java.net.URL;

public class Pong extends JPanel implements ActionListener {

    // ----- REVISAR ------
    private final static int HEIGHT = 1080;
    private final static int WIDTH = 1920;

    // 1. Declaramos un arreglo lo suficientemente grande para los códigos de teclas
    // 256 suele bastar, pero 1024 cubre prácticamente cualquier teclado especial
    private final boolean[] keys = new boolean[1024];
    // Variables de movimiento y posición

    private final int RADI = 20;

    private static final double DXPILOTAINI = 6, DYPILOTAINI = 6;
    private static final int XPILOTAINI = 500, YPILOTAINI = 500;

    private static final double XRECTANGLESIZE = 50, YRECTANGLESIZE = 180;
    private static final double XRECTANGLE11 = 200, YRECTANGLE11 = 100;
    private static final double XRECTANGLE22 = 700, YRECTANGLE12 = 100;
    private static final double XRECTANGLE21 = WIDTH - (XRECTANGLE11 + XRECTANGLESIZE), YRECTANGLE21 = 100;
    private static final double XRECTANGLE12 = WIDTH - (XRECTANGLE22 + XRECTANGLESIZE), YRECTANGLE22 = 100;

    private static final double RECTANGLEVEL = 15.0;

    private Image imagenJ1;
    private Image imagenJ2;

    private final int DELAY = 10;

    private Timer timer;
    private Image imagenPelota;

    private int puntuacion1 = 0;
    private int puntuacion2 = 0;

    // Variables para mostrar posición del ratón
    private int mouseX, mouseY;

    Rectangle r1 = new Rectangle(XRECTANGLE11, YRECTANGLE11, XRECTANGLESIZE, YRECTANGLESIZE, RECTANGLEVEL,
            RECTANGLEVEL);
    Rectangle r2 = new Rectangle(XRECTANGLE12, YRECTANGLE12, XRECTANGLESIZE, YRECTANGLESIZE, RECTANGLEVEL,
            RECTANGLEVEL);
    Rectangle r3 = new Rectangle(XRECTANGLE22, YRECTANGLE22, XRECTANGLESIZE, YRECTANGLESIZE, RECTANGLEVEL,
            RECTANGLEVEL);
    Rectangle r4 = new Rectangle(XRECTANGLE21, YRECTANGLE21, XRECTANGLESIZE, YRECTANGLESIZE, RECTANGLEVEL,
            RECTANGLEVEL);
    Cercle c1 = new Cercle(XPILOTAINI, YPILOTAINI, DXPILOTAINI, DYPILOTAINI, RADI);

    public Pong() {
        // 1. Intentamos cargar la imagen dentro de un bloque try
        try {
            URL urlJ1 = getClass().getResource("/a1.png");
            URL urlJ2 = getClass().getResource("/b1.png");

            if (urlJ1 != null) {
                imagenJ1 = ImageIO.read(urlJ1);
                imagenJ2 = ImageIO.read(urlJ2);
            } else {
                // Esto te avisará en la consola si la ruta está mal
                System.err.println("Error: No se encontró el archivo en /res/jugador1.png");
            }

        } catch (IOException e) {
            // 2. Aquí capturamos el error si falla la lectura
            System.err.println("Error al leer la imagen: " + e.getMessage());
            e.printStackTrace();
        }

        setBackground(Color.GRAY);
        addKeyboard();

        // Listener del ratón integrado correctamente (java.awt.event.MouseEvent)
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Rebote bordes horizontales (Puntos)
        if (c1.getPosX() + 2 * RADI >= getWidth() || c1.getPosX() <= 0) {
            if (c1.getPosX() <= 0)
                point(2);
            else
                point(1);
        }

        // Rebote bordes verticales
        if (c1.getPosY() + 2 * RADI >= getHeight() || c1.getPosY() <= 0) {
            c1.setYvel(-c1.getVelY());
        }

        if (CollisionDetector.collides(r1, c1))
            handleCollision(r1);
        if (CollisionDetector.collides(r2, c1))
            handleCollision(r2);
        if (CollisionDetector.collides(r3, c1))
            handleCollision(r3);
        if (CollisionDetector.collides(r4, c1))
            handleCollision(r4);

        // Mover la pelota
        c1.setXpos(c1.getPosX() + c1.getVelX());
        c1.setYpos(c1.getPosY() + c1.getVelY());

        repaint();
    }

    private long ultimoRebote = 0;
    private static final long DELAY_REBOTE = 300; // milisegundos

    private void handleCollision(Rectangle r) {
        long ahora = System.currentTimeMillis();
        if (ahora - ultimoRebote < DELAY_REBOTE)
            return;

        ultimoRebote = ahora;

        double circleCenterX = c1.getPosX() + c1.radi;
        double circleCenterY = c1.getPosY() + c1.radi;

        double rectCenterX = r.getPosX() + r.getSizeX() / 2;
        double rectCenterY = r.getPosY() + r.getSizeY() / 2;

        double dx = circleCenterX - rectCenterX;
        double dy = circleCenterY - rectCenterY;

        if (Math.abs(dx) > Math.abs(dy)) {
            c1.setXvel(-c1.getVelX());
        } else {
            c1.setYvel(-c1.getVelY());
        }
    }

    private void dxBounce() {
        c1.setXvel(-c1.getVelX());
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        drawCampo(g2d);

        drawCercle(g2d);

        drawRectangle(g2d, r1, imagenJ1);
        drawRectangle(g2d, r2, imagenJ1);
        drawRectangle(g2d, r3, imagenJ2);
        drawRectangle(g2d, r4, imagenJ2);

        // ----- CONTADOR -----
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Punts J1: " + puntuacion1, 50, 50);
        g2d.drawString("Punts J2: " + puntuacion2, 50, 80);
        g2d.drawString("Ratolí: " + mouseX + ", " + mouseY, 50, 110);

    }

    private void drawRectangle(Graphics2D g2d, Rectangle r, Image img) {
        g2d.setColor(Color.GREEN);
        g2d.fillRect((int) r.getPosX(), (int) r.getPosY(), (int) r.getSizeX(), (int) r.getSizeY());

        if (img != null) {
            g2d.drawImage(img, (int) r.getPosX(), (int) r.getPosY(), (int) r.getSizeX(), (int) r.getSizeY(),
                    this);
        }
    }

    private void drawCercle(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillOval((int) c1.getPosX(), (int) c1.getPosY(), RADI * 2, RADI * 2);
    }

    private void drawCampo(Graphics2D g2d) {
        // 1. Fondo del césped
        g2d.setColor(new Color(34, 139, 34)); // Verde bosque
        g2d.fillRect(0, 0, getWidth(), getHeight());
        // 2. Configurar el color y grosor de las líneas
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(3)); // Líneas un poco más gruesas
        // --- Líneas Perimetrales ---
        int margen = 20;
        int campoW = getWidth() - (margen * 2);
        int campoH = getHeight() - (margen * 2);
        g2d.drawRect(margen, margen, campoW, campoH);
        // --- Línea de Medio Campo ---
        int mitadX = getWidth() / 2;
        g2d.drawLine(mitadX, margen, mitadX, getHeight() - margen);
        // --- Círculo Central ---
        int radioCirculo = 100;
        g2d.drawOval(mitadX - radioCirculo, (getHeight() / 2) - radioCirculo, radioCirculo * 2, radioCirculo * 2);
        // Punto central
        g2d.fillOval(mitadX - 5, (getHeight() / 2) - 5, 10, 10);
        // --- Áreas de Portería---
        int anchoArea = 150;
        int altoArea = 400;
        // Área Izquierda
        g2d.drawRect(margen, (getHeight() / 2) - (altoArea / 2), anchoArea, altoArea);
        // Área Derecha
        g2d.drawRect(getWidth() - margen - anchoArea, (getHeight() / 2) - (altoArea / 2), anchoArea, altoArea);
    }

    private void updateMovement() {

        if (keys[KeyEvent.VK_W]) {
            r1.setPosY(r1.getPosY() - r1.getVelY());
            r2.setPosY(r2.getPosY() - r2.getVelY());
        }
        if (keys[KeyEvent.VK_S]) {
            r1.setPosY(r1.getPosY() + r1.getVelY());
            r2.setPosY(r2.getPosY() + r2.getVelY());
        }
        if (keys[KeyEvent.VK_UP]) {
            r3.setPosY(r3.getPosY() - r3.getVelY());
            r4.setPosY(r4.getPosY() - r4.getVelY());
        }
        if (keys[KeyEvent.VK_DOWN]) {
            r3.setPosY(r3.getPosY() + r3.getVelY());
            r4.setPosY(r4.getPosY() + r4.getVelY());
        }

        repaint();
    }

    private void point(int player) {
        if (player == 1)
            puntuacion1++;
        else
            puntuacion2++;

        resetCerclePos();

    }

    private void resetCerclePos() {
        c1.setPos(getWidth() / 2, getHeight() / 2);
        c1.setXvel(new Random().nextBoolean() ? 6.0 : -6.0);
        c1.setYvel(new Random().nextBoolean() ? 6.0 : -6.0);
    }

    private void addKeyboard() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                int code = e.getKeyCode();

                // Validamos que el código esté dentro del rango del arreglo
                if (code >= 0 && code < keys.length) {
                    if (e.getID() == KeyEvent.KEY_PRESSED) {
                        keys[code] = true;
                    } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                        keys[code] = false;
                    }
                }
                return false;
            }
        });

        // Bucle de movimiento (Timer)
        Timer gameLoop = new Timer(16, e -> updateMovement());
        gameLoop.start();
    }
}