package model.treasures;

import controller.GameObject;
import view.Player;

public abstract class Treasure implements GameObject {
    protected String name;
    protected String description;
    protected int value;
    protected boolean collected;
    protected int x, y;
    protected char icon;

    public Treasure(String name, String description, int value, char icon, int x, int y) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.collected = false;
        this.icon = icon;
        this.x = x;
        this.y = y;
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return description; }

    @Override
    public boolean isCollectable() { return !collected; }

    @Override
    public char getIcon() { return collected ? '.' : icon; }

    @Override
    public int getX() { return x; }

    @Override
    public int getY() { return y; }

    public int getValue() { return value; }

    // Método abstracto que debe ser implementado por las subclases
    public abstract void applyEffect(Player player);

    @Override
    public void interact(Player player) {
        if (!collected) {
            System.out.println("¡Has encontrado " + name + "!");
            player.addToInventory(this);
            applyEffect(player);
            collected = true;
        } else {
            System.out.println("Ya has recogido este tesoro.");
        }
    }
}
