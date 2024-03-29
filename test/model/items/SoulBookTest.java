package model.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.map.Location;
import model.units.Sorcerer;
import model.units.IUnit;
import org.junit.jupiter.api.Test;

public class SoulBookTest extends AbstractTestItem{
    private SoulBook soulBook;
    private SoulBook wrongSoulBook;
    private Sorcerer sorcerer;
    @Override
    public void setTestItem() {
        expectedName = "Common soulBook";
        expectedPower = 10;
        expectedMinRange = 2;
        expectedMaxRange = 4;
        expectedHealer = false;
        soulBook = new SoulBook(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    }

    @Override
    public void setWrongRangeItem() {
        wrongSoulBook = new SoulBook("Wrong soulBook", 10, 1, 1);
    }

    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(50, 2, new Location(0,0),3);
    }

    @Override
    public IEquipableItem getWrongTestItem() {
        return wrongSoulBook;
    }

    @Override
    public IEquipableItem getTestItem() {
        return soulBook;
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

    @Override
    @Test
    public void testFightAgainst() {
        DarkBook db = new DarkBook("darkBook", 20, 1, 2);
        int damage = soulBook.fightAgainst(db);
        assertEquals(soulBook.getPower() - 20, damage);
        LightBook lb = new LightBook("lightBook", 10, 1, 2);
        damage = soulBook.fightAgainst(lb);
        assertEquals(soulBook.getPower() * 3 / 2, damage);
        Sword sword = new Sword("sword", 10, 1, 2);
        damage = soulBook.fightAgainst(sword);
        assertEquals(soulBook.getPower() * 3 / 2, damage);
    }
}
