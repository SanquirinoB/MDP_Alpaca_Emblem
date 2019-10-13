package model.units;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents an <i>Alpaca</i> type unit.
 * <p>
 * This are a special kind of unit that can carry an unlimited amount of items but can't use any of
 * them.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 *
 * NEW ACTUALIZATION
 * Nothing new...
 * @author Fernanda Sanchirico
 * @since 2.0
 */
public class Alpaca extends AbstractUnit {

  /**
   * Creates a new Alpaca.
   *
   * @param hitPoints
   *     the amount of damage this unit can receive
   * @param movement
   *     number of cells the unit can move
   * @param location
   *     current position of the unit
   *
   *     By default, this unit isn't agressive
   */
  Alpaca(int hitPoints, final int movement, Location location,
         IEquipableItem... items) {
    super(hitPoints, movement, location, Integer.MAX_VALUE, false, items);
  }

    @Override
    public void equipItem(IEquipableItem item) {
        System.out.println("You can't do it");
    }

  /**
   * {@inheritDoc}
   * <p>
   * The <i>Alpaca</i> cannot equip any item.
   */


}
