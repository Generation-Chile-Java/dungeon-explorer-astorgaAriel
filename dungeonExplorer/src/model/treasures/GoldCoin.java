package model.treasures;

import view.Player;

public class GoldCoin extends Treasure{

        public GoldCoin(int x, int y) {
            super("Moneda de Oro", "Una brillante moneda de oro", 100, '¤', x, y);
        }

        @Override
        public void applyEffect(Player player) {
            System.out.println("¡Has obtenido una valiosa moneda de oro!");
        }
    }

