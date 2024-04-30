/**
 * The MadHatterCharacter class extends Characters to provide some custom logic for the Mad Hatter.
 * 
 * The Mad Hatter will give Alice 5 bunnies if she has the "watch" item.
 */
public class MadHatterCharacter extends Characters {
    /**
     * The default constructor.
     * 
     * @param characterName the character's name
     * @param energy the character's energy
     * @param characterTextFile the text file location where the dialogue for the character is stored.
     */
    public MadHatterCharacter(String characterName, int energy, String characterTextFile) {
        super(characterName, energy, characterTextFile);
    }

    
    /** 
     * @param gameState the game state
     */
    @Override
    // If Alice has his watch, he takes it back, thanks her, and gives her some bunnies.
    public void specialCharacterLogic(GameState gameState) {
        Items watch = gameState.inventory.checkAndGetItem("watch");
        Items bunny = gameState.handyItems.get("bunny");
        // Check watch count is 1 because otherwise the Hatter will keep giving bunnies because a 
        // watch count of 0 is not null, and would allow repeat bunnies even though the watch is gone.
        if (watch != null && watch.count == 1 && bunny != null) {
            gameState.inventory.remove(watch);
            bunny.count += 5;
            
            System.out.println("""
            "You found my watch!", cries the Mad Hatter. "And it's two days wrong! Alas! Now here, take these bunnies off my hands! I mean here is your reward!"

            Your inventory suddlenly feels heavier. And more wriggly.
            """);
        } 
    } 
}
