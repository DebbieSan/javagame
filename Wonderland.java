import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.ArrayList; // import this class for a dynamic list
import java.util.Arrays; // import this class for Array methods
import java.util.HashMap;//import the HashMap class
import java.util.Scanner; // Import the Scanner class to read text files

public class Wonderland {

    public static void main(String[] args) {

        // Create Alice

        Characters alice = new Characters("alice", 50, "alice.txt");

        // Create other characters

        Characters queenOfHearts = new Characters("Queen of Hearts", 50, "queenOfHearts.txt");
        Characters whiteRabbit = new Characters("White Rabbit", 50, "whiteRabbit.txt");
        Characters cheshireCat = new Characters("Cheshire Cat", 50, "cheshireCat.txt");
        Characters madHatter = new Characters(" Mad Hatter", 50, "madHatter.txt");
        Characters kingOfHearts = new Characters("King of Hearts", 50, "kingOfHearts.txt");

        ArrayList<Characters> charactersList = new ArrayList<>(
                Arrays.asList(queenOfHearts, whiteRabbit, cheshireCat, madHatter, kingOfHearts));

        // load items into memory

        Items cookie = new ItemSize(
                "cookie",
                "The cookie reads 'eat me'",
                1,
                -1
                );

        Items bottle = new ItemSize(
                "bottle",
                "The bottle reads 'drink me'",
                1,
                1
        );

        Items mushroom = new ItemSize(
                "mushroom",
                "The mushroom helps you get back to normal",
                1,
                0);

        Items butterfly = new ItemEnergy(
                "butterfly",
                "what a cute little butterfly, I bet I can use it!",
                1,
                -10);

        Items key = new Items(
                "key",
                "Use this key to enter through tiny doors.",
                1,
                0);
        
        Items transporter = new Items(
                "transporter",
                "Use this transporter to get out of tough situations.",
                1,
                0);
        

        Items turkey = new ItemEnergy(
                "turkey",
                "Eat this turkey to recover your full energy.",
                1,
                50);

        Items apple = new ItemEnergy(
                "apple",
                "This apple restores half of your energy",
                1,
                25);

        Items bunny = new Items(
                "bunny",
                "Collect bunnies and keep them with you to protect them against evil.",
                1,
                0);

        Items coin = new Items(
                "coin",
                "Collect coins to increase points",
                10,
                0);

        
                
        



        // load locations into memory

        HashMap<String, Locations> roomExitHashMap = new HashMap<String, Locations>();
        boolean hasVisited = false;
        String fullDescriptionFileName = "WhiteRabbitHouse.txt";
        String shortDescription = " Welcome back to the White Rabbit House! \n-------------------------------------------------------------------------------- ";
        Locations WhiteRabbitHouse = new Locations(
                roomExitHashMap,
                hasVisited,
                fullDescriptionFileName,
                shortDescription,
                new ArrayList<>(Arrays.asList(transporter, cookie, cookie, bunny, bunny, coin, coin, mushroom, apple, turkey, butterfly, key)),
                false,
                charactersList,
                true,
                new ArrayList<>(Arrays.asList()),
                new ArrayList<>(Arrays.asList())
        );

        roomExitHashMap = new HashMap<String, Locations>();
        hasVisited = false;
        fullDescriptionFileName = "MadHatterHouse.txt";
        shortDescription = " Welcome back to the Mad Hatter House!  \n--------------------------------------------------------------------------------";
        Locations MadHatterHouse = new Locations(
                roomExitHashMap,
                hasVisited,
                fullDescriptionFileName,
                shortDescription,
                new ArrayList<>(Arrays.asList(bottle, bunny, bunny, bunny, coin, mushroom, butterfly)),
                false,
                charactersList,
                false,
                new ArrayList<>(Arrays.asList("key")),
                new ArrayList<>(Arrays.asList())

        );

        roomExitHashMap = new HashMap<String, Locations>();
        hasVisited = false;
        fullDescriptionFileName = "HoleRoom.txt";
        shortDescription = " Welcome back to the Hole Room!  \n--------------------------------------------------------------------------------";
        Locations HoleRoom = new HoleLocation(
                roomExitHashMap,
                hasVisited,
                fullDescriptionFileName,
                shortDescription,
                new ArrayList<>(Arrays.asList()),
                false,
                charactersList,
                true,
                new ArrayList<>(Arrays.asList()),
                new ArrayList<>(Arrays.asList())

        );


        roomExitHashMap = new HashMap<String, Locations>();
        hasVisited = false;
        fullDescriptionFileName = "MeadowOfLivingFlowers.txt";
        shortDescription = " Welcome back to the Meadow of the Living Flowers!  \n--------------------------------------------------------------------------------";
        Locations MeadowOfLivingFlowers = new Locations(
                roomExitHashMap,
                hasVisited,
                fullDescriptionFileName,
                shortDescription,
                new ArrayList<>(Arrays.asList(key, coin, coin, mushroom, apple)),
                false,
                charactersList,
                false,
                new ArrayList<>(Arrays.asList()),
                new ArrayList<>(Arrays.asList())
        );

        roomExitHashMap = new HashMap<String, Locations>();
        hasVisited = false;
        fullDescriptionFileName = "SafeRoom.txt";
        shortDescription = " Welcome back to the Safe Room!  \n--------------------------------------------------------------------------------";
        Locations SafeRoom = new Locations(
                roomExitHashMap,
                hasVisited,
                fullDescriptionFileName,
                shortDescription,
                new ArrayList<>(Arrays.asList(turkey, coin, mushroom, turkey)),
                true,
                charactersList,
                true,
                new ArrayList<>(Arrays.asList()),
                new ArrayList<>(Arrays.asList())
        );

        roomExitHashMap = new HashMap<String, Locations>();
        hasVisited = false;
        fullDescriptionFileName = "WinningLocationText.txt";
        shortDescription = " Welcome back to the Winning Location!  \n--------------------------------------------------------------------------------";
        Locations WinningLocation = new WinningLocation(
                roomExitHashMap,
                hasVisited,
                fullDescriptionFileName,
                shortDescription,
                new ArrayList<>(Arrays.asList()),
                true,
                charactersList,
                true,
                new ArrayList<>(Arrays.asList()),
                new ArrayList<>(Arrays.asList())
        );


        WhiteRabbitHouse.roomExitHashMap.put("east", MadHatterHouse);

        WhiteRabbitHouse.roomExitHashMap.put("south", HoleRoom);

        MadHatterHouse.roomExitHashMap.put("west", WhiteRabbitHouse);

        MadHatterHouse.roomExitHashMap.put("north", MeadowOfLivingFlowers);

        MeadowOfLivingFlowers.roomExitHashMap.put("south", MeadowOfLivingFlowers);

        MeadowOfLivingFlowers.roomExitHashMap.put("west", SafeRoom);

        MeadowOfLivingFlowers.roomExitHashMap.put("east", HoleRoom);

        MeadowOfLivingFlowers.roomExitHashMap.put("north", WinningLocation);

        SafeRoom.roomExitHashMap.put("east", MeadowOfLivingFlowers);

        SafeRoom.roomExitHashMap.put("south", WhiteRabbitHouse);

        SafeRoom.roomExitHashMap.put("south", WhiteRabbitHouse);

        WinningLocation.roomExitHashMap.put("south", MeadowOfLivingFlowers);



        // teleporting rooms

        HashMap<String,Locations> teleportLocations = new HashMap<>();
        teleportLocations.put("safeRoom", SafeRoom);
        teleportLocations.put("winningLocationRoom", WinningLocation);

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

        // Create Inventory

        Inventory inventory = new Inventory();

        // Instantiate game state

        GameState gameState = new GameState(alice, WhiteRabbitHouse, inventory, teleportLocations);
        gameState.location.loadLocation();
        // Entering first room

        // entering control loop

        Control control = new Control(gameState);

        control.userInput();

    }
}