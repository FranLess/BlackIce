package main;

public class Caballero extends Player {
    public CaballeroAnimations animations = new CaballeroAnimations();

    public Caballero(String nom) {
        super(nom);
    }

    public void tomahawk() {
        special = true;
    }

}
