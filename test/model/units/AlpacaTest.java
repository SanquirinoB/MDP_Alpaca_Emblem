package model.units;

import model.map.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test set for the alpaca unit
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class AlpacaTest extends AbstractTestUnit {

  private Alpaca alpaca;

  @Override
  public void setTestUnit() {
    alpaca = new Alpaca(50, 2, field.getCell(0, 0));
  }

  @Override
  public Alpaca getTestUnit() {
    return alpaca;
  }

  @Override
  @Test
  public void testAttack(){
    IUnit murder = new Archer(50, 2, field.getCell(1,2));
    IUnit victim = new Alpaca(50, 2, field.getCell(0,0));
    murder.setEquippedItem(getBow());
    int m_health = murder.getCurrentHitPoints();
    int v_health = victim.getCurrentHitPoints();
    murder.attack(victim);
    boolean is = murder.attackViable(victim);
    double dist = murder.getLocation().distanceTo(victim.getLocation());
    int m_health2 = murder.getCurrentHitPoints();
    int v_health2 = victim.getCurrentHitPoints();
    assertEquals(v_health - getBow().getPower(), victim.getCurrentHitPoints());
    assertEquals(m_health, murder.getCurrentHitPoints());
    }
  }
