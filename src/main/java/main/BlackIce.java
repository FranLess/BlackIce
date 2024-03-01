package main;

import java.util.Scanner;

public class BlackIce {
    public static void main(String[] args) throws Exception {

        System.out.println("Bienvenido ¿qué deseaís jugar?: ");
        System.out.println("1. PvP");
        System.out.println("2. PvE");
        Scanner sc = new Scanner(System.in);
        switch (sc.nextLine()) {
            case "1":
                PvP multiplayer = new PvP();
                multiplayer.Game();
                break;
            case "2":
                PvE coop = new PvE();
                coop.Mission();
                break;
            default:
                break;
        }
    sc.close();
    }
}