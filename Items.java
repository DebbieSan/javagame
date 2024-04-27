public class Items {

    // edible drinkable

    String name;
    String displayText;
    // int energy;
    int count;
    int modifier; // used by used Items to modify character's attributes by `modifier`.

    
    // boolean hasDisplayed = false;

    public Items(String name, String displayText, int count, int modifier) {
        this.name = name;
        this.displayText = displayText;
        // this.energy = energy;
        this.count = count;
        // this.hasDisplayed = hasDisplayed;

    }

    // Pass in character so item can use `modify` value on character attribute.
    public void useItem(Characters character){
        System.out.println( character.characterName + " uses " + this.name);
    }

}
