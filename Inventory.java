//this is the player's inventory NOT the room's inventory

// this is loaded into `GameState` as player's inventory

import java.util.ArrayList;

public class Inventory {

    ArrayList<Items> inventory;

    public Inventory() {
        this.inventory = new ArrayList<Items>();
    }

    public void add(Items item) {
        inventory.add(item);
    }

    public void remove(Items item) {
        if (item.count > 0) {
            item.count -= 1;
        } else {
        inventory.remove(item);
        }
    }

    public void show() {
        if (inventory.size() == 0) {
            System.out.println("Sorry, you don't have any items yet");
        } else {

            System.out.println("These are your items: ");
            for (Items item : inventory) {
                System.out.println(item.name + " : " + item.count);
            }

        }
    }

    public Items checkAndGetItem(String name){
        for ( Items item : inventory){
            if ( item.name.equals(name)){
                return item;
            }

        }
        return null;

    }
}

