import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JLabel lblImagen;
    private JRadioButton rbWindows, rbLinux, rbMac;
    private ButtonGroup grupoSO;

    public SelectorSistemas() {
        // Configuración básica de la ventana
        setTitle("Selector de Sistema Operativo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 1. Panel de opciones (RadioButtons)
        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new FlowLayout());

        rbWindows = new JRadioButton("Windows");
        rbLinux = new JRadioButton("Linux");
        rbMac = new JRadioButton("macOS");

        // Agrupamos los botones para que la selección sea exclusiva
        grupoSO = new ButtonGroup();
        grupoSO.add(rbWindows);
        grupoSO.add(rbLinux);
        grupoSO.add(rbMac);

        panelOpciones.add(rbWindows);
        panelOpciones.add(rbLinux);
        panelOpciones.add(rbMac);

        // 2. Label para mostrar la imagen
        lblImagen = new JLabel("Selecciona un sistema", SwingConstants.CENTER);
        lblImagen.setPreferredSize(new Dimension(200, 150));

        // 3. Eventos (ActionListeners)
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = e.getActionCommand();
                cambiarImagen(seleccion);
            }
        };

        rbWindows.addActionListener(listener);
        rbLinux.addActionListener(listener);
        rbMac.addActionListener(listener);

        // Añadir componentes al frame
        add(panelOpciones, BorderLayout.NORTH);
        add(lblImagen, BorderLayout.CENTER);

        setVisible(true);
    }

    private void cambiarImagen(String so) {
        // Nota: Asegúrate de tener las imágenes en la carpeta raíz de tu proyecto
        // o cambia la ruta a una URL de internet para probar.
        String ruta = "";

        switch (so) {
            case "Windows":
                ruta = "windows_icon.png";
                break;
            case "Linux":
                ruta = "linux_icon.png";
                break;
            case "macOS":
                ruta = "mac_icon.png";
                break;
        }

        try {
            ImageIcon iconoOriginal = new ImageIcon(ruta);
            // Reescalamos la imagen para que no rompa el diseño (100x100 px)
            Image imgEscalada = iconoOriginal.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(imgEscalada));
            lblImagen.setText(""); // Quitamos el texto cuando hay imagen
        } catch (Exception e) {
            lblImagen.setText("No se encontró la imagen de " + so);
        }
    }

    public static void main(String[] args) {
        // Ejecución en el hilo de Swing
        SwingUtilities.invokeLater(() -> new SelectorSistemas());
    }
}