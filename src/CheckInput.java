public class CheckInput {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final char CHAR_ZERO = '0';
    private static final char CHAR_ONE = '1';
    private static final char CHAR_THREE = '3';
    private static final char CHAR_NINE = '9';

    static boolean checkingInput(String coordinates) {

        if (!isNumbers(coordinates)) {
            System.out.println("You should enter numbers!");
            return true;
        } else if (!isOneToThree(coordinates)) {
            System.out.println("Coordinates should be from 1 to 3!");
            return true;
        } else if (isOccupied(coordinates)) {
            System.out.println("This cell is occupied! Choose another one!");
            return true;
        }
        return false;
    }

    private static boolean isNumbers(String coordinates) {
        return asExpected(CHAR_ZERO,CHAR_NINE, coordinates);
    }

    private static boolean asExpected(char firChar, char secChar, String coordinates) {
        boolean checksOut = false;
        for (int i = ZERO; i <= (coordinates.length() - ONE); i+=TWO) {
            if (coordinates.charAt(i) >= firChar &&
                    coordinates.charAt(i) <= secChar) {
                checksOut = true;
            } else {
                return false;
            }
        }
        return checksOut;
    }

    private static boolean isOneToThree(String coordinates) {
        return asExpected(CHAR_ONE, CHAR_THREE, coordinates);
    }

    private static boolean isOccupied(String coordinates) {
        char one = '1';
        int row = coordinates.charAt(ZERO) - one;
        int col = coordinates.charAt(TWO) - one;
        return ("X".equals(GameBoard.getBoard()[row][col]) ||
                "O".equals(GameBoard.getBoard()[row][col]));
    }
}
