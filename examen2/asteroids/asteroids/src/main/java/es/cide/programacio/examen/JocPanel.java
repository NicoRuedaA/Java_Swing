
package es.cide.programacio.examen;

import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

class JocPanel extends JPanel {

    // importam les imgs de la classe Recursos
    private static final Image tieIMG = Recursos.TIE;
    private static final Image mfIMG = Recursos.MF;

    private NauEspacial nau; // Nau espacial del jugador
    private List<Asteroide> asteroides; // Llista d'asteroides
    private List<Estrella> estrelles; // Llista d'estrelles
    private int puntuacio; // Puntuació del jugador
    private boolean jocActiu; // Indica si el joc està actiu

    public JocPanel(CardLayout cardLayout, JPanel contenedor) {
        nau = new NauEspacial(350, 550); // Cream objecte nau
        asteroides = new ArrayList<>(); // Inicialitza la llista d'asteroides
        estrelles = new ArrayList<>(); // Inicialitza la llista d'estrelles
        puntuacio = 0; // Inicialitza la puntuació
        jocActiu = true; // El joc comença actiu

        // Configura el panell
        setBackground(Color.BLACK); // Fons negre
        setFocusable(true); // Permet que el panell rebi el focus
        requestFocusInWindow(); // Demana el focus per rebre les tecles
        // modificacio del listen del keyboard unicament per poder moure abaix i adalt
        // al mateix temps
        // i mmillor "feeling"
        addKeyboard();

        // Crea un Timer per actualitzar el joc
        Timer temporitzador = new Timer(20, e -> {
            if (jocActiu) {
                actualitzarJoc(); // Actualitza l'estat del joc
                repaint(); // Redibuixa el panell
            }
        });
        temporitzador.start(); // Inicia el temporitzador
    }

    // variable per "sumar" inputs
    private final boolean[] keys = new boolean[1024];

    private void addKeyboard() {
        // keyevents
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                int code = e.getKeyCode();
                // validam el codi del input
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

        // aplicam el moviment
        Timer gameLoop = new Timer(16, e -> updateMovement());
        gameLoop.start();
    }

    private void updateMovement() {
        // si el joc no esta actiu, no fa res
        if (!jocActiu)
            return;
        // comprova el input...
        if ((keys[KeyEvent.VK_RIGHT]) || (keys[KeyEvent.VK_D])) {
            nau.moureDreta();
        }
        if ((keys[KeyEvent.VK_LEFT]) || (keys[KeyEvent.VK_A])) {
            nau.moureEsquerra();
        }
        if ((keys[KeyEvent.VK_UP]) || (keys[KeyEvent.VK_W])) {
            nau.moureAdalt();
        }
        if ((keys[KeyEvent.VK_DOWN]) || (keys[KeyEvent.VK_S])) {
            nau.moureAbaix();
        }
        // actiañotza visualment (metode de libreria)
        repaint();
    }

    private void actualitzarJoc() {
        // Genera asteroides aleatoris
        if (Math.random() < 0.02) {
            asteroides.add(new Asteroide(getWidth())); // Afegeix un nou asteroide
        }

        // Genera estrelles Aleatories
        if (Math.random() < 0.5) {
            estrelles.add(new Estrella(getWidth(), getHeight())); // Afegeix una nova estrella
        }

        // Mou els asteroides i comprova les col·lisions
        for (int i = asteroides.size() - 1; i >= 0; i--) {
            Asteroide asteroide = asteroides.get(i);
            asteroide.moure(); // Mou l'asteroide

            // Comprova si l'asteroide col·lisiona amb la nau
            if (collisio(nau, asteroide)) {
                jocActiu = false; // El joc acaba
            }

            // Comprova si l'asteroide ha sortit de la pantalla
            if (asteroide.getY() > getHeight()) {
                asteroides.remove(i); // Elimina l'asteroide
                puntuacio++; // Augmenta la puntuació
            }
        }

        // Mou les estrelles i comprova si ha sortir de pantalla
        for (int i = estrelles.size() - 1; i >= 0; i--) {
            Estrella estrella = estrelles.get(i);
            estrella.moure(); // Mou la estrella

            // Comprova si la estrella ha sortit de la pantalla
            if (estrella.getY() > getHeight()) {

                estrelles.remove(i); // Elimina la estrella

            }
        }

    }

    // detecta la colisio entre 2 rectangles. Formula general (podriem sustituir Nau
    // Espacial i
    // asteroide per una classe pare "Rectangle")
    private boolean collisio(NauEspacial r1, Asteroide r2) {

        return r1.getX() < r2.getX() + r2.getAmple()
                && r1.getX() + r1.getAmple() > r2.getX()
                && r1.getY() < r2.getY() + r2.getAlt()
                && r1.getY() + r1.getY() > r2.getX();

    }

    // visual
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibuixa cada asteroide de la llista
        for (Asteroide asteroide : asteroides) {

            dibuixarAsteroide(g, asteroide);
        }

        // dibuica cada estrella de la llista
        for (Estrella estrella : estrelles) {
            estrella.dibuixar(g);
        }

        // Dibuixa la puntuació
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Puntuació: " + puntuacio, 10, 30);

        // Dibuixa el missatge de final de joc
        // si el joc no esta actiu
        if (!jocActiu) {
            // modificam el color de la font
            g.setColor(Color.YELLOW);
            // modificam la font
            g.setFont(new Font("Arial", Font.BOLD, 40));
            // texte + valor d'aquest
            String missatge = "Fi del joc";
            int ampleMissatge = g.getFontMetrics().stringWidth(missatge);
            g.drawString(missatge, getWidth() / 2 - ampleMissatge / 2, getHeight() / 2);
        }

        // Dibuixam la nau
        dibuixarNau(g);

    }

    private void dibuixarNau(Graphics g) {
        if (mfIMG != null) {
            g.drawImage(mfIMG, nau.getX(), nau.getY(), nau.getAmple(), nau.getAlt(), this);
        }
    }

    private void dibuixarAsteroide(Graphics g, Asteroide a) {
        if (tieIMG != null) {
            // la dibuixa
            g.drawImage(tieIMG, a.getX(), a.getY(), a.getAmple(), a.getAlt(), this);
        }
    }

}