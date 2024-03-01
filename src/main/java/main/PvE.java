package main;

import java.util.Random;
import java.util.Scanner;

public class PvE {
    
    public void Mission() {
        Scanner sc = new Scanner(System.in); String w,c; // Intro
        System.out.print("Mago escribe tu nombre: ");
        w = sc.nextLine();
        System.out.print("Caballero escribe tu nombre: ");
        c = sc.nextLine();
        System.out.println("Bienvenidos sean "+w+" Mago y "+c+" Caballero!!!");
        System.out.println("Vos enfrentaráis al dragon...");
        Caballero cab = new Caballero(c); // Constructores
        Mago wiz = new Mago(w);
        Dragon drag = new Dragon();
        
        boolean first_round = true;
        int hc_heal = 0, hc_block = 0, hc_tomahawk = 0, hc_poison = 0; // handicaps
        while (true){ // GAME ON!!
            System.out.println("### Turno del Mago ###");
            System.out.println("[1] Relampago");
            System.out.println("[2] Sanación");
            System.out.println("[3] Veneno");
            System.out.print("Elige tu movimiento: ");
            switch (sc.nextLine()) {
                case "1":
                    if (Posibilidad(94,0)) {wiz.atacar();}
                    else {System.out.println("El ataque falló...");}
                    break;
                case "2":
                    System.out.print("¿Para quien? [C]aballero o [M]ago");
                    switch (sc.nextLine()) {
                        case "C":
                            if (Posibilidad(98,hc_heal)) {
                                cab.regenerar(65);
                                hc_heal += 15;
                                System.out.println("Caballero regenera 65 hp...");
                            } else {
                                System.out.println("La curación falló...");
                            } break;
                        default:
                            if (Posibilidad(98,hc_heal)) {
                                wiz.regenerar(65);
                                hc_heal += 15;
                                System.out.println("Mago regenera 65 hp...");
                            } else {
                                System.out.println("La curación falló...");
                            }
                    }
                    break;
                case "3":
                    if (Posibilidad(95,hc_poison)){
                        wiz.veneno();
                        hc_poison += 15;
                    } else {System.out.println("El veneno falló...");}
                    break;
                default:
                    if (Posibilidad(94,0)) {wiz.atacar();}
                    else {System.out.println("El ataque falló...");}
            }
            System.out.println("### Turno del Caballero ###");
            System.out.println("[1] Espiritu Guerrero");
            System.out.println("[2] Bloquear");
            System.out.println("[3] Tomahawk");
            System.out.print("Elige tu movimiento: ");
            switch (sc.nextLine()) {
                case "1":
                    if (Posibilidad(95,0)) {cab.atacar();}
                    else {System.out.println("El ataque falló...");}
                    break;
                case "2":
                    System.out.print("¿A quien? [C]aballero o [M]ago");
                    switch (sc.nextLine()) {
                        case "C":
                            if (Posibilidad(96,hc_block)) {
                                cab.bloquear();// modo defensa
                                hc_block += 12;
                                System.out.println("Caballero se cubre...");
                            } else {
                                System.out.println("La defensa falló...");
                            } break;
                        default:
                            if (Posibilidad(96,hc_block)) {
                                wiz.curar();// modo defensa
                                hc_block += 12;
                                System.out.println("Mago es protegido...");
                            } else {
                                System.out.println("La defensa falló...");
                            }
                    }
                    break;
                case "3":
                    if (Posibilidad(95,hc_tomahawk)){
                        cab.tomahawk();
                        hc_tomahawk += 20;
                    } else {System.out.println("El Tomahawk falló...");}
                    break;
                default:
                    if (Posibilidad(95,0)) {cab.atacar();}
                    else {System.out.println("El ataque falló...");}
            }
            
            drag.Golpiza(wiz, cab, drag); // Dragon Time
            
            /*if (wiz.checkDeath() && cab.checkDeath()) { // se vale soñar
                System.out.println("Game Over! Dragon hizo una doble."); break;
            }*/
            if (wiz.checkDeath()) {
                System.out.println("Game Over! Mago murio."); break;
            }
            if (cab.checkDeath()) {
                System.out.println("Game Over! Caballero murio."); break;
            }
            if (drag.checkDeath()) {
                System.out.println("VICTORIA! HAN DERROTADO AL DRAGON!!"); break;
            }
            cab.enGuardia();
            wiz.enGuardia();
            
            if (!first_round) { // handicaps shit
                if (hc_heal>0) {hc_heal-=10;if (hc_heal<=0){hc_heal=0;}}
                if (hc_block>0) {hc_block-=10;if (hc_block<=0){hc_block=0;}}
                if (hc_tomahawk>0) {hc_tomahawk-=10;if (hc_tomahawk<=0){hc_tomahawk=0;}}
                if (hc_heal>0) {hc_heal-=10;if (hc_heal<=0){hc_heal=0;}}
            } else { first_round = false; }
        }
        
    }
    
    public boolean Posibilidad(int porcentaje, int handicap) {
        return new Random().nextInt(100) + 1 <= (porcentaje-handicap);
    }
    
}
