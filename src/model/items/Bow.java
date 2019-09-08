package model.items;

import model.units.Archer;

/**
 * @author Ignacio Slater Muñoz
 * @since
 */
public class Bow extends AbstractItem {

  /**
   * Creates a new bow.
   * <p>
   * Bows are weapons that can't attack adjacent units, so it's minimum range must me greater than
   * one.
   *
   * @param name
   *     the name of the bow
   * @param power
   *     the damage power of the bow
   * @param minRange
   *     the minimum range of the bow
   * @param maxRange
   *     the maximum range of the bow
   */
  public Bow(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange, false);
    this.minRange = Math.max(minRange, 2);
    this.maxRange = Math.max(maxRange, this.minRange);
  }


  @Override
  public int fightAgainst(IEquipableItem enemyEquippedItem) {
    return enemyEquippedItem.attackedByBow(this);
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
  public int attackedBySoulBook(IEquipableItem soulBook) {
    return powerful(soulBook);
  }

  @Override
  public int attackedByDarkBook(IEquipableItem darkBook) {
    return powerful(darkBook);
  }

  @Override
  public int attackedByLightBook(IEquipableItem lightBook) {
    return powerful(lightBook);
  }

  @Override
  public int attackedByBow(IEquipableItem bow) { return bow.getPower();}


    @Override
    public void eqquipedByArcher(Archer archer) {
        archer.setEquippedItem(this);
    }

}
