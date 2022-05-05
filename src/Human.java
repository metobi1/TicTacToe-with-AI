import java.util.Scanner;

public class Human extends Player{
    private final Scanner scanner = new Scanner(System.in);

    public Human(String player) {
        super(player);
    }

    @Override
    public void setMove() {
        do {
            System.out.print("Enter the coordinates: ");
            move = scanner.nextLine();
        } while (CheckInput.checkingInput(move));
    }
}
