package model.enemy;

import model.treasures.GoldCoin;
import model.treasures.HealthPotion;
import model.treasures.Treasure;

public class Orc extends Enemy {
    public Orc(int x, int y) {
        super("Orco", 50, 15, "Un feroz orco con una gran hacha", 'O', x, y);
    }

    @Override
    public Treasure getDroppedTreasure() {
        return Math.random() > 0.5 ? new HealthPotion(x, y) : new GoldCoin(x, y);
    }
}