package es.cide.programacio;

public class Hotel {
    String nom;
    int nits;

    public Hotel(String n, int x) {
        this.nom = n;
        this.nits = x;
    }

    public String getNom() {
        return this.nom;
    }

    public int getNits() {
        return this.nits;
    }

    public void setNom(String s) {
        this.nom = s;
    }

    public void setNits(int x) {
        this.nits = x;
    }

}
