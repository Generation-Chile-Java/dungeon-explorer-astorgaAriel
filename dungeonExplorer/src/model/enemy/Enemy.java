package model.enemy;

import model.room.Combatable;
import model.treasures.Treasure;

public abstract class Enemy implements Combatable {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int damage;
    protected String description;
    protected char icon;
    protected int x, y;
    protected boolean defeated;

    public Enemy(String name, int health, int damage, String description, char icon, int x, int y) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
        this.description = description;
        this.icon = icon;
        this.x = x;
        this.y = y;
        this.defeated = false;
    }

    @Override
    public String getName() { return name; }

    public String getDescription() { return description; }

    @Override
    public boolean isAlive() { return health > 0 && !defeated; }

    @Override
    public char getIcon() { return defeated ? '×' : icon; }

    public int getX() { return x; }
    public int getY() { return y; }

    @Override
    public int attack() {
        if (isAlive()) {
            return damage + (int)(Math.random() * 5); // Daño variable
        }
        return 0;
    }

    @Override
    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
        if (health > 0) {
            System.out.println(name + " recibe " + damage + " puntos de daño. Vida restante: " + health);
        } else {
            System.out.println(name + " ha sido derrotado!");
            defeated = true;
        }
    }

    // Método abstracto para obtener el tesoro que deja al morir
    public abstract Treasure getDroppedTreasure();
}