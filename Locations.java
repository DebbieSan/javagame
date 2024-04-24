import java.util.ArrayList;
import java.util.HashMap; // import the HashMap class
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Set;
import java.util.Random;//import the random number class
import java.util.Collections;

public class Locations {

    // HashMap<String, Locations> roomExitHashMap = new HashMap<String,
    // Locations>();
    HashMap<String, Locations> roomExitHashMap;
    boolean hasVisited = false;
    String fullDescriptionFileName;
    String shortDescription;
    ArrayList<Items> items;
    boolean isSafeRoom = false;
    ArrayList<Characters> charactersList;
    Characters npc;
    boolean noNpcsAllowed = false;
    boolean haveMet = false;
    Scanner characterDialogue;

    public Locations(HashMap<String, Locations> roomExitHashMap, boolean hasVisited, String fullDescriptionFileName,
            String shortDescription, ArrayList<Items> items, boolean isSafeRoom, ArrayList<Characters> charactersList,
            boolean noNpcsAllowed) {
        this.roomExitHashMap = roomExitHashMap;
        this.hasVisited = hasVisited;
        this.fullDescriptionFileName = fullDescriptionFileName;
        this.shortDescription = shortDescription;
        this.items = items;
        this.isSafeRoom = isSafeRoom;
        this.charactersList = charactersList;
        this.noNpcsAllowed = noNpcsAllowed;

    }

    /* Reads the full room file description from disk */

    static void getFullDescription(String fullDescriptionFileName) {

        try {

            File textFile = new File(fullDescriptionFileName);
            Scanner fileReader = new Scanner(textFile);

            // For each line: add that line's word count to the total.

            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                System.out.println(data);

            }

            fileReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void loadLocation() {

        // prevents monsters from entering the first room ( any room but only applicable
        // to the first room, White Rabbit House, in this game)
        if (noNpcsAllowed == false) {
            characterGenerator();

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

    public void printExits() {

        Set<String> exits = roomExitHashMap.keySet();

        System.out.println("The exits are:");
        System.out.println(exits.toString());
        System.out.println("--------------------------------------------------------------------------------");

    }

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

            /*
             * if (!hasDisplayed) {
             * System.out.println(item.displayText);
             * item.name.hasDisplayed = true;
             * 
             * } else {
             * System.out.println(
             * "--------------------------------------------------------------------------------"
             * );
             * }
             */
        }

    }

    // This randomly put a character in a room based on probability. Maybe no
    // characters show depending on chance.

    public void characterGenerator() {

        // is there a character?

        Random characterProbability = new Random();
        double randomNumber = characterProbability.nextDouble();
        double probabilityThreshold = 0.0;
        Collections.shuffle(charactersList);

        if (randomNumber >= probabilityThreshold) {
            this.npc = charactersList.get(0);

        }

    }

    public void displayCharacters() {

        if (this.npc != null) {
            System.out.println(npc.characterName + " is here");
        }

    }

    public void startCharacterEncounter() {
        if (haveMet == false) {
            try {
                this.characterDialogue = new Scanner(new File(npc.characterTextFile));
                System.out.println(characterDialogue.nextLine());

                /*
                 * while (sc2.hasNextLine()) {
                 * System.out.println(sc2.nextLine());
                 * 
                 * }
                 */

                haveMet = true;
                // sc2.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(npc.characterName + " says greets you again");

        }
    }
}
