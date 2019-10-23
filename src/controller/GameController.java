package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;

/**
 * Controller of the game.
 * The controller manages all the input received from de game's GUI.
 *
 * @author Ignacio Slater Muñoz
 * @version 2.0
 * @since 2.0
 */
public class GameController implements PropertyChangeListener {

    private int nPlayers;
    private final int sizeMap;
    private List<Tactician> tacticians;
    private Tactician currentTactician;
    private Field field;
    private int rounds;
    private int rounds_played;
    private ArrayList<Integer> turns;
    private int indexTurn;
    private IUnit currentUnit;
    private List<String> winners;
    private IEquipableItem selectedItem;
    private Tactician nextPlayer;
    private PropertyChangeListener control;
    private boolean firstRoad;

    /**
     * Creates the controller for a new game.
     *
     * @param numberOfPlayers the number of players for this game
     * @param mapSize         the dimensions of the map, for simplicity, all maps are squares
     */
    public GameController(int numberOfPlayers, int mapSize, Long seed) {
        nPlayers = numberOfPlayers;
        sizeMap = mapSize;
        createMap(mapSize);
        tacticians = createTacticians(numberOfPlayers);
        createTurns(numberOfPlayers, seed);
        currentTactician = getTurnOwner();
        winners = new ArrayList<>();
    }

    private void createTurns(int n, Long seed) {
        Random seedBkn = new Random(seed);
        turns = new ArrayList<>();
        while (turns.size() < n) {
            int turn = seedBkn.nextInt(n);
            if (!turns.contains(turn))
                turns.add(turn);
        }
    }

