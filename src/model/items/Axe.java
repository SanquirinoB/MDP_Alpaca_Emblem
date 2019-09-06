package model.items;

import model.units.Fighter;
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

  @Override
  public int fightAgainst(IEquipableItem other){
    return other.attackedByAxe(this);
  }

  @Override
  public int attackedByAxe(IEquipableItem axe) { return axe.getPower(); }

  @Override
  public int attackedBySword(IEquipableItem sword) {
    return powerful(sword);
  }

  @Override
  public int attackedBySpear(IEquipableItem spear) {
    return weak(spear);
  }

  @Override
  public int attackedBySoulBook(IEquipableItem soulBook) { return powerful(soulBook);};

  @Override
  public int attackedByDarkBook(IEquipableItem darkBook) {return powerful(darkBook);}

  @Override
  public int attackedByLightBook(IEquipableItem lightBook) {return powerful(lightBook);}

  @Override
  public int attackedByBow(IEquipableItem bow) { return bow.getPower();}

  @Override
  public int healedByStaff(IEquipableItem staff) {return -staff.getPower();}

    @Override
    public void eqquipedByFighter(Fighter fighter) {
        fighter.setEquippedItem(this);
    }

}
