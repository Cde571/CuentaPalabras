import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// Usa la interfaz Map en lugar de la clase HashTable, que es obsoleta.
class CuentaTipoPalabras extends JFrame {
    private JTextArea campoEntrada;
    private JLabel indicador;
    private JTextArea pantalla;
    private JButton botonIniciar;

    // Usa la interfaz Map en lugar de la clase HashTable, que es obsoleta.
    private Map<String, Integer> tabla;

    public CuentaTipoPalabras() {
        super("Cuenta de tipos de palabras");
        campoEntrada = new JTextArea(3, 20);
        // Inicializa la tabla como un HashMap.
        tabla = new HashMap<>();
        botonIniciar = new JButton("Inicio");
        botonIniciar.addActionListener(new ActionListener() {
            // El método actionPerformed solo debe tener una anotación @Override.
            @Override
            public void actionPerformed(ActionEvent e) {
                crearTabla();
                pantalla.setText(crearSalida());
            }
        });
        indicador = new JLabel("Escriba una palabra:"); // Corrección de ortografía en "Escriba".
        pantalla = new JTextArea(15, 30); // Se debe inicializar pantalla.
        pantalla.setEditable(false);
        JScrollPane mostrarPanelDesplazable = new JScrollPane(pantalla);
        Container contenedor = getContentPane();
        contenedor.setLayout(new FlowLayout());
        contenedor.add(indicador);
        contenedor.add(campoEntrada);
        contenedor.add(botonIniciar);
        contenedor.add(mostrarPanelDesplazable);
        setSize(400, 400);
        setVisible(true);
    }

    private String crearSalida() {
        StringBuilder salida = new StringBuilder();
        // Itera sobre las entradas de la tabla y construye la salida.
        for (Map.Entry<String, Integer> entry : tabla.entrySet()) {
            salida.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return salida.toString();
    }

    private void crearTabla() {
        String entrada = campoEntrada.getText();
        StringTokenizer texto = new StringTokenizer(entrada);
        while (texto.hasMoreTokens()) {
            String palabra = texto.nextToken().toLowerCase();
            // Si la tabla ya tiene la palabra, incrementa su valor en 1.
            if (tabla.containsKey(palabra)) {
                tabla.put(palabra, tabla.get(palabra) + 1);
            } else { // Si no, agrega la palabra con un valor de 1.
                tabla.put(palabra, 1);
            }
        }
    }

    public static void main(String[] args) {
        // Crea una instancia de CuentaTipoPalabras y muestra la ventana.
        new CuentaTipoPalabras().setVisible(true);
    }
}
