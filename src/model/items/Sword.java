package model.items;

import model.units.Hero;
import model.units.SwordMaster;

/**
 * This class represents a sword type item.
 * <p>
 * Swords are strong against axes and weak against spears.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Sword extends AbstractItem {

  /**
   * Creates a new Sword.
   *
   * @param name
   *     the name that identifies the weapon
   * @param power
   *     the base damage pf the weapon
   * @param minRange
   *     the minimum range of the weapon
   * @param maxRange
   *     the maximum range of the weapon
   */
  public Sword(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange, false);
  }

  @Override
  public int fightAgainst(IEquipableItem other){
    return other.attackedBySword(this);
  }

  @Override
  public int attackedByAxe(IEquipableItem axe) {
      return weak(axe);
    }

  @Override
  public int attackedBySword(IEquipableItem sword) { return sword.getPower();}

  @Override
  public int attackedBySpear(IEquipableItem spear) {
    return powerful(spear);
  }

  @Override
  public int attackedBySoulBook(IEquipableItem soulBook) {return powerful(soulBook);}

  @Override
  public int attackedByDarkBook(IEquipableItem darkBook) { return powerful(darkBook);}

  @Override
  public int attackedByLightBook(IEquipableItem lightBook) { return powerful(lightBook);}

  @Override
  public int attackedByBow(IEquipableItem bow) {return bow.getPower();}

  @Override
  public int healedByStaff(IEquipableItem staff) {return -staff.getPower();}

  @Override
  public void eqquipedBySwordMaster(SwordMaster swordMaster) { swordMaster.setEquippedItem(this); }

}
