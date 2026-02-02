package es.cide.programacio;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class Main {
    public static void main(String[] args) {
        FlatDarculaLaf.setup();

        CalculadoraVisual calculadora = new CalculadoraVisual();
    }
}