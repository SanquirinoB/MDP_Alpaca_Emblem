package model.units;

import java.util.List;
import model.items.IEquipableItem;
import model.map.Location;

/**
 * This interface represents all units in the game.
 * <p>
 * The signature of all the common methods that a unit can execute are defined here. All units
 * except some special ones can carry at most 3 weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IUnit {

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  void equipItem(IEquipableItem item);

  /**
   * @return hit points of the unit
   */
  int getCurrentHitPoints();

  /**
   * Set the i amount of HitPoints
   * @param i
   */
  void setCurrentHitPoints(int i);

  /**
   * @return the items carried by this unit
   */
  List<IEquipableItem> getItems();

  void setItems(List<IEquipableItem> list);
  /**
   * @return the currently equipped item
   */
  IEquipableItem getEquippedItem();

  /**
   * @param item
   *     the item to be equipped
   */
  void setEquippedItem(IEquipableItem item);

  /**
   * @return the current location of the unit
   */
  Location getLocation();

  /**
   * Sets a new location for this unit,
   */
  void setLocation(final Location location);

  /**
   * @return the number of cells this unit can move
   */
  int getMovement();

  /**
   * Moves this unit to another location.
   * <p>
   * If the other location is out of this unit's movement range, the unit doesn't move.
   */
  void moveTo(Location targetLocation);

  /**
   * @param enemy the unit that is going to be attacked
   * @return if the units can start an attack.
   */
  boolean attackViable(IUnit enemy);

  /**
   * @return if a unit can make an attack or not.
   */
  boolean isAgressive();

  /**
   * Fight method
   * @param enemy the unit who is going to be attacked
   */
  void attack(IUnit enemy);

  /**
   * An announcement of war
   * @param equippedItem the item who attacks to this unit
   */
  void attackedBy(IEquipableItem equippedItem);

  /**
   * An answer to the initial attack
   * @param abstractUnit the unit who started the war
   */
  void attackBack(AbstractUnit abstractUnit);

  /**
   * @return the maximal amount of items that a unit can handle
   */
  int getMaxItems();

  /**
   * @param giver unit that gives an item
   * @param receiver who receives the item
   * @return if the exchange between those units is viable
   */
  boolean exchangeViable(IUnit giver, IUnit receiver);

  /**
   * @param unit  who receive the item
   * @param index the index of the item in the unit's list  that we want to give
   */
  void exchangeTo(IUnit unit, int index);

  /**
   * @param unit the unit who is going to be healed by a Cleric with an Staff
   */
  void healedByStaff(IUnit unit);

  /**
   * @param unit some unit that is going to be healed (DD)
   */
  void healing(IUnit unit);

  /**
   * It delete the i-th item of the unit's item list
   *
   * @param index the index of the item in the unit's list that we want to delete
   */
  void quitItem(int index);

  /**
   * It add a new item to the unit's item list
   *
   * @param item the item that is going to be added to the unit's item list
   */
  void addItem(IEquipableItem item);

    void useItemOn(IUnit victim);
}
