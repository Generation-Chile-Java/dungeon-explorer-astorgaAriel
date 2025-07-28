package model.enemy;

import model.treasures.GoldCoin;
import model.treasures.Treasure;

public class Goblin extends Enemy {
    public Goblin(int x, int y) {
        super("Goblin", 30, 8, "Un pequeño goblin verde con dientes afilados", 'G', x, y);
    }

    @Override
    public Treasure getDroppedTreasure() {
        return new GoldCoin(x, y);
    }
}