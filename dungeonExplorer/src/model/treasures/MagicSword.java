package model.treasures;

import view.Player;

public class MagicSword extends Treasure {
    private int damageBonus;

    public MagicSword(int x, int y) {
        super("Espada Mágica", "Una espada encantada que brilla con poder místico", 200, '†', x, y);
        this.damageBonus = 15;
    }

    @Override
    public void applyEffect(Player player) {
        player.increaseDamage(damageBonus);
        System.out.println("¡La espada mágica aumenta tu poder de ataque en " + damageBonus + " puntos!");
    }
}
