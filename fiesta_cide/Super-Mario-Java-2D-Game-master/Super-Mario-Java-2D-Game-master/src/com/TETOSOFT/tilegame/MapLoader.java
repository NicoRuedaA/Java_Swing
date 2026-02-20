package com.TETOSOFT.tilegame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import com.TETOSOFT.graphics.*;
import com.TETOSOFT.tilegame.sprites.*;

/**
 * The MapLoader class loads and manages tile Images and
 * "host" Sprites used in the game. Game Sprites are cloned from
 * "host" Sprites.
 */
public class MapLoader {
    private ArrayList tiles;
    public int currentMap;
    private GraphicsConfiguration gc;

    // host sprites used for cloning
    private Sprite playerSprite;
    private Sprite musicSprite;
    private Sprite coinSprite;
    private Sprite goalSprite;
    private Sprite grubSprite;
    private Sprite flySprite;

    public MapLoader(GraphicsConfiguration gc) {
        this.gc = gc;
        loadTileImages();
        loadCreatureSprites();
        loadPowerUpSprites();
    }

    public Image loadImage(String name) {
        String filename = "images/" + name;
        return new ImageIcon(filename).getImage();
    }

    public Image getMirrorImage(Image image) {
        return getScaledImage(image, -1, 1);
    }

    public Image getFlippedImage(Image image) {
        return getScaledImage(image, 1, -1);
    }

    private Image getScaledImage(Image image, float x, float y) {
        AffineTransform transform = new AffineTransform();
        transform.scale(x, y);
        transform.translate(
                (x - 1) * image.getWidth(null) / 2,
                (y - 1) * image.getHeight(null) / 2);

        Image newImage = gc.createCompatibleImage(
                image.getWidth(null),
                image.getHeight(null),
                Transparency.BITMASK);

        Graphics2D g = (Graphics2D) newImage.getGraphics();
        g.drawImage(image, transform, null);
        g.dispose();

        return newImage;
    }

    public TileMap loadNextMap() {
        TileMap map = null;
        while (map == null) {
            currentMap++;
            try {
                map = loadMap("maps/map" + currentMap + ".txt");
            } catch (IOException ex) {
                if (currentMap == 2)
                    return null;
                currentMap = 0;
                map = null;
            }
        }
        return map;
    }

    public TileMap reloadMap() {
        try {
            return loadMap("maps/map" + currentMap + ".txt");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private TileMap loadMap(String filename) throws IOException {
        ArrayList lines = new ArrayList();
        int width = 0;
        int height = 0;

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                reader.close();
                break;
            }
            if (!line.startsWith("#")) {
                lines.add(line);
                width = Math.max(width, line.length());
            }
        }

        height = lines.size();
        TileMap newMap = new TileMap(width, height);
        for (int y = 0; y < height; y++) {
            String line = (String) lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                char ch = line.charAt(x);
                int tile = ch - 'A';
                if (tile >= 0 && tile < tiles.size()) {
                    newMap.setTile(x, y, (Image) tiles.get(tile));
                } else if (ch == 'o') {
                    addSprite(newMap, coinSprite, x, y);
                } else if (ch == '!') {
                    addSprite(newMap, musicSprite, x, y);
                } else if (ch == '*') {
                    addSprite(newMap, goalSprite, x, y);
                } else if (ch == '1') {
                    addSprite(newMap, grubSprite, x, y);
                } else if (ch == '2') {
                    addSprite(newMap, flySprite, x, y);
                }
            }
        }

        Sprite player = (Sprite) playerSprite.clone();
        player.setX(TileMapDrawer.tilesToPixels(3));
        player.setY(lines.size());
        newMap.setPlayer(player);

