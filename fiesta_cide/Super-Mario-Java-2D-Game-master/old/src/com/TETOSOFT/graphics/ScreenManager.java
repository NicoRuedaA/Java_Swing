package com.TETOSOFT.graphics;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class ScreenManager {

    private JFrame frame;

    public void setWindowedMode(int width, int height) {
        frame = new JFrame("SuperMiro Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIgnoreRepaint(true);
        frame.setResizable(false);

        // Ajusta el area de contenido (excluye barra de titulo y bordes)
        frame.getContentPane().setPreferredSize(new Dimension(width, height));
        frame.pack();
        frame.setLocationRelativeTo(null); // centrado en pantalla
        frame.setVisible(true);

        frame.createBufferStrategy(2);
    }

    public Graphics2D getGraphics() {
        if (frame != null) {
            BufferStrategy strategy = frame.getBufferStrategy();
            return (Graphics2D) strategy.getDrawGraphics();
        }
        return null;
    }

    public void update() {
        if (frame != null) {
            BufferStrategy strategy = frame.getBufferStrategy();
            if (!strategy.contentsLost()) {
                strategy.show();
            }
        }
        Toolkit.getDefaultToolkit().sync();
    }

    public JFrame getWindow() {
        return frame;
    }

    public int getWidth() {
        return frame != null ? frame.getContentPane().getWidth() : 0;
    }

    public int getHeight() {
        return frame != null ? frame.getContentPane().getHeight() : 0;
    }

    public void restoreScreen() {
        if (frame != null) {
            frame.dispose();
            frame = null;
        }
    }

    public BufferedImage createCompatibleImage(int w, int h, int transparency) {
        if (frame != null) {
            GraphicsConfiguration gc = frame.getGraphicsConfiguration();
            return gc.createCompatibleImage(w, h, transparency);
        }
        return null;
    }
}
