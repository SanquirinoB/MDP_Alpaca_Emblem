package model.units;

import model.map.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Ignacio Slater Muñoz
 */
public class SwordMasterTest extends AbstractTestUnit {

  private SwordMaster swordMaster;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    swordMaster = new SwordMaster(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return swordMaster;
  }

  @Override
  public void equipSwordTest() {
    assertNull(swordMaster.getEquippedItem());
    swordMaster.equipItem(sword);
    assertEquals(sword, swordMaster.getEquippedItem());
  }

  @Override
  @Test
  public void testAttack() {
    IUnit murder = new SwordMaster(50, 2, field.getCell(1, 1));
    IUnit victim = new Cleric(50, 2, field.getCell(0, 0));
    IUnit victim2 = new Fighter(50, 2, field.getCell(0, 0));
    murder.setEquippedItem(getSpear());
    victim2.setEquippedItem(getAxe());
    int m_health = murder.getCurrentHitPoints();
    int v_health = victim.getCurrentHitPoints();
    int v2_health = victim2.getCurrentHitPoints();
    murder.attack(victim);
    assertEquals(v_health - getSpear().getPower(), victim.getCurrentHitPoints());
    assertEquals(m_health, murder.getCurrentHitPoints());
    murder.attack(victim2);
    assertEquals(v2_health - (getSword().getPower() - 20), victim2.getCurrentHitPoints());
    assertEquals(m_health - getAxe().getPower() * 3 / 2, murder.getCurrentHitPoints());
  }

  @Override
  @Test
  public void testEquipItem() {
    swordMaster.equipItem(darkBook);
    assertNull(swordMaster.getEquippedItem());
    swordMaster.equipItem(sword);
    assertEquals(sword, swordMaster.getEquippedItem());
  }

  @Override
  public void testExchange(){}
}