import java.util.HashMap;


public class GameState {

    public Characters player;
    public Locations location;
    public String lastVerb, lastNoun;
    public Inventory inventory;

    HashMap<String,Locations> teleportLocation ;

    public GameState(Characters player, Locations location, Inventory inventory, HashMap<String,Locations> teleportLocation ) {
        this.player = player;
        this.location = location;
        this.inventory = inventory;
        this.teleportLocation = teleportLocation;
    }

    public boolean isValidExit(String direction) {
        Locations exit = location.roomExitHashMap.get(direction);
        return (exit != null) ? true : false;
    }

    public void setLocation(String direction) {
        location = location.roomExitHashMap.get(direction);
    }

    public Locations getLocation(String direction) {
        return location.roomExitHashMap.get(direction);
    }

    public void setLastVerb(String verb) {
        this.lastVerb = verb;
    }

    public void setLastNoun(String noun) {
        this.lastNoun = noun;
    }

}
