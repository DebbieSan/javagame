/** Control is where the player input is gathered, parsed to ignore case, etc., and passed on to
 * Actions to determine validity and execution.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The Control class runs an infinite loop that reads player input via a Scanner.
 */
public class Control {
    /**
     * the game state.
     */
    public GameState gameState;
    
    /**
     * The default constructor.
     * 
     * @param gameState the game state.
     */
    public Control(GameState gameState) {
        this.gameState = gameState;
    }

    /** 
     * userInput gets input from the player and puts it into verb-noun form.
     * 
     * This is the last thing called in Wonderland and loops a scanner for player input until the game stops, and
     * runs Actions.runCommand on each line of player input.
     */
    public void userInput() {
        Scanner sc = new Scanner(System.in);
        Actions action = new Actions(gameState);

        // Get the player's input and load into the GameState as lastVerb and lastNoun
        while (true) {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.print("What's your command? Enter two words separated by space:\n> ");

            String command = sc.nextLine();
            String[] words = command.split("\\s+");
            
            // Make all input lower case so case doesn't matter, and remove non-letter characters.
            // do it here, as soon as we get it, so the text is normalized for the rest of the game's logic.
            for (int i = 0; i < words.length; i++) {
                words[i] = words[i].toLowerCase().replaceAll("[^a-z]", "");
            }
            
            // Create aliases for `go` and `use` by seeing if the `words` length is one character, and if it is, then seeing if
            // the word is in an array of shortcuts, and if is, adding the appropriate prefix so it fits with the verb-noun schema.
            // This converts "n" and "north" into "go north", and `i` and `invent` to `open inventory`.
            if (words.length == 1) {
                String onlyWord = words[0];
                // Create a new array with enough capacity to hold the verb we're adding, since the String[] is immutable.
                String[] newWordsArray = new String[2];
                
                // `go`
                ArrayList<String> goAliases = new ArrayList<>(Arrays.asList("n", "north", "s", "south", "e", "east", "w", "west", "exits"));
                if (goAliases.contains(onlyWord)) {
                    newWordsArray[0] = "go";
                    newWordsArray[1] = onlyWord;
                    words = newWordsArray;
                }

                // `open`
                ArrayList<String> openAliases = new ArrayList<>(Arrays.asList("i", "inv", "invent", "inventory"));
                if (openAliases.contains(onlyWord)) {
                    newWordsArray[0] = "open";
                    newWordsArray[1] = onlyWord;
                    words = newWordsArray;
                }
                
                // replace the existing player input with the rewritten player input.
              }


              // Load the input into the GameState and execute runCommand to interpret the typed input.
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
