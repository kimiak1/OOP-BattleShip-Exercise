package org.example;

import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private Scanner scanner;

    public Game() {
        scanner = new Scanner(System.in);
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        player1.getBoard().placeShipsRandomly();
        player2.getBoard().placeShipsRandomly();
    }

    public void start() {
        boolean player1Turn = true;

        while (!isGameOver()) {
            Player currentPlayer = player1Turn ? player1 : player2;
            Player opponent = player1Turn ? player2 : player1;

            System.out.println("\n" + currentPlayer.getName() + "'s turn:");
            opponent.getBoard().printBoard(true); // نمایش برد حریف بدون کشتی‌ها

            boolean validMove = false;
            while (!validMove) {
                System.out.print("Enter target (e.g., A5): ");
                String input = scanner.next().toUpperCase();
                if (Coordinate.isValid(input)) {
                    Coordinate target = new Coordinate(input);
                    validMove = opponent.getBoard().attack(target);
                } else {
                    System.out.println("Invalid input, try again.");
                }
            }
            player1Turn = !player1Turn;
        }

        System.out.println("Game Over! Winner: " + (player1.hasLost() ? player2.getName() : player1.getName()));
    }

    private boolean isGameOver() {
        return player1.hasLost() || player2.hasLost();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
