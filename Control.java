import java.util.Scanner;

public class Control {

    public GameState gameState;

    public Control(GameState gameState) {
        this.gameState = gameState;

    }

    public void userInput() {
        Scanner sc = new Scanner(System.in);
        Actions action = new Actions(gameState);

        while (true) {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.print("What's your command? Enter two words separated by space:\n> ");

            String command = sc.nextLine();
            String[] words = command.split("\\s+");

            if (words.length == 2) {
                gameState.lastVerb = words[0];
                gameState.lastNoun = words[1];
                action.runCommand();
            } else {
                System.out.println("Invalid input. Please enter two words separated by a space");
            }

        }

    }

}
