package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
// import java.util.Timer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.*;
import java.awt.*;

public class Animation {
    private File dir;

    public Animation(String dir) {
        this.dir = new File(dir);
        if (!this.dir.isDirectory())
            try {
                throw new Exception("La animación debe contener un directorio");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void start(int miliseconds) throws InterruptedException, IOException {
        JFrame frame = new JFrame("ASCII Animation");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Centra la ventana en la pantalla
        frame.setLocationRelativeTo(null);
        IntStream.range(0, 3).forEach(i -> {
            try {
                readAnimation(miliseconds, frame);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        frame.dispose();
    };

    public void readAnimation(int miliseconds, JFrame frame) throws InterruptedException, IOException {

        // Crea un área de texto para mostrar la animación ASCII
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 2));
        frame.add(new JScrollPane(textArea));
        for (File file : this.dir.listFiles((dir, name) -> name.endsWith(".txt"))) {
            Path path = Paths.get(file.getPath());
            String animation = "";
            List<String> lines = Files.lines(path).collect(Collectors.toList());
            for (String line : lines) {
                // System.out.println(line);
                animation += line + "\n";
            }
            textArea.setText(animation);
            // stop();
            // Hace visible la ventana
            frame.setVisible(true);
            Thread.sleep(miliseconds);
            Helper.cleanConsole();
            textArea.setText("");
        }
    };

    // public void start(boolean shouldCleanConsole) throws InterruptedException,
    // IOException {
    // active = true;
    // while (active) {
    // for (File file : this.dir.listFiles((dir, name) -> name.endsWith(".txt"))) {
    // Path path = Paths.get(file.getPath());
    // List<String> lines = Files.lines(path).collect(Collectors.toList());
    // for (String line : lines) {
    // System.out.println(line);
    // }
    // // stop();
    // if (shouldCleanConsole)
    // Helper.cleanConsole();
    // }
    // }
    // };

    // public void startWithPng(int miliseconds) throws Exception {
    // active = true;
    // while (active) {
    // for (File file : this.dir.listFiles((dir, name) -> name.endsWith(".png"))) {
    // ASCIIArtCreator.printAndPerformActionsAfterScaling("200", file);
    // // stop();
    // Thread.sleep(miliseconds);
    // Helper.cleanConsole();
    // }
    // }
    // }

    // public void stop() throws IOException {
    // System.out.print("Presiona cualquier tecla para continuar o 'q' para salir:
    // ");
    // int key = System.in.read();
    // if (key == 'q') {
    // active = false;
    // }
    // }
}
