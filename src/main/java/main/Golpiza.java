package main;

import java.io.IOException;
import java.util.Random;

public class Golpiza {
    private Mago wiz;
    private Caballero cab;
    private Dragon drag;

    public Golpiza(Mago wiz, Caballero cab, Dragon drag) {
        this.cab = cab;
        this.wiz = wiz;
        this.drag = drag;
    }

    public void start() throws InterruptedException, IOException {
        if (drag.getStun() > 0) {
            drag.setStun(drag.getStun() - 10);
            if (drag.getStun() <= 0) {
                drag.setStun(0);
            }
        }
        // si ambos atacan
        if (wiz.isAttacking() && cab.isAttacking()) {
            playersAtack();
        } else if (wiz.isAttacking() || cab.isAttacking()) {
            oneAtacks();
        }

        // si ambos usan ult
        if (wiz.isUlting() && cab.isUlting()) {
            playersUlt();
        } else if (wiz.isUlting()) {
            wizUlt();
        } else if (cab.isUlting()) {
            cabUlt();
        }

        if (drag.isUlting()) {
            drag.setUltimate(false);
            System.out.println("El Dragón todavia no se la cree que se metio "
                    + "un madrazo el solo, pierde turno.");
            return;// dumbass ultimate
        }
        if (dragonIsSelfHitting())
            return;

        if (Posibilidad(94 - drag.getStun())) {
            // ANIMATION
            drag.animations.fly.start();

            System.out.println("El ataque dragón falló...");
            return;
        } else {
            drag.setStun(drag.getStun() - 10);
        }
        if (Posibilidad(50 + drag.getHandicap())) { // Dragon's turn
            dragonAttack();
        } else {
            dragonAttackTwo();
        }
        System.out.println("XXXXXX");
    }

    private boolean Posibilidad(int porcentaje) {
        return new Random().nextInt(100) + 1 <= porcentaje;
    }

    private void playersAtack() throws InterruptedException, IOException {
        drag.impacto(195);
        drag.setStun(drag.getStun() + 15);
        // ANIMATIONS
        wiz.animations.electric.start();
        cab.animations.atack.start();

        System.out.println("Un ataque devastador para el Dragón! (-195 hp)");
        System.out.println("Dragon's hp: " + String.valueOf(drag.getHp()));
    }

    private void oneAtacks() throws InterruptedException, IOException {
        drag.impacto(85);

        // ANIMATIONS
        if (Posibilidad(50))
            wiz.animations.electric.start();
        else
            cab.animations.atack.start();

        drag.setStun(drag.getStun() + 5);
        System.out.println("El Dragón es impactado. (-85 hp)");
        System.out.println("Dragon's hp: " + String.valueOf(drag.getHp()));
    }

    public void playersUlt() throws InterruptedException, IOException {
        drag.impacto(300);
        drag.setStun(drag.getStun() + 25);

        // ANIMATION
        wiz.animations.poison.start();
        cab.animations.hawk.start();

        System.out.println("El Dragón recibe un impacto critico! (-300 hp)");
        System.out.println("Dragon's hp: " + String.valueOf(drag.getHp()));
    }

    public void wizUlt() throws InterruptedException, IOException {
        drag.impacto(95);
        drag.setStun(drag.getStun() + 20);

        // ANIME
        wiz.animations.poison.start();

        System.out.println("El Dragón es Envenenado (-95 hp)");
        System.out.println("Dragon's hp: " + String.valueOf(drag.getHp()));
    }

    public void cabUlt() throws InterruptedException, IOException {
        drag.impacto(125); // last moment buffs?
        drag.setStun(drag.getStun() + 10);

        // anime
        cab.animations.hawk.start();

        System.out.println("El Dragón es impactado por un Tomahawk (-125 hp)");
        System.out.println("Dragon's hp: " + String.valueOf(drag.getHp()));
    }

    public boolean dragonIsSelfHitting() {
        if (drag.getStun() >= 10) {
            if (Posibilidad(3)) {
                drag.selfHit(); // drag's ult is missing 2 turns xd
                drag.setUltimate(true);
                System.out.println("El Dragón es se golpea a si mismo...");
                System.out.println("Dragon's hp: " + String.valueOf(drag.getHp()));
                return true;
            }
        } else if (Posibilidad(1)) {
            drag.selfHit();
            drag.setUltimate(true);
            System.out.println("El Dragón es se golpea a si mismo...");
            System.out.println("Dragon's hp: " + String.valueOf(drag.getHp()));
            return true;
        }
        return false;
    }

    public void dragonAttack() throws InterruptedException, IOException {
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
        if (drag.getHandicap() > 50) {
            drag.setHandicap(50);
        } else {
            drag.setHandicap(drag.getHandicap() - 15);
        }
    }

    public void dragonAttackTwo() throws InterruptedException, IOException {
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
        if (drag.getHandicap() < 50) {
            drag.setHandicap(50);
        } else {
            drag.setHandicap(drag.getHandicap() + 15);
        }
    }
}
