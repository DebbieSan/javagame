import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Location for the WinningLocation, also known as TheTree. This subclasses Locations and contains
 * an override of specialLocationLogic to handle determining if the player has all the coins and bunnies
 * necessary, and what the score is.
 */
public class WinningLocation extends Locations {

    /**
     * The default constructor.
     * 
     * @param roomExitHashMap the HashMap of all the exit directions and the Locations associated with them.
     * @param hasVisited whether the room has been visited - full or short description depends on this.
     * @param fullDescriptionFileName the filename for the full description of the room. Displayed once.
     * @param shortDescription the short description, displayed when the player returns to a location.
     * @param items an ArrayList of the Items in the room.
     * @param isSafeRoom whether the room is a safe room, where no characters can spawn.
     * @param charactersList an ArrayList of all the possible characters, used for generating a possible random character.
     * @param noNpcsAllowed whether characters can appear in this room.
     * @param itemEntryRequirements required items to enter the room
     * @param sizeEntryRequirements required sizes (small, normal, etc.) to enter the room.
     */
    public WinningLocation(HashMap<String, Locations> roomExitHashMap, boolean hasVisited, String fullDescriptionFileName,
        String shortDescription, ArrayList<Items> items, boolean isSafeRoom, ArrayList<Characters> charactersList,
        boolean noNpcsAllowed, ArrayList<String> itemEntryRequirements, int sizeEntryRequirements) {

        super(roomExitHashMap, hasVisited, fullDescriptionFileName, shortDescription, items, isSafeRoom, charactersList, noNpcsAllowed, itemEntryRequirements, sizeEntryRequirements);
    }

    
    /** 
     * Some special logic for winning the game. Checks how many bunnies and coins the player has, and prints the relevant
     * messages to inform the player of their status non the road to victory.
     * 
     * If the player meets the requirements to win, it prints a message, and leaves the player free to continue to explore
     * Wonderland.
     * @param gameState the game state
     */
    @Override
    public void specialLocationLogic(GameState gameState) {
        Items coin = gameState.inventory.checkAndGetItem("coin");
        Items bunny = gameState.inventory.checkAndGetItem("bunny");

        int winningCoinCount = 10;
        int winningBunnyCount = 10;

        // Check coin and bunny count and inform the player if they've got enough of each one.
        if( coin != null && coin.count >= winningCoinCount  ){
            System.out.println("Congratulations!You have " + coin.count + ". That's a winning score! But have you saved enough bunnies?");

        }else {
            System.out.println("Sorry, you don't have a winning score. Collect more coins to potentially win the game.");
            
        }if( bunny != null && bunny.count >= winningBunnyCount){
            System.out.println("Congratulations! You saved " + bunny.count + " cute bunnies. Yay! That's enough bunnies to potentially win the game!");
        }else {
            System.out.println("Sorry you evil you. You should save more bunny before saving yourself! Go rescue more bunnies before you can win the game. The Mad Hatter may be able to help!");
            

        }
        if(coin != null && bunny != null && coin.count >= winningCoinCount && bunny.count >= winningBunnyCount){
            // Calculate the score
            int score = coin.count + (2 * bunny.count);
            System.out.println("Yay! You won the game! It's a hard one, so give yourself a hug! (づ｡◕‿‿◕｡)づ  ༼ つ ◕_◕ ༽つ");
            System.out.println("Enjoy all your free time now that you don't have this game to conquer! (ʘ‿ʘ)╯");
            System.out.println("Score: " + score);
        }

    }
}
    

