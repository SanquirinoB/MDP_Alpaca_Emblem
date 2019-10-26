package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Random;


import controller.GameController;
import model.items.Bow;
import model.items.Staff;
import model.map.Field;
import model.map.Location;
import model.units.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TacticianTest {
    private GameController controller;
    private long randomSeed;
    private Tactician playerTest;
    private List<IUnit> unitsTest;
    private Field field;
    private boolean make = true;

    @BeforeEach
    void setUp() {
        randomSeed = new Random().nextLong();
        controller = new GameController(2, 7, randomSeed);
        controller.initEndlessGame();
        playerTest = controller.getTurnOwner();
        setField();
        setUnitsTest();
    }

    public void setField() {
        this.field = new Field();
        this.field.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
                new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
                new Location(2, 1), new Location(2, 2));
        this.field.isConnected();
    }

    @Test
    void setUnitsTest() {
        playerTest.setTestNullUnits();
        IUnit unit_1 = new Cleric(50, 2, null, new Staff("StaffTest", 10, 1, 2));
        assertEquals(0, playerTest.getUnits().size());
        playerTest.addUnit(unit_1);
        assertEquals(1, playerTest.getUnits().size());
        assertEquals(unit_1, playerTest.getUnits().get(0));
        IUnit unit_2 = new Archer(50, 2, null, new Bow("BowTest", 10, 1, 2));
        playerTest.addUnit(unit_2);
        assertEquals(2, playerTest.getUnits().size());
        assertEquals(unit_2, playerTest.getUnits().get(1));
        assertNotEquals(unit_1, playerTest.getUnits().get(1));
        unitsTest = playerTest.getUnits();
    }

    @Test
    void getNameTest() {
        String playerName = playerTest.getName();
        assertEquals("Player 1", playerName);
    }

    @Test
    void deleteUnit() {
        int i = playerTest.getUnits().size();
        assertEquals(2, playerTest.getUnits().size());
        IUnit unit = playerTest.getUnits().get(i - 1);
        playerTest.getUnits().remove(i - 1);
        assertEquals(i - 1, playerTest.getUnits().size());
        assertNotEquals(unit, playerTest.getUnits().get(0));
    }

    @Test
    void setUnitIn() {
        Hero hero1 = new Hero(50, 2, null);
        playerTest.addUnit(hero1);
        //playerTest.showUnits();
        playerTest.getUnits().get(2).setUnitIn(new Location(0, 0));
        //controller.showUnitsInMap();
        assertEquals(hero1, controller.getGameMap().getCell(0, 0).getUnit());
    }


}
