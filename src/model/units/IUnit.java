package model.units;

import java.beans.PropertyChangeListener;
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
 *
 * ACTUALIZED
 * @author Fernanda Sanchirico
 * @version 2.1
 * @since 2.0
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
   * @param item the index of the item in the unit's list  that we want to give
   */
  void exchangeTo(IUnit unit, IEquipableItem item);

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

  /**
   * It is an abstraction for the use of items, we use DD for determinate
   * which actions must be realized
   *
   * @param victim The unit who is going to be attacked or healed
   */
  void useItemOn(IUnit victim);

  /**
   * When the location is seated by the Tactician we use this method and notice to the controller
   * that is a new unit in the map
   *
   * @param location
   */
  void setUnitIn(Location location);

  /**
   * It allow us to connect the units with their Tacticians
   * @param tControl
   */
  void addPropertyChangeListener(PropertyChangeListener tControl);

  /**
   * It communicate to the Tactician that the unit is selected by the controller
   */
  void imSelected();
}