        return newMap;
    }

    private void addSprite(TileMap map, Sprite hostSprite, int tileX, int tileY) {
        if (hostSprite != null) {
            Sprite sprite = (Sprite) hostSprite.clone();
            sprite.setX(TileMapDrawer.tilesToPixels(tileX) + (TileMapDrawer.tilesToPixels(1) - sprite.getWidth()) / 2);
            sprite.setY(TileMapDrawer.tilesToPixels(tileY + 1) - sprite.getHeight());
            map.addSprite(sprite);
        }
    }

    public void loadTileImages() {
        tiles = new ArrayList();
        char ch = 'A';
        while (true) {
            String name = ch + ".png";
            File file = new File("images/" + name);
            if (!file.exists())
                break;
            tiles.add(loadImage(name));
            ch++;
        }
    }

    public void loadCreatureSprites() {
        Image[][] images = new Image[4][];

        // 0: Original, 1: Mirror (Derecha), 2: Flipped, 3: Flipped Mirror
        images[0] = new Image[] {
                loadImage("player.png"),
                loadImage("fly1.png"),
                loadImage("fly2.png"),
                loadImage("fly3.png"),
                loadImage("grub1.png"),
                loadImage("grub2.png"),
        };

        images[1] = new Image[images[0].length];
        images[2] = new Image[images[0].length];
        images[3] = new Image[images[0].length];

        for (int i = 0; i < images[0].length; i++) {
            images[1][i] = getMirrorImage(images[0][i]);
            images[2][i] = getFlippedImage(images[0][i]);
            images[3][i] = getFlippedImage(images[1][i]);
        }

        Animation[] playerAnim = new Animation[4];
        Animation[] flyAnim = new Animation[4];
        Animation[] grubAnim = new Animation[4];

        for (int i = 0; i < 4; i++) {
            playerAnim[i] = createPlayerAnim(images[i][0]);
            flyAnim[i] = createFlyAnim(images[i][1], images[i][2], images[i][3]);
            grubAnim[i] = createGrubAnim(images[i][4], images[i][5]);
        }

        // CORRECCIÓN: Invertimos índices 0 y 1 para que el giro coincida con el motor
        // Ahora playerAnim[1] (Mirror) se asigna a la dirección "Derecha" del
        // constructor
        playerSprite = new Player(playerAnim[1], playerAnim[0], playerAnim[3], playerAnim[2]);

        flySprite = new Fly(flyAnim[0], flyAnim[1], flyAnim[2], flyAnim[3]);
        grubSprite = new Grub(grubAnim[0], grubAnim[1], grubAnim[2], grubAnim[3]);
    }

    private Animation createPlayerAnim(Image playerSheet) {
        Animation anim = new Animation();

        // ============================================================
        // EDITAR AQUÍ: Define qué frames quieres para el movimiento
        // Ejemplo: {0, 1, 2, 3} usa los primeros 4 frames para caminar.
        // ============================================================
        int[] framesCaminar = { 0, 1, 2 };
        int frameIdle = 4; // El 5º sprite (índice 4)

        int totalFramesInSheet = 5;
        int width = playerSheet.getWidth(null) / totalFramesInSheet;
        int height = playerSheet.getHeight(null);

        // 1. Recortamos todos los frames primero
        BufferedImage[] framesExtraidos = new BufferedImage[totalFramesInSheet];
        for (int i = 0; i < totalFramesInSheet; i++) {
            framesExtraidos[i] = gc.createCompatibleImage(width, height, Transparency.BITMASK);
            Graphics g = framesExtraidos[i].getGraphics();
            g.drawImage(playerSheet, 0, 0, width, height, i * width, 0, (i + 1) * width, height, null);
            g.dispose();
        }

        // 2. Introducimos en la animación solo los de caminar (con duración 100ms)
        for (int i : framesCaminar) {
            anim.addFrame(framesExtraidos[i], 100);
        }

        // 3. Introducimos el de Idle AL FINAL con duración 0
        // Al poner duración 0, el método update() de Animation lo saltará
        // automáticamente
        // pero estará disponible para cuando estemos quietos.
        anim.addFrame(framesExtraidos[frameIdle], 0);

        return anim;
    }

    private Animation createFlyAnim(Image img1, Image img2, Image img3) {
        Animation anim = new Animation();
        anim.addFrame(img1, 50);
        anim.addFrame(img2, 50);
        anim.addFrame(img3, 50);
        anim.addFrame(img2, 50);
        return anim;
    }

    private Animation createGrubAnim(Image img1, Image img2) {
        Animation anim = new Animation();
        anim.addFrame(img1, 250);
        anim.addFrame(img2, 250);
        return anim;
    }

    private void loadPowerUpSprites() {
        Animation anim = new Animation();
        anim.addFrame(loadImage("heart.png"), 150);
        goalSprite = new PowerUp.Goal(anim);

        anim = new Animation();
        anim.addFrame(loadImage("coin1.png"), 250);
        anim.addFrame(loadImage("coin2.png"), 250);
        anim.addFrame(loadImage("coin3.png"), 250);
        anim.addFrame(loadImage("coin4.png"), 250);
        anim.addFrame(loadImage("coin5.png"), 250);
        coinSprite = new PowerUp.Star(anim);

        anim = new Animation();
        anim.addFrame(loadImage("music1.png"), 150);
        anim.addFrame(loadImage("music2.png"), 150);
        anim.addFrame(loadImage("music3.png"), 150);
        anim.addFrame(loadImage("music2.png"), 150);
        musicSprite = new PowerUp.Music(anim);
    }
}