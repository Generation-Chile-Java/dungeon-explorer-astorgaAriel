package model.room;

import view.Player;
import model.treasures.MagicSword;
import model.treasures.Treasure;

public class LockedRoom extends BaseRoom {
    private boolean unlocked;
    private Treasure specialTreasure;

    public LockedRoom() {
        super();
        this.description = "Una sala con una puerta cerrada mágicamente.";
        this.unlocked = false;

        // Colocar puertas (paredes especiales)
        for (int i = 0; i < SIZE; i++) {
            matrix[0][i] = '#';
            matrix[SIZE-1][i] = '#';
            matrix[i][0] = '#';
            matrix[i][SIZE-1] = '#';
        }

        // Entrada en el centro de la pared sur
        matrix[SIZE-1][SIZE/2] = '.';

        // Tesoro especial en el centro
        specialTreasure = new MagicSword(SIZE/2, SIZE/2);
        matrix[SIZE/2][SIZE/2] = specialTreasure.getIcon();
    }

    @Override
    public String getDescription() {
        return unlocked ? "Una sala que antes estaba cerrada, ahora abierta." : description;
    }

    @Override
    public String getRoomType() { return "Sala Cerrada"; }

    @Override
    public void enter(Player player) {
        if (!unlocked && player.getKeys() > 0) {
            System.out.println("¿Usar una llave para abrir esta sala? (s/n)");
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            if (scanner.nextLine().toLowerCase().startsWith("s")) {
                player.useKey();
                unlocked = true;
                System.out.println("¡Has abierto la sala con tu llave mágica!");
            }
        }

        if (!explored && unlocked) {
            System.out.println("Entras en " + getDescription());
            player.exploreRoom();
            explored = true;
        } else if (!unlocked) {
            System.out.println("La sala está cerrada. Necesitas una llave mágica.");
        }
    }

    @Override
    public void processPosition(Player player, int x, int y) {
        if (!unlocked) {
            System.out.println("No puedes moverte en una sala cerrada.");
            return;
        }

        if (specialTreasure != null && specialTreasure.getX() == x &&
                specialTreasure.getY() == y && specialTreasure.isCollectable()) {
            specialTreasure.interact(player);
            matrix[x][y] = '.';
            specialTreasure = null;
        }
    }

    @Override
    public boolean canMoveTo(int x, int y) {
        if (!unlocked) return false;
        return super.canMoveTo(x, y) && matrix[x][y] != '#';
    }

    @Override
    public void displayMatrix(int playerX, int playerY) {
        if (!unlocked) {
            System.out.println("\n=== SALA CERRADA ===");
            System.out.println("Esta sala está cerrada. Necesitas una llave mágica.");
            return;
        }
        super.displayMatrix(playerX, playerY);
    }
}