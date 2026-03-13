package es.cide.programacio.examen;

class NauEspacial {

    private int x, y; // Posició de la nau espacial
    private final int ample = 100; // Ample de la nau espacial
    private final int alt = 100; // Alt de la nau espacial

    // constructor
    public NauEspacial(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moureEsquerra() {
        x -= 5; // Mou la nau cap a l'esquerra
    }

    public void moureDreta() {
        x += 5; // Mou la nau cap a la dreta
    }

    public void moureAdalt() {
        y -= 5; // Mou la nau cap a la dreta
    }

    public void moureAbaix() {
        y += 5; // Mou la nau cap a la dreta
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
}