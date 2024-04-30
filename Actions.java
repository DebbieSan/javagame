    import java.io.File;
    import java.io.FileNotFoundException;
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.Collections;
    import java.util.Random;
    import java.util.Scanner;

    /**
     * Actions is where the player input, as captured and passed on by Control, is interpreted for
     * validity and then is executed.
     */
    public class Actions {
        /**
         * the game state
         */
        public GameState gameState;

        /**
         * The default constructor
         * 
         * @param gameState the game state
         */
        public Actions(GameState gameState) {
            this.gameState = gameState;
        }

        
        /** 
         * Exit the game if the player has run out of health + warn about low health.
         * 
         * @param gameState the GameState.
         */
        public void checkHealth(GameState gameState){
            // The game is over if player energy drops to 0.
            if (gameState.player.energy <= 0) {
                System.out.println("Oh no! Your energy has fallen to zero! You lost!");
                System.exit(0);
            // Give a warning about energy falling low.
            } else if (gameState.player.energy > 0 && gameState.player.energy <= 10) {
                System.out.println("You feel incredibly hungry...");
            }
        }

        
        /** 
         * Conditionally reduce player energy; called when changing rooms.
         * 
         * Reduces player health if King of Hearts is in the room and if queen is there, empties player health, unless
         * the player has 5+ bunnies.
         * 
         * Also reduces energy each rooms switch.
         * 
         * @param gameState the GameState
         */
        public void reducePlayerEnergy(GameState gameState) {
            // Independently of any characters, energy goes down by 10 every room change.
            gameState.player.energy -= 10;    

            // Handle energy reduction from characters that may be in the room.
            if (gameState.location.npc != null) {
                // Logic for Queen of Hearts being in a room -> she lowers energy, unless the player has 5+ bunnies.
                if (gameState.location.npc.characterName.equals("Queen of Hearts")) {
                    Items bunny = gameState.inventory.checkAndGetItem("bunny");
                    if (bunny != null && bunny.count >= 5) {
                    System.out.println("Lucky you! You saved enough bunnies to save your life! But you've lost 5 precious, lovable bunnies!");
                    bunny.count -= 5;
                    System.out.println("You now have " + bunny.count + " bunny");
                    } else {
                        System.out.println("The Queen of Hearts flies into a rage and cries out, \"Off with her head!\"");
                        gameState.player.energy -= 50;
                    }
                // The King of hearts lowers energy less.
                } else if (gameState.location.npc.characterName.equals("King of Hearts")) {
                    gameState.player.energy -= 25;
                    System.out.println("You have lost 25 energy. Your energy is now " + (gameState.player.energy <= 0 ? 0 : gameState.player.energy));

                }
            }
            // After all the energy reductions, check if the character still has more than 0 energy.
            checkHealth(gameState);

        }

        /**
         * Interpret all typed commands. "go", "use", etc.
         * 
         * The Scanner from Control.userInput() calls this on every line to verify the player
         * input and perform the appropriate action.
         */
        public void runCommand() {

            switch (gameState.lastVerb) {
                case "go":
                    // use `go exits` to see the exits again.
                    if (gameState.lastNoun.equals("exits")) {
                        gameState.location.printExits(); 
                        break;
                    }

                    // Handle directional shortcuts with `go: ``go n` -> `go north`, etc.
                    switch (gameState.lastNoun) {
                        case "n":
                            gameState.lastNoun = "north";
                            break;
                        case "e":
                            gameState.lastNoun = "east";
                            break;
                        case "s":
                            gameState.lastNoun = "south";
                            break;
                        case "w":
                            gameState.lastNoun = "west";
                            break;
                    }

                    // Location change logic.
                    if (gameState.isValidExit(gameState.lastNoun)) {
                        // Special case: stop players from leaving a room  if they have an orange in their
                        // inventory. Currently the orange is only in the WonderlandMaze.
                        if (gameState.inventory.checkAndGetItem("orange") != null) {
                            System.out.println("A mysterious energy surrounds you and you see a vision of a succulent orange. You cannot imagine leaving this wonderful maze.");
                            break;
                        }

                        // The default case for switching rooms. setLocation looks at the Location's Exit HashMap
                        // and uses lastNoun (e.g. north) as a key, which loads the location value of the room to the north.
                        if (gameState.getLocation(gameState.lastNoun).playerMeetsEntryRequirements(gameState.inventory, gameState.player)) {
                            gameState.setLocation(gameState.lastNoun);
                            gameState.location.loadLocation(gameState);
                            reducePlayerEnergy(gameState);
                            gameState.location.specialLocationLogic(gameState); 
                            // Characters won't be in every room, so check first.
                            if (gameState.location.npc != null) {
                                gameState.location.npc.specialCharacterLogic(gameState);
                            }

                            // There's a 10% chance you will trip while entering a room, and it costs 10 energy.
                            Random random = new Random();        
                            double randomNumber = random.nextDouble();
                            double tripProbability = 0.10;
                            ArrayList<String> thingsToTripOver = new ArrayList<>(Arrays.asList(
                                "some roots.",
                                "a small hedgehog, which looks annoyed.",
                                "some beautiful flowers.",
                                "a VERY sleepy dormouse, who doesn't even wake up and instead merely yawns.",
                                "a small pile of rocks.",
                                "air of all things.",
                                "a pile of hats.",
                                "a curious looking mushroom."
                            ));
                            if (randomNumber <= tripProbability) {
                                Collections.shuffle(thingsToTripOver);
                                String trippedOn = thingsToTripOver.get(0);
                                gameState.player.energy -= 10; 
                                System.out.println("\nOuch! You tripped over " + trippedOn + "\n");
                            }
                        };

                    } else {
                        System.out.println("Not a valid direction.");
                    }

                    break;
                case "open":
                    // Add shortcut aliases for `open` commands. `open i` -> `open inventory`
                    switch (gameState.lastNoun) {
                        case "i":
                        gameState.lastNoun = "inventory";
                        break;
                        case "inv":
                        gameState.lastNoun = "inventory";
                        break;
                        case "invent":
                        gameState.lastNoun = "inventory";
                        break;
                        case "e":
                        gameState.lastNoun = "energy";
                        break;
                        case "h":
                        gameState.lastNoun = "help";
                        break;
                    }

                    // match the open commands ("inventory", "energy", etc.) and run the appropriate function.
                    if (gameState.lastNoun.equals("inventory")) {
                        gameState.inventory.show();

                    } else if (gameState.lastNoun.equals("energy")) {
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
                    // Check inventory to see if you can use an item.
                    Items selectedItem = gameState.inventory.checkAndGetItem(gameState.lastNoun);
                    if(selectedItem != null) {
                        selectedItem.useItem(gameState);
                        // Only remove consumables when they're used.
                        if (selectedItem.consumeOnUse) {
                            gameState.inventory.remove(selectedItem);
                        }
                        checkHealth(gameState);

                    // An undocumented cheat code to get to the last room!
                    }else if(gameState.lastNoun.equals("win")){
                        Locations WinningLocation = gameState.teleportLocation.get("winningLocationRoom");
                        gameState.location = WinningLocation;
                        gameState.location.loadLocation(gameState);
                        gameState.location.specialLocationLogic(gameState); 
                        if (gameState.location.npc != null) {
                            gameState.location.npc.specialCharacterLogic(gameState);
                        }
                    } else {
                        System.out.println("Can't find " + gameState.lastNoun + " to use.");
                    }

                    return;
                    
                    

                case "grab":
                    // grab everything in the room
                    if (gameState.lastNoun.equals("all")) {
                        if (gameState.location.items.size() == 0) {
                            System.out.println("There are no items to grab.");
                            break;
                        }

                        // add all the items from the room to the player inventory
                        for (Items item : gameState.location.items) {
                            gameState.inventory.add(item);
                        }

                        // remove all the items from the room
                        gameState.location.items.clear();
                        System.out.println("You grab EVERYTHING in the room!");
                        break;
                    } else {
                    // grab a specific item by name
                    
                        for (Items roomItem : gameState.location.items) {
                            // looking to see if item exists in the room
                            if (roomItem.name.equals(gameState.lastNoun)) {
                                gameState.location.items.remove(roomItem);
                                gameState.inventory.add(roomItem);
                                System.out.println("The following has been added to your inventory: " + roomItem.name + ". You now have " + roomItem.count);
                                return;
                            }
                        }
                        System.out.println("Sorry no " + gameState.lastNoun + " in this room to grab.");
                        break;
                    }

                case "place":
                    if (gameState.location.isSafeRoom == true) {
                        // Find the item, remove it from player's inventory, and add to room.
                        for (Items item : gameState.inventory.inventory) {
                            if (item.name.equals(gameState.lastNoun)) {
                                gameState.inventory.remove(item);
                                gameState.location.items.add(item);
                                System.out.println("You have placed " + item.name + " in the safe room.");
                                return;
                            }
                        }
                        System.out.println("You have no " + gameState.lastNoun + " to place.");
                        return;
                    } else {
                        System.out.println("Sorry.You can only place items inside the safe room.");
                        return;
                    }

                case "talk":
                    // Exit talking if there is no character here.
                    if (gameState.location.characterDialogue == null) {
                        System.out.println("There's nobody here to talk to.");
                        break;
                    };

                    /* Access the characterDialogue scanner as set by loadLocation -> startCharacterEncounter.
                    The first line of the file is read when entering the room. Further uses of `talk character`
                    will advance the scanner if possible.
                    */
                    if (gameState.location.characterDialogue.hasNextLine()) {
                        System.out.println(gameState.location.characterDialogue.nextLine());
                    } else {
                        System.out.println(gameState.location.npc.characterName + " has no more to say.");
                    }

                    return;

                case "exit":
                    System.exit(0);
                    

                default:

                    break;

            }

        }

    }
