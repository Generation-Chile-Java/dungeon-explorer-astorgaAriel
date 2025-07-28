package controller;

public enum Direction {
    NORTH(-1, 0, "norte"),
    SOUTH(1, 0, "sur"),
    EAST(0, 1, "este"),
    WEST(0, -1, "oeste");

    private final int deltaX, deltaY;
    private final String name;

    Direction(int deltaX, int deltaY, String name) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.name = name;
}
    public int getDeltaX() { return deltaX; }
    public int getDeltaY() { return deltaY; }
    public String getName() { return name; }
}
