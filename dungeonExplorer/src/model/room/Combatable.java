package model.room;

public interface Combatable {
    int attack();
    void takeDamage(int damage);
    boolean isAlive();
    String getName();
    char getIcon();
}
