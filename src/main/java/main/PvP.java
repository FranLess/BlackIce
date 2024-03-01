package main;

import java.util.Scanner;

public class PvP {

    public void Game() throws Exception {
        Scanner sc = new Scanner(System.in);
        String w, c;
        System.out.println("Bienvenido a la arena pvp campeones");
        System.out.print("Mago escribe tu nombre: ");
        w = sc.nextLine();
        Mago wiz = new Mago(w);
        System.out.print("Caballero escribe tu nombre: ");
        c = sc.nextLine();
        Caballero cab = new Caballero(c);
        System.out.println("vos " + w + " Mago y " + c + " Caballero se daran en la madre, buena suerte!");
        System.out.println("La dinámica es simple, como un piedra papel o tijera");
        System.out.println("Los movimientos defensivos bloquean ataques normales");
        System.out.println("Los ataques especiales rompen defensas");
        System.out.println("Los ataques especiales son débiles ante ataques normales");
        System.out.println();
        System.out.println("Es importante que: ");
        System.out.println("¡EL CABALLERO NO DEBE VER EL MOVIMIENTO DEL MAGO!");
        System.out.println("\n Iniciar...");
        sc.nextLine();
        Helper.cleanConsole();

        while (true) {
            System.out.println("### Turno del Mago ###");
            System.out.println("[1] Atacar (Relampago)");
            System.out.println("[2] Defender (Regeneras salud y esquivas ataques)");
            System.out.println("[3] Anti-Defensa (Veneno)");
            System.out.print("Elige tu movimiento: ");
            switch (sc.nextLine()) {
                case "1":
                    wiz.atacar();
                    break;
                case "2":
                    wiz.curar();
                    break;
                case "3":
                    wiz.veneno();
                    break;
                default:
                    wiz.atacar();
            }

            for (int i = 0; i < 20; i++) {
                System.out.println("XX######XX");
            }

            System.out.println("### Turno del Caballero ###");
            System.out.println("[1] Atacar (Espada)");
            System.out.println("[2] Defender (Escudo)");
            System.out.println("[3] Anti-Defensa (Tomahawk)");
            System.out.print("Elige tu movimiento: ");
            switch (sc.nextLine()) {
                case "1":
                    cab.atacar();
                    break;
                case "2":
                    cab.bloquear();
                    break;
                case "3":
                    cab.tomahawk();
                    break;
                default:
                    cab.atacar();
            }

            if (wiz.isAttacking() && cab.isAttacking()) {
                wiz.animations.electric.start();
                cab.animations.atack.start();
                wiz.impacto(70);
                cab.impacto(55); // mago pierde mas salud
                System.out.println("Mago utiliza Relampago pero las tecnicas de"
                        + "combate del Caballero le aventajan un poco");
                System.out.println("Mago recibe un impacto de 70 hp dejandolo "
                        + "con " + wiz.getSalud() + " hp.");
                System.out.println("Caballero recibe un impacto de 55 hp dejandolo "
                        + "con " + cab.getSalud() + " hp.");
            }
            if (wiz.isAttacking() && cab.isDefending()) {
                wiz.animations.electric.start();
                cab.animations.shield.start();
                cab.impacto(5);
                wiz.impacto(15); // caballero refleja el daño al bloquearlo
                System.out.println("Mago utiliza Relampago pero el Caballero estaba "
                        + "preparado reflejando el hechizo del mago con su Escudo");
                System.out.println("Mago recibe un ligero impacto de 15 hp dejandolo "
                        + "con " + wiz.getSalud() + " hp.");
                System.out.println("Caballero sintio algo quitandole 5 hp de su salud. "
                        + cab.getSalud() + " hp total");
            }
            if (wiz.isAttacking() && cab.isUlting()) {
                wiz.animations.electric.start();
                cab.animations.hawk.start();
                cab.impacto(70); // caballero falla y se castiga
                System.out.println("Mago utiliza Relampago y sorprende al Caballero "
                        + "con su guardia baja, Caballero es severamente penalizado");
                System.out.println("Caballero recibe un fuerte impacto de 70 hp "
                        + "dejandolo " + "con " + cab.getSalud() + " hp.");
            }
            if (wiz.isDefending() && cab.isAttacking()) {
                wiz.animations.cure.start();
                cab.animations.atack.start();
                wiz.regenerar(20); // mago esquiva y se regenera
                System.out.println("El Caballero ataca pero el Mago esquiva sus "
                        + "movimientos y se cura en el proceso");
                System.out.println("Mago regenera 20 hp obteniendo un total de "
                        + wiz.getSalud() + " hp");
            }
            if (wiz.isDefending() && cab.isDefending()) {
                wiz.animations.cure.start();
                cab.animations.shield.start();
                wiz.regenerar(45); // mago se regenera aun más
                System.out.println("El Mago decide regenerar salud y no es "
                        + "molestado por el Caballero");
                System.out.println("Mago regenera 45 hp obteniendo un total de "
                        + wiz.getSalud() + " hp");
            }
            if (wiz.isDefending() && cab.isUlting()) {
                wiz.animations.cure.start();
                cab.animations.hawk.start();
                wiz.impacto(45); // mago es castigado
                System.out.println("El Mago decide regenerar salud pero el "
                        + "pero es sorprendido por el Tomahawk del Caballero");
                System.out.println("Mago recibe un impacto de 45 hp dejandolo "
                        + "con " + wiz.getSalud() + " hp.");
            }
            if (wiz.isUlting() && cab.isAttacking()) {
                wiz.animations.poison.start();
                cab.animations.atack.start();
                wiz.impacto(75); // mago es castigado
                System.out.println("El Mago decide invocar un hechizo de envenenamiento "
                        + "pero fallo debido a las maniobras de combate del Caballero");
                System.out.println("Mago recibe un fuerte impacto de 75 hp dejandolo "
                        + "con " + wiz.getSalud() + " hp.");
            }
            if (wiz.isUlting() && cab.isDefending()) {
                wiz.animations.poison.start();
                cab.animations.shield.start();
                cab.impacto(70); // caballero es castigado
                System.out.println("El Caballero decide protegerse de un ataque "
                        + "por el mago pero no contaba con ataques anti-armadura");
                System.out.println("Caballero recibe un severo impacto de 70 hp "
                        + "dejandolo " + "con " + cab.getSalud() + " hp.");
            }
            if (wiz.isUlting() && cab.isUlting()) {
                wiz.animations.poison.start();
                cab.animations.hawk.start();
                wiz.impacto(15);
                cab.impacto(45); // caballero pierde mas salud
                System.out.println("El Mago decide invocar un hechizo de envenenamiento "
                        + "y es algo exitoso pero recive algo de daño por el Caballero");
                System.out.println("El Mago recive un ligero impacto de 15 hp "
                        + " dejandolo con " + wiz.getSalud() + " hp");
                System.out.println("El Caballero recive un impacto de 45 hp "
                        + " dejandolo con " + cab.getSalud() + " hp");
            }

            if (wiz.checkDeath() && cab.checkDeath()) {
                System.out.println("Empate");
                break;
            }
            if (wiz.checkDeath()) {
                System.out.println("Caballero Gana");
                break;
            }
            if (cab.checkDeath()) {
                System.out.println("Mago Gana");
                break;
            }
            cab.enGuardia(); // regresamos valores a predeterminado
            wiz.enGuardia(); // excepto hp para el sig round

        }

    }
}