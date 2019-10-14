package model.units;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents a fighter type unit.
 * A fighter is a unit that can only use axe type weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 *
 * NEW ACTUALIZATION
 * New Method for equip an item with DD
 * @author Fernanda Sanchirico
 * @since 2.0
 */
public class Fighter extends AgressiveUnit {

  public Fighter( int hitPoints,  int movement,  Location location,
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
  public void equipItem(final IEquipableItem item) {
      if (this.getItems().contains(item)) {
          item.equippedByFighter(this);
      }
  }
}
