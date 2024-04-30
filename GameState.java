import java.util.HashMap;

/**
 * GameState is a state manager that gives the game access to the core class objects.
 *
 * This is used so instead of having to pass many different class
 * instances around or use global variables, much of that can be stored as an attribute of this class,
 * and then there's one source of truth for all the state/values.
 */
public class GameState {
    /**
     * The player's character
     */
    public Characters player;
    /**
     * the current location in the game
    */  
    public Locations location;
    /**
     * the last verb and noun typed by the player.
     */
    public String lastVerb, lastNoun;
    /**
     * the player's inventory.
     */
    public Inventory inventory;
    HashMap<String,Locations> teleportLocation ;
    HashMap<String,Items> handyItems;

    /**
     * The default constructor.
     * 
     * @param player the player's character
     * @param location the current location in the game (i.e. the current room).
     * @param inventory the player's inventory
     * @param teleportLocation a map of rooms that can be teleported to (used by the transporter, and the `use win` cheat.)
     * @param handyItems a map of items that the game can access any time. Used by the MadHatter to access bunnies from thin air.
     */
    public GameState(
        Characters player,
        Locations location,
        Inventory inventory,
        HashMap<String,Locations> teleportLocation,
        HashMap<String,Items> handyItems
    ) {
        this.player = player;
        this.location = location;
        this.inventory = inventory;
        this.teleportLocation = teleportLocation;
        this.handyItems = handyItems;
    }

    
    /** 
     * Checks whether a direction is valid.
     * 
     * @param direction the direction keey, such as "north", "south", etc.
     * @return boolean
     */
    public boolean isValidExit(String direction) {
        Locations exit = location.roomExitHashMap.get(direction);
        return (exit != null) ? true : false;
    }

    
    /** 
     * Set the Location associated with direction as the current Location in the GameState.
     * 
     * @param direction the direction key, such as "north", "south", etc.
     */
    public void setLocation(String direction) {
        location = location.roomExitHashMap.get(direction);
    }

    
    /** 
     * Get the Location object for a particular direction
     * 
     * @param direction the direction key, such as "north", "south", etc.
     * @return Locations
     */
    public Locations getLocation(String direction) {
        return location.roomExitHashMap.get(direction);
    }

    
    /** 
     * Take the user's most recently typed verb and set that as lastVerb for use in the GameState.
     * 
     * @param verb the verb that the player typed as input.
     */
    public void setLastVerb(String verb) {
        this.lastVerb = verb;
    }

    
    /** 
     * Take the user's most recently typed noune and set that as lastNoun for use in the GameState.
     * 
     * @param noun the noun that the player typed as input.
     */
    public void setLastNoun(String noun) {
        this.lastNoun = noun;
    }

}
