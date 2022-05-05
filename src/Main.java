import java.util.Scanner;

public class Main {
    private static final Scanner scanner =
            new Scanner(System.in);
    private static boolean exit = false;
    public static void main(String[] args) {

        while(!exit) {
            System.out.printf("Input command: ");
            String command = scanner.nextLine();

            if ("exit".equals(command)) {
                exit = true;
                continue;
            } else {
                GamePlay.startGame(command);
            }
        }
    }
}
