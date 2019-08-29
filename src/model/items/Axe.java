package model.items;

import model.units.IUnit;

/**
 * This class represents an Axe.
 * <p>
 * Axes are strong against spears but weak agains swords.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Axe extends AbstractItem {


  /**
   * Creates a new Axe item
   *
   * @param name
   *     the name of the Axe
   * @param power
   *     the damage of the axe
   * @param minRange
   *     the minimum range of the axe
   * @param maxRange
   *     the maximum range of the axe
   */
  public Axe(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange, false);
  }
  // Give them use


  @Override
  public int fightAgainst(IEquipableItem other){
    return other.attackedByAxe(this);
  }

  @Override
  public int attackedByAxe(IEquipableItem axe) {
    return axe.getPower();
  }

  @Override
  public int attackedBySword(IEquipableItem sword) {
    return sword.getPower()*(3/2);
  }

  @Override
  public int attackedBySpear(IEquipableItem spear) {
    return spear.getPower() - 20;
  }

}
