import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The default Items class, and model for most items. Items are used in Inventory.inventory and Locations.items.
 * 
 * ItemEnergy and ItemSize subclass this class.
 * 
 * useItemModifier is an int that changes the amount which a specific attribute is changed. Which attribute
 * is changed depends on the subclass and what's put in useItem.
 */
public class Items {

    String name;
    String displayText;
    int count;
    int useItemModifier; // used by used Items to modify character's attributes by `modifier`.
    boolean consumeOnUse;       // only things that modify energy and size are consumed on use (the fan is an exception)

    /**
     * The constructor
     *  
     * @param name the character name.
     * @param displayTextFileName the display text to use for the item, as loaded by setItemDisplayText.
     * @param count the count of this particular item
     * @param useItemModifier the amount by which subclasses will modify an attribute via an overriden useItem.
     * @param consumeOnUse whether an item is consumed. Typically only items based on the ItemEnergy subclass are consumed.
     */
    public Items(String name, String displayTextFileName, int count, int useItemModifier, boolean consumeOnUse) {
        this.name = name;
        this.displayText = setItemDisplayText(displayTextFileName);
        this.count = count;
        this.useItemModifier = useItemModifier;
        this.consumeOnUse = consumeOnUse; 

    }

    
    /** 
     * The item displayTextFileName has the description of the item on one line. This sets
     * that line to displayText. Use try-with-resources so that the Scanner opens, exists
     * to read, and then closes automatically when done.
     * 
     * @param fileName the file name that has the display text for the item.
     * @return String
     */
    public String setItemDisplayText(String fileName) {
        File textFile = new File(fileName);
        try (Scanner fileReader = new Scanner(textFile)) {
            return fileReader.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    /** 
     * Prints output about running an item or runs custom logic in Items subclasses.
     * 
     * By default items do nothing, but subclasses ItemEnergy and ItemSize will modify the state, such as
     * the player's size or energy, in custom logic specific to the subclass.
     * 
     * @param gameState Pass in a GameState so item can use `modify` value on character attribute.
     */
    public void useItem(GameState gameState){
        System.out.println( gameState.player.characterName + " uses " + this.name + ". But nothing happens!");
    }

}
