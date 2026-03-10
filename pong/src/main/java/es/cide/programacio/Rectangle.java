package es.cide.programacio;

public class Rectangle implements Collider {

    // crear velocidad por defecto
    // poner velocidad por defecto en el constructor sin velocidad
    double xPos;
    double yPos;
    double xSize;
    double ySize;
    double xVel;
    double yVel;

    @Override
    public String getShapeType() {
        return "rectangle";
    }

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

    public double getPosX() {
        return this.xPos;
    }

    public double getPosY() {
        return this.yPos;
    }

    public double getSizeX() {
        return this.xSize;
    }

    public double getSizeY() {
        return this.ySize;
    }

    public double getVelX() {
        return this.xVel;
    }

    public double getVelY() {
        return this.yVel;
    }

}
