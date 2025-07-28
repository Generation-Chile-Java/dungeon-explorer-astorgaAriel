package model.enemy;

import model.treasures.MagicKey;
import model.treasures.Treasure;

public class Skeleton extends Enemy {
    public Skeleton(int x, int y) {
        super("Esqueleto", 25, 6, "Un esqueleto animado que protege la mazmorra", 'S', x, y);
    }

    @Override
    public Treasure getDroppedTreasure() {
        return new MagicKey(x, y);
    }
}