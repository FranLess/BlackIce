package main;

public class Mensajes {
    public static void mostrarOpcionesCaballero() {
        System.out.println("### Turno del Caballero ###");
        System.out.println("[1] Espiritu Guerrero");
        System.out.println("[2] Bloquear");
        System.out.println("[3] Tomahawk");
        System.out.print("Elige tu movimiento: ");
    }

    public static void mostrarOpcionesMago() {
        System.out.println("### Turno del Mago ###");
        System.out.println("[1] Relampago");
        System.out.println("[2] Sanación");
        System.out.println("[3] Veneno");
        System.out.print("Elige tu movimiento: ");
    }

    public static void mostrarInicioPve(String w, String c) {
        System.out.println("Bienvenidos sean " + w + " Mago y " + c + " Caballero!!!");
        System.out.println("Vos enfrentaráis al dragon...");
        System.out.println("Nobles aventureros tened en cuenta las siguientes pautas:");
        System.out.println("1. Los ataques normales casi nunca fallan.");
        System.out.println("2. Los movimientos defensivos y especiales tienen mayor probalidada de fallar.");
        System.out.println("3. Entre más daño recibe el dragón es más probable que no ataque.");
        System.out.println("¡SUERTE EN VUESTRA AVENTURA!");
        System.out.println("\n Iniciar...");
    }
}
