package main;
public class Player {
    
    protected String name;
    protected int hp;
    protected boolean attack;
    protected boolean defend;
    protected boolean special;
    
    public Player () {
        this.name = "Dragón";
        this.hp = 1750;
        this.attack = false;
        this.defend = false;
        this.special = false;
    }
    
    public Player(String nom){
        this.name = nom;
        this.hp = 200;
        this.attack = false;
        this.defend = false;
        this.special = false;
    }
    
    public String getSalud(){
        return String.valueOf(hp);
    }
    public boolean isAttacking(){
        return attack;
    }
    public boolean isDefending(){
        return defend;
    }
    public boolean isUlting(){
        return special;
    }
    public void enGuardia(){
        this.attack = false;
        this.defend = false;
        this.special = false;
    }
    public void setUltimate(boolean status) {
        this.special = status; // I have something.
    }
    public void atacar(){
        this.attack = true;
    }
    public void impacto(int salud){ // puntos de daño
        this.hp -= salud;
        if (this.hp<=0) { this.hp = 0; }
    }
    public void regenerar(int salud){
        this.hp += salud;
    }
    public boolean checkDeath(){
        return (this.hp == 0);
    }
}
