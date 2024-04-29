public class ItemEnergy extends Items {

    public ItemEnergy(String name, String displayText, int count, int useItemModifier) {
        super(name, displayText, count, useItemModifier);
    }

    @Override
    public void useItem(Characters character) {
        character.energy += useItemModifier;
        if(character.energy > 50){
            character.energy = 50;
        }
   
   
        System.out.println(character.characterName + " now has " + character.energy + " energy");
    }
    
}
