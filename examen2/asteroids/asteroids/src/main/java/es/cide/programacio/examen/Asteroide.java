package es.cide.programacio.examen;

import java.util.Random;

class Asteroide {

    private int x, y; // Posició de l'asteroide
    private int ample = 25; // Ample de l'asteroide
    private int alt = 15; // Alt de l'asteroide

    private int velocitat; // Velocitat de l'asteroide

    // constructor
    public Asteroide(int amplePanell) {

        Random aleatori = new Random();
        // decidim el tamany del asteroide (multiplicam aquest valor * tamany base)
        int tamanyRandom = generarTamany(aleatori);
        ample *= tamanyRandom;
        alt *= tamanyRandom;

        x = aleatori.nextInt(amplePanell - ample) + ample; // Posició x aleatòria
        y = 0; // Comença a la part superior del panell

        velocitat = aleatori.nextInt(8) + 3; // Velocitat aleatòria entre 3 i 8 (sino me duermo)

    }

    // generam valor entre 1 i 3
    private int generarTamany(Random r) {
        int valor = r.nextInt(1, 4);
        return valor;
    }

    public void moure() {
        y += velocitat; // Mou l'asteroide cap avall
    }

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
