package model.items;

import model.units.Cleric;

/**
 * This class represents a <i>Staff</i> type item.
 * <p>
 * A staff is an item that can heal other units nut cannot counter any attack
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Staff extends AbstractItem {

  /**
   * Creates a new Staff item.
   *
   * @param name
   *     the name of the staff
   * @param power
   *     the healing power of the staff
   * @param minRange
   *     the minimum range of the staff
   * @param maxRange
   *     the maximum range of the staff
   */
  public Staff(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange, true);
  }

  @Override
  public int attackedByAxe(IEquipableItem axe) {
    return axe.getPower();
  }

  @Override
  public int attackedBySword(IEquipableItem sword) {
    return sword.getPower();
  }

  @Override
  public int attackedBySpear(IEquipableItem spear) {
    return spear.getPower();
  }

  @Override
  public int attackedBySoulBook(IEquipableItem soulBook) {return powerful(soulBook);}

  @Override
  public int attackedByDarkBook(IEquipableItem darkBook) { return powerful(darkBook);}

  @Override
  public int attackedByLightBook(IEquipableItem lightBook) {return powerful(lightBook);}

  @Override
  public int attackedByBow(IEquipableItem bow) {return bow.getPower();}

  @Override
  public int healedByStaff(IEquipableItem staff) {return -staff.getPower();}

    @Override
    public void eqquipedByCleric(Cleric cleric) {
        cleric.setEquippedItem(this);
    }


}
