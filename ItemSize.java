public class ItemSize extends Items {

    public ItemSize(String name, String displayText, int count, int useItemModifier) {
        super(name, displayText, count, useItemModifier);
    }

    @Override
    public void useItem(Characters character) {
        character.size = useItemModifier;
        String sizeText;
        if(character.size < 0){
            sizeText = "small";

        } else if (character.size > 0){
            sizeText = "big";
        } else{
            sizeText = "normal";
        }
        System.out.println(character.characterName + " is now size " + sizeText);
    }
}
