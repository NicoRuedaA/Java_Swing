package es.cide.programacio;

public interface Collider {
    double getPosX();

    double getPosY();

    String getShapeType(); // "rectangle", "circle", etc.
}