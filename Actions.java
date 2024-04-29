import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Actions {

    // grab go drop eat drink throw east west north south use open

    // public void pick() {

    // }

    public GameState gameState;

    public Actions(GameState gameState) {
        this.gameState = gameState;
    }

    public void checkHealth(GameState gameState){
        if (gameState.player.energy <= 0) {
            System.out.println("Oh no! Your health has fallen to zero! You lost!");
            System.exit(0);
        }
    }

    // reduces player health if King of Hearts is in the room and if queen is there, empties player health.
    public void reducePlayerEnergy(GameState gameState) {
        if (gameState.location.npc != null) {
            if (gameState.location.npc.characterName.equals("Queen of Hearts")) {
                Items bunny = gameState.inventory.checkAndGetItem("bunny");
                if (bunny != null && bunny.count >= 5) {
                   System.out.println("Lucky you! You saved enough bunnies to save your life! But you've lost 5 precious, lovable bunnies!");
                   bunny.count -= 5;
                   System.out.println("You now have " + bunny.count + " bunny");
                } else {
                    gameState.player.energy -= 50;
                }
            } else if (gameState.location.npc.characterName.equals("King of Hearts")) {
                gameState.player.energy -= 25;
                System.out.println("You have lost 25 energy. Your energy is now " + (gameState.player.energy <= 0 ? 0 : gameState.player.energy));

            }
        }

        checkHealth(gameState);

    }

    public void runCommand() {

        switch (gameState.lastVerb) {
            case "go":

                if (gameState.isValidExit(gameState.lastNoun)) {
                   if (gameState.getLocation(gameState.lastNoun).playerMeetsEntryRequirements(gameState.inventory, gameState.player)) {
                       gameState.setLocation(gameState.lastNoun);
                       gameState.location.loadLocation();
                       reducePlayerEnergy(gameState);
                       gameState.location.specialLocationLogic(gameState); 
                   };

                    // Here is where we check the location's exit requirements; if not met, print message
                    // Here is where we do the check on the new location's requirements; if not met, print message
                } else {
                    System.out.println("Not a valid direction.");
                }

                break;
            case "open":
                if (gameState.lastNoun.equals("i")) {
                    gameState.inventory.show();

                } else if (gameState.lastNoun.equals("e")) {
                    System.out.println("Your energy is: " + gameState.player.energy);

                }else if (gameState.lastNoun.equals("help")) {
                    try {

                        File textFile = new File("Help.txt");
                        Scanner fileReader = new Scanner(textFile);

                        while (fileReader.hasNextLine()) {
                            String data = fileReader.nextLine();
                            System.out.println(data);
                        }

                        fileReader.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                else {
                    System.out.println("Not something you can open. Did you mean to use 'i' to open your inventory?");
                }
                return;
            case "use":
                if(gameState.lastNoun.equals("cookie") || gameState.lastNoun.equals("bottle") || gameState.lastNoun.equals("mushroom") || gameState.lastNoun.equals("apple") || gameState.lastNoun.equals("turkey")|| gameState.lastNoun.equals("butterfly")){
                    Items item = gameState.inventory.checkAndGetItem(gameState.lastNoun);
                    item.useItem(gameState.player);
                    gameState.inventory.remove(item);
                    checkHealth(gameState);

                }else if(gameState.lastNoun.equals("transporter")){
                    Locations safeRoom = gameState.teleportLocation.get("safeRoom");
                    gameState.location = safeRoom;
                    gameState.location.loadLocation();
                    reducePlayerEnergy(gameState);
                    gameState.location.specialLocationLogic(gameState); 
                }else if(gameState.lastNoun.equals("win")){
                    Locations WinningLocation = gameState.teleportLocation.get("winningLocationRoom");
                    gameState.location = WinningLocation;
                    gameState.location.loadLocation();
                    reducePlayerEnergy(gameState);
                    gameState.location.specialLocationLogic(gameState); 
                }return;
                
                

            case "grab":
                // Use
                if (gameState.lastNoun.equals("all")) {
                    // add all the items from the room to the player inventory
                    for (Items item : gameState.location.items) {
                        gameState.inventory.add(item);
                    }

                    // remove all the items from the room
                    for (Items item : gameState.location.items) {
                        gameState.inventory.add(item);
                    }
                    break;
                } else {
                
                    for (Items roomItem : gameState.location.items) {
                        // looking to see if item exists in the room
                        if (roomItem.name.equals(gameState.lastNoun)) {
                            // incrementing items count if found in player's inventory
                            gameState.location.items.remove(roomItem);
                            for (Items playerItem : gameState.inventory.inventory) {
                                if (playerItem.name.equals(roomItem.name)) {
                                    playerItem.count += 1;
                                    System.out.println("You now have " + playerItem.count + " : " + playerItem.name + "\n");

                                    return;
                                }

                            }

                            gameState.inventory.add(roomItem);
                            System.out.println("You have added " + roomItem.name + " to your inventory.");
                            return;
                        }
                    }
                    System.out.println("Sorry no " + gameState.lastNoun + " in this room to grab.");
                    break;
                }

            case "place":
                if (gameState.location.isSafeRoom == true) {
                    for (Items item : gameState.inventory.inventory) {
                        if (item.name.equals(gameState.lastNoun)) {
                            gameState.inventory.remove(item);
                            gameState.location.items.add(item);
                            System.out.println("You have placed " + item.name + " in the safe room.");
                            return;
                        }
                    }

                }

                else {
                    System.out.println("Sorry.You can only place items inside the safe room.");
                    return;
                }

                System.out.println("No " + gameState.lastNoun + " is available in this room.");
            case "talk":

                if (gameState.location.characterDialogue.hasNextLine()) {
                    System.out.println(gameState.location.characterDialogue.nextLine());
                }

                return;

            case "exit":
                System.exit(0);
                

            default:

                break;

        }

    }

}
