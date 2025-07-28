package model.treasures;

import view.Player;

public class MagicKey extends Treasure {
    public MagicKey(int x, int y) {
        super("Llave Mágica", "Una llave dorada que abre puertas especiales", 150, '♦', x, y);
    }

    @Override
    public void applyEffect(Player player) {
        player.addKey();
        System.out.println("¡Has obtenido una llave mágica!");
    }
}