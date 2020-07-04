package tictactoe;

import java.util.IllegalFormatException;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void printBField (String[] field) {
        System.out.printf("---------%n");
        System.out.printf("| %s %s %s |%n", field[0], field[1], field[2]);
        System.out.printf("| %s %s %s |%n", field[3], field[4], field[5]);
        System.out.printf("| %s %s %s |%n", field[6], field[7], field[8]);
        System.out.printf("---------%n");
    }

    public static String checkStatus (int[][] bf) {
        String gameStatus = "No define";
        int battleSum = 0;
        int numX=0;
        int numO=0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                battleSum += bf[i][j];
                if (bf[i][j] == 0) {
                    numO++;
                } else if (bf[i][j] == 1) {
                    numX++;
                }
            }
        }
        if (bf[0][0] + bf[0][1] + bf[0][2] == 3 || bf[1][0] + bf[1][1] + bf[1][2] == 3 ||
                bf[2][0] + bf[2][1] + bf[2][2] == 3 || bf[0][0] + bf[1][0] + bf[2][0] == 3 ||
                bf[0][1] + bf[1][1] + bf[2][1] == 3 || bf[0][2] + bf[1][2] + bf[2][2] == 3 ||
                bf[0][0] + bf[1][1] + bf[2][2] == 3 || bf[0][2] + bf[1][1] + bf[2][0] == 3) {
            gameStatus = "X wins";
        }

        if (bf[0][0] + bf[0][1] + bf[0][2] == 0 || bf[1][0] + bf[1][1] + bf[1][2] == 0 ||
                bf[2][0] + bf[2][1] + bf[2][2] == 0 || bf[0][0] + bf[1][0] + bf[2][0] == 0 ||
                bf[0][1] + bf[1][1] + bf[2][1] == 0 || bf[0][2] + bf[1][2] + bf[2][2] == 0 ||
                bf[0][0] + bf[1][1] + bf[2][2] == 0 || bf[0][2] + bf[1][1] + bf[2][0] == 0) {
            if ("X wins".equals(gameStatus)) {
                gameStatus = "Impossible";
            } else {
                gameStatus = "O wins";
            }
        } else if (Math.abs(numO - numX) > 1) {
            gameStatus = "Impossible";
        } else if (battleSum < 100 && "No define".equals(gameStatus))  {
            gameStatus = "Draw";
        } else if (battleSum > 100 && "No define".equals(gameStatus)){
            gameStatus = "Game not finished";
        }
//        System.out.println("BattleSum: " + battleSum + "game stus: " + gameStatus);
        return gameStatus;
    }

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String[] cells = new String[9];

        for (int i = 0; i < 9; i++) {
                cells[i] = " ";
        }

       printBField(cells);

        String gameStatus = "No define";
        int[][] bf = new int[3][3];
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ("X".equals(cells[k])) {
                    bf[i][j] = 1;
                } else if ("O".equals(cells[k])) {
                    bf[i][j] = 0;
                } else {
                    bf[i][j] = 100;
                }
                k++;
            }
        }

        int inputX;
        int inputY;
        boolean flag = false;
        String result = "";
        int counter = 0;

        while (!flag) {
            System.out.println("Enter the coordinates: ");
            try {
                inputX = scanner.nextInt();
                inputY = scanner.nextInt();
                int newI = 101;
                int newJ = 101;

                switch (inputX) {
                    case 1:
                        newJ = 0;
                        break;
                    case 2:
                        newJ = 1;
                        break;
                    case 3:
                        newJ = 2;
                        break;
                    default:
                        newJ = 100;
                }

                switch (inputY) {
                    case 1:
                        newI = 2;
                        break;
                    case 2:
                        newI = 1;
                        break;
                    case 3:
                        newI = 0;
                        break;
                    default:
                        newI = 100;
                }
//                System.out.printf("newI = %d newJ = %d",newI,newJ);
                if (newI == 100 || newJ == 100) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (bf[newI][newJ] == 0 || bf[newI][newJ] == 1) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    counter++;
                    if (counter % 2 == 0) {
                        cells[newI * 3 + newJ] = "O";
                        bf[newI][newJ] = 0;
                    } else {
                        cells[newI * 3 + newJ] = "X";
                        bf[newI][newJ] = 1;
                    }
                    printBField(cells);
                }
            } catch (IllegalFormatException e) {
                System.out.println("You should enter numbers!");
            }
            result = checkStatus(bf);

            if ("X wins".equals(result) || "O wins".equals(result) || "Draw".equals(result)) {
                flag = true;
            }
        }
        System.out.println(result);

    }
}
