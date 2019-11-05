package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import model.Tactician;
import model.items.Bow;
import model.items.IEquipableItem;
import model.items.Spear;
import model.items.Staff;
import model.map.Field;
import model.units.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 * @since v2.0
 */
class GameControllerTest {

    private GameController controller;
    private long randomSeed;
    private List<String> testTacticians;

    @BeforeEach
    void setUp() {
        // Se define la semilla como un número aleatorio para generar variedad en los tests
        randomSeed = new Random().nextLong();
        controller = new GameController(4, 4, randomSeed);
        testTacticians = List.of("Player 0", "Player 1", "Player 2", "Player 3");
        controller.initEndlessGame();
    }

    @Test
    void getTacticians() {
        List<Tactician> tacticians = controller.getTacticians();
        assertEquals(4, tacticians.size());
        for (int i = 0; i < tacticians.size(); i++) {
            assertEquals("Player " + i, tacticians.get(i).getName());
        }
    }

    @Test
    void getGameMap() {
        Field gameMap = controller.getGameMap();
        assertEquals(4, gameMap.getSize()); // getSize deben definirlo
        assertTrue(controller.getGameMap().isConnected());
        Random testRandom = new Random(randomSeed);

    }

    @Test
    void getTurnOwner() {
        List<Integer> turns = controller.getTurns();
        for (int i = 0; i < controller.getNPlayers(); i++) {
            assertTrue(controller.getTurns().contains(i));
        }
    }

    @Test
    void getRoundNumber() {
        controller.initGame(10);
        for (int i = 1; i < 10; i++) {
            assertEquals(i, controller.getRoundNumber());
            for (int j = 0; j < 4; j++) {
                controller.endTurn();
            }
        }
    }

    @Test
    void getMaxRounds() {
        Random randomTurnSequence = new Random();
        IntStream.range(0, 50).map(i -> randomTurnSequence.nextInt() & Integer.MAX_VALUE).forEach(nextInt -> {
            controller.initGame(nextInt);
            System.out.println(nextInt);
            assertEquals(nextInt, controller.getMaxRounds());
            System.out.println(nextInt);
        });
        controller.initEndlessGame();
        assertEquals(-1, controller.getMaxRounds());
    }

    @Test
    void endTurn() {
        int first = controller.getTurns().get(0);
        int second = controller.getTurns().get(1);
        Tactician firstPlayer = controller.getTacticians().get(first);
        Tactician secondPlayer = controller.getTacticians().get(second);
        assertNotEquals(secondPlayer.getName(), firstPlayer.getName());

        controller.endTurn();
        assertNotEquals(firstPlayer.getName(), controller.getTurnOwner().getName());
        assertEquals(secondPlayer.getName(), controller.getTurnOwner().getName());
    }

