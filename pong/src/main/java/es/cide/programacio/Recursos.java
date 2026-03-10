package es.cide.programacio;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

public class Recursos {

    public static final Image FONDO_MENU = cargarImagen("/menu.png");

    public static final Image BOTO_COMENCAR_IMG = cargarImagen("/b_comenzar.png");
    public static final Image BOTO_INSTRUCCIONS_IMG = cargarImagen("/b_instrucciones.png");
    public static final Image BOTO_PANTALLACOMPLETA_IMG = cargarImagen("/b_pantallaCompleta.png");
    public static final Image BOOT_SALIR_IMG = cargarImagen("/b_salir.png");

    public static final Image BOTO_ATRAS_IMG = escalar(cargarImagen("/b_atras.png"));
    public static final Image BOTO_AJUSTES_IMG = cargarImagen("/b_ajustes.png");

    private static Image cargarImagen(String ruta) {
        try {
            URL url = Recursos.class.getResource(ruta);
            if (url != null)
                return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Image escalar(Image img) {
        if (img == null)
            return null;
        return img.getScaledInstance(300, 60, Image.SCALE_SMOOTH);
    }
}