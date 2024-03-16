package main;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class PvE {
    public static final int HEAL_POINTS = 65;
    // HANDICAPS
    private int hc_heal = 0;
    private int hc_block = 0;
    private int hc_tomahawk = 0;
    private int hc_poison = 0;
    boolean first_round = true;
    private Mago wiz;
    private Caballero cab;
    private Dragon drag;
    Scanner sc = new Scanner(System.in);
    // Intro
    String wizName;
    String cabName;
    private Golpiza golpiza; // Dragon Time

    public void Mission() throws Exception {
        // SE INICIALIZA EL JUEGO
        inicializarJuego();

        while (true) { // GAME ON!!

            // MOSTRAR OPCIONES DEL MAGO
            Mensajes.mostrarOpcionesMago();
            switch (sc.nextLine()) {
                case "1": // CASO DE ATAQUE
                    wizAtack();
                    break;
                case "2": // CASO DE CURA
                    generalHeal();
                    break;
                case "3": // CASO DE ULTI
                    wizUlt();
                    break;
                default:
                    wizAtack();
            }

            // OPCIONES DE CABALLERO
            Mensajes.mostrarOpcionesCaballero();
            switch (sc.nextLine()) {
                case "1":// ATAQUE DE CABALLERO
                    cabAtack();
                    break;
                case "2": // ESCUDO DE CABALLERO
                    generalShield();
                    break;
                case "3": // ULTI DE CABALLERO
                    cabUlt();
                    break;
                default:
                    cabAtack();
            }
            System.out.print("Continuar...");
            sc.nextLine();

            Helper.cleanConsole();
            golpiza.start();

            /*
             * if (wiz.checkDeath() && cab.checkDeath()) { // se vale soñar
             * System.out.println("Game Over! Dragon hizo una doble."); break;
             * }
             */
            if (wiz.checkDeath()) {
                System.out.println("Game Over! Mago murio.");
                break;
            }
            if (cab.checkDeath()) {
                System.out.println("Game Over! Caballero murio.");
                break;
            }
            if (drag.checkDeath()) {
                drag.animations.die.start();
                System.out.println("VICTORIA! HAN DERROTADO AL DRAGON!!");
                break;
            }
            handleHandicap();
            cab.enGuardia();
            wiz.enGuardia();

        }
        sc.close();

    }

    public void inicializarJuego() {
        System.out.print("Mago escribe tu nombre: ");
        wizName = sc.nextLine();
        System.out.print("Caballero escribe tu nombre: ");
        cabName = sc.nextLine();
        // MENSAJE DE INICIO
        Mensajes.mostrarInicioPve(wizName, cabName);
        sc.nextLine();
        Helper.cleanConsole();
        // CONSTRUCTORES
        cab = new Caballero(cabName);
        wiz = new Mago(wizName);
        drag = new Dragon();

        golpiza = new Golpiza(wiz, cab, drag);
    }

    public boolean Posibilidad(int porcentaje, int handicap) {
        return new Random().nextInt(100) + 1 <= (porcentaje - handicap);
    }

    public void wizHeal(Player player) throws InterruptedException, IOException {
        if (Posibilidad(98, hc_heal)) {
            player.regenerar(HEAL_POINTS);
            hc_heal += 15;

            // ANIMATION
            wiz.animations.cure.start();

            System.out.println("Caballero regenera 65 hp...");
        } else {
            System.out.println("La curación falló...");
        }
    }

    public void wizAtack() {
        if (Posibilidad(94, 0)) {
            wiz.atacar();
        } else {
            System.out.println("El ataque falló...");
        }
    }

    public void generalHeal() throws InterruptedException, IOException {
        System.out.print("¿Para quien? [C]aballero o [M]ago: ");
        switch (sc.nextLine()) {
            case "C":
                wizHeal(cab);
                break;
            default:
                wizHeal(wiz);
        }
    }

    public void wizUlt() {
        if (Posibilidad(95, hc_poison)) {
            wiz.veneno();
            hc_poison += 15;
        } else {
            System.out.println("El veneno falló...");
        }
    }

    public void cabAtack() {
        if (Posibilidad(95, 0)) {
            cab.atacar();
        } else {
            System.out.println("El ataque falló...");
        }
    }

    public void cabShield(Player player) throws InterruptedException, IOException {
        if (Posibilidad(96, hc_block)) {
            player.bloquear();// modo defensa
            hc_block += 12;

            // ANIMATION
            cab.animations.shield.start();
            System.out.println("Caballero se cubre...");
        } else {
            System.out.println("La defensa falló...");
        }
    }

    public void generalShield() throws InterruptedException, IOException {
        System.out.print("¿A quien? [C]aballero o [M]ago: ");
        switch (sc.nextLine()) {
            case "C":
                cabShield(cab);
                break;
            default:
                cabShield(wiz);
                break;
        }
    }

    public void cabUlt() {
        if (Posibilidad(95, hc_tomahawk)) {
            cab.tomahawk();
            hc_tomahawk += 20;
        } else {
            System.out.println("El Tomahawk falló...");
        }
    }

    public void handleHandicap() {
        if (!first_round) { // handicaps shit
            if (hc_heal > 0) {
                hc_heal -= 10;
                if (hc_heal <= 0) {
                    hc_heal = 0;
                }
            }
            if (hc_block > 0) {
                hc_block -= 10;
                if (hc_block <= 0) {
                    hc_block = 0;
                }
            }
            if (hc_tomahawk > 0) {
                hc_tomahawk -= 10;
                if (hc_tomahawk <= 0) {
                    hc_tomahawk = 0;
                }
            }
            if (hc_heal > 0) {
                hc_heal -= 10;
                if (hc_heal <= 0) {
                    hc_heal = 0;
                }
            }
        } else {
            first_round = false;
        }
    }

}
