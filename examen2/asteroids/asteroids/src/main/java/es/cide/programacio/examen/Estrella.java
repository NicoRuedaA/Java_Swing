package es.cide.programacio.examen;

import java.util.Random;
import java.awt.*;

public class Estrella {

    private int x, y; // Posició de la estrella
    private int ample = 1; // Ample de la estrella
    private int alt; // Alt de la estrella
    private int velocitat; // Velocitat de la estrella

    // constructor
    public Estrella(int amplePanell, int altPanell) {
        alt = altPanell;
        Random aleatori = new Random();
        x = aleatori.nextInt(amplePanell - ample) + ample; // Posició x aleatòria
        y = 0;
        velocitat = 150;

    }

    public void moure() {

        y += velocitat;// Mou la estrella cap avall
    }

    public void dibuixar(Graphics g) {
        g.setColor(Color.WHITE); // Color de la estrella
        g.drawRect(x - ample, y - alt, ample, alt); // dibuixam estrella
    }

    // getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAmple() {
        return ample;
    }

    public int getAlt() {
        return alt;
    }

    // setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setAmple(int a) {
        this.ample = a;
    }

    public void setAlt(int a) {
        this.alt = a;
    }

}