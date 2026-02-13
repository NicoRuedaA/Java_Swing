package es.cide.programacio;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class Main {
    public static void main(String[] args) {
        // 1. Iniciamos el tema visual
        // FlatDarkLaf.setup();

        // 2. Instanciamos las 3 capas
        CalculadoraVisual vista = new CalculadoraVisual();
        CalculadoraLogica logica = new CalculadoraLogica();

        // 3. El controlador las une
        CalculadoraListener calculadora = new CalculadoraListener(vista, logica);

        vista.setVisible(true);
    }
}