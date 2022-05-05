public class AlgoBoard {

    static class Move {
        int row, col;
    }
    static String player = "X", opponent = "O";

    static Boolean isMovesLeft(String board[][]) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(" ")) {
                    return true;
                }
            }
        }
        return false;
    }

    // This is the minimax function. It considers all
    // the possible ways the game can go and returns
    // the value of the board
    static int minimax(String board[][],
                       int depth, Boolean isMax) {
        int score = evaluate(board);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10) {
            return score;
        }
        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10) {
            return score;
        }
        // If there are no more moves and
        // no winner then it is a tie
        if (!isMovesLeft(board)) {
            return 0;
        }
        // If this maximizer's move
        if (isMax) {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j].equals(" ")) {
                        // Make the move
                        board[i][j] = player;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = " ";
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j].equals(" ")) {
                        // Make the move
                        board[i][j] = opponent;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = " ";
                    }
                }
            }
            return best;
        }
    }



    // This will return the best possible
    // move for the player
    static Move findBestMove(String board[][], String player) {

        int bestVal = 0;

        if (player == AlgoBoard.player) {
            bestVal = -1000;
        } else if (player == AlgoBoard.opponent) {
            bestVal = 1000;
        }

        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (board[i][j].equals(" ")) {
                    // Make the move
                    board[i][j] = player; // the player should be returned based on the input

                    boolean isMax = false;

                    if("O".equals(player)) {
                        isMax = true;
                    }

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(board, 0, isMax); // isMax is false because it always starts with X

                    // Undo the move
                    board[i][j] = " ";

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal && !isMax) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    } else if (moveVal < bestVal && isMax) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        return bestMove;
    }

    static int evaluate(String b[][]) {

        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++) {

            if (b[row][0].equals(b[row][1]) &&
                    b[row][1].equals(b[row][2])) {

                if (b[row][0].equals("X")) {
                    return +10;
                }
                else if (b[row][0].equals("O")) {
                    return -10;
                }
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++) {

            if (b[0][col].equals(b[1][col]) &&
                    b[1][col].equals(b[2][col])) {

                if (b[0][col].equals("X")) {
                    return +10;
                }
                else if (b[0][col].equals("O")) {
                    return -10;
                }
            }
        }

        // Checking for Diagonals for X or O victory.
        if (b[0][0].equals(b[1][1]) && b[1][1].equals(b[2][2])) {

            if (b[0][0].equals("X")) {
                return +10;
            }
            else if (b[0][0].equals("O")) {
                return -10;
            }
        }

        if (b[0][2].equals(b[1][1]) && b[1][1].equals(b[2][0])) {

            if (b[0][2].equals("X")) {
                return +10;
            }
            else if (b[0][2].equals("O")) {
                return -10;
            }
        }

        // Else if none of them have won then return 0
        return 0;
    }
}

