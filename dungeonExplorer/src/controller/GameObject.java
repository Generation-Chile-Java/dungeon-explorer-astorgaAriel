package controller;

import view.Player;

public interface GameObject {
    String getName();
    String getDescription();
    void interact(Player player);
    boolean isCollectable();
    char getIcon();
    int getX();
    int getY();
}
