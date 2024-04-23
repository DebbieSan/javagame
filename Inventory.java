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
        inventory.remove(item);
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
}