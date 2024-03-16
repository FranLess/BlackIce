package main;

public class Mago extends Player {
    public MagoAnimations animations = new MagoAnimations();

    public Mago(String nom) {
        super(nom);
    }

    public void veneno() {
        special = true;
    }

    public void curar() {
        defend = true;
    }
}
