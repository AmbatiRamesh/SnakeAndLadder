package com.snakeLadder;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
public class TwoPlayer {
    public static void main(String[] args) {
        SnakeNLadder snakeLadder = new SnakeNLadder();
        snakeLadder.startGame();
    }
}
class SnakeNLadder {
    final static int WINPOINT = 100;
    static Map<Integer, Integer> snake = new HashMap<>();
    static Map<Integer, Integer> ladder = new HashMap<>();
    {
        snake.put(99, 25);
        snake.put(65, 55);
        snake.put(45, 39);
        snake.put(25, 2);
        snake.put(95, 72);

        ladder.put(6, 32);
        ladder.put(11, 45);
        ladder.put(58, 85);
        ladder.put(22, 90);
        ladder.put(17, 69);
    }

    //this method will generate random number from 1-6
    public int rollDice() {
        int n = 0;
        Random r = new Random();
        n = r.nextInt(7);
        return (n == 0 ? 1 : n);
    }
    
    public int calculatePlayerValue(int playerPosition, int diceValue) {
        int playerNewPosition=playerPosition+diceValue;

        if (playerNewPosition > WINPOINT)
            return playerPosition;

        if (null !=snake.get(playerNewPosition)) {
            System.out.println("Oops..ate by the snake..");
            playerNewPosition=snake.get(playerNewPosition);
        }

        if (null !=ladder.get(playerNewPosition)) {
            System.out.println("YAY! climbing the ladder..");
            playerNewPosition=ladder.get(playerNewPosition);
        }

        return playerNewPosition;
    }

    public boolean isWin(int playerPosition) {
        return WINPOINT==playerPosition;
    }

    public void startGame() {
        int player1Position=0, player2Position=0;
        int currentPlayer=-1;
        Scanner scan= new Scanner(System.in);
        String rPressed;
        int diceValue = 0;
        do {
            System.out.println(currentPlayer == -1
                    ? "\n\nFirst player's turn" : "\n\nSecond player's turn");
            System.out.println("Press 'w' to roll Dice");
            rPressed=scan.next();
            diceValue=rollDice();

            if (currentPlayer==-1) {
                player1Position=calculatePlayerValue(player1Position, diceValue);
                System.out.println("First Player Position:"+player1Position);
                System.out.println("Second Player Position:"+player2Position);
                System.out.println("-------------------------");
                if (isWin(player1Position)) {
                    System.out.println("Congratulations! First player won");
                    return;
                }
            } else {
                player2Position = calculatePlayerValue(player2Position, diceValue);
                System.out.println("First Player Position:"+player1Position);
                System.out.println("Second Player Position:"+player2Position);
                System.out.println("-------------------------");
                if (isWin(player2Position)) {
                    System.out.println("Congratulations! Second player won");
                    return;
                }
            }
            currentPlayer = -currentPlayer;
        } while ("w".equals(rPressed));
    }
}