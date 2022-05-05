public class GameBoard {
    private static final int ZERO = 0;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int NINE = 9;
    private static final String[][] board = new
            String[THREE][THREE];
    private static String[] emptyPositions;
    private static int playCount;

    GameBoard() {
        //System.out.print("Enter the cells: ");
        String initialState = " ";
        setBoard(initialState);
        playCount = 0;
    }

    public static String[][] getBoard() {
        return board;
    }

    public void setBoard(String initialState) {

        for (int i = ZERO; i < board.length; i++) {
            for (int j = ZERO; j < board[i].length; j++) {
                board[i][j] = initialState;
            }
        }
    }

    /*public String getPosition() {
        return position;
    }*/

    public void setPosition(String player, String position) {
        char one = '1';
        int row = position.charAt(ZERO) - one;
        int col = position.charAt(TWO) - one;
        // checking if to put X int received coordinate
        board[row][col] = player;
        // removed this.positions and deleted it.
        playCount++;
    }

    public static String[] getEmptyPositions() {
        return emptyPositions;
    }

    // get the unoccupied coordinates on the game board
    public static void setEmptyPositions() {

        // nine is the length of the two dim array
        // positions minus position filled
        int arrSize = NINE - playCount;
        emptyPositions = new String[arrSize];
        int inc = 0;
        for (int i = ZERO; i < board.length; i++) {
            for (int j = ZERO; j < board[i].length; j++) {
                // store the coordinates of free positions
                if (" ".equals(board[i][j])) {
                    emptyPositions[inc++] = String.format("%d %d", i + 1, j + 1);
                }
            }
        }
    }



    private void displayBoundaryLine() {
        System.out.println("---------");
    }

    private void gameInternals() {
        for (int i = ZERO; i < board.length; i++) {
            System.out.print("|");
            for (int j = ZERO; j < board[i].length; j++) {
                System.out.printf(" %s", board[i][j]);
            }
            System.out.println(" |");
        }
    }

    public void display() {
        displayBoundaryLine();
        gameInternals();
        displayBoundaryLine();
    }
}
