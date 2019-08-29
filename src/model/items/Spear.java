package model.items;

/**
 * This class represents a <i>spear</i>.
 * <p>
 * Spears are strong against swords and weak against axes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Spear extends AbstractItem {

  /**
   * Creates a new Axe item
   *
   * @param name     the name of the Axe
   * @param power    the damage of the axe
   * @param minRange the minimum range of the axe
   * @param maxRange the maximum range of the axe
   */
  public Spear(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange, false);
  }

  @Override
  public int fightAgainst(IEquipableItem other){
    return other.attackedBySpear(this);
  }

  @Override
  public int attackedByAxe(IEquipableItem axe) {
    return axe.getPower()*(3/2);
  }

  @Override
  public int attackedBySword(IEquipableItem sword) {
    return sword.getPower() - 20;
  }

  @Override
  public int attackedBySpear(IEquipableItem spear) {
    return spear.getPower();
  }

}