    private List<Tactician> createTacticians(int numberOfPlayers) {
        List<Tactician> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            Tactician player = new Tactician("Player " + i);
            player.addPropertyChangeListener(control);
            players.add(player);
        }
        return players;
    }

    private void createMap(int mapSize) {
        field = new Field();
        int m = 0;
        Location[] loc = new Location[mapSize * mapSize];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                loc[m] = new Location(i, j);
                m++;
            }
        }
        field.addCells(true, loc);
        field.setSize(mapSize);
    }

    /**
     * @return the list of all the tacticians participating in the game.
     */
    public List<Tactician> getTacticians() {
        return tacticians;
    }

    /**
     * @return the map of the current game
     */
    public Field getGameMap() {
        return field;
    }

    /**
     * @return the tactician that's currently playing
     */
    public Tactician getTurnOwner() {
        for (int i = 0; i < getNPlayers(); i++) {
            if (getTacticians().get(i).getName().equals("Player " + getTurns().get(getIndexTurn()))) {
                nextPlayer = getTacticians().get((getTurns().get(getIndexTurn() + 1) % getNPlayers()));
                return getTacticians().get(i);
            }
        }
        return null;
    }


    /**
     * This method is made only for testing.
     *
     * @return the next tactician in the game
     */
    public Tactician getNextPlayer() {
        return nextPlayer;
    }

    private int getIndexTurn() {
        return indexTurn;
    }

    /**
     * @return the number of rounds since the start of the game.
     */
    public int getRoundNumber() {
        return rounds_played;
    }

    /**
     * @return the maximum number of rounds a match can last
     */
    public int getMaxRounds() {
        return rounds;
    }

    /**
     * Finishes the current player's turn.
     */
    public void endTurn() {
        if (getIndexTurn() == (getNPlayers() - 1)) {
            if (getRoundNumber() == 1 && !firstRoad) {
                rounds_played++;
            } else {
                firstRoad = false;
            }
        }
        indexTurn = (indexTurn + 1) % getNPlayers();
        currentTactician = getTurnOwner();
        hasGameEnd();
    }

    private void selectWinners() {
        winners = new ArrayList<>();
        if (getTacticians().size() == 1) {
            winners.add(getTacticians().get(0).getName());
        } else {
            winners = searchTheTop();
        }
    }

    private List<String> searchTheTop() {
        int max = -1;
        List<String> winners = new ArrayList<>();
        for (Tactician player : getTacticians()) {
            int numOfUnits = player.getUnits().size();
            if (numOfUnits >= max) {
                max = numOfUnits;
                winners.add(player.getName());
            }
        }
        return winners;
    }

    private void hasGameEnd() {
        boolean allPlayersOut = getNPlayers() == 1;
        boolean maxRoundsReached = getRoundNumber() == getMaxRounds();
        if (allPlayersOut || maxRoundsReached) {
            selectWinners();
        }
    }

    /**
     * Removes a tactician and all of it's units from the game.
     *
     * @param tactician the player to be removed
     */
    public void removeTactician(String tactician) {
        for (int i = 0; i < getNPlayers(); i++) {
            if (getTacticians().get(i).getName().equals(tactician)) {
                Tactician player = getTacticians().get(i);
                player.remove();
                getTacticians().remove(player);
                getTurns().remove(i);
                nPlayers--;
            }
        }
    }

    /**
     * Starts the game.
     *
     * @param maxTurns the maximum number of turns the game can last
     */
    public void initGame(final int maxTurns) {
        rounds = maxTurns;
        rounds_played = 1;
        indexTurn = 0;
        winners = new ArrayList<>();
        firstRoad = true;
    }

    /**
     * Starts a game without a limit of turns.
     */
    public void initEndlessGame() {
        rounds = -1;
        rounds_played = 1;
        indexTurn = 0;
        winners = new ArrayList<>();
        firstRoad = true;
    }

    /**
     * @return the winner of this game, if the match ends in a draw returns a list of all the winners
     */
    public List<String> getWinners() {
        return winners;
    }

    /**
     * @return the current player's selected unit
     */
    public IUnit getSelectedUnit() {
        return currentUnit;
    }

    /**
     * Selects a unit in the game map
     *
     * @param x horizontal position of the unit
     * @param y vertical position of the unit
     */
    public void selectUnitIn(int x, int y) {
        for (int i = 0; i < getNPlayers(); i++) {
            Tactician player = getTacticians().get(i);
            IUnit unit = player.getUnitIn(x, y);
            if (unit != null) {
                currentUnit = unit;
            }
        }
    }

    public IUnit getUnitIn(int x, int y) {
        for (int i = 0; i < getNPlayers(); i++) {
            Tactician player = getTacticians().get(i);
            IUnit unit = player.getUnitIn(x, y);
            if (unit != null) {
                return unit;
            }
        }
        return null;
    }

    /**
     * @return the inventory of the currently selected unit.
     */
    public List<IEquipableItem> getItems() {
        return getCurrentUnit().getItems();
    }

    private IUnit getCurrentUnit() {
        return currentUnit;
    }

    /**
     * Equips an item from the inventory to the currently selected unit.
     *
     * @param index the location of the item in the inventory.
     */
    public void equipItem(int index) {
        IEquipableItem item = currentUnit.getItems().get(index);
        currentUnit.equipItem(item);
    }

    /**
     * Uses the equipped item on a target
     *
     * @param x horizontal position of the target
     * @param y vertical position of the target
     */
    public void useItemOn(int x, int y) {
        IUnit receiver = getUnitIn(x, y);
        if (receiver != null) {
            currentUnit.useItemOn(receiver);
        }
    }

    /**
     * Selects an item from the selected unit's inventory.
     *
     * @param index the location of the item in the inventory.
     */
    public void selectItem(int index) {
        selectedItem = currentUnit.getItems().get(index);
    }

    /**
     * Gives the selected item to a target unit.
     *
     * @param x horizontal position of the target
     * @param y vertical position of the target
     */
    public void giveItemTo(int x, int y) {
        IUnit receiver = getUnitIn(x, y);
        if (receiver != null) currentUnit.exchangeTo(getUnitIn(x, y), selectedItem);
    }

    public int getNPlayers() {
        return nPlayers;
    }

    public List<Integer> getTurns() {
        return turns;
    }

    public int getSizeMap() {
        return sizeMap;
    }

    public Tactician getCurrentTactician() {
        return currentTactician;
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        Tactician loser = (Tactician) propertyChangeEvent.getNewValue();
        this.getTacticians().remove(loser);
        int num = loser.getName().charAt(loser.getName().length() - 1);
        this.getTurns().remove(num);
        hasGameEnd();
    }
}
