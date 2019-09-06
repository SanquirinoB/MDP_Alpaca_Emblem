package model.units;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents a <i>SwordMaster</i> type unit.
 * <p>
 * A <i>SwordMaster</i> is a unit that <b>can only use sword type weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SwordMaster extends AbstractUnit {

  public SwordMaster( int hitPoints,  int movement,  Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, true, items);
  }

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem( IEquipableItem item) {
    item.eqquipedBySwordMaster(this);
  }
}
