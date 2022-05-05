import java.util.Objects;
import java.util.Random;

public class MediumAI extends EasyAI {

    private static boolean hor = false;
    private static boolean vert = false;
    private static boolean diaUpR = false;
    private static boolean diaDownR = false;

    private static int winningMove = 0;

    public MediumAI(String player) {
        super(player);
    }

    public static void setHor(boolean hor) {
        MediumAI.hor = hor;
    }

    public static void setVert(boolean vert) {
        MediumAI.vert = vert;
    }

    public static void setDiaUpR(boolean diaUp) {
        MediumAI.diaUpR = diaUp;
    }

    public static void setDiaDownR(boolean diaDown) {
        MediumAI.diaDownR = diaDown;
    }
    // get the winning cell
    private String winningMove(String winLine) {

        String move = "";

        if (hor) {
            int row = winLine.charAt(0) - '0';
            for (int i = 0; i < GameBoard.getBoard()[row].length; i++) {
                if (" ".equals(GameBoard.getBoard()[row][i])) {
                    move = String.format(++row + " " + ++i);
                    hor = false;
                    break;
                }
            }
        } else if (vert) {

            int col = winLine.charAt(2) - '0';
            for (int i = 0; i < GameBoard.getBoard().length; i++) {
                if (" ".equals(GameBoard.getBoard()[i][col])) {
                    move = String.format(++i + " " + ++col);
                    vert = false;
                    break;
                }
            }
        } else if (diaDownR) {
            int inc = 0;
            while (inc <= 2) {
                if (" ".equals(GameBoard.getBoard()[inc][inc])) {
                    move = String.format(++inc + " " + inc);
                    diaDownR = false;
                    break;
                }
                inc++;
            }
        } else if (diaUpR) {
            int inc = 0;
            int dec = 2;
            while (inc <= 2) {
                if (" ".equals(GameBoard.getBoard()[dec][inc])) {
                    move = String.format(++dec + " " + ++inc);
                    diaUpR = false;
                    break;
                }
                inc++;
                dec--;
            }
        }

        return move;
    }

    protected String getOtherPlayer() {
        String otherPlayer = "X";
        if (Objects.equals(otherPlayer, getPlayer())) {
            otherPlayer = "O";
        }
        return otherPlayer;
    }

    @Override
    protected String randMove() {

        if (winningMove >= 2) {
            // return a cell of the winning line
            String winLine = GameState.checkGameState(getPlayer());
            if (!"".equals(winLine)){
                // get the winning cell
                return winningMove(winLine);
            } // get the winning cell of the opponent
            String stopWin = GameState.checkGameState(getOtherPlayer());
            if (!"".equals(stopWin)) {
                return winningMove(stopWin);
            }
        }

        Random random = new Random();
        GameBoard.setEmptyPositions();
        int upperLimit = GameBoard.getEmptyPositions().length;
        int lowerLimit = 1;
        // get a random number for the array index
        // the array is a string of empty positions
        int moveAI = random.nextInt(upperLimit - lowerLimit + 1) + 1;
        // array length - one is obvious
        return GameBoard.getEmptyPositions()[moveAI - 1];
    }
    @Override
    public void setMove() {
        winningMove++;
        System.out.println("Making move level \"medium\"");
        move = randMove();
    }
}
