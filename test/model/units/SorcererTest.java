package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.map.Location;
import org.junit.jupiter.api.Test;

public class SorcererTest extends AbstractTestUnit{
    private Sorcerer sorcerer;
    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(50, 2, field.getCell(0,0), 3);
    }

    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }

    @Override
    @Test
    public void equipBookTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(darkBook);
        assertEquals(darkBook, sorcerer.getEquippedItem());
        sorcerer.equipItem(lightBook);
        assertEquals(lightBook, sorcerer.getEquippedItem());
        sorcerer.equipItem(soulBook);
        assertEquals(soulBook, sorcerer.getEquippedItem());
    }

    @Override
    @Test
    public void testAttack() {
        IUnit murder = sorcerer;
        murder.equipItem(darkBook);
        assertEquals(darkBook, murder.getEquippedItem());
        IUnit victim_nonMag = new Fighter(50, 2, field.getCell(1,0));
        victim_nonMag.equipItem(axe);
        IUnit victim_Mag = new Sorcerer(50, 2, field.getCell(1,0), 3);
        victim_Mag.equipItem(lightBook);
        int murderHealth = murder.getCurrentHitPoints();
        int victimNonHealth = victim_nonMag.getCurrentHitPoints();
        int victimMagHealth = victim_Mag.getCurrentHitPoints();
        murder.attack(victim_nonMag);
        assertEquals(murderHealth - axe.getPower()*3/2, murder.getCurrentHitPoints());
        assertEquals(victimNonHealth - darkBook.getPower()*3/2, victim_nonMag.getCurrentHitPoints());
        murderHealth = murder.getCurrentHitPoints();
        murder.attack(victim_Mag);
        assertEquals(murderHealth - lightBook.getPower()*3/2, murder.getCurrentHitPoints());
        assertEquals(victimMagHealth - (darkBook.getPower() - 20), victim_Mag.getCurrentHitPoints());
    }

    @Override
    public void testExchange() {

    }
}
