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
 *
 * NEW ACTUALIZATION
 * New Method for equip an item with DD
 * @author Fernanda Sanchirico
 * @since 2.0
 */
public class SwordMaster extends AgressiveUnit {
  /**
   * Creates a new Unit.
   *
   * @param hitPoints the maximum amount of damage a unit can sustain
   * @param movement  the number of panels a unit can move
   * @param location  the current position of this unit on the map
   * @param items     By default, this unit is agressive
   */
  public SwordMaster( int hitPoints,  int movement,  Location location,
      IEquipableItem... items) {
      super(hitPoints, movement, location, 3, items);
  }

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem( IEquipableItem item) {
      if (this.getItems().contains(item)) {
          item.equippedBySwordMaster(this);
      }
  }
}
