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
import javax.imageio.ImageIO;

import java.io.IOException;
import java.net.URL;

public class CercleRebotant extends JPanel implements ActionListener {

    // Variables de movimiento y posición
    private double dxPilota = 6, dyPilota = 6;
    private double xRectangle11 = 100, yRectangle11 = 100;
    private double xRectangle12 = 400, yRectangle12 = 100;
    private double xRectangle21 = 1150, yRectangle21 = 50;
    private double xRectangle22 = 850, yRectangle22 = 50;

    private double xRectangleSize = 50, yRectangleSize = 180;
    private final int RADI = 20;
    private final int DELAY = 10;
    private final static int HEIGHT = 1080;
    private final static int WIDTH = 1920;

    private int xPilota = 500, yPilota = 500;
    private Timer timer;
    private Image imagenPelota;

    private int puntuacion1 = 0;
    private int puntuacion2 = 0;
    private long ultimoRebote = 0;

    // Variables para mostrar posición del ratón
    private int mouseX, mouseY;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Cercle Rebotant");
            CercleRebotant panel = new CercleRebotant();

            frame.add(panel);
            frame.setSize(WIDTH, HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private Image imagenJ1;
    private Image imagenJ2;

    public CercleRebotant() {
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        dibujarCampo(g2d);

        // Suavizado de dibujo
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar Pelota (Imagen o círculo rojo si falla la carga)
        if (imagenPelota != null) {
            g2d.drawImage(imagenPelota, xPilota, yPilota, RADI * 2, RADI * 2, this);
        } else {
            g2d.setColor(Color.RED);
            g2d.fillOval(xPilota, yPilota, RADI * 2, RADI * 2);
        }

        // Rectángulos grupo 1 (Verdes)
        g2d.setColor(Color.GREEN);
        g2d.fillRect((int) xRectangle11, (int) yRectangle11, (int) xRectangleSize, (int) yRectangleSize);

        // Dibujamos la imagen ENCIMA. Al usar xRectangleSize e yRectangleSize, se
        // escala sola.
        if (imagenJ1 != null) {
            g2d.drawImage(imagenJ1, (int) xRectangle11, (int) yRectangle11, (int) xRectangleSize, (int) yRectangleSize,
                    this);
        }

        // Repetir para los demás...
        g2d.setColor(Color.decode("#00008b"));
        g2d.fillRect((int) xRectangle12, (int) yRectangle12, (int) xRectangleSize, (int) yRectangleSize);
        if (imagenJ2 != null) {
            g2d.drawImage(imagenJ2, (int) xRectangle12, (int) yRectangle12, (int) xRectangleSize, (int) yRectangleSize,
                    this);
        }

        // --- RECTÁNGULOS GRUPO 2 (Ejemplo: Jugador 2) ---

        g2d.setColor(Color.BLUE);
        g2d.fillRect((int) xRectangle21, (int) yRectangle21, (int) xRectangleSize,
                (int) yRectangleSize);
        if (imagenJ2 != null) {
            g2d.drawImage(imagenJ2, (int) xRectangle21, (int) yRectangle21, (int) xRectangleSize, (int) yRectangleSize,
                    this);
        }

        g2d.setColor(Color.decode("#006400"));
        g2d.fillRect((int) xRectangle22, (int) yRectangle22, (int) xRectangleSize,
                (int) yRectangleSize);
        if (imagenJ1 != null) {
            g2d.drawImage(imagenJ1, (int) xRectangle22, (int) yRectangle22, (int) xRectangleSize, (int) yRectangleSize,
                    this);
        }

        // UI: Puntuación y Coordenadas Ratón
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Punts J1: " + puntuacion1, 50, 50);
        g2d.drawString("Punts J2: " + puntuacion2, 50, 80);
        g2d.drawString("Ratolí: " + mouseX + ", " + mouseY, 50, 110);

    }

    private void dibujarCampo(Graphics2D g2d) {
        // 1. Fondo del césped
        g2d.setColor(new Color(34, 139, 34)); // Verde bosque
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // 2. Configurar el color y grosor de las líneas
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(3)); // Líneas un poco más gruesas

        // --- Líneas Perimetrales ---
        // Dejamos un pequeño margen de 20px para que no peguen al borde
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

        // Punto central (opcional)
        g2d.fillOval(mitadX - 5, (getHeight() / 2) - 5, 10, 10);

        // --- Áreas de Portería (Opcional pero recomendado) ---
        int anchoArea = 150;
        int altoArea = 400;

        // Área Izquierda
        g2d.drawRect(margen, (getHeight() / 2) - (altoArea / 2), anchoArea, altoArea);

        // Área Derecha
        g2d.drawRect(getWidth() - margen - anchoArea, (getHeight() / 2) - (altoArea / 2), anchoArea, altoArea);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long tiempoActual = System.currentTimeMillis();

        // Rebote bordes horizontales (Puntos)
        if (xPilota + 2 * RADI >= getWidth() || xPilota <= 0) {
            if (xPilota <= 0)
                point(2);
            else
                point(1);
        }

        // Rebote bordes verticales
        if (yPilota + 2 * RADI >= getHeight() || yPilota <= 0) {
            dyPilota = -dyPilota;
        }

        // Lógica de colisiones con rectángulos (con delay de 0.5s)
        checkCollision(xRectangle11, yRectangle11, tiempoActual, false);
        checkCollision(xRectangle12, yRectangle12, tiempoActual, false);
        checkCollision(xRectangle21, yRectangle21, tiempoActual, true); // True porque es el lado derecho
        checkCollision(xRectangle22, yRectangle22, tiempoActual, true);

        xPilota += dxPilota;
        yPilota += dyPilota;
        repaint();
    }

