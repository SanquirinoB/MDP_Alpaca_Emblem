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
 *
 * NEW VERSION:
 * THE BEST CONTROLLER
 * @author Fernanda Sanchirico
 * @version 2.1
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
    private Long seed;
    /**
     * Creates the controller for a new game.
     *
     * @param numberOfPlayers the number of players for this game
     * @param mapSize         the dimensions of the map, for simplicity, all maps are squares
     */
    public GameController(int numberOfPlayers, int mapSize, Long s) {
        nPlayers = numberOfPlayers;
        sizeMap = mapSize;
        createMap(mapSize);
        currentTactician = null;
        seed = s;
    }

    /**
     * Starts the game.
     *
     * @param maxTurns the maximum number of turns the game can last
     */
    public void initGame(int maxTurns) {
        rounds = maxTurns;
        initAll();
    }

    /**
     * Starts a game without a limit of turns.
     */
    public void initEndlessGame() {
        rounds = -1;
        initAll();
    }

    /**
     * Set the common variables of the game
     */
    private void initAll() {
        tacticians = createTacticians(getNPlayers());
        winners = new ArrayList<>();
        rounds_played = 1;
        indexTurn = 0;
        createTurns(getNPlayers(), getSeed());
        currentTactician = getTurnOwner();
        winners = new ArrayList<>();
    }


    /**
     * Get the seed
     * @return seed
     */
    private Long getSeed() {
        return seed;
    }

    /**
     * In a randomly way we set the turns of the players
     * @param n number of players
     * @param seed seed :D
     */
    private void createTurns(int n, Long seed) {
        Random seedBkn = new Random(seed);
        turns = new ArrayList<>();
        while (turns.size() < n) {
            int turn = seedBkn.nextInt(n);
            if (!turns.contains(turn))
                turns.add(turn);
        }
    }


    /**
     * It creates all the Tacticians
     * @param numberOfPlayers
     * @return List of Tactician
     */
    private List<Tactician> createTacticians(int numberOfPlayers) {
        List<Tactician> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            Tactician player = new Tactician("Player " + i);
            player.giveMap(getGameMap());
            player.addPropertyChangeListener(this);
            players.add(player);
        }
        return players;
    }

    /**
     * We create the map of the game, it's an square
     * FUTURE VERSION: The map will be with an random shape
     * @param mapSize
     */
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
                return getTacticians().get(i);
            }
        }
        return null;
    }


    /**
     * @return get the index of the current turn
     */
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
            rounds_played++;
        }
        indexTurn = (indexTurn + 1) % getNPlayers();
        currentTactician = getTurnOwner();
        hasGameEnd();
    }

    /**
     * Analyzing the state of the game, it determinate the list of winners
     */
    private void selectWinners() {
        winners = new ArrayList<>();
        if (getTacticians().size() == 1) {
            winners.add(getTacticians().get(0).getName());
        } else {
            winners = searchTheTop();
        }
    }

    /**
     * @return Sorted list of winner based on the number of unit alive
     */
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

    /**
     * Analise if the game has ended or not
     */
    private void hasGameEnd() {
        boolean allPlayersOut = getNPlayers() == 1;
        boolean maxRoundsReached = getRoundNumber() == getMaxRounds() + 1;
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
                hasGameEnd();
            }
        }
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
     *  @param x horizontal position of the unit
     * @param y vertical position of the unit
     */
    public void selectUnitIn(int x, int y) {
        Field map = getGameMap();
        currentUnit =  map.getCell(x,y).getUnit();
        if(currentUnit != null){
            currentUnit.imSelected();
        }
    }

    /**
     * @param x horizontal position of the unit
     * @param y vertical position of the unit
     * @return the unit in that Location
     */
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

    /**
     * @return get the current unit
     */
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
     * @return get the selected item
     */
    public IEquipableItem getSelectedItem(){
        return selectedItem;
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

    /**
     * @return get the number of players
     */
    public int getNPlayers() {
        return nPlayers;
    }

    /**
     * @return the list of turns with the number of the Tacticians
     */
    public List<Integer> getTurns() {
        return turns;
    }

    /**
     * @return the size of the map
     */
    public int getSizeMap() {
        return sizeMap;
    }

    /**
     * @return get the current Tactician
     */
    public Tactician getCurrentTactician() {
        return currentTactician;
    }

    /**
     * It analyze the signals observed by the controller
     * @param propertyChangeEvent
     */
    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().equals("heroIsDead")) {
            Tactician loser = (Tactician) propertyChangeEvent.getNewValue();
            this.getTacticians().remove(loser);
            int num = loser.getName().charAt(loser.getName().length() - 1);
            this.getTurns().remove(num);
            hasGameEnd();
        }
        else if (propertyChangeEvent.getPropertyName().equals("newUnitInMap")) {
            Location l = (Location) propertyChangeEvent.getNewValue();
            this.getGameMap().getCell(l.getRow(), l.getColumn()).setUnit((IUnit) propertyChangeEvent.getOldValue());
        }
        else if (propertyChangeEvent.getPropertyName().equals("quitUnit")){
            Location l = (Location) propertyChangeEvent.getNewValue();
            this.getGameMap().getCell(l.getRow(), l.getColumn()).setUnit(null);
        }
    }

    /**
     * Developed only for visual testing
     */
    public void showUnitsInMap() {
        Field map = getGameMap();
        int size = getSizeMap();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("| " + map.getCell(i, j).getUnit().getClass() + " |");
            }
            System.out.println("");
        }

    }

}
