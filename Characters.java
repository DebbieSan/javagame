/** Characters has the model for all characters in the game.
 *
 * Some characters will rely on a subclass of this when they need to override specialCharacterLogic().
 */
public class Characters {

    String characterName;
    int energy;
    String characterTextFile;
    int size;
    boolean haveMet;
 
    /**
     * Create a character
     * 
     * @param characterName the name of the character.
     * @param energy the energy it should have.
     * @param characterTextFile the text file name/path of the file that has the character dialagoue.
     */
    public Characters(String characterName, int energy, String characterTextFile) {
        this.characterName = characterName;
        this.energy = energy;
        this.characterTextFile = characterTextFile;
    }

    /**
     * special logic to run for some characters (e.g. the MadHatter).
     * 
     * @param gameState the game state.
     */
    public void specialCharacterLogic(GameState gameState) {}
}
