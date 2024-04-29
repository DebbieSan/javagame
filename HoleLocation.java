import java.util.ArrayList;
import java.util.HashMap;

public class HoleLocation extends Locations {

    public HoleLocation(HashMap<String, Locations> roomExitHashMap, boolean hasVisited, String fullDescriptionFileName,
        String shortDescription, ArrayList<Items> items, boolean isSafeRoom, ArrayList<Characters> charactersList,
        boolean noNpcsAllowed, ArrayList<String> itemEntryRequirements, int sizeEntryRequirements) {

        super(roomExitHashMap, hasVisited, fullDescriptionFileName, shortDescription, items, isSafeRoom, charactersList, noNpcsAllowed, itemEntryRequirements, sizeEntryRequirements);
    }

    @Override
    public void specialLocationLogic(GameState gameState) {
        Items transporter = gameState.inventory.checkAndGetItem("transporter");
        if(transporter != null){
            System.out.println("You have a transporter! You can use it to leave this room.");
        }else{
            System.out.println("Sorry, should have grabbed that transporter.");
            System.out.println("Game OVER!");
            System.exit(0);
        }
    }
}