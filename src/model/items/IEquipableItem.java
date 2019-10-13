package model.items;

import model.units.*;

/**
 * This interface represents the <i>weapons</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the weapons are defined here. Every weapon have a
 * base damage and is strong or weak against other type of weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 *
 * NEW ACTUALIZATION
 * We have more methods
 * @author Fernanda Sanchirico Barrera
 * @since 2.0
 *
 */
public interface IEquipableItem {

  /**
   * Equips this item to a unit.
   *
   * @param unit
   *     the unit that will be quipped with the item
   */
  void equipTo(IUnit unit);

  /**
   * @return the unit that has currently equipped this item
   */
  IUnit getOwner();

  /**
   * @return the name of the item
   */
  String getName();

  /**
   * @return the power of the item
   */
  int getPower();

  /**
   * @return the minimum range of the item
   */
  int getMinRange();

  /**
   * @return the maximum range of the item
   */
  int getMaxRange();

  /**
   * @return if an item can heal or not
   */
  boolean isHealer();

  /**
   * @param enemyEquippedItem the item of the enemy
   * @return how much damage we can do to the item's owner
   */
  int fightAgainst(IEquipableItem enemyEquippedItem);

  /**
   * @param axe the axe that is attacking us
   * @return how much damage can do the axe against us
   */
  int attackedByAxe(IEquipableItem axe);

  /**
   * @param spear the spear that is attacking us
   * @return how much damage can do the spear against us
   */
  int attackedBySpear(IEquipableItem spear);

  /**
   * @param sword the sword that is attacking us
   * @return how much damage can do the sword against us
   */
  int attackedBySword(IEquipableItem sword);

  /**
   * @param soulBook the soulBook that is attacking us
   * @return how much damage can do the soulBook against us
   */
  int attackedBySoulBook(IEquipableItem soulBook);

  /**
   * @param darkBook the darkBook that is attacking us
   * @return how much damage can do the darkBook against us
   */
  int attackedByDarkBook(IEquipableItem darkBook);

  /**
   * @param lightBook the lightBook that is attacking us
   * @return how much damage can do the lightBook against us
   */
  int attackedByLightBook(IEquipableItem lightBook);

  /**
   * @param bow the bow that is attacking us
   * @return how much damage can do the lightBook against us
   */
  int attackedByBow(IEquipableItem bow);

  /**
   * @param item the item who increase his damage
   * @return the increased power of the item
   */
  int powerful(IEquipableItem item);

  /**
   * @param item the item who lost power in his attack
   * @return the decreased power of the item
   */
  int weak(IEquipableItem item);

  /**
   * DD for equip some items that only can being equipped by an Archer
   *
   * @param archer
   */
  void equippedByArcher(Archer archer);

  /**
   * DD for equip some items that only can being equipped by a Cleric
   *
   * @param cleric
   */
  void equippedByCleric(Cleric cleric);

  /**
   * DD for equip some items that only can being equipped by a Fighter
   *
   * @param fighter
   */
  void equippedByFighter(Fighter fighter);

  /**
   * DD for equip some items that only can being equipped by a Hero
   *
   * @param hero
   */
  void equippedByHero(Hero hero);

  /**
   * DD for equip some items that only can being equipped by a Sorcerer
   *
   * @param sorcerer
   */
  void equippedBySorcerer(Sorcerer sorcerer);

  /**
   * DD for equip some items that only can being equipped by an SwordMaster
   *
   * @param swordMaster
   */
  void equippedBySwordMaster(SwordMaster swordMaster);
}
