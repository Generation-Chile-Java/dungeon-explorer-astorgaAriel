package model.enemy;

import model.treasures.MagicSword;
import model.treasures.Treasure;

public class Dragon extends Enemy {
    public Dragon(int x, int y) {
        super("Dragón", 80, 20, "Un poderoso dragón que escupe fuego", 'D', x, y);
    }

    @Override
    public int attack() {
        int baseAttack = super.attack();
        if (Math.random() > 0.7) {
            System.out.println("¡El dragón usa aliento de fuego!");
            return baseAttack * 2;
        }
        return baseAttack;
    }

    @Override
    public Treasure getDroppedTreasure() {
        return new MagicSword(x, y);
    }
}