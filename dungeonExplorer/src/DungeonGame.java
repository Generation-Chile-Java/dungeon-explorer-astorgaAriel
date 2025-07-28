import controller.Direction;
import model.room.*;
import view.Player;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class DungeonGame {
    private Player player;
    private java.util.List<Room> dungeon;
    private int currentRoom;
    private int playerX, playerY;
    private java.util.Scanner scanner;
    private static final int ROOM_SIZE = 5;

    public DungeonGame() {
        scanner = new java.util.Scanner(System.in);
        initializeGame();
    }

    private void initializeGame() {
        System.out.println("=================================");
        System.out.println("  BIENVENIDO A LA MAZMORRA 2D");
        System.out.println("=================================");
        System.out.print("Ingresa el nombre de tu héroe: ");
        String playerName = scanner.nextLine();

        player = new Player(playerName);
        currentRoom = 0;
        playerX = ROOM_SIZE / 2; // Empezar en el centro
        playerY = ROOM_SIZE / 2;

        // Crear la mazmorra con diferentes tipos de salas (POLIMORFISMO)
        dungeon = new java.util.ArrayList<>();
        dungeon.add(new EmptyRoom());
        dungeon.add(new TreasureRoom());
        dungeon.add(new EnemyRoom());
        dungeon.add(new TreasureRoom());
        dungeon.add(new EnemyRoom());
        dungeon.add(new LockedRoom());
        dungeon.add(new TreasureRoom());
        dungeon.add(new EnemyRoom()); // Sala final con jefe

        System.out.println("\n¡" + playerName + " entra en la misteriosa mazmorra!");
        System.out.println("La mazmorra tiene " + dungeon.size() + " salas por explorar.");
        System.out.println("Usa WASD para moverte por cada sala matriz 5x5.");
        System.out.println("¡Que comience la aventura!\n");
    }

    public void play() {
        while (player.isAlive() && currentRoom < dungeon.size()) {
            Room room = dungeon.get(currentRoom);

            if (!room.isExplored()) {
                room.enter(player);
            }

            showGameStatus();
            room.displayMatrix(playerX, playerY);

            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("W/A/S/D - Moverse (Norte/Oeste/Sur/Este)");
            System.out.println("I - Ver estado del jugador");
            System.out.println("Q - Salir del juego");
            System.out.print("Comando: ");

            String input = scanner.nextLine().toUpperCase().trim();

            if (input.length() == 0) continue;

            switch (input.charAt(0)) {
                case 'W': movePlayer(Direction.NORTH); break;
                case 'S': movePlayer(Direction.SOUTH); break;
                case 'A': movePlayer(Direction.WEST); break;
                case 'D': movePlayer(Direction.EAST); break;
                case 'I': player.showStatus(); break;
                case 'Q':
                    System.out.println("¡Gracias por jugar! Hasta la próxima aventura.");
                    return;
                default:
                    System.out.println("Comando inválido. Usa W/A/S/D para moverte.");
            }

            if (!player.isAlive()) {
                gameOver();
                return;
            }
        }

        if (currentRoom >= dungeon.size()) {
            victory();
        }
    }

    private void movePlayer(Direction direction) {
        int newX = playerX + direction.getDeltaX();
        int newY = playerY + direction.getDeltaY();
        Room currentRoomObj = dungeon.get(currentRoom);

        // Verificar si el movimiento está dentro de la matriz actual
        if (currentRoomObj.canMoveTo(newX, newY)) {
            playerX = newX;
            playerY = newY;
            System.out.println("Te mueves hacia el " + direction.getName() + ".");
            currentRoomObj.processPosition(player, playerX, playerY);
        } else {
            // Intentar cambiar de sala
            if (canChangeRoom(direction)) {
                changeRoom(direction);
            } else {
                System.out.println("No puedes moverte en esa dirección.");
            }
        }
    }

    private boolean canChangeRoom(Direction direction) {
        // Verificar si está en el borde de la sala y puede avanzar a la siguiente
        switch (direction) {
            case NORTH:
                return playerX == 0 && currentRoom > 0;
            case SOUTH:
                return playerX == ROOM_SIZE - 1 && currentRoom < dungeon.size() - 1;
            case EAST:
                return playerY == ROOM_SIZE - 1 && currentRoom < dungeon.size() - 1;
            case WEST:
                return playerY == 0 && currentRoom > 0;
            default:
                return false;
        }
    }

    private void changeRoom(Direction direction) {
        switch (direction) {
            case NORTH:
                if (currentRoom > 0) {
                    currentRoom--;
                    playerX = ROOM_SIZE - 1; // Aparecer en el lado opuesto
                    System.out.println("¡Avanzas a la sala anterior!");
                }
                break;
            case SOUTH:
                if (currentRoom < dungeon.size() - 1) {
                    currentRoom++;
                    playerX = 0; // Aparecer en el lado opuesto
                    System.out.println("¡Avanzas a la siguiente sala!");
                }
                break;
            case EAST:
                if (currentRoom < dungeon.size() - 1) {
                    currentRoom++;
                    playerY = 0; // Aparecer en el lado opuesto
                    System.out.println("¡Avanzas a la siguiente sala!");
                }
                break;
            case WEST:
                if (currentRoom > 0) {
                    currentRoom--;
                    playerY = ROOM_SIZE - 1; // Aparecer en el lado opuesto
                    System.out.println("¡Retrocedes a la sala anterior!");
                }
                break;
        }

        // Entrar en la nueva sala si no ha sido explorada
        Room newRoom = dungeon.get(currentRoom);
        if (!newRoom.isExplored()) {
            newRoom.enter(player);
        }
    }

    private void showGameStatus() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Sala: " + (currentRoom + 1) + "/" + dungeon.size() +
                " | Posición: (" + playerX + "," + playerY + ")" +
                " | Vida: " + player.getHealth() + "/" + player.getMaxHealth() +
                " | Llaves: " + player.getKeys());
        System.out.println("=".repeat(60));
    }

    private void gameOver() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           JUEGO TERMINADO");
        System.out.println("=".repeat(50));
        System.out.println("¡" + player.getName() + " ha caído en la mazmorra!");
        System.out.println("Posición final: Sala " + (currentRoom + 1) + " (" + playerX + "," + playerY + ")");
        System.out.println("Salas exploradas: " + player.getRoomsExplored() + "/" + dungeon.size());
        System.out.println("¡Mejor suerte la próxima vez!");
        System.out.println("=".repeat(50));
    }

    private void victory() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           ¡VICTORIA!");
        System.out.println("=".repeat(50));
        System.out.println("¡Felicidades " + player.getName() + "!");
        System.out.println("Has explorado toda la mazmorra y sobrevivido.");
        System.out.println("Posición final: Sala " + currentRoom + " (" + playerX + "," + playerY + ")");
        player.showStatus();
        System.out.println("¡Eres un verdadero héroe!");
        System.out.println("=".repeat(50));
    }

    public static void main(String[] args) {
        try {
            DungeonGame game = new DungeonGame();
            game.play();
        } catch (Exception e) {
            System.out.println("Error en el juego: " + e.getMessage());
            e.printStackTrace();
        }
    }
}