package model.items;

import static org.junit.jupiter.api.Assertions.assertTrue;

import model.map.Location;
import model.units.Sorcerer;
import model.units.IUnit;
import org.junit.jupiter.api.Test;

public class DarkBookTest extends AbstractTestItem {
    private DarkBook darkBook;
    private DarkBook wrongDarkBook;
    private Sorcerer sorcerer;
    @Override
    public void setTestItem() {
        expectedName = "Common darkBook";
        expectedPower = 10;
        expectedMinRange = 2;
        expectedMaxRange = 4;
        expectedHealer = false;
        darkBook = new DarkBook(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    }

    @Override
    public void setWrongRangeItem() {
         wrongDarkBook = new DarkBook("Wrong darkBook", 10, 1, 1);
    }

    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(50, 2, new Location(0,0),3);
    }

    @Override
    public IEquipableItem getWrongTestItem() {
        return wrongDarkBook;
    }

    @Override
    public IEquipableItem getTestItem() {
        return darkBook;
    }

    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }

    @Override
    @Test
    public void incorrectRangeTest() {
        assertTrue(getWrongTestItem().getMinRange() > 0);
        assertTrue(getWrongTestItem().getMaxRange() >= getWrongTestItem().getMinRange());
    }
}
