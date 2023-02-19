package com.hz;

import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {

        introduce();

        char[][] gameBoard = {
                { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' },
                { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' },
                { ' ', '|', ' ', '|', ' ' } };
        printGameBoard(gameBoard);

        while (true) {
            ConsoleReader reader = new ConsoleReader();
            InputAdapter adapter = new InputAdapter(reader);

            int playerPos = adapter.readResponse();

            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("Position taken! Enter a correct position");
                playerPos = adapter.readResponse();
            }

            placePiece(gameBoard, playerPos, "player");

            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            Random random = new Random();
            int cpuPos = random.nextInt(9) + 1;
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = random.nextInt(9) + 1;
            }

            placePiece(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);

            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
            System.out.println(result);
        }

    }

    public static void placePiece(char[][] gameBoard, int pos, String user) {
        PieceFactory pieceFactory = new PieceFactory();
        char symbol = ' ';
        if (user.equals("player")) {
            Piece piece = pieceFactory.getPiece("CROSS");
            symbol = piece.draw();
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            Piece piece = pieceFactory.getPiece("CIRCLE");
            symbol = piece.draw();
            cpuPositions.add(pos);
        }

        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
        }
    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static String checkWinner() {

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning) {
            if (playerPositions.containsAll(l)) {
                displayEndArt(0);
                return "Congratulations you won!";
            } else if (cpuPositions.containsAll(l)) {
                displayEndArt(1);
                return "CPU wins! Sorry :(";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                displayEndArt(2);
                return "It's a tie!";
            }
        }
        return "";
    }

    private static void displayEndArt(Integer result) {
        ArtBuilder artbuilder = new ArtBuilder();
        switch (result) {
            case 0:
                artbuilder.getText(
                        "       _      _                   ",
                        "      (_)    | |                  ",
                        "__   ___  ___| |_ ___  _ __ _   _ ",
                        "\\ \\ / / |/ __| __/ _ \\| '__| | | |");
                System.out.println(artbuilder.getText(
                        " \\ V /| | (__| || (_) | |  | |_| |",
                        "  \\_/ |_|\\___|\\__\\___/|_|   \\__, |",
                        "                             __/ |",
                        "                            |___/ "));
                break;
            case 1:
                artbuilder.getText(
                        "                      _                ",
                        "                     | |               ",
                        "  _   _  ___  _   _  | | ___  ___  ___ ",
                        " | | | |/ _ \\| | | | | |/ _ \\/ __|/ _ \\"

                );
                System.out.println(artbuilder.getText(
                        " | |_| | (_) | |_| | | | (_) \\__ \\  __/",
                        "  \\__, |\\___/ \\__,_| |_|\\___/|___/\\___|",
                        "   __/ |                               ",
                        "  |___/                                "));
                break;
            default:
                artbuilder.getText(
                        "  _______ _      ",
                        " |__   __(_)     ",
                        "    | |   _  ___ ",
                        "    | |  | |/ _ \\");
                System.out.println(artbuilder.getText(
                        "    | |  | |  __/",
                        "    |_|  |_|\\___|",
                        "                 ",
                        "                 "));
                break;
        }

    }

    public static void introduce() {
        IntroductionBuilder introductionbuilder = new IntroductionBuilder(); // uses the Textbuilder class as an
                                                                             // introduction builder
        System.out.println(introductionbuilder.getText(
                "Welcome player, to our fantastic CLI tic-tac-toe game.",
                "You will be matched up against our enhanced algorythm that dynamically makes its moves based on RNG-powered-decisionmaking",
                "Simply enter a number on your turn, and a cross will be placed in the corresponding square. If you don't know the rules of tic-tac-toe, google them.",
                "Good luck! ☺"));
    }
}