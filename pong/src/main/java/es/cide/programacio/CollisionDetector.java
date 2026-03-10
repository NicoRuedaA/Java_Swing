package es.cide.programacio;

public class CollisionDetector {

    public static boolean collides(Collider a, Collider b) {
        String typeA = a.getShapeType();
        String typeB = b.getShapeType();

        if (typeA.equals("rectangle") && typeB.equals("rectangle")) {
            return rectRect((Rectangle) a, (Rectangle) b);

        } else if (typeA.equals("cercle") && typeB.equals("cercle")) { // ✅
            return circleCircle((Cercle) a, (Cercle) b);

        } else if (typeA.equals("rectangle") && typeB.equals("cercle")) { // ✅
            return rectCircle((Rectangle) a, (Cercle) b);

        } else if (typeA.equals("cercle") && typeB.equals("rectangle")) { // ✅
            return rectCircle((Rectangle) b, (Cercle) a);
        }

        return false;
    }

    // ── Rectángulo vs Rectángulo (AABB) ──────────────────────────────
    private static boolean rectRect(Rectangle r1, Rectangle r2) {
        return r1.getPosX() < r2.getPosX() + r2.getSizeX()
                && r1.getPosX() + r1.getSizeX() > r2.getPosX()
                && r1.getPosY() < r2.getPosY() + r2.getSizeY()
                && r1.getPosY() + r1.getSizeY() > r2.getPosY();
    }

    // ── Círculo vs Círculo ────────────────────────────────────────────
    private static boolean circleCircle(Cercle c1, Cercle c2) {
        double dx = c1.getPosX() - c2.getPosX();
        double dy = c1.getPosY() - c2.getPosY();
        double distancia = Math.sqrt(dx * dx + dy * dy);
        return distancia < (c1.radi + c2.radi);
    }

    // ── Rectángulo vs Círculo ─────────────────────────────────────────
    private static boolean rectCircle(Rectangle r, Cercle c) {
        // ✅ Centro del círculo, no esquina
        double circleCenterX = c.getPosX() + c.radi;
        double circleCenterY = c.getPosY() + c.radi;

        double nearestX = Math.max(r.getPosX(), Math.min(circleCenterX, r.getPosX() + r.getSizeX()));
        double nearestY = Math.max(r.getPosY(), Math.min(circleCenterY, r.getPosY() + r.getSizeY()));

        double dx = circleCenterX - nearestX;
        double dy = circleCenterY - nearestY;
        return Math.sqrt(dx * dx + dy * dy) < c.radi;
    }

}