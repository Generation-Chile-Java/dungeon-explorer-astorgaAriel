package model.room;

import view.Player;
import model.treasures.*;

public class TreasureRoom extends BaseRoom {
    private java.util.List<Treasure> treasures;

    public TreasureRoom() {
        super();
        this.description = "Una sala que brilla débilmente, parece contener algo valioso.";
        this.treasures = new java.util.ArrayList<>();
        placeTreasures();
    }
    private void placeTreasures() {
        // Colocar tesoros en posiciones aleatorias
        java.util.Random rand = new java.util.Random();
        int numTreasures = 1 + rand.nextInt(3); // 1-3 tesoros

        for (int i = 0; i < numTreasures; i++) {
            int x, y;
            do {
                x = rand.nextInt(SIZE);
                y = rand.nextInt(SIZE);
            } while (matrix[x][y] != '.');

            Treasure treasure;
            switch (rand.nextInt(4)) {
                case 0: treasure = new HealthPotion(x, y); break;
                case 1: treasure = new GoldCoin(x, y); break;
                case 2: treasure = new MagicSword(x, y); break;
                default: treasure = new MagicKey(x, y); break;
            }

            treasures.add(treasure);
            matrix[x][y] = treasure.getIcon();
        }
    }

    @Override
    public String getDescription() { return description; }

    @Override
    public String getRoomType() { return "Sala del Tesoro"; }

    @Override
    public void enter(Player player) {
        if (!explored) {
            System.out.println("Entras en " + description);
            player.exploreRoom();
            explored = true;
        }
    }

    @Override
    public void processPosition(Player player, int x, int y) {
        for (Treasure treasure : treasures) {
            if (treasure.getX() == x && treasure.getY() == y && treasure.isCollectable()) {
                treasure.interact(player);
                matrix[x][y] = '.'; // Limpiar la posición
                break;
            }
        }
    }

    @Override
    public void displayMatrix(int playerX, int playerY) {
        // Actualizar matriz con iconos actuales
        for (Treasure treasure : treasures) {
            matrix[treasure.getX()][treasure.getY()] = treasure.getIcon();
        }
        super.displayMatrix(playerX, playerY);
    }
}