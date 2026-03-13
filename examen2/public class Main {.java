public class Main {
    
}package es.cide.programacio;

public class Triangle implements Collidable {

    double x1, y1; // vértice 1
    double x2, y2; // vértice 2
    double x3, y3; // vértice 3
    double xVel, yVel;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3,
            double xVel, double yVel) {
        this(x1, y1, x2, y2, x3, y3);
        this.xVel = xVel;
        this.yVel = yVel;
    }

    @Override
    public double getPosX() {
        return (x1 + x2 + x3) / 3.0;
    } // centroide

    @Override
    public double getPosY() {
        return (y1 + y2 + y3) / 3.0;
    }

    @Override
    public String getShapeType() {
        return "triangle";
    }

    public double[][] getVertices() {
        return new double[][] { { x1, y1 }, { x2, y2 }, { x3, y3 } };
    }
}

package es.cide.programacio;

public class Polygon implements Collidable {

    double[] xPoints;
    double[] yPoints;
    double xVel, yVel;

    public Polygon(double[] xPoints, double[] yPoints) {
        if (xPoints.length != yPoints.length || xPoints.length < 3) {
            throw new IllegalArgumentException("Se necesitan al menos 3 vértices");
        }
        this.xPoints = xPoints;
        this.yPoints = yPoints;
    }

    public Polygon(double[] xPoints, double[] yPoints, double xVel, double yVel) {
        this(xPoints, yPoints);
        this.xVel = xVel;
        this.yVel = yVel;
    }

    @Override
    public double getPosX() { // centroide
        double sum = 0;
        for (double x : xPoints)
            sum += x;
        return sum / xPoints.length;
    }

    @Override
    public double getPosY() {
        double sum = 0;
        for (double y : yPoints)
            sum += y;
        return sum / yPoints.length;
    }

    @Override
    public String getShapeType() {
        return "polygon";
    }

    public double[][] getVertices() {
        double[][] vertices = new double[xPoints.length][2];
        for (int i = 0; i < xPoints.length; i++) {
            vertices[i][0] = xPoints[i];
            vertices[i][1] = yPoints[i];
        }
        return vertices;
    }

    public int getNumSides() {
        return xPoints.length;
    }
}

package es.cide.programacio;

public class CollisionDetector {

    public static boolean collides(Collidable a, Collidable b) {
        String typeA = a.getShapeType();
        String typeB = b.getShapeType();

        // Círculo vs Círculo
        if (typeA.equals("circle") && typeB.equals("circle"))
            return circleCircle((Cercle) a, (Cercle) b);

        // Círculo vs Polígono (en cualquier orden)
        if (typeA.equals("circle") && isPolygonType(typeB))
            return circlePolygon((Cercle) a, toVertices(b));
        if (isPolygonType(typeA) && typeB.equals("circle"))
            return circlePolygon((Cercle) b, toVertices(a));

        // Polígono vs Polígono (rectangle, triangle, polygon — todos)
        if (isPolygonType(typeA) && isPolygonType(typeB))
            return sat(toVertices(a), toVertices(b));

        return false;
    }

    // Considera rectangle, triangle y polygon como polígonos
    private static boolean isPolygonType(String type) {
        return type.equals("rectangle") || type.equals("triangle") || type.equals("polygon");
    }

    // Convierte cualquier figura poligonal a lista de vértices
    private static double[][] toVertices(Collidable shape) {
        if (shape instanceof Rectangle r) {
            return new double[][] {
                    { r.getPosX(), r.getPosY() },
                    { r.getPosX() + r.getSizeX(), r.getPosY() },
                    { r.getPosX() + r.getSizeX(), r.getPosY() + r.getSizeY() },
                    { r.getPosX(), r.getPosY() + r.getSizeY() }
            };
        }
        if (shape instanceof Triangle t)
            return t.getVertices();
        if (shape instanceof Polygon p)
            return p.getVertices();
        throw new IllegalArgumentException("Figura no reconocida");
    }

    // ── SAT: Separating Axis Theorem ─────────────────────────────────
    // Funciona para cualquier par de polígonos convexos
    private static boolean sat(double[][] vertsA, double[][] vertsB) {
        // Comprueba los ejes normales de A y luego los de B
        return !hasSeperatingAxis(vertsA, vertsB) && !hasSeperatingAxis(vertsB, vertsA);
    }

    private static boolean hasSeperatingAxis(double[][] vertsA, double[][] vertsB) {
        int n = vertsA.length;
        for (int i = 0; i < n; i++) {
            // Arista entre vértice i y el siguiente
            double edgeX = vertsA[(i + 1) % n][0] - vertsA[i][0];
            double edgeY = vertsA[(i + 1) % n][1] - vertsA[i][1];

            // Normal perpendicular al eje
            double axisX = -edgeY;
            double axisY = edgeX;

            double[] projA = project(vertsA, axisX, axisY);
            double[] projB = project(vertsB, axisX, axisY);

            // Si las proyecciones no se solapan → hay separación → no colisionan
            if (projA[1] < projB[0] || projB[1] < projA[0])
                return true;
        }
        return false;
    }

    // Proyecta todos los vértices sobre un eje y devuelve [min, max]
    private static double[] project(double[][] verts, double axisX, double axisY) {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (double[] v : verts) {
            double dot = v[0] * axisX + v[1] * axisY;
            if (dot < min)
                min = dot;
            if (dot > max)
                max = dot;
        }
        return new double[] { min, max };
    }

    // ── Círculo vs Polígono ───────────────────────────────────────────
    private static boolean circlePolygon(Cercle c, double[][] verts) {
        int n = verts.length;
        for (int i = 0; i < n; i++) {
            double nearX = Math.max(Math.min(c.getPosX(),
                    Math.max(verts[i][0], verts[(i + 1) % n][0])),
                    Math.min(verts[i][0], verts[(i + 1) % n][0]));
            double nearY = Math.max(Math.min(c.getPosY(),
                    Math.max(verts[i][1], verts[(i + 1) % n][1])),
                    Math.min(verts[i][1], verts[(i + 1) % n][1]));

            double dx = c.getPosX() - nearX;
            double dy = c.getPosY() - nearY;
            if (Math.sqrt(dx * dx + dy * dy) < c.radi)
                return true;
        }
        // Comprobar también si el centro del círculo está dentro del polígono
        return pointInPolygon(c.getPosX(), c.getPosY(), verts);
    }

    // Ray casting: comprueba si un punto está dentro de un polígono
    private static boolean pointInPolygon(double px, double py, double[][] verts) {
        boolean inside = false;
        int n = verts.length;
        for (int i = 0, j = n - 1; i < n; j = i++) {
            if ((verts[i][1] > py) != (verts[j][1] > py) &&
                    px < (verts[j][0] - verts[i][0]) * (py - verts[i][1])
                            / (verts[j][1] - verts[i][1]) + verts[i][0]) {
                inside = !inside;
            }
        }
        return inside;
    }

    // ── Círculo vs Círculo ────────────────────────────────────────────
    private static boolean circleCircle(Cercle c1, Cercle c2) {
        double dx = c1.getPosX() - c2.getPosX();
        double dy = c1.getPosY() - c2.getPosY();
        return Math.sqrt(dx * dx + dy * dy) < (c1.radi + c2.radi);
    }
}