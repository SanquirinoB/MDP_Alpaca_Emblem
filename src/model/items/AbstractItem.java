package model.items;

import model.units.*;

/**
 * Abstract class that defines some common information and behaviour between all items.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractItem implements IEquipableItem {

  private final String name;
  private final int power;
  protected int maxRange;
  private boolean ishealer;
  protected int minRange;
  private IUnit owner;

  /**
   * Constructor for a default item without any special behaviour.
   *
   * @param name
   *     the name of the item
   * @param power
   *     the power of the item (this could be the amount of damage or healing the item does)
   * @param minRange
   *     the minimum range of the item
   * @param maxRange
   *     the maximum range of the item
   * @param ishealer
   *     if the item is healer or not
   */
  public AbstractItem(final String name, final int power, final int minRange, final int maxRange, final boolean ishealer) {
    this.name = name;
    this.power = power;
    this.minRange = Math.max(minRange, 1);
    this.maxRange = Math.max(maxRange, this.minRange);
    this.ishealer = ishealer;
  }

  /**
   * Sets the item to the unit
   * @param unit
   */
  @Override
  public void equipTo(final IUnit unit) {
    unit.setEquippedItem(this);
    owner = unit;
  }

  /**
   * Give the owner's name
   * @return The item's owner name
   */
  @Override
  public IUnit getOwner() {
    return owner;
  }

  /**
   * Give the name of the item
   * @return item's name
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Give the power of the item
   * @return item's power
   */
  @Override
  public int getPower() {
    return power;
  }

  /**
   * Give the minimal range of the item
   * @return minimal range
   */
  @Override
  public int getMinRange() {
    return minRange;
  }

  /**
   * Give the maximal range of the item
   * @return maximal range
   */
  @Override
  public int getMaxRange() {
    return maxRange;
  }

  /**
   * Answer if the item is healer or not
   * @return true: is healer; false: not healer
   */
  @Override
  public boolean isHealer() {
    return ishealer;
  }

  @Override
  public int fightAgainst(IEquipableItem enemyEquippedItem){
    return enemyEquippedItem.getPower();
  }

  @Override
  public abstract int attackedByAxe(IEquipableItem axe);

  @Override
  public abstract int attackedBySword(IEquipableItem sword);

  @Override
  public abstract int attackedBySpear(IEquipableItem spear);

  @Override
  public abstract int attackedBySoulBook(IEquipableItem soulBook);

  @Override
  public abstract int attackedByDarkBook(IEquipableItem darkBook);

  @Override
  public abstract int attackedByLightBook(IEquipableItem lightBook);

  @Override
  public int powerful(IEquipableItem item) {
    return item.getPower()*3/2;
  }

  @Override
  public int weak(IEquipableItem item) {
    return item.getPower()-20;
  }

  @Override
  public abstract int attackedByBow(IEquipableItem bow);

  @Override
  public void equippedByArcher(Archer archer) {
  }

  @Override
  public void equippedByCleric(Cleric cleric) {
  }

  @Override
  public void equippedByFighter(Fighter fighter) {
  }

  @Override
  public void equippedByHero(Hero hero) {
  }

  @Override
  public void equippedBySorcerer(Sorcerer sorcerer) {
  }

  @Override
  public void equippedBySwordMaster(SwordMaster swordMaster) {
  }


}
