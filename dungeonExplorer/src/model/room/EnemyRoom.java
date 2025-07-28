package model.room;

import view.Player;
import model.enemy.*;
import model.treasures.Treasure;

public class EnemyRoom extends BaseRoom {
    private java.util.List<Enemy> enemies;

    public EnemyRoom() {
        super();
        this.description = "Una sala oscura de la que provienen sonidos amenazantes.";
        this.enemies = new java.util.ArrayList<>();
        placeEnemies();
    }

    private void placeEnemies() {
        java.util.Random rand = new java.util.Random();
        int numEnemies = 1 + rand.nextInt(2); // 1-2 enemigos

        for (int i = 0; i < numEnemies; i++) {
            int x, y;
            do {
                x = rand.nextInt(SIZE);
                y = rand.nextInt(SIZE);
            } while (matrix[x][y] != '.');

            Enemy enemy;
            switch (rand.nextInt(4)) {
                case 0: enemy = new Goblin(x, y); break;
                case 1: enemy = new Orc(x, y); break;
                case 2: enemy = new Dragon(x, y); break;
                default: enemy = new Skeleton(x, y); break;
            }

            enemies.add(enemy);
            matrix[x][y] = enemy.getIcon();
        }
    }

    @Override
    public String getDescription() { return description; }

    @Override
    public String getRoomType() { return "Sala de Combate"; }

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
        for (Enemy enemy : enemies) {
            if (enemy.getX() == x && enemy.getY() == y && enemy.isAlive()) {
                System.out.println("¡Te has encontrado con un " + enemy.getName() + "! " + enemy.getDescription());
                combat(player, enemy);
                matrix[x][y] = enemy.getIcon(); // Actualizar icono
                break;
            }
        }
    }

    @Override
    public void displayMatrix(int playerX, int playerY) {
        // Actualizar matriz con iconos actuales
        for (Enemy enemy : enemies) {
            matrix[enemy.getX()][enemy.getY()] = enemy.getIcon();
        }
        super.displayMatrix(playerX, playerY);
    }

    private void combat(Player player, Enemy enemy) {
        System.out.println("\n¡COMBATE INICIADO!");

        while (player.isAlive() && enemy.isAlive()) {
            // Turno del jugador
            int playerDamage = player.attack();
            System.out.println("\n" + player.getName() + " ataca con " + playerDamage + " puntos de daño!");
            enemy.takeDamage(playerDamage);

            if (!enemy.isAlive()) {
                System.out.println("¡Has derrotado al " + enemy.getName() + "!");
                Treasure droppedTreasure = enemy.getDroppedTreasure();
                if (droppedTreasure != null) {
                    System.out.println("El " + enemy.getName() + " ha dejado caer algo...");
                    droppedTreasure.interact(player);
                }
                break;
            }

            // Turno del enemigo
            int enemyDamage = enemy.attack();
            if (enemyDamage > 0) {
                System.out.println("¡" + enemy.getName() + " contraataca con " + enemyDamage + " puntos de daño!");
                player.takeDamage(enemyDamage);
            }
        }

        if (!player.isAlive()) {
            System.out.println("¡Has sido derrotado! El juego ha terminado...");
        }

        System.out.println("COMBATE TERMINADO\n");
    }
}
