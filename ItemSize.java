/** 
 * Subclass for items that change player size.
 * 
 * useItemModifier is an int that says how much to change the relevant attribute. In this class,
 * useItem will change player.size by the amount in useItemModifier.
 */
public class ItemSize extends Items {
     /**
     * The default constructor
     *  
     * @param name the character name.
     * @param displayTextFileName the display text to use for the item, as loaded by setItemDisplayText.
     * @param count the count of this particular item
     * @param useItemModifier the amount by which subclasses will modify an attribute via an overriden useItem.
     * @param consumeOnUse whether an item is consumed. Typically only items based on the ItemEnergy subclass are consumed.
     */
    public ItemSize(String name, String displayTextFileName, int count, int useItemModifier, boolean consumeOnUse) {
        super(name, displayTextFileName, count, useItemModifier, consumeOnUse);
    }

    
    /** 
     * Makes the player small, big, or normal sized, depending on the item used.
     * 
     * Prints output to the player to inform them of the change.
     * 
     * @param gameState the game state
     */
    @Override
    public void useItem(GameState gameState) {
        gameState.player.size = useItemModifier;
        String sizeText;
        if(gameState.player.size < 0){
            sizeText = "small";

        } else if (gameState.player.size > 0){
            sizeText = "big";
        } else{
            sizeText = "normal";
        }
        System.out.println(gameState.player.characterName + " is now size " + sizeText);
    }
}
