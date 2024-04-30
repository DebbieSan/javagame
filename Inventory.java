    import java.util.ArrayList;

    /**
     * This is the player's inventory NOT the room's inventory. The room's inventory is just an ArrayList of Items.
     * 
     * This is loaded into `GameState` as gameState.inventory
     */
    public class Inventory {

        ArrayList<Items> inventory;

        /**
         * The default constructor.
         * 
         * Initialiazes this.inventory as a new ArrayList to hold Items, but does not put any items into it. Items
         * are added later by the player using `grab itemname`.
         */
        public Inventory() {
            this.inventory = new ArrayList<Items>();
        }

        
        /** 
         * Adds an item to the player's inventory.
         * 
         * @param item the item to add to the player's inventory.
         */
        public void add(Items item) {
            if (inventory.contains(item)) {
                item.count += 1;
            } else {
                inventory.add(item);
            };
        }

        
        /** 
         * Removes an item from the player's inventory.
         * 
         * @param item the item to remove from the player's inventory.
         */
        public void remove(Items item) {
            if (item.count > 1) {
                item.count -= 1;
            } else {
            inventory.remove(item);
            }
        }

        /**
         * Shows the player's inventory by printing to the screen.
         */
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

        
        /** 
         * Return the specific Items object from the player's inventory if it's found.
         * 
         * @param name the name of the item to check the inventory for.
         * @return Items
         */
        public Items checkAndGetItem(String name){
            for ( Items item : inventory){
                if ( item.name.equals(name)){
                    return item;
                }

            }
            return null;

        }
    }

