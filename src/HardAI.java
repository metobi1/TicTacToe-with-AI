
public class HardAI extends EasyAI{
    public HardAI(String player) {

        super(player);
    }

    @Override
    public void setMove() {

        System.out.println("Making move level \"hard\"");

        move = startAlgo();

    }

    public String startAlgo() {

        AlgoBoard.Move bestMove = AlgoBoard.findBestMove(GameBoard.getBoard(), getPlayer());

        return (bestMove.row + 1) + " " + (bestMove.col + 1);
    }
}
