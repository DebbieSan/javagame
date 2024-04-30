import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.ArrayList; // import this class for a dynamic list
import java.util.Arrays; // import this class for Array methods
import java.util.HashMap;//import the HashMap class
import java.util.Scanner; // Import the Scanner class to read text files

/**
 * Compilation: javac Wonderland.java
 * Run: java Wonderland
 * Generate Javadocs: javadoc 
 * 
 * Wonderland is the main entrypoint for the Wonderland game.
 * 
 * It has the main function * that coordinates all the game logic and instantiates all the classes.
 * This includes:
 * - creating every Character and putting them into an ArrayList of Characters;
 * - creating every item;
 * - creating every location;
 * - creating the Control class object;
 * - creating the Actions class object; and
 * - creating the GameState class object.
 * 
 * Everything is then loaded into its respective objects (e.g. items into rooms), then all the objects are
 * loaded into GameState, information the game is printed, the first locatoin is loaded, and then the
 * main function calls Control and passes in the GameState object, and that starts a Scanner that awaits player
 * input in an infinite loop, parsing each command in Control, sending it to Actions, and using the appropriate item,
 * loading the appropriate room, etc.
 */
public class Wonderland {
    /**
     * The main entry point.
     * @param args this game takes no arguments
     */
    public static void main(String[] args) {
        // Create Alice
        
        Characters alice = new Characters("alice", 50, "alice.txt");

        // Create other characters

        Characters queenOfHearts = new Characters("Queen of Hearts", 50, "queenOfHearts.txt");
        Characters whiteRabbit = new Characters("White Rabbit", 50, "whiteRabbit.txt");
        Characters cheshireCat = new Characters("Cheshire Cat", 50, "cheshireCat.txt");
        Characters madHatter = new MadHatterCharacter("Mad Hatter", 50, "madHatter.txt");
        Characters kingOfHearts = new Characters("King of Hearts", 50, "kingOfHearts.txt");

        ArrayList<Characters> charactersList = new ArrayList<>(
                Arrays.asList(queenOfHearts, whiteRabbit, cheshireCat, madHatter, kingOfHearts));

        // load items into memory

        Items cookie = new ItemSize(
                "cookie",
                "ItemTextCookie.txt",
                1,
                -1,
                true
                );

        Items bottle = new ItemSize(
                "bottle",
                "ItemTextBottle.txt",
                1,
                1,
                true
        );

        Items mushroom = new ItemSize(
                "mushroom",
                "ItemTextMushroom.txt",
                1,
                0,
                true
        );

        Items butterfly = new ItemEnergy(
                "butterfly",
                "ItemTextButterfly.txt",
                1,
                -10,
                true
        );

        Items key = new Items(
                "key",
                "ItemTextKey.txt",
                1,
                0,
                false
        );
        
        Items transporter = new ItemTransporter(
                "transporter",
                "ItemTextTransporter.txt",
                1,
                0,
                false
        );
        

        Items turkey = new ItemEnergy(
                "turkey",
                "ItemTextTurkey.txt",
                1,
                50,
                true
        );

        Items apple = new ItemEnergy(
                "apple",
                "ItemTextApple.txt",
                1,
                25,
                true
        );

        Items bunny = new Items(
                "bunny",
                "ItemTextBunny.txt",
                1,
                0,
                false
        );

        Items coin = new Items(
                "coin",
                "ItemTextCoin.txt",
                1,
                0,
                false
        );

        Items watch = new Items(
                "watch",
                "ItemTextWatch.txt",
                1,
                0,
                false
        );

        Items fan = new ItemSize(
                "fan",
                "ItemTextFan.txt",
                1,
                -1,
                false
        );

        Items flower = new ItemEnergy(
                "flower",
                "ItemTextFlower.txt",
                1,
                -5,
                true
        );

        Items tea = new ItemEnergy(
                "tea",
                "ItemTextTea.txt",
                1,
                5,
                true
        );
                
        Items treacle = new ItemEnergy(
                "treacle",
                "ItemTextTreacle.txt",
                1,
                -1,
                true
        );
                
        Items mallet = new Items(
                "mallet",
                "ItemTextMallet.txt",
                1,
                0,
                false
        );
                
        Items orange = new ItemEnergy(
                "orange",
                "ItemTextOrange.txt",
                1,
                15,
                true
        );
                
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
                new ArrayList<>(Arrays.asList(transporter, cookie, cookie, bunny, bunny, coin, coin, mushroom, apple, butterfly, key, butterfly, cookie, bottle, watch)),
                false,
                charactersList,
                true,
                new ArrayList<>(Arrays.asList()),
                0
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
                new ArrayList<>(Arrays.asList(bottle, bunny, bunny, bunny, coin, coin, mushroom, butterfly, tea, treacle)),
                false,
                charactersList,
                false,
                new ArrayList<>(Arrays.asList("key")),
                0

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
                0

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
                new ArrayList<>(Arrays.asList(key, coin, coin, mushroom, apple, flower, fan, mallet)),
                false,
                charactersList,
                false,
                new ArrayList<>(Arrays.asList()),
                -1  // Must eat cookies to be tiny to get in.
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
                new ArrayList<>(Arrays.asList(turkey, coin, coin, coin, mushroom, turkey, bunny, bunny)),
                true,
                charactersList,
                true,
                new ArrayList<>(Arrays.asList()),
                0
        );

