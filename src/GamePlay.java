public class GamePlay {

    private static boolean endGame = false;

    static boolean correctInputCommand(int len, String inputCommand) {

        if (len == 15) {
            if (("start").equals(inputCommand.substring(0, 5)) &&
                    (("easy").equals(inputCommand.substring(6, 10))
                            || ("user").equals(inputCommand.substring(6, 10))
                            || ("hard").equals(inputCommand.substring(6, 10))) &&
                    (("user").equals(inputCommand.substring(11, 15))
                            || ("easy").equals(inputCommand.substring(11, 15))
                            || ("hard").equals(inputCommand.substring(11, 15)))) {
                return true;
            }
        } else if (len == 17) {
            if (("start").equals(inputCommand.substring(0, 5)) &&
                    (("easy").equals(inputCommand.substring(6, 10))
                            || ("user").equals(inputCommand.substring(6, 10))
                            || ("hard").equals(inputCommand.substring(6, 10))
                            || ("medium").equals(inputCommand.substring(6, 12))) &&
                    (("easy").equals(inputCommand.substring(13, 17))
                            || ("user").equals(inputCommand.substring(13, 17))
                            || ("hard").equals(inputCommand.substring(13, 17))
                            || ("medium").equals(inputCommand.substring(11, 17)))) {
                return true;
            }
        } else {
            if (("start").equals(inputCommand.substring(0, 5)) &&
                    (("medium").equals(inputCommand.substring(6, 12)) &&
                            ("medium").equals(inputCommand.substring(13, 19)))) {
                return true;
            }
        }
        return false;
    }
    public static void startGame(String inputCommand) {
        /*The length depending on the combination of input command
           i.e. from -- start user easy to -- start medium medium */
        if (!(inputCommand.length() >= 15 && inputCommand.length() <= 19)) {
            System.out.println("Bad parameters!");
            return;
        } else if (correctInputCommand(inputCommand.length(), inputCommand)) {
            runGame(inputCommand);
        } else {
            System.out.println("Bad parameters!");
            return;
        }
    }
    // get human, easyAI of Medium AI
    private static Player getPlayerType(String input, String player) {

        if ("user".equals(input)) {
            return new Human(player);
        } else if ("easy".equals(input)) {
            return new EasyAI(player);
        } else if ("hard".equals(input)) {
            return new HardAI(player);
        }
        return new MediumAI(player);
    }
    private static void runGame(String inputCommand) {


        GameBoard gameBoard = new GameBoard();
        gameBoard.display();

        Player player1 = getPlayerType(inputCommand.substring(6, 10), "X");
        Player player2 = getPlayerType(inputCommand.substring(11, 15), "O");

        // to determine start of game state checks.
        int checkState = 0;
        while(true) {
            player1.setMove();
            gameBoard.setPosition(player1.getPlayer(), player1.getMove());
            gameBoard.display();
            if (playOn(checkState)) {
                setEndGame(false);
                break;
            }
            player2.setMove();
            gameBoard.setPosition(player2.getPlayer(), player2.getMove());
            gameBoard.display();
            if (playOn(checkState)) {
                setEndGame(false);
                break;
            }
            checkState++;
        }
    }

    private static boolean playOn(int checkState) {
        if (checkState >= 2) {
            GameState.checkGameState();
            return isEndGame();
        }
        return false;
    }

    public static boolean isEndGame() {
        return endGame;
    }

    public static void setEndGame(boolean endGame) {
        GamePlay.endGame = endGame;
    }
}