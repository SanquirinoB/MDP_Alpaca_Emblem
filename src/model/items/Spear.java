package model.items;

import model.units.Hero;

/**
 * This class represents a <i>spear</i>.
 * <p>
 * Spears are strong against swords and weak against axes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 *
 * NEW ACTUALIZATION
 * New Methods for fighting
 * @author Fernanda Sanchirico
 * @since 2.0
 */
public class Spear extends AbstractItem {

  /**
   * Creates a new Axe item
   *
   * @param name     the name of the Axe
   * @param power    the damage of the axe
   * @param minRange the minimum range of the axe
   * @param maxRange the maximum range of the axe
   *                 By default, this item is agressive
   */
  public Spear(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange, false);
  }

  @Override
  public int fightAgainst(IEquipableItem other){
    return other.attackedBySpear(this);
  }

  @Override
  public int attackedByAxe(IEquipableItem axe) {return powerful(axe);}

  @Override
  public int attackedBySword(IEquipableItem sword) {return weak(sword);}

  @Override
  public int attackedBySpear(IEquipableItem spear) { return spear.getPower();}

  @Override
  public int attackedBySoulBook(IEquipableItem soulBook) { return powerful(soulBook);}

  @Override
  public int attackedByDarkBook(IEquipableItem darkBook) {return powerful(darkBook);}

  @Override
  public int attackedByLightBook(IEquipableItem lightBook) {return powerful(lightBook);}

  @Override
  public int attackedByBow(IEquipableItem bow) { return bow.getPower();}

  @Override
  public void equippedByHero(Hero hero) {
        hero.setEquippedItem(this);
    }

}