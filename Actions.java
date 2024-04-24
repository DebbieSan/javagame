public class Actions {

    // grab go drop eat drink throw east west north south use open

    // public void pick() {

    // }

    public GameState gameState;

    public Actions(GameState gameState) {
        this.gameState = gameState;
    }

    public void reducePlayerEnergy(GameState gameState) {
        if (gameState.location.npc != null) {
            if (gameState.location.npc.characterName.equals("Queen of Hearts")) {
                gameState.player.energy -= 50;
                System.exit(0);
                System.out.println("You lost!");
            } else if (gameState.location.npc.characterName.equals("King of Hearts")) {
                gameState.player.energy -= 25;
                System.out.println("You have lost 25 energy");

            }
        }

    }

    public void runCommand() {

        switch (gameState.lastVerb) {
            case "go":

                if (gameState.isValidExit(gameState.lastNoun)) {

                    gameState.setLocation(gameState.lastNoun);
                    gameState.location.loadLocation();
                    reducePlayerEnergy(gameState);
                } else {
                    System.out.println("Not a valid direction.");
                }

                break;
            case "open":
                if (gameState.lastNoun.equals("i")) {
                    gameState.inventory.show();

                } else if (gameState.lastNoun.equals("e")) {
                    System.out.println("Your energy is: " + gameState.player.energy);

                }

                else {
                    System.out.println("Not something you can open. Did you mean to use 'i' to open your inventory?");
                }
                return;

            case "grab":
                for (Items roomItem : gameState.location.items) {
                    // looking to see if item exists in the room
                    if (roomItem.name.equals(gameState.lastNoun)) {
                        gameState.location.items.remove(roomItem);
                        // incrementing items count if found in player's inventory
                        for (Items playerItem : gameState.inventory.inventory) {
                            if (playerItem.name.equals(roomItem.name)) {
                                playerItem.count += roomItem.count;
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

            default:

                break;

        }

    }

}
