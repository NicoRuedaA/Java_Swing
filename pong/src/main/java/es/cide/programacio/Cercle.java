package es.cide.programacio;

public class Cercle {

    double xPos;
    double yPos;
    double xVel;
    double yVel;
    double radi;

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
        this.xPos = newYpos;
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

}
