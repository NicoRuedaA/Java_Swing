package es.cide.programacio;

public class Rectangle {

    // crear velocidad por defecto
    // poner velocidad por defecto en el constructor sin velocidad
    double xPos;
    double yPos;
    double xSize;
    double ySize;
    double xVel;
    double yVel;

    public Rectangle() {

    }

    public Rectangle(double initialPosX, double initialPosY, double initialSizeX, double initialSizeY) {
        this.xPos = initialPosX;
        this.yPos = initialPosY;
        this.xSize = initialSizeX;
        this.ySize = initialSizeY;
        // falta xvel
        // falta yvel
    }

    public Rectangle(double initialPosX, double initialPosY, double initialSizeX, double initialSizeY,
            double initialXvel, double initialYvel) {
        this.xPos = initialPosX;
        this.yPos = initialPosY;
        this.xSize = initialSizeX;
        this.ySize = initialSizeY;
        this.xVel = initialXvel;
        this.yVel = initialYvel;
    }

    public void setPosX(double newX) {
        this.xPos = newX;
    }

    public void setPosY(double newY) {
        this.yPos = newY;
    }

    public void setPos(double newX, double newY) {
        setPosX(newX);
        setPosY(newY);
    }

    public void setXvel(double newXvel) {
        this.xVel = newXvel;
    }

    public void setYvel(double newYvel) {
        this.yVel = newYvel;
    }

    public void setVel(double newXvel, double newYvel) {
        setXvel(newXvel);
        setYvel(newYvel);
    }

    double getPosX() {
        return this.xPos;
    }

    double getPosY() {
        return this.yPos;
    }

}
