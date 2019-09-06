package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.map.Location;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 */
public class HeroTest extends AbstractTestUnit {

  private Hero hero;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    hero = new Hero(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return hero;
  }

  @Override
  @Test
  public void equipSpearTest() {
    assertNull(hero.getEquippedItem());
    hero.equipItem(spear);
    assertEquals(spear, hero.getEquippedItem());
  }

  @Override
  @Test
  public void testAttack() {
    IUnit murder = new Hero(50, 2, field.getCell(1,1));
    IUnit victim = new Cleric(50, 2, field.getCell(0,0));
    IUnit victim2 = new Fighter(50, 2, field.getCell(0,0));
    murder.setEquippedItem(getSpear());
    victim2.setEquippedItem(getAxe());
    int m_health = murder.getCurrentHitPoints();
    int v_health = victim.getCurrentHitPoints();
    int v2_health = victim2.getCurrentHitPoints();
    murder.attack(victim);
    assertEquals(v_health - getSpear().getPower(), victim.getCurrentHitPoints());
    assertEquals(m_health, murder.getCurrentHitPoints());
    murder.attack(victim2);
    assertEquals(v2_health - (getSpear().getPower() - 20), victim2.getCurrentHitPoints());
    assertEquals(m_health - getAxe().getPower()*3/2, murder.getCurrentHitPoints());
  }

  @Override
  public void testExchange(){}
}