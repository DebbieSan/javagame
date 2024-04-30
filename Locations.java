import java.util.ArrayList;
import java.util.HashMap; // import the HashMap class
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Set;
import java.util.Random;//import the random number class
import java.util.Collections;

/**
 * The models for Locations in the game.
 * 
 * This serves as a superclass for a few rooms that need to override the specialLocationLogic() method.
 */
public class Locations {

    HashMap<String, Locations> roomExitHashMap;
    boolean hasVisited = false;
    String fullDescriptionFileName;
    String shortDescription;
    ArrayList<Items> items;
    boolean isSafeRoom = false;
    ArrayList<Characters> charactersList;
    Characters npc;
    boolean noNpcsAllowed = false;
    Scanner characterDialogue;
    ArrayList<String> itemEntryRequirements;
    int sizeEntryRequirements;

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
    public Locations(HashMap<String, Locations> roomExitHashMap, boolean hasVisited, String fullDescriptionFileName,
            String shortDescription, ArrayList<Items> items, boolean isSafeRoom, ArrayList<Characters> charactersList,
            boolean noNpcsAllowed, ArrayList<String> itemEntryRequirements, int sizeEntryRequirements) {
        this.roomExitHashMap = roomExitHashMap;
        this.hasVisited = hasVisited;
        this.fullDescriptionFileName = fullDescriptionFileName;
        this.shortDescription = shortDescription;
        this.items = items;
        this.isSafeRoom = isSafeRoom;
        this.charactersList = charactersList;
        this.noNpcsAllowed = noNpcsAllowed;
        this.itemEntryRequirements = itemEntryRequirements;
        this.sizeEntryRequirements = sizeEntryRequirements;
        
    }

    
    /**
     * Reads the full room file description from disk.
     * 
     * @param fullDescriptionFileName the filename/path of the file that has the description for the room.
     */
        static void getFullDescription(String fullDescriptionFileName) {

        try {

            File textFile = new File(fullDescriptionFileName);
            Scanner fileReader = new Scanner(textFile);

            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                System.out.println(data);

            }

            fileReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    
    
    /** 
     * A coordinating function to load specific location by calling all the functions required to load a room.
     * 
     * This will (maybe) add a character to a room (if the room even allows it), keep track whether the
     * player has visited the room, print the correct description for whether they've visited the room before
     * or not, show the exits, show the items, and show the characters, and importantly, set the characterDialogue
     * value to a scanner (via startCharacterEncounter) for that specific character, so talking with that character works.
     * 
     * The characterDialogue scanner is then accessed, via gameState, in Actions, under the "talk" switch value. It's
     * there that Actions reaches into GameState to interact with gameState.location.characterDialogue, which is this
     * specific Location's dialogue scanner.
     * 
     * @param gameState the game state
     */
    public void loadLocation(GameState gameState) {

        // prevents monsters from entering the first room ( any room but only applicable
        // to the first room, White Rabbit House, in this game)
        if (noNpcsAllowed == false) {
            characterGenerator(gameState);

        }

        if (hasVisited) {
            System.out.println(shortDescription);

        } else {
            getFullDescription(fullDescriptionFileName);
            hasVisited = true;

        }
        printExits();
        displayItems();
        displayCharacters();
        if (npc != null) {
            startCharacterEncounter();

        }

    }

    /**
     * Print the current exits for the room.
     */
    public void printExits() {

        Set<String> exits = roomExitHashMap.keySet();

        System.out.println("The exits are:");
        System.out.println(exits.toString());
        System.out.println("--------------------------------------------------------------------------------");

    }

    /**
     * Display all the items in the room. Called by loadLocation.
     */
    public void displayItems() {

        String lastDisplayedName = null;

        for (Items item : items) {
            System.out.println("The room contains " + item.name);
            if (lastDisplayedName == null || !lastDisplayedName.equals(item.name)) {
                System.out.println(item.displayText);
                System.out.println("--------------------------------------------------------------------------------");
                lastDisplayedName = item.name;
            } else {
                System.out.println("grab to add more " + item.name + " for your collection!");
                System.out.println("--------------------------------------------------------------------------------");
            }
        }

    }

    
    /** 
     * This randomly put a character in a room based on probability. Maybe no characters show depending on chance.
     * 
     * This also has some custom logic to force the Queen of Hearts to show up if the player has the mallet item
     * in their inventory.
     * 
     * @param gameState the game state
     */
    public void characterGenerator(GameState gameState) {

        // Conditional logic so that the Queen of Hearts shows up if the player has the mallet; characterGenerator is only
        // called in rooms where characters can appear.
        Items mallet = gameState.inventory.checkAndGetItem("mallet");
        if (mallet != null) {
            for (Characters character: charactersList) {
                if (character.characterName == "Queen of Hearts") {
                    this.npc = character;
                }
            }
        } else {

        // When the player doesn't have a mallet, do the normal character generation check.

            Random random = new Random();
            double randomNumber = random.nextDouble();
            // set character probability. 0.8 = 80% chance of a character.
            double probabilityThreshold = 0.6;
            Collections.shuffle(charactersList);

            // Set a character if threshold is greater than the random number; otherwise,
            // clear any character that may be there from a previous room entry.
            if (probabilityThreshold >= randomNumber) {
                this.npc = charactersList.get(0);
            } else {
                this.npc = null;
            }
        }

    }

    /**
     * Display that a character is present, if one is indeed present.
     */
    public void displayCharacters() {

        if (this.npc != null) {
            System.out.println(npc.characterName + " is here");
        }

    }

    /**
     * Runs when a room is entered (in loadLocation), if a character is there. Keeps track of
     * which characters the player has met. Also assigns a scanner to characterDialogue, which
     * prints the first immediately. To access more lines, type `talk character`. The "talk" switch
     * value in Actions access the scanner set here, and does so via GameState.
     */
    public void startCharacterEncounter() {
        if (npc.haveMet == false) {
            try {
                this.characterDialogue = new Scanner(new File(npc.characterTextFile));
                System.out.println(characterDialogue.nextLine());

                npc.haveMet = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(npc.characterName + " greets you again");

        }
    }

    
    /** 
     * Return true if player can enter the room.
     * 
     * Checks size requirements and item requirements, as some rooms require the player to be a
     * certain size or have a certain item. E.g. you need the "key" item to get into the MadHatterHouse
     * Location.
     * 
     * @param inventory the player's inventory.
     * @param player the player Character instance.
     * @return boolean
     */
    public boolean playerMeetsEntryRequirements(Inventory inventory, Characters player) {
        System.out.println("Checking requirements...");
    
        // Check item requirements
        for(String entryItem : itemEntryRequirements ){
            Items inventoryItem = inventory.checkAndGetItem(entryItem);
            if(inventoryItem == null){
                System.out.println("Sorry, you are missing " + entryItem);
                return false;
            } 
        }
        
        // Check size requirements
        if(player.size != sizeEntryRequirements){
            // use sizeText to tell the player the size they need to be for the room.
            String sizeText;
            switch (sizeEntryRequirements) {
                case -1:
                  sizeText = "small";
                  break;
                case 1:
                  sizeText = "big";
                  break;
                default:
                  sizeText = "normal";
                  break;
            }
          
            System.out.println("Sorry, you are the wrong SiZe! You need to be " + sizeText + ".");
            return false;
        }

        return true;

    }

    /**
     * A special logic for the hole and winning rooms, other rooms won't use it.
     * 
     * @param gameState the game state
     */
    public void specialLocationLogic(GameState gameState){}
}
