package model.units;

import javafx.scene.shape.Arc;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test set for the alpaca unit
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class AlpacaTest extends AbstractTestUnit{

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
    assertEquals(v_health - getBow().getPower(), victim.getCurrentHitPoints());
    assertEquals(m_health, murder.getCurrentHitPoints());
  }


  @Override
  @Test
  public void testExchange() {
    IUnit alpaca = new Alpaca(50, 2, field.getCell(0,0), getAxe());
    IUnit other = new Archer(50, 2, field.getCell(0,0));
    alpaca.exchangeTo(other,0);
    assertEquals(getAxe(), other.getItems().get(0));
    assertTrue(alpaca.getItems().isEmpty());
  }


}

