package es.cide.programacio;

//import del tema
import com.formdev.flatlaf.FlatDarkLaf;

public class Main {
    public static void main(String[] args) {
        // tema visual
        FlatDarkLaf.setup();

        CalculadoraVisual vista = new CalculadoraVisual();
        CalculadoraLogica logica = new CalculadoraLogica();

        CalculadoraListener calculadora = new CalculadoraListener(vista, logica);

    }
}