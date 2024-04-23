import java.util.ArrayList;
import java.util.HashMap; // import the HashMap class
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Set;

public class Locations {

    // HashMap<String, Locations> roomExitHashMap = new HashMap<String,
    // Locations>();
    HashMap<String, Locations> roomExitHashMap;
    boolean hasVisited = false;
    String fullDescriptionFileName;
    String shortDescription;
    ArrayList<Items> items;
    boolean isSafeRoom = false;

    public Locations(HashMap<String, Locations> roomExitHashMap, boolean hasVisited, String fullDescriptionFileName,
            String shortDescription, ArrayList<Items> items, boolean isSafeRoom) {
        this.roomExitHashMap = roomExitHashMap;
        this.hasVisited = hasVisited;
        this.fullDescriptionFileName = fullDescriptionFileName;
        this.shortDescription = shortDescription;
        this.items = items;
        this.isSafeRoom = isSafeRoom;

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
        if (hasVisited) {
            System.out.println(shortDescription);
            printExits();
            displayItems();

        } else {
            getFullDescription(fullDescriptionFileName);
            hasVisited = true;
            printExits();
            displayItems();

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

}
