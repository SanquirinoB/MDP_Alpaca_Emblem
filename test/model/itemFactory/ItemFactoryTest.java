package model.itemFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Factory.Item.ItemFactory;
import model.items.*;
import model.Factory.Item.*;
import org.junit.jupiter.api.Test;


public class ItemFactoryTest {
    ItemFactory axeFact = new AxeFactory();
    ItemFactory bowFact = new BowFactory();
    ItemFactory darkFact = new DarkBookFactory();
    ItemFactory lightFact = new LightBookFactory();
    ItemFactory soulFact = new SoulBookFactory();
    ItemFactory spearFact = new SpearFactory();
    ItemFactory staffFact = new StaffFactory();
    ItemFactory swordFact = new SwordFactory();

    @Test
    void axeMakeAxes() {
        IEquipableItem axe = new Axe("a", 10, 1, 2);
        IEquipableItem desiredAxe = axeFact.createI();
        assertEquals(axe.getClass(), desiredAxe.getClass());
    }

    @Test
    void bowMakeBows() {
        IEquipableItem bow = new Bow("a", 10, 1, 2);
        IEquipableItem desiredBow = bowFact.createI();
        assertEquals(bow.getClass(), desiredBow.getClass());
    }

    @Test
    void darkMakeDark() {
        IEquipableItem dark = new DarkBook("a", 10, 1, 2);
        IEquipableItem desiredDark = darkFact.createI();
        assertEquals(dark.getClass(), desiredDark.getClass());
    }

    @Test
    void lightMakeLights() {
        IEquipableItem light = new LightBook("a", 10, 1, 2);
        IEquipableItem desiredLight = lightFact.createI();
        assertEquals(light.getClass(), desiredLight.getClass());
    }

    @Test
    void soulMakeSouls() {
        IEquipableItem soul = new SoulBook("a", 10, 1, 2);
        IEquipableItem desiredSoul = soulFact.createI();
        assertEquals(soul.getClass(), desiredSoul.getClass());
    }

    @Test
    void spearMakeSpears() {
        IEquipableItem spear = new Spear("a", 10, 1, 2);
        IEquipableItem desiredSpear = spearFact.createI();
        assertEquals(spear.getClass(), desiredSpear.getClass());
    }

    @Test
    void staffMakeStaffs() {
        IEquipableItem staff = new Staff("a", 10, 1, 2);
        IEquipableItem desiredStaff = staffFact.createI();
        assertEquals(staff.getClass(), desiredStaff.getClass());
    }

    @Test
    void swordMakeSwords() {
        IEquipableItem sword = new Sword("a", 10, 1, 2);
        IEquipableItem desiredSword = swordFact.createI();
        assertEquals(sword.getClass(), desiredSword.getClass());
    }
}
