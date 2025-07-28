package model.treasures;

import view.Player;

public class HealthPotion extends Treasure{

        private int healAmount;

        public HealthPotion(int x, int y) {
            super("Poción de Vida", "Una poción mágica que restaura salud", 50, '♥', x, y);
            this.healAmount = 20;
        }

        @Override
        public void applyEffect(Player player) {
            player.heal(healAmount);
            System.out.println("¡La poción te ha curado " + healAmount + " puntos de vida!");
        }
    }

