package es.cide.programacio;

import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.formdev.flatlaf.extras.FlatSVGIcon;

public class Calculadora extends CalculadoraLogica {

    public static CalculadoraLogica a() {
        return CalculadoraLogica();
    }

    public static CalculadoraVisual b() {
        return CalculadoraVisual();
    }

}
