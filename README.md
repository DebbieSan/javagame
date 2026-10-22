# Wonderland — Java Text Adventure 🐇

A text adventure inspired by Alice in Wonderland. Explore locations,
collect and use items, manage your inventory, and talk to characters
through typed commands.

## Built With

Java

## Running the Game

You need a Java Development Kit (JDK), which includes `javac`.

Clone the repository:

```bash
git clone https://github.com/DebbieSan/javagame.git
cd javagame
```

Compile all source files, then start the game:

```bash
javac *.java
java Wonderland
```

Run these commands from the repository folder so the game can
find its accompanying text files.

## Commands

Commands use a verb and a noun and are case insensitive.

| Command | Action |
|---|---|
| `open help` | Display instructions |
| `go exits` | Show available exits |
| `go north` or `go n` | Move in an available direction |
| `open inventory` | View your inventory |
| `open energy` | Check your energy |
| `grab ITEM` | Pick up an item |
| `grab all` | Pick up all available items |
| `use ITEM` | Use an item |
| `place ITEM` | Put an item in the current room |
| `talk CHARACTER` | Talk to a character |
| `exit game` | Quit |

Replace `ITEM` and `CHARACTER` with names shown in the game.