    private void checkCollision(double rx, double ry, long tiempoActual, boolean isRightSide) {
        if (tiempoActual - ultimoRebote > 500) {
            // Detección simple de colisión por área
            if (xPilota + RADI * 2 >= rx && xPilota <= rx + xRectangleSize &&
                    yPilota + RADI * 2 >= ry && yPilota <= ry + yRectangleSize) {

                dxPilota *= -1;
                ultimoRebote = tiempoActual;
            }
        }
    }

    // 1. Declaramos un arreglo lo suficientemente grande para los códigos de teclas
    // 256 suele bastar, pero 1024 cubre prácticamente cualquier teclado especial
    private final boolean[] keys = new boolean[1024];

    /*
     * private void addKeyboard() {
     * 
     * KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
     * new KeyEventDispatcher() {
     * 
     * @Override
     * 
     * public boolean dispatchKeyEvent(KeyEvent e) {
     * 
     * if (e.getID() == KeyEvent.KEY_PRESSED) {
     * 
     * int c = e.getKeyCode();
     * 
     * double vel = 15.0;
     * 
     * switch (c) {
     * 
     * case KeyEvent.VK_W:
     * 
     * yRectangle11 -= vel;
     * 
     * yRectangle22 -= vel;
     * 
     * break;
     * 
     * case KeyEvent.VK_S:
     * 
     * yRectangle11 += vel;
     * 
     * yRectangle22 += vel;
     * 
     * break;
     * 
     * case KeyEvent.VK_UP:
     * 
     * yRectangle21 -= vel;
     * 
     * yRectangle12 -= vel;
     * 
     * break;
     * 
     * case KeyEvent.VK_DOWN:
     * 
     * yRectangle21 += vel;
     * 
     * yRectangle12 += vel;
     * 
     * break;
     * 
     * 
     * 
     * }
     * 
     * }
     * 
     * return false;
     */

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

    private void updateMovement() {
        double vel = 15.0;

        // Jugador 1 (WASD)
        if (keys[KeyEvent.VK_W]) {
            yRectangle11 -= vel;
            yRectangle22 -= vel;
        }
        if (keys[KeyEvent.VK_S]) {
            yRectangle11 += vel;
            yRectangle22 += vel;
        }

        // Jugador 2 (Flechas)
        if (keys[KeyEvent.VK_UP]) {
            yRectangle21 -= vel;
            yRectangle12 -= vel;
        }
        if (keys[KeyEvent.VK_DOWN]) {
            yRectangle21 += vel;
            yRectangle12 += vel;
        }

        repaint();
    }

    private void point(int player) {
        if (player == 1)
            puntuacion1++;
        else
            puntuacion2++;

        // Reset posición
        xPilota = getWidth() / 2;
        yPilota = getHeight() / 2;

        // Dirección aleatoria al sacar
        dxPilota = (new Random().nextBoolean() ? 6 : -6);
        dyPilota = (new Random().nextBoolean() ? 6 : -6);
    }
}