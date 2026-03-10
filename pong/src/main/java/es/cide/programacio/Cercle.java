package es.cide.programacio;

public class Cercle implements Collider {
    private static final double ACEL = 1.05;

    private double initialXvel;
    private double initialYvel;

    private double xPos;
    private double yPos;
    private double xVel;

    private double yVel;

    private double radi;

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

        this.initialXvel = xVel;
        this.initialYvel = yVel;
    }

    public void accelerate() {
        setXvel(getVelX() * ACEL);
        setYvel(yVel * ACEL);
    }

    public void slowDown() {
        setVel(initialXvel, initialYvel);

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

    public double getRadi() {
        return this.radi;
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

    public double getAcel() {
        return this.ACEL;
    }
}
