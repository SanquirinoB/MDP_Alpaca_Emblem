package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import model.items.Bow;
import model.items.Staff;
import model.map.Field;
import model.map.Location;
import model.units.Archer;
import model.units.Cleric;
import model.units.IUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TacticianTest {
    private Tactician playerTest;
    private List<IUnit> unitsTest;
    private Field field;

    @BeforeEach
    void setUp() {
        playerTest = new Tactician("Player X");
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
        assertEquals("Player X", playerName);
    }

    @Test
    void deleteUnit() {
        assertEquals(2, playerTest.getUnits().size());
    }


}
