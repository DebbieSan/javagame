import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.ArrayList; // import this class for a dynamic list
import java.util.Arrays; // import this class for Array methods
import java.util.HashMap;//import the HashMap class
import java.util.Scanner; // Import the Scanner class to read text files

public class Wonderland {

    public static void main(String[] args) {

        // load items into memory

        Items cookie = new Items(
                "cookie",
                "The cookie reads 'eat me'",
                0,
                1);

        Items bottle = new Items(
                "bottle",
                "The bottle reads 'drink me'",
                0,
                1);

        Items key = new Items(
                "key",
                "Use this key to enter through tiny doors.",
                0,
                1);

        Items turkey = new Items(
                "turkey",
                "Eat this turkey to recover your full energy.",
                0,
                1);

        Items bunny = new Items(
                "bunny",
                "Collect bunnies and keep them with you to protect them against evil.",
                0,
                1);

        Items coin = new Items(
                "coin",
                "Collect coins to increase points",
                0,
                10);

        Items energy = new Items(
                "energy",
                "You have 50 energy power",
                50,
                1);

        Items apple = new Items(
                "apple",
                "This apple restores half of your energy",
                25,
                1);

        // load locations into memory

        HashMap<String, Locations> roomExitHashMap = new HashMap<String, Locations>();
        boolean hasVisited = false;
        String fullDescriptionFileName = "WhiteRabbitHouse.txt";
        String shortDescription = " Welcome back to the White Rabbit House! \n-------------------------------------------------------------------------------- ";
        Locations WhiteRabbitHouse = new Locations(roomExitHashMap, hasVisited, fullDescriptionFileName,
                shortDescription, new ArrayList<>(Arrays.asList(cookie, cookie, bunny, bunny, coin, coin)),
                false);

        roomExitHashMap = new HashMap<String, Locations>();
        hasVisited = false;
        fullDescriptionFileName = "MadHatterHouse.txt";
        shortDescription = " Welcome back to the Mad Hatter House!  \n--------------------------------------------------------------------------------";
        Locations MadHatterHouse = new Locations(roomExitHashMap, hasVisited, fullDescriptionFileName,
                shortDescription, new ArrayList<>(Arrays.asList(bottle, bunny, bunny, bunny, coin)), false);

        roomExitHashMap = new HashMap<String, Locations>();
        hasVisited = false;
        fullDescriptionFileName = "MeadowOfLivingFlowers.txt";
        shortDescription = " Welcome back to the Meadow of the Living Flowers!  \n--------------------------------------------------------------------------------";
        Locations MeadowOfLivingFlowers = new Locations(roomExitHashMap, hasVisited, fullDescriptionFileName,
                shortDescription, new ArrayList<>(Arrays.asList(key, coin, coin)), false);

        roomExitHashMap = new HashMap<String, Locations>();
        hasVisited = false;
        fullDescriptionFileName = "SafeRoom.txt";
        shortDescription = " Welcome back to the Safe Room!  \n--------------------------------------------------------------------------------";
        Locations SafeRoom = new Locations(roomExitHashMap, hasVisited, fullDescriptionFileName,
                shortDescription, new ArrayList<>(Arrays.asList(turkey, coin)), true);

        WhiteRabbitHouse.roomExitHashMap.put("east", MadHatterHouse);

        MadHatterHouse.roomExitHashMap.put("west", WhiteRabbitHouse);

        MadHatterHouse.roomExitHashMap.put("north", MeadowOfLivingFlowers);

        MeadowOfLivingFlowers.roomExitHashMap.put("south", MeadowOfLivingFlowers);

        MeadowOfLivingFlowers.roomExitHashMap.put("west", SafeRoom);

        SafeRoom.roomExitHashMap.put("east", MeadowOfLivingFlowers);

        SafeRoom.roomExitHashMap.put("south", WhiteRabbitHouse);

        /*
         * Introduction
         */
        String fileName = "welcome.txt";

        // https://www.w3schools.com/java/java_files_read.asp
        try {
            File textFile = new File(fileName);
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
        // Create Alice

        Characters alice = new Characters("alice");

        // Create other characters

        Characters queenOfHearts = new Characters("queenOfHearts");
        Characters whiteRabbit = new Characters("whiteRabbit");
        Characters cheshireCat = new Characters("cheshireCat");
        Characters madHatter = new Characters("madHatter");
        Characters kingOfHearts = new Characters("kingOfHearts");

        // Create Inventory

        Inventory inventory = new Inventory();

        // Instantiate game state

        GameState gameState = new GameState(alice, WhiteRabbitHouse, inventory);
        gameState.location.loadLocation();
        // Entering first room

        // entering control loop

        Control control = new Control(gameState);

        control.userInput();

        /*
         * gameState.location.loadLocation();
         * gameState.setLocation("east");
         * 
         * gameState.location.loadLocation();
         * 
         * gameState.setLocation("west");
         * 
         * gameState.location.loadLocation();
         */

        // MadHatterHouse.loadLocation();
        // MadHatterHouse.loadLocation();
    }
}