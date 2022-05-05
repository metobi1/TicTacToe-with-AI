public class GameState {
    private static final int ZERO = 0;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int TWO64 = 264;
    private static final int TWO37 = 237;
    private static final int SEVEN47 = 747;
    private static final int SEVEN56 = 756;

    private static String canWin;
    private static boolean whoWins(int cellsSum) {

        String x = "X";
        String o = "O";

        if (cellsSum == TWO64) {
            System.out.printf("%s wins%n", x);
            GamePlay.setEndGame(true);
            return true;
        } else if (cellsSum == TWO37) {
            System.out.printf("%s wins%n", o);
            GamePlay.setEndGame(true);
            return true;
        }
        return false;
    }

    private static void isDraw(int drwValue) {
        // value for a drawn game
        if (drwValue >= SEVEN47 && drwValue <= SEVEN56) {
            GamePlay.setEndGame(true);
            System.out.println("Draw");
        } /*else {
            System.out.println("Game not finished");
        }*/
    }

    private static boolean isWinner(int horSum, int vertSum,
                                    int diaDownRght, int diaUpRght) {
        // check if horizontal wins
        if(whoWins(horSum)) {
            return true;
            // check if vertical wins
        } else if(whoWins(vertSum)) {
            return true;
            // check if diagonal-down right wins
        } else if (whoWins(diaDownRght)) {
            return true;
            // check if diagonal-up right wins
        } else if (whoWins(diaUpRght)) {
            return true;
        }
        return false;
    }

    static void checkGameState() {
        int horSum = ZERO, vertSum = ZERO,
                diaDownRght = ZERO, diaUpRght = ZERO,
                drwValue = ZERO, hold = ZERO, inc = ZERO;
        int dec = TWO;

        while (true) {
            // add all in row i.e 11 12 13
            horSum += GameBoard.getBoard()[hold][inc].charAt(ZERO);
            // add all in column i.e 11 21 31
            vertSum += GameBoard.getBoard()[inc][hold].charAt(ZERO);
            // add all in diagonal i.e from up left to right down
            // i.e. 11 22 33
            diaDownRght += GameBoard.getBoard()[inc][inc].charAt(ZERO);
            // add all in diagonal i.e from down left to up right
            // i.e. 31 22 13
            diaUpRght += GameBoard.getBoard()[dec][inc].charAt(ZERO);
            // end of row/column/diagonal
            if (inc == TWO) {
                hold++;
                inc = ZERO;
                dec = TWO;
                if (isWinner(horSum, vertSum, diaDownRght, diaUpRght)) {
                    break;
                }
                // get sum of all cells
                drwValue += horSum;
                // reset all positional sums to get next sum (row/column)
                horSum = ZERO;
                vertSum = ZERO;
                diaDownRght = ZERO;
                diaUpRght = ZERO;
                // all positions have been added
                if (hold == THREE) {
                    // check if the game is a draw or unfinished
                    isDraw(drwValue);
                    break;
                }
                continue;
            }
            inc++;
            dec--;
        }
    }

    static boolean whatLine(int cellsSum, String player) {
        int winLineValue = (player.charAt(0) * 2) + 32;
        if (cellsSum == winLineValue) {
            return true;
        }
        return false;
    }

    static boolean canWin(int horSum, int vertSum,
                          int diaDownRght, int diaUpRght, String player) {
        // check if horizontal can win
        if(whatLine(horSum, player)) {
            MediumAI.setHor(true);
            canWin = "horSum";
            return true;
            // check if vertical can win
        } else if(whatLine(vertSum, player)) {
            MediumAI.setVert(true);
            canWin = "vertSum";
            return true;
            // check if diagonal-down right can win
        } else if (whatLine(diaDownRght, player)) {
            MediumAI.setDiaDownR(true);
            canWin = "diaDownRght";
            return true;
            // check if diagonal-up right can win
        } else if (whatLine(diaUpRght, player)) {
            MediumAI.setDiaUpR(true);
            canWin = "diaUpRght";
            return true;
        }
        return false;
    }

    static String checkGameState(String player) {
        int horSum = ZERO, vertSum = ZERO,
                diaDownRght = ZERO, diaUpRght = ZERO,
                hold = ZERO, inc = ZERO;
        int dec = TWO;

        while (true) {
            // add all in row i.e 11 12 13
            horSum += GameBoard.getBoard()[hold][inc].charAt(ZERO);
            // add all in column i.e 11 21 31
            vertSum += GameBoard.getBoard()[inc][hold].charAt(ZERO);
            // add all in diagonal i.e from up left to right down
            // i.e. 11 22 33
            diaDownRght += GameBoard.getBoard()[inc][inc].charAt(ZERO);
            // add all in diagonal i.e from down left to up right
            // i.e. 31 22 13
            diaUpRght += GameBoard.getBoard()[dec][inc].charAt(ZERO);
            // end of row/column/diagonal
            if (inc == TWO) {

                if (canWin(horSum, vertSum, diaDownRght, diaUpRght, player)) {
                    switch (canWin) {
                        case "horSum":
                            return String.format("%d %d", hold, inc);
                        case "vertSum":
                            return String.format("%d %d", inc, hold);
                        case "diaDownRght":
                            return String.format("%d %d", inc, inc);
                        case "diaUpRght":
                            return String.format("%d %d", dec, inc);
                    }
                }

                hold++;
                inc = ZERO;
                dec = TWO;

                // reset all positional sums to get next sum (row/column)
                horSum = ZERO;
                vertSum = ZERO;
                diaDownRght = ZERO;
                diaUpRght = ZERO;
                // all positions have been added
                if (hold == THREE) {
                    break;
                }
                continue;
            }
            inc++;
            dec--;
        }
        return "";
    }
}
