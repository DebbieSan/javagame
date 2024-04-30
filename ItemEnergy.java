import java.util.Random;

/**
 * An Items subclass for items that modify the player's energy.
 */
public class ItemEnergy extends Items {
     /**
     * The default constructor
     *  
     * @param name the character name.
     * @param displayTextFileName the display text to use for the item, as loaded by setItemDisplayText.
     * @param count the count of this particular item
     * @param useItemModifier the amount by which subclasses will modify an attribute via an overriden useItem.
     * @param consumeOnUse whether an item is consumed. Typically only items based on the ItemEnergy subclass are consumed.
     */
    public ItemEnergy(String name, String displayTextFileName, int count, int useItemModifier, boolean consumeOnUse) {
        super(name, displayTextFileName, count, useItemModifier, consumeOnUse);
    }

    
    /** 
     * Logic for using items that modify the player's energy.
     * 
     * This will either add energy or reduce energy from the player.
     * Some items always reduce energy, and some *USUALLY* increase energy, but there is a chance all items that increase
     * energy may lower energy. Items that reduce energy always reduce energy.
     * 
     * @param gameState the game state.
     */
    @Override
    public void useItem(GameState gameState) {
        // Using food items has a random outcomes, because sometimes food is BAD and takes away health.
        Random random = new Random();
        double randomNumber = random.nextDouble();
        double probabilityThreshold = 0.2;

        // 20% chance of losing 10 energy, but only for food with a positive energy change.
        // This doesn't apply to items that are meant to reduce energy anyway.
        if (useItemModifier > 0 && randomNumber < probabilityThreshold) {
            gameState.player.energy -= 10;
            System.out.println("Oh no! The " + name + " was bad! " + gameState.player.characterName + " now has " + gameState.player.energy + " energy");
        } else {
            gameState.player.energy += useItemModifier;
            if(gameState.player.energy > 50){
                gameState.player.energy = 50;
            }
       
            System.out.println(gameState.player.characterName + " now has " + gameState.player.energy + " energy");
        }
    
    }
}
