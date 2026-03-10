package es.cide.programacio;

public class Cercle implements Collider {

    double xPos;
    double yPos;
    double xVel;
    double yVel;
    double radi;

    @Override
    public String getShapeType() {
        return "cercle";
    }

    public Cercle() {

    }

    public Cercle(double initialxPos, double initialYPos, double initialXvel, double initialYvel, double initialRadi) {
        this.radi = initialRadi;
        this.xPos = initialxPos;
        this.yPos = initialYPos;
        this.xVel = initialXvel;
        this.yVel = initialYvel;
    }

    public void setXpos(double newXpos) {
        this.xPos = newXpos;
    }

    public void setYpos(double newYpos) {
        this.yPos = newYpos;
    }

    public void setPos(double newXpos, double newYpos) {
        setXpos(newXpos);
        setYpos(newYpos);
    }

    public void setRadi(double newRadi) {
        this.radi = newRadi;
    }

    public void setXvel(double newXvel) {
        this.xVel = newXvel;
    }

    public void setYvel(double newYvel) {
        this.yVel = newYvel;
    }

    public void setPosX(double newX) {
        this.xPos = newX;
    }

    public void setPosY(double newY) {
        this.yPos = newY;
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

    public double getVelX() {
        return this.xVel;
    }

    public double getVelY() {
        return this.yVel;
    }

}
