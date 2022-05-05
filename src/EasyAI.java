import java.util.Random;

public class EasyAI extends Player{

    public EasyAI(String player) {
        super(player);
    }
    @Override
    public void setMove() {

        System.out.println("Making move level \"easy\"");
        move = randMove();
    }

    protected String randMove() {
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
}