    @Test
    void removeTactician() {
        assertEquals(4, controller.getTacticians().size());
        controller.getTacticians()
                .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

        controller.removeTactician("Player 0");
        assertEquals(3, controller.getTacticians().size());
        controller.getTacticians().forEach(tactician -> assertNotEquals("Player 0", tactician));
        controller.getTacticians()
                .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

        controller.removeTactician("Player 5");
        assertEquals(3, controller.getTacticians().size());
        controller.getTacticians()
                .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));
    }

    @Test
    void getWinners() {
        controller.initGame(2);
        IntStream.range(0, 8).forEach(i -> controller.endTurn());
        assertEquals(4, controller.getWinners().size());
        controller.getWinners()
                .forEach(player -> Assertions.assertTrue(testTacticians.contains(player)));

        controller.initGame(2);
        IntStream.range(0, 4).forEach(i -> controller.endTurn());
        assertEquals(new ArrayList<>(), controller.getWinners());
        controller.removeTactician("Player 0");
        controller.removeTactician("Player 2");
        IntStream.range(0, 2).forEach(i -> controller.endTurn());
        assertEquals(2, controller.getWinners().size());
        assertTrue(List.of("Player 1", "Player 3").containsAll(controller.getWinners()));
        setUp();
        controller.initEndlessGame();
        for (int i = 0; i < 3; i++) {
            assertEquals(new ArrayList<>(), controller.getWinners());
            controller.removeTactician("Player " + i);
        }
        assertEquals(1, controller.getWinners().size());
        assertTrue(List.of("Player 3").containsAll(controller.getWinners()));
    }

    // Desde aquí en adelante, los tests deben definirlos completamente ustedes
    @Test
    void setUnitIn(){
        Tactician player = controller.getTurnOwner();
        Hero heroP1 = new Hero(50, 2, null);
        player.addUnit(heroP1);
        player.getUnits().get(0).setUnitIn(controller.getGameMap().getCell(1, 1));
        assertEquals(controller.getGameMap().getCell(1, 1), player.getUnits().get(0).getLocation());
        assertEquals(player.getUnits().get(0), controller.getGameMap().getCell(1,1).getUnit());
    }

    @Test
    void selectUnitIn() {
        Tactician player = controller.getTurnOwner();
        Hero heroP1 = new Hero(50, 2, null);
        Sorcerer sorcererP1 = new Sorcerer(50, 2, null, 3);
        player.addUnit(heroP1);
        player.getUnits().get(0).setUnitIn(controller.getGameMap().getCell(1,1));
        player.addUnit(sorcererP1);
        player.getUnits().get(1).setUnitIn(controller.getGameMap().getCell(0,1));
        controller.selectUnitIn(0, 1);
        assertEquals(sorcererP1, controller.getSelectedUnit());
        controller.selectUnitIn(0, 0);
        assertNull(controller.getSelectedUnit());
        controller.selectUnitIn(1, 1);
        assertNotEquals(sorcererP1, controller.getSelectedUnit());
    }

    @Test
    void getSelectedUnit() {
        controller.selectUnitIn(1, 1);
        assertNull(controller.getSelectedUnit());
        Tactician player = controller.getTurnOwner();
        Hero heroP1 = new Hero(50, 2, null);
        Sorcerer sorcererP1 = new Sorcerer(50, 2, null, 3);
        player.addUnit(sorcererP1);
        player.getUnits().get(0).setUnitIn(controller.getGameMap().getCell(1, 1));
        controller.selectUnitIn(1, 1);
        assertEquals(sorcererP1, controller.getSelectedUnit());
        player.addUnit(heroP1);
        player.getUnits().get(1).setUnitIn(controller.getGameMap().getCell(0, 1));
        controller.selectUnitIn(0, 1);
        assertEquals(heroP1, controller.getSelectedUnit());
    }


    @Test
    void getItems() {
        Tactician player = controller.getTurnOwner();
        player.addUnit(new Hero(50, 10, null));
        IEquipableItem s = new Spear("S", 10, 1, 2);
        player.getUnits().get(0).setUnitIn(controller.getGameMap().getCell(0,0));
        controller.selectUnitIn(0,0);
        assertTrue(controller.getItems().isEmpty());
        player.getUnits().get(0).addItem(s);
        assertEquals(List.of(s), controller.getItems());
    }

    @Test
    void selectItem() {
        Tactician player = controller.getTurnOwner();
        player.addUnit(new Hero(50, 10, null));
        IEquipableItem s = new Spear("S", 10, 1, 2);
        IEquipableItem b = new Bow("B", 10, 1, 2);
        player.getUnits().get(0).setUnitIn(controller.getGameMap().getCell(0,0));
        player.getUnits().get(0).addItem(s);
        player.getUnits().get(0).addItem(b);
        controller.selectUnitIn(0,0);
        controller.selectItem(0);
        assertEquals(s, controller.getSelectedItem());
        assertNotEquals(b, controller.getSelectedItem());
    }

    @Test
    void equipItem() {
        Tactician player = controller.getTurnOwner();
        player.addUnit(new Hero(50, 10, null));
        IEquipableItem s = new Spear("S", 10, 1, 2);
        player.getUnits().get(0).addItem(s);
        player.getUnits().get(0).setUnitIn(controller.getGameMap().getCell(0,0));
        controller.selectUnitIn(0,0);
        assertNull(controller.getSelectedUnit().getEquippedItem());
        controller.equipItem(0);
        assertEquals(s, controller.getSelectedUnit().getEquippedItem());
        assertEquals(s, player.getUnits().get(0).getEquippedItem());
        IEquipableItem b = new Bow("B", 10, 1, 2);
        player.getUnits().get(0).addItem(b);
        controller.equipItem(1);
        assertEquals(s, controller.getSelectedUnit().getEquippedItem());
    }

    @Test
    void useItemOn() {
        Tactician player1 = controller.getTurnOwner();
        player1.addUnit(new Alpaca(50, 1, null));
        player1.getUnits().get(0).setUnitIn(controller.getGameMap().getCell(0,1));
        controller.endTurn();
        Tactician player2 = controller.getTurnOwner();
        player2.addUnit(new Hero(50, 10, null));
        player2.addUnit(new Cleric(50, 1,null));
        IEquipableItem s = new Spear("S", 10, 1, 2);
        IEquipableItem staff = new Staff("SS", 10, 1, 2);
        player2.getUnits().get(0).addItem(s);
        player2.getUnits().get(1).addItem(staff);
        player2.getUnits().get(0).setUnitIn(controller.getGameMap().getCell(0,0));
        player2.getUnits().get(1).setUnitIn(controller.getGameMap().getCell(1,1));
        controller.selectUnitIn(0,0);
        controller.equipItem(0);
        controller.selectItem(0);
        int receiverHealth = player1.getUnits().get(0).getCurrentHitPoints();
        int giverHealth = player2.getUnits().get(0).getCurrentHitPoints();
        controller.useItemOn(0,1);
        assertEquals(giverHealth, controller.getSelectedUnit().getCurrentHitPoints());
        assertEquals(receiverHealth - 10, player1.getUnits().get(0).getCurrentHitPoints());
        controller.selectUnitIn(1,1);
        controller.equipItem(0);
        controller.useItemOn(0,1);
        assertEquals(receiverHealth, player1.getUnits().get(0).getCurrentHitPoints());
    }

    @Test
    void giveItemTo() {
        Tactician player2 = controller.getTurnOwner();
        player2.addUnit(new Hero(50, 10, null));
        player2.addUnit(new Cleric(50, 1,null));
        IEquipableItem s = new Spear("S", 10, 1, 2);
        IEquipableItem staff = new Staff("SS", 10, 1, 2);
        player2.getUnits().get(0).addItem(s);
        player2.getUnits().get(0).addItem(staff);
        player2.getUnits().get(0).setUnitIn(controller.getGameMap().getCell(0,0));
        player2.getUnits().get(1).setUnitIn(controller.getGameMap().getCell(0,1));
        controller.selectUnitIn(0,0);
        controller.selectItem(1);
        assertTrue(player2.getUnits().get(1).getItems().isEmpty());
        controller.giveItemTo(0,1);
        assertEquals(List.of(staff), player2.getUnits().get(1).getItems());
    }
}