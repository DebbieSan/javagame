public class GameState {

    public Characters player;
    public Locations location;
    public String lastVerb, lastNoun;
    public Inventory inventory;

    public GameState(Characters player, Locations location, Inventory inventory) {
        this.player = player;
        this.location = location;
        this.inventory = inventory;
    }

    public boolean isValidExit(String direction) {
        Locations exit = location.roomExitHashMap.get(direction);
        return (exit != null) ? true : false;
    }

    public void setLocation(String direction) {
        location = location.roomExitHashMap.get(direction);
    }

    public void setLastVerb(String verb) {
        this.lastVerb = verb;
    }

    public void setLastNoun(String noun) {
        this.lastNoun = noun;
    }

}
