package model.room;

import view.Player;

public class EmptyRoom extends BaseRoom {
    public EmptyRoom() {
        super();
        this.description = "Una sala vacía y silenciosa. Solo se escucha el eco de tus pasos.";
        // Añadir algunas paredes decorativas
        matrix[1][1] = '#';
        matrix[3][3] = '#';
    }

    @Override
    public String getDescription() { return description; }

    @Override
    public String getRoomType() { return "Sala Vacía"; }

    @Override
    public void enter(Player player) {
        if (!explored) {
            System.out.println("Entras en " + description);
            System.out.println("No hay nada interesante aquí, pero al menos puedes descansar.");
            player.exploreRoom();
            explored = true;
        }
    }

    @Override
    public void processPosition(Player player, int x, int y) {
        if (matrix[x][y] == '#') {
            System.out.println("¡No puedes caminar a través de una pared!");
            return;
        }
        if (!explored) {
            player.heal(2); // Pequeña curación por descansar
            System.out.println("Descansas un momento y recuperas 2 puntos de vida.");
        }
    }

    @Override
    public boolean canMoveTo(int x, int y) {
        return super.canMoveTo(x, y) && matrix[x][y] != '#';
    }
}
