package model.room;

public abstract class BaseRoom implements Room {
    protected char[][] matrix;
    protected boolean explored;
    protected String description;
    protected static final int SIZE = 5;

    public BaseRoom() {
        matrix = new char[SIZE][SIZE];
        explored = false;
        initializeMatrix();
    }

    protected void initializeMatrix() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrix[i][j] = '.'; // Espacio vacío
            }
        }
    }

    @Override
    public char[][] getMatrix() { return matrix; }

    @Override
    public boolean isExplored() { return explored; }

    @Override
    public boolean canMoveTo(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    @Override
    public void displayMatrix(int playerX, int playerY) {
        System.out.println("\n=== " + getRoomType().toUpperCase() + " ===");
        System.out.println("  0 1 2 3 4");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                if (i == playerX && j == playerY) {
                    System.out.print("@ ");
                } else {
                    System.out.print(matrix[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("\nLeyenda: @ = Tú, . = Vacío, ♥ = Poción, ¤ = Oro, † = Espada, ♦ = Llave");
        System.out.println("         G = Goblin, O = Orco, D = Dragón, S = Esqueleto, × = Derrotado, # = Pared");
    }
}