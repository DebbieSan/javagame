/**
 * An item to get out of TheHole that extends Items.
 */
public class ItemTransporter extends Items {

     /**
     * The default constructor
     *  
     * @param name the character name.
     * @param displayTextFileName the display text to use for the item, as loaded by setItemDisplayText.
     * @param count the count of this particular item
     * @param useItemModifier the amount by which subclasses will modify an attribute via an overriden useItem.
     * @param consumeOnUse whether an item is consumed. Typically only items based on the ItemEnergy subclass are consumed.
     */
    public ItemTransporter(String name, String displayTextFileName, int count, int useItemModifier, boolean consumeOnUse) {
        super(name, displayTextFileName, count, useItemModifier, consumeOnUse);
    }

    /**
     * Teleports the player to the saferoom when used.
     * 
     * @param gameState the game state
     */
    @Override
    public void useItem(GameState gameState) {
        Locations safeRoom = gameState.teleportLocation.get("safeRoom");
        gameState.location = safeRoom;
        gameState.location.loadLocation(gameState);
        // No energy reduction while using the transporter. Wonderland is weird!
        gameState.location.specialLocationLogic(gameState); 
        if (gameState.location.npc != null) {
            gameState.location.npc.specialCharacterLogic(gameState);
        }
    }
    
}
