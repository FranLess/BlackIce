
package main;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Console {

    public static void main(String[] args) {
        // Define la animación ASCII como una cadena
        String animation = "  __ _\n / _| |_   _ ___\n| |_| | | | / __|\n|  _| | |_| \\__ \\\n|_| |_\\__,_|___/";

        // Crea una ventana emergente
        JFrame frame = new JFrame("ASCII Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Crea un área de texto para mostrar la animación ASCII
        JTextArea textArea = new JTextArea();
        textArea.setText(animation);

        // Agrega el área de texto a la ventana
        frame.add(new JScrollPane(textArea));

        // Centra la ventana en la pantalla
        frame.setLocationRelativeTo(null);

        // Hace visible la ventana
        frame.setVisible(true);
    }

}
