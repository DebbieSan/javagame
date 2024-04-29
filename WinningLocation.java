import java.util.ArrayList;
import java.util.HashMap;

public class WinningLocation extends Locations {


    public WinningLocation(HashMap<String, Locations> roomExitHashMap, boolean hasVisited, String fullDescriptionFileName,
        String shortDescription, ArrayList<Items> items, boolean isSafeRoom, ArrayList<Characters> charactersList,
        boolean noNpcsAllowed, ArrayList<String> entryRequirements, ArrayList<String> exitRequirements) {

        super(roomExitHashMap, hasVisited, fullDescriptionFileName, shortDescription, items, isSafeRoom, charactersList, noNpcsAllowed, entryRequirements, exitRequirements);
    }

    @Override
    public void specialLocationLogic(GameState gameState) {
        Items coin = gameState.inventory.checkAndGetItem("coin");
        Items bunny = gameState.inventory.checkAndGetItem("bunny");

        int winningCoinCount = 100;
        int winningBunnyCount = 10;

        if( coin != null && coin.count >= winningCoinCount  ){
            System.out.println("Congratulations!You have " + coin.count + ". That's a winning score! But have you saved enough bunnies?");

        }else {
            System.out.println("Sorry, you don't have a winning score. Collect more coins to potentially win the game.");
            
        }if( bunny != null && bunny.count >= winningBunnyCount){
            System.out.println("Congratulations! You saved " + bunny.count + "cute bunnies. Yay! That's enough bunnies to potentially win the game!");
        }else {
            System.out.println("Sorry you evil you. You should save more bunny before saving yourself! Go rescue more bunnies before you can win the game.");
            

        }
        if(coin != null && bunny != null && coin.count >= winningCoinCount && bunny.count >= winningBunnyCount){
            System.out.println("Yay! You won the game! It's a hard one, so give yourself a hug! (づ｡◕‿‿◕｡)づ  ༼ つ ◕_◕ ༽つ");
            System.out.println("Enjoy all your free time now that you don't have this game to conquer! (ʘ‿ʘ)╯");
        }

    }
}
    

