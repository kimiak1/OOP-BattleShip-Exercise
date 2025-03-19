package org.example;

public class Coordinate {
    private int row;
    private int col;

    public Coordinate(String input) {
        this.col = input.charAt(0) - 'A';
        this.row = Character.getNumericValue(input.charAt(1));
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public static boolean isValid(String input) {
        return input.length() == 2 && input.charAt(0) >= 'A' && input.charAt(0) <= 'J' &&
                input.charAt(1) >= '0' && input.charAt(1) <= '9';
    }
}


