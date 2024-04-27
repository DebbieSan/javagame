public class ItemSize extends Items {

    public ItemSize(String name, String displayText, int count, int modifier) {
        super(name, displayText, count, modifier);
    }

    @Override
    public void useItem(Characters character) {
        character.size += modifier;
        System.out.println(character.characterName + " is now size " + character.size);
    }
}
