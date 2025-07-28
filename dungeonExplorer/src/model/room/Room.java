package model.room;

import view.Player;

public interface Room {
    String getDescription();
    void enter(Player player);
    boolean isExplored();
    String getRoomType();
    char[][] getMatrix();
    void displayMatrix(int playerX, int playerY);
    boolean canMoveTo(int x, int y);
    void processPosition(Player player, int x, int y);
}
