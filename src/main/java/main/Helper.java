package main;
public class Helper {
        // Limpia la consola en que se ejecuta
    public static void cleanConsole() {
        // Comprobar si el sistema es compatible con ANSI
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            // Si es Windows, se puede usar el comando "cls"
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Si es otro sistema operativo, se puede usar la secuencia ANSI para borrar la
            // pantalla
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    /**
     * @param message
     * @throws InterruptedException
     */
    public static void CharginAnimation(String message) throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            System.out.print(message + "| ");
            Thread.sleep(500); // Pausa de 0.5 segundos
            cleanConsole();

            System.out.print(message + "/ ");
            Thread.sleep(500);
            cleanConsole();

            System.out.print(message + "- ");
            Thread.sleep(500);
            cleanConsole();

            System.out.print(message + "\\ ");
            Thread.sleep(500);
            cleanConsole();

            System.out.println();
        }
    }

    /**
     * @throws InterruptedException
     */
    public static void CharginAnimation() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            Thread.sleep(500); // Pausa de 0.5 segundos
            cleanConsole();

            System.out.print("/ ");
            Thread.sleep(500);
            cleanConsole();

            System.out.print("- ");
            Thread.sleep(500);
            cleanConsole();

            System.out.print("\\ ");
            Thread.sleep(500);
            cleanConsole();

            System.out.println();
        }
    }


}
