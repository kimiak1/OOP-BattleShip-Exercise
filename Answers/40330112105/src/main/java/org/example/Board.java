package org.example;

import java.util.Random;

public class Board {
    private static final int SIZE = 10;
    private char[][] grid;
    private Ship[] ships = {
            new Ship("Patrol Boat", 2),
            new Ship("Submarine", 3),
            new Ship("Battleship", 4),
            new Ship("Aircraft Carrier", 5)
    };

    public Board() {
        grid = new char[SIZE][SIZE];
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                grid[i][j] = '~';
    }

    public void printBoard(boolean hideShips) {
        System.out.println("  A B C D E F G H I J");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                if (hideShips && grid[i][j] == 'S') {
                    System.out.print("~ ");  // کشتی‌ها پنهان شوند
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void placeShipsRandomly() {
        Random rand = new Random();
        for (Ship ship : ships) {
            boolean placed = false;
            while (!placed) {
                int row = rand.nextInt(SIZE);
                int col = rand.nextInt(SIZE);
                boolean horizontal = rand.nextBoolean();
                if (canPlaceShip(row, col, ship.getSize(), horizontal)) {
                    for (int i = 0; i < ship.getSize(); i++) {
                        grid[row + (horizontal ? 0 : i)][col + (horizontal ? i : 0)] = 'S';
                    }
                    placed = true;
                }
            }
        }
    }

    private boolean canPlaceShip(int row, int col, int size, boolean horizontal) {
        if (horizontal && col + size > SIZE) return false;
        if (!horizontal && row + size > SIZE) return false;

        for (int i = 0; i < size; i++) {
            if (grid[row + (horizontal ? 0 : i)][col + (horizontal ? i : 0)] != '~') return false;
        }
        return true;
    }

    public boolean attack(Coordinate target) {
        int row = target.getRow();
        int col = target.getCol();
        if (grid[row][col] == 'S') {
            System.out.println("Hit!");
            grid[row][col] = 'X';
            return true;
        } else if (grid[row][col] == '~') {
            System.out.println("Miss.");
            grid[row][col] = 'O';
            return true;
        }
        return false;
    }

    public boolean allShipsSunk() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (grid[i][j] == 'S') return false;
        return true;
    }
}