        roomExitHashMap = new HashMap<String, Locations>();
        hasVisited = false;
        fullDescriptionFileName = "TheTree.txt";
        shortDescription = " Welcome back to The Tree!  \n--------------------------------------------------------------------------------";
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
                0
        );


        roomExitHashMap = new HashMap<String, Locations>();
        hasVisited = false;
        fullDescriptionFileName = "TheWonderlandMaze.txt";
        shortDescription = " Welcome back to The Wonderland Maze!  \n--------------------------------------------------------------------------------";
        Locations WonderlandMaze = new Locations(
                roomExitHashMap,
                hasVisited,
                fullDescriptionFileName,
                shortDescription,
                new ArrayList<>(Arrays.asList(coin, coin, coin, bunny, orange)),
                false,
                charactersList,
                false,
                new ArrayList<>(Arrays.asList()),
                1 // Must be big to enter - use the bottle.
        );


        // load locations into the HashMap of rooms. Each room stores its exits in a HashMap, where
        // for example, 'north' is associated with a particular location object, and 'south' another.
        // Each room only knows its own exits, but ultimately they form a web of connected rooms.

        WhiteRabbitHouse.roomExitHashMap.put("east", MadHatterHouse);

        WhiteRabbitHouse.roomExitHashMap.put("south", HoleRoom);

        MadHatterHouse.roomExitHashMap.put("west", WhiteRabbitHouse);

        MadHatterHouse.roomExitHashMap.put("east", WonderlandMaze);

        MadHatterHouse.roomExitHashMap.put("north", MeadowOfLivingFlowers);

        WonderlandMaze.roomExitHashMap.put("west", MadHatterHouse);

        MeadowOfLivingFlowers.roomExitHashMap.put("south", MadHatterHouse);

        MeadowOfLivingFlowers.roomExitHashMap.put("west", SafeRoom);

        MeadowOfLivingFlowers.roomExitHashMap.put("east", HoleRoom);

        MeadowOfLivingFlowers.roomExitHashMap.put("north", WinningLocation);

        SafeRoom.roomExitHashMap.put("east", MeadowOfLivingFlowers);

        SafeRoom.roomExitHashMap.put("south", WhiteRabbitHouse);

        WinningLocation.roomExitHashMap.put("south", MeadowOfLivingFlowers);



        // teleporting rooms - rooms the game needs to access without a connection via the roomExitHashMap.

        HashMap<String,Locations> teleportLocations = new HashMap<>();
        teleportLocations.put("safeRoom", SafeRoom);
        teleportLocations.put("winningLocationRoom", WinningLocation);

        // Map of items to make available in game state. The Mad Hatter summons bunnies from here.
        HashMap<String,Items> handyItems = new HashMap<>();
        handyItems.put("bunny", bunny);

        /*
         * Print out the Introduction
         */
        String fileName = "welcome.txt";

        // https://www.w3schools.com/java/java_files_read.asp
        try {
            File textFile = new File(fileName);
            Scanner fileReader = new Scanner(textFile);

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

        GameState gameState = new GameState(alice, WhiteRabbitHouse, inventory, teleportLocations, handyItems);
        gameState.location.loadLocation(gameState);
        // Entering first room

        // entering control loop to run the game

        Control control = new Control(gameState);

        control.userInput();

    }
}
