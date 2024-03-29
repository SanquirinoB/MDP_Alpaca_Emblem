package model.items;

import model.map.Location;
import model.units.IUnit;
import model.units.SwordMaster;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test set for swords
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SwordTest extends AbstractTestItem {

  private Sword sword;
  private Sword wrongSword;
  private SwordMaster swordMaster;

  /**
   * Sets which item is going to be tested
   */
  @Override
  public void setTestItem() {
    expectedName = "Common sword";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 1;
    expectedHealer = false;
    sword = new Sword(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongSword = new Sword("Wrong sword", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    swordMaster = new SwordMaster(10, 5, new Location(0, 0));
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongSword;
  }

  /**
   * @return the item being tested
   */
  @Override
  public IEquipableItem getTestItem() {
    return sword;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return swordMaster;
  }

  @Override
  @Test
  public void testFightAgainst() {
    Axe axe = new Axe("axe", 10, 1, 2);
    int damage = sword.fightAgainst(axe);
    assertEquals(sword.getPower() * 3 / 2, damage);
    Spear spear = new Spear("spear", 10, 1, 2);
    damage = sword.fightAgainst(spear);
    assertEquals(sword.getPower() - 20, damage);
  }
}
