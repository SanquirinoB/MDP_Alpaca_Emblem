package model.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.map.Field;
import model.units.IUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Defines some common methods for all the items tests
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractTestItem {

  protected String expectedName;
  protected int expectedPower;
  protected short expectedMinRange;
  protected short expectedMaxRange;
  protected boolean expectedHealer;
    protected Bow bow;
    protected Field field;
    protected Axe axe;
    protected Sword sword;
    protected Staff staff;
    protected Spear spear;
    protected DarkBook darkBook;
    protected LightBook lightBook;
    protected SoulBook soulBook;

  /**
   * Sets up the items to be tested
   */
  @BeforeEach
  public void setUp() {
      setTestItem();
      setWrongRangeItem();
      setTestUnit();
      setWeapons();
  }

    public void setWeapons() {
        this.axe = new Axe("Axe", 20, 1, 2);
        this.sword = new Sword("Sword", 20, 1, 2);
        this.spear = new Spear("Spear", 20, 1, 2);
        this.staff = new Staff("Staff", 20, 1, 2);
        this.bow = new Bow("Bow", 20, 2, 3);
        this.darkBook = new DarkBook("DarkBook", 20, 1, 3);
        this.lightBook = new LightBook("LightBook", 20, 1, 3);
        this.soulBook = new SoulBook("SoulBook", 20, 1, 3);
    }

  /**
   * Sets up a correctly implemented item that's going to be tested
   */
  public abstract void setTestItem();

  /**
   * Sets up an item with wrong ranges setted.
   */
  public abstract void setWrongRangeItem();

  /**
   * Sets the unit that will be equipped with the test item
   */
  public abstract void setTestUnit();

  /**
   * Checks that the tested item cannot have ranges outside of certain bounds.
   */
  @Test
  public void incorrectRangeTest() {
    assertTrue(getWrongTestItem().getMinRange() >= 0);
    assertTrue(getWrongTestItem().getMaxRange() >= getWrongTestItem().getMinRange());
  }

  public abstract IEquipableItem getWrongTestItem();

  /**
   * Tests that the constructor for the tested item works properly
   */
  @Test
  public void constructorTest() {
    assertEquals(getExpectedName(), getTestItem().getName());
    assertEquals(getExpectedBasePower(), getTestItem().getPower());
    assertEquals(getExpectedMinRange(), getTestItem().getMinRange());
    assertEquals(getExpectedMaxRange(), getTestItem().getMaxRange());
    assertEquals(getExpectedIsHealer(), getTestItem().isHealer());
  }

  /**
   * @return the expected name of the item
   */
  public String getExpectedName() {
    return expectedName;
  }

  /**
   * @return the item being tested
   */
  public abstract IEquipableItem getTestItem();

  /**
   * @return the expected power of the Item
   */
  public int getExpectedBasePower() {
    return expectedPower;
  }

  /**
   * @return the expected minimum range of the item
   */
  public int getExpectedMinRange() {
    return expectedMinRange;
  }

  /**
   * @return the expected maximum range of the item
   */
  public int getExpectedMaxRange() {
    return expectedMaxRange;
  }

  /**
   * @return the expected state of being healer or not
   */
  public boolean getExpectedIsHealer() { return expectedHealer; }

  /**
   * Checks that the Item can be correctly equipped to a unit
   */
  @Test
  public void equippedToTest() {
    assertNull(getTestItem().getOwner());
    IUnit unit = getTestUnit();
    getTestItem().equipTo(unit);
    assertEquals(unit, getTestItem().getOwner());
  }

  /**
   * @return a unit that can equip the item being tested
   */
  public abstract IUnit getTestUnit();

    @Test
    public abstract void testFightAgainst();

    @Test
    public void testAttackedByAxe() {
        int damage;
        damage = this.axe.attackedByAxe(this.axe);
        assertEquals(this.axe.getPower(), damage);
        damage = this.bow.attackedByAxe(this.axe);
        assertEquals(this.axe.getPower(), damage);
        damage = this.darkBook.attackedByAxe(this.axe);
        assertEquals(this.axe.getPower() * 3 / 2, damage);
        damage = this.lightBook.attackedByAxe(this.axe);
        assertEquals(this.axe.getPower() * 3 / 2, damage);
        damage = this.soulBook.attackedByAxe(this.axe);
        assertEquals(this.axe.getPower() * 3 / 2, damage);
        damage = this.spear.attackedByAxe(this.axe);
        assertEquals(this.axe.getPower() * 3 / 2, damage);
        damage = this.staff.attackedByAxe(this.axe);
        assertEquals(this.axe.getPower(), damage);
        damage = this.sword.attackedByAxe(this.axe);
        assertEquals(this.axe.getPower() - 20, damage);
    }

    @Test
    public void testAttackedByBow() {
        int damage;
        damage = this.axe.attackedByBow(this.bow);
        assertEquals(bow.getPower(), damage);
        damage = this.bow.attackedByBow(this.bow);
        assertEquals(bow.getPower(), damage);
        damage = this.sword.attackedByBow(this.bow);
        assertEquals(bow.getPower(), damage);
        damage = this.darkBook.attackedByBow(this.bow);
        assertEquals(bow.getPower() * 3 / 2, damage);
        damage = this.lightBook.attackedByBow(this.bow);
        assertEquals(bow.getPower() * 3 / 2, damage);
        damage = this.soulBook.attackedByBow(this.bow);
        assertEquals(bow.getPower() * 3 / 2, damage);
        damage = this.spear.attackedByBow(this.bow);
        assertEquals(bow.getPower(), damage);
        damage = this.staff.attackedByBow(this.bow);
        assertEquals(bow.getPower(), damage);
        damage = this.sword.attackedByBow(this.bow);
        assertEquals(bow.getPower(), damage);
    }

    @Test
    public void testAttackedByDarkBook() {
        int damage;
        damage = this.axe.attackedByDarkBook(this.darkBook);
        assertEquals(this.darkBook.getPower() * 3 / 2, damage);
        damage = this.bow.attackedByDarkBook(this.darkBook);
        assertEquals(this.soulBook.getPower() * 3 / 2, damage);
        damage = this.darkBook.attackedByDarkBook(this.darkBook);
        assertEquals(this.soulBook.getPower(), damage);
        damage = this.lightBook.attackedByDarkBook(this.darkBook);
        assertEquals(this.darkBook.getPower() - 20, damage);
        damage = this.soulBook.attackedByDarkBook(this.darkBook);
        assertEquals(this.darkBook.getPower() * 3 / 2, damage);
        damage = this.spear.attackedByDarkBook(this.darkBook);
        assertEquals(this.soulBook.getPower() * 3 / 2, damage);
        damage = this.staff.attackedByDarkBook(this.darkBook);
        assertEquals(this.soulBook.getPower() * 3 / 2, damage);
        damage = this.sword.attackedByDarkBook(this.darkBook);
        assertEquals(this.soulBook.getPower() * 3 / 2, damage);
    }

    @Test
    public void testAttackedByLightBook() {
        int damage;
        damage = this.axe.attackedByLightBook(this.lightBook);
        assertEquals(this.lightBook.getPower() * 3 / 2, damage);
        damage = this.bow.attackedByLightBook(this.lightBook);
        assertEquals(this.lightBook.getPower() * 3 / 2, damage);
        damage = this.darkBook.attackedByLightBook(this.lightBook);
        assertEquals(this.lightBook.getPower() * 3 / 2, damage);
        damage = this.lightBook.attackedByLightBook(this.lightBook);
        assertEquals(this.lightBook.getPower(), damage);
        damage = this.soulBook.attackedByLightBook(this.lightBook);
        assertEquals(this.lightBook.getPower() - 20, damage);
        damage = this.spear.attackedByLightBook(this.lightBook);
        assertEquals(this.lightBook.getPower() * 3 / 2, damage);
        damage = this.staff.attackedByLightBook(this.lightBook);
        assertEquals(this.lightBook.getPower() * 3 / 2, damage);
        damage = this.sword.attackedByLightBook(this.lightBook);
        assertEquals(this.lightBook.getPower() * 3 / 2, damage);
    }

    @Test
    public void testAttackedBySoulBook() {
        int damage;
        damage = this.axe.attackedBySoulBook(this.soulBook);
        assertEquals(this.soulBook.getPower() * 3 / 2, damage);
        damage = this.bow.attackedBySoulBook(this.soulBook);
        assertEquals(this.soulBook.getPower() * 3 / 2, damage);
        damage = this.darkBook.attackedBySoulBook(this.soulBook);
        assertEquals(this.soulBook.getPower() - 20, damage);
        damage = this.lightBook.attackedBySoulBook(this.soulBook);
        assertEquals(this.soulBook.getPower() * 3 / 2, damage);
        damage = this.soulBook.attackedBySoulBook(this.soulBook);
        assertEquals(this.soulBook.getPower(), damage);
        damage = this.spear.attackedBySoulBook(this.soulBook);
        assertEquals(this.soulBook.getPower() * 3 / 2, damage);
        damage = this.staff.attackedBySoulBook(this.soulBook);
        assertEquals(this.soulBook.getPower() * 3 / 2, damage);
        damage = this.sword.attackedBySoulBook(this.soulBook);
        assertEquals(this.soulBook.getPower() * 3 / 2, damage);
    }

    @Test
    public void testAttackedBySpear() {
        int damage;
        damage = this.axe.attackedBySpear(this.spear);
        assertEquals(this.spear.getPower() - 20, damage);
        damage = this.bow.attackedBySpear(this.spear);
        assertEquals(this.spear.getPower(), damage);
        damage = this.darkBook.attackedBySpear(this.spear);
        assertEquals(this.spear.getPower() * 3 / 2, damage);
        damage = this.lightBook.attackedBySpear(this.spear);
        assertEquals(this.spear.getPower() * 3 / 2, damage);
        damage = this.soulBook.attackedBySpear(this.spear);
        assertEquals(this.spear.getPower() * 3 / 2, damage);
        damage = this.spear.attackedBySpear(this.spear);
        assertEquals(this.spear.getPower(), damage);
        damage = this.staff.attackedBySpear(this.spear);
        assertEquals(this.spear.getPower(), damage);
        damage = this.sword.attackedBySpear(this.spear);
        assertEquals(this.spear.getPower() * 3 / 2, damage);

    }

    @Test
    public void testAttackedBySword() {
        int damage;
        damage = this.axe.attackedBySword(this.sword);
        assertEquals(this.sword.getPower() * 3 / 2, damage);
        damage = this.bow.attackedBySword(this.sword);
        assertEquals(this.sword.getPower(), damage);
        damage = this.darkBook.attackedBySword(this.sword);
        assertEquals(this.sword.getPower() * 3 / 2, damage);
        damage = this.lightBook.attackedBySword(this.sword);
        assertEquals(this.sword.getPower() * 3 / 2, damage);
        damage = this.soulBook.attackedBySword(this.sword);
        assertEquals(this.sword.getPower() * 3 / 2, damage);
        damage = this.spear.attackedBySword(this.sword);
        assertEquals(this.sword.getPower() - 20, damage);
        damage = this.sword.attackedBySword(this.sword);
        assertEquals(this.sword.getPower(), damage);
    }
}
