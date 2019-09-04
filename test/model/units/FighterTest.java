package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.map.Location;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 */
public class FighterTest extends AbstractTestUnit {

  private Fighter fighter;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    fighter = new Fighter(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return fighter;
  }

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipAxeTest() {
    assertNull(fighter.getEquippedItem());
    fighter.equipItem(axe);
    assertEquals(axe, fighter.getEquippedItem());
  }
  @Test
  @Override
  public void testAttack() {
    IUnit murder = new Fighter(50, 2, field.getCell(0,0));
    IUnit victim = new Alpaca(50, 2, field.getCell(2,0));
    IUnit victim2 = new Archer(50, 2, field.getCell(1,0));
    murder.setEquippedItem(getAxe());
    victim2.setEquippedItem(getBow());
    int m_health = murder.getCurrentHitPoints();
    int v_health = victim.getCurrentHitPoints();
    int v2_health = victim2.getCurrentHitPoints();
    murder.attack(victim);
    assertEquals(v_health - getBow().getPower(), victim.getCurrentHitPoints());
    assertEquals(m_health, murder.getCurrentHitPoints());
    murder.attack(victim2);
    assertEquals(v2_health - getAxe().getPower(), victim2.getCurrentHitPoints());
    assertEquals(m_health, murder.getCurrentHitPoints());

  }

  @Override
  public void testExchange(){}
}