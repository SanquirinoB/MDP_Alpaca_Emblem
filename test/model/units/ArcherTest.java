package model.units;

import model.items.IEquipableItem;
import model.map.Location;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test set for the Archer unit.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class ArcherTest extends AbstractTestUnit {

  private Archer archer;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    archer = new Archer(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return archer;
  }

  /**
   * Checks if the bow is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipBowTest() {
    assertNull(archer.getEquippedItem());
    archer.equipItem(bow);
    assertEquals(bow, archer.getEquippedItem());
  }

  @Override
  @Test
  public void testAttack(){
    IUnit murder = new Archer(50, 2, field.getCell(0,0));
    IUnit victim = new Alpaca(50, 2, field.getCell(1,2));
    IUnit victim2 = new Fighter(50, 2, field.getCell(1,2));
    murder.setEquippedItem(getBow());
    victim2.setEquippedItem(getAxe());
    int m_health = murder.getCurrentHitPoints();
    int v_health = victim.getCurrentHitPoints();
    int v2_health = victim2.getCurrentHitPoints();
    murder.attack(victim);
    assertEquals(v_health - getBow().getPower(), victim.getCurrentHitPoints());
    assertEquals(m_health, murder.getCurrentHitPoints());
    murder.attack(victim2);
    assertEquals(v2_health - getBow().getPower(), victim2.getCurrentHitPoints());
    assertEquals(m_health, murder.getCurrentHitPoints());
  }

  @Override
  @Test
  public void testExchange() {
    IUnit alpaca = new Alpaca(50, 2, field.getCell(0, 0), getAxe(), getStaff());
    IUnit archer = new Archer(50, 2, field.getCell(0, 0), getBow(), getSpear());
    alpaca.exchangeTo(archer, 1);
    assertEquals(getStaff(), archer.getItems().get(2));
    List<IEquipableItem> list = archer.getItems();
    alpaca.exchangeTo(archer,0);
    // It should say "You cant do it"
    assertEquals(list, archer.getItems());

  }

}


