package model.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.map.Location;
import model.units.Sorcerer;
import model.units.IUnit;
import org.junit.jupiter.api.Test;

public class LightBookTest extends AbstractTestItem{
    private LightBook lightBook;
    private LightBook wrongLightBook;
    private Sorcerer sorcerer;
    @Override
    public void setTestItem() {
        expectedName = "Common lightBook";
        expectedPower = 10;
        expectedMinRange = 2;
        expectedMaxRange = 4;
        expectedHealer = false;
        lightBook = new LightBook(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    }

    @Override
    public void setWrongRangeItem() {
        wrongLightBook = new LightBook("Wrong lightBook", 10, 1, 1);
    }

    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(50, 2, new Location(0,0),3);
    }

    @Override
    public IEquipableItem getWrongTestItem() {
        return wrongLightBook;
    }

    @Override
    public IEquipableItem getTestItem() {
        return lightBook;
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
        SoulBook sb = new SoulBook("soulBook", 20, 1, 2);
        int damage = lightBook.fightAgainst(sb);
        assertEquals(lightBook.getPower() - 20, damage);
        DarkBook db = new DarkBook("darkBook", 20, 1, 2);
        damage = lightBook.fightAgainst(db);
        assertEquals(lightBook.getPower() * 3 / 2, damage);
        Sword sword = new Sword("sword", 10, 1, 2);
        damage = lightBook.fightAgainst(sword);
        assertEquals(lightBook.getPower() * 3 / 2, damage);
    }

}
