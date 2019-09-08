package model.units;

import model.items.IEquipableItem;
import model.items.Staff;
import model.map.Location;

/**
 * This class represents a cleric type unit. A cleric can only use staff type weapons, which means
 * that it can receive attacks but can't counter attack any of those.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Cleric extends AbstractUnit {

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   */
  public Cleric( int hitPoints,  int movement,  Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, false, items);
  }

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(final IEquipableItem item) {
    item.eqquipedByCleric(this);
  }

    @Override
    public void healedByStaff(IUnit friend) {
        if (this.getEquippedItem() != null) {
            friend.setCurrentHitPoints(friend.getCurrentHitPoints() + this.getEquippedItem().getPower());
        }
    }
}
