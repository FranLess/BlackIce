
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

    public void selfHit() {
        hp -= 65;
        if (hp <= 0) {
            hp = 0;
        }
    }

    public int getStun() {
        return stun;
    }

    public void setStun(int stun) {
        this.stun = stun;
    }

    public int getHandicap() {
        return handicap;
    }

    public void setHandicap(int handicap) {
        this.handicap = handicap;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
