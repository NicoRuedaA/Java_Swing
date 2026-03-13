package es.cide.programacio.examen;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

//classe per carrger imatges i mantener el codi net
public class Recursos {

    // img asteroides
    public static final Image TIE = cargarImagen("/tie.png");
    // img falcon
    public static final Image MF = cargarImagen("/mf.png");
    public static final Image BOTO_COMENCAR_IMG = escalar(cargarImagen("/menu.png"));

    // carregar img
    private static Image cargarImagen(String ruta) {
        // iintentam carregar img
        try {
            URL url = Recursos.class.getResource(ruta);
            if (url != null)
                return ImageIO.read(url);
        }
        // en el cas de no trobar img....
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // metode per escalar image
    public static Image escalar(Image img) {
        if (img == null)
            return null;
        return img.getScaledInstance(155, 200, Image.SCALE_SMOOTH);
    }
}