package view;

import model.treasures.Treasure;

public class Player {
    private String name;
    private int health;
    private int maxHealth;
    private int baseDamage;
    private java.util.List<Treasure> inventory;
    private int roomsExplored;
    private int keys;
    private char icon;

    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.maxHealth = 100;
        this.baseDamage = 10;
        this.inventory = new java.util.ArrayList<>();
        this.roomsExplored = 0;
        this.keys = 0;
        this.icon = '@';
    }

    // Métodos de acceso (ENCAPSULACIÓN)
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public boolean isAlive() { return health > 0; }
    public int getRoomsExplored() { return roomsExplored; }
    public char getIcon() { return icon; }
    public int getKeys() { return keys; }

    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
        System.out.println(name + " recibe " + damage + " puntos de daño. Vida restante: " + health + "/" + maxHealth);
    }

    public void heal(int amount) {
        health = Math.min(maxHealth, health + amount);
    }

    public void increaseDamage(int bonus) {
        baseDamage += bonus;
    }

    public void addKey() {
        keys++;
    }

    public boolean useKey() {
        if (keys > 0) {
            keys--;
            return true;
        }
        return false;
    }

    public int attack() {
        return baseDamage + (int)(Math.random() * 5);
    }

    public void addToInventory(Treasure item) {
        inventory.add(item);
        System.out.println(item.getName() + " añadido al inventario.");
    }

    public void exploreRoom() {
        roomsExplored++;
    }

    public void showStatus() {
        System.out.println("\n=== ESTADO DEL JUGADOR ===");
        System.out.println("Nombre: " + name + " (" + icon + ")");
        System.out.println("Vida: " + health + "/" + maxHealth);
        System.out.println("Poder de ataque: " + baseDamage);
        System.out.println("Llaves: " + keys);
        System.out.println("Salas exploradas: " + roomsExplored);
        System.out.println("Inventario (" + inventory.size() + " objetos):");
        if (inventory.isEmpty()) {
            System.out.println("  - Inventario vacío");
        } else {
            for (Treasure item : inventory) {
                System.out.println("  - " + item.getName() + " (" + item.getIcon() + ") - Valor: " + item.getValue());
            }
        }
        System.out.println("==========================\n");
    }
}