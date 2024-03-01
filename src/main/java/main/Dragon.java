
package main;

import java.util.Random;

public class Dragon extends Player {
    public DragonAnimations animations = new DragonAnimations();

    private int stun;
    private int handicap; // on attacks to the same target

    public Dragon() {
        super();
        stun = 0; // extra chance of missing an attack
        handicap = 0; // better chance of predicting drag attacks
    }

    public void Golpiza(Mago wiz, Caballero cab, Dragon drag) throws Exception {
        if (stun > 0) {
            stun -= 10;
            if (stun <= 0) {
                stun = 0;
            }
        } // stun calibration
        if (wiz.isAttacking() && cab.isAttacking()) { // Player Attacks
            drag.impacto(195);
            stun += 15;
            // ANIMATIONS
            wiz.animations.electric.start();
            cab.animations.atack.start();

            System.out.println("Un ataque devastador para el Dragón! (-195 hp)");
            System.out.println("Dragon's hp: " + hp);
        } else if (wiz.isAttacking() || cab.isAttacking()) {
            drag.impacto(85);

            // ANIMATIONS
            if (Posibilidad(50))
                wiz.animations.electric.start();
            else
                cab.animations.atack.start();

            stun += 5;
            System.out.println("El Dragón es impactado. (-85 hp)");
            System.out.println("Dragon's hp: " + hp);
        }
        if (wiz.isUlting() && cab.isUlting()) {
            drag.impacto(300);
            stun += 25;

            // ANIMATION
            wiz.animations.poison.start();
            cab.animations.hawk.start();

            System.out.println("El Dragón recibe un impacto critico! (-300 hp)");
            System.out.println("Dragon's hp: " + hp);
        } else if (wiz.isUlting()) {
            drag.impacto(95);
            stun += 20;

            // ANIME
            wiz.animations.poison.start();

            System.out.println("El Dragón es Envenenado (-95 hp)");
            System.out.println("Dragon's hp: " + hp);
        } else if (cab.isUlting()) {
            drag.impacto(125); // last moment buffs?
            stun += 10;

            // anime
            cab.animations.hawk.start();

            System.out.println("El Dragón es impactado por un Tomahawk (-125 hp)");
            System.out.println("Dragon's hp: " + hp);
        }

        if (drag.isUlting()) {
            drag.setUltimate(false);
            System.out.println("El Dragón todavia no se la cree que se metio "
                    + "un madrazo el solo, pierde turno.");
            return;// dumbass ultimate
        }
        if (stun >= 10) {
            if (Posibilidad(3)) {
                selfHit(); // drag's ult is missing 2 turns xd
                drag.setUltimate(true);
                System.out.println("El Dragón es se golpea a si mismo...");
                System.out.println("Dragon's hp: " + hp);
                return;
            }
        } else if (Posibilidad(1)) {
            selfHit();
            drag.setUltimate(true);
            System.out.println("El Dragón es se golpea a si mismo...");
            System.out.println("Dragon's hp: " + hp);
            return;
        }
        if (Posibilidad(94 - stun)) {
            // ANIMATION
            drag.animations.fly.start();

            System.out.println("El ataque dragón falló...");
            return;
        } else {
            stun -= 10;
        }
        if (Posibilidad(50 + handicap)) { // Dragon's turn
            // ANIMATION
            drag.animations.atack.start();
            if (cab.isDefending()) {
                cab.impacto(15);
                System.out.println("El caballero resiste un ataque del Dragón! (-15 hp)");
                System.out.println("Knight's hp: " + cab.getSalud());
            } else {
                cab.impacto(120);
                System.out.println("El caballero es atacado por el Dragón (-120 hp)");
                System.out.println("Knight's hp: " + cab.getSalud());
            }
            if (handicap > 50) {
                handicap = 50;
            } else {
                handicap -= 15;
            }
        } else {
            // ANIMATION
            drag.animations.atack.start();

            if (wiz.isDefending()) {
                wiz.impacto(15);
                System.out.println("El mago resiste un ataque del Dragón! (-15 hp)");
                System.out.println("Wizzard's hp: " + wiz.getSalud());
            } else {
                wiz.impacto(120);
                System.out.println("El mago es atacado por el Dragón! (-120 hp)");
                System.out.println("Wizzard's hp: " + wiz.getSalud());
            }
            if (handicap < 50) {
                handicap = 50;
            } else {
                handicap += 15;
            }
        }
        System.out.println("XXXXXX");
    }

    private void selfHit() {
        hp -= 65;
        if (hp <= 0) {
            hp = 0;
        }
    }

    private boolean Posibilidad(int porcentaje) {
        return new Random().nextInt(100) + 1 <= porcentaje;
    }

    private int randNum(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

}
