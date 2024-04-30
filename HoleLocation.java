import java.util.ArrayList;
import java.util.HashMap;

/**
 * A Locations subclass for The Hole. You're stuck here without a transporter.
 */
public class HoleLocation extends Locations {
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
    public HoleLocation(HashMap<String, Locations> roomExitHashMap, boolean hasVisited, String fullDescriptionFileName,
        String shortDescription, ArrayList<Items> items, boolean isSafeRoom, ArrayList<Characters> charactersList,
        boolean noNpcsAllowed, ArrayList<String> itemEntryRequirements, int sizeEntryRequirements) {

        super(roomExitHashMap, hasVisited, fullDescriptionFileName, shortDescription, items, isSafeRoom, charactersList, noNpcsAllowed, itemEntryRequirements, sizeEntryRequirements);
    }

    
    /** 
     * Tell the player to use the transporter if they have it, and if not, end the game.
     * 
     * @param gameState the game state
     */
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
