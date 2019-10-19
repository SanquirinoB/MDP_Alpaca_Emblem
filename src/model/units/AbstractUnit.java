package model.units;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import model.items.IEquipableItem;
import model.map.Location;


/**
 * This class represents an abstract unit.
 * <p>
 * An abstract unit is a unit that cannot be used in the
 * game, but that contains the implementation of some of the methods that are common for most
 * units.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 *
 * NEW ACTUALIZATION
 *
 * We have a lot of new methods
 * @author Fernanda Sanchirico Barrera
 * @since 2.0
 */

public abstract class AbstractUnit implements IUnit {

  protected List<IEquipableItem> items = new ArrayList<>();
  private int currentHitPoints;
  private final int movement;
  protected IEquipableItem equippedItem;
  private Location location;
  private int maxItems;

  /**
   * Creates a new Unit.
   *
   * @param hitPoints the maximum amount of damage a unit can sustain
   * @param movement  the number of panels a unit can move
   * @param location  the current position of this unit on the map
   * @param maxItems  maximum amount of items this unit can carry
   */
  protected AbstractUnit(int hitPoints, int movement,
                         Location location, final int maxItems, IEquipableItem... items) {
    this.currentHitPoints = hitPoints;
    this.movement = movement;
    this.location = location;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
    this.maxItems = maxItems;
  }

  /**
   * @return the hitPoints of the unit
   */
  @Override
  public int getCurrentHitPoints() {
    return currentHitPoints;
  }


  /**
   * Setter of hitPoints
   * @param i the new hitPoints
   */
  @Override
  public void setCurrentHitPoints(int i) {
    this.currentHitPoints = i;
  }


  /**
   * @return the list of items of the unit
   */
  @Override
  public List<IEquipableItem> getItems() {
    return items;
  }


  /**
   * We set a new list of items to the unit
   *
   * @param list the new list of items
   */
  @Override
  public void setItems(List<IEquipableItem> list) {
    this.items = list;
  }


  /**
   * @return the equippedItem of the unit
   */
  @Override
  public IEquipableItem getEquippedItem() {
    return equippedItem;
  }


  /**
   * We set a new item
   * @param item the item to be equipped
   */
  @Override
  public void setEquippedItem(final IEquipableItem item) {
    this.equippedItem = item;
  }

  /**
   * @return the location of the unit
   */
  @Override
  public Location getLocation() {
    return location;
  }

  /**
   * We set a new location for the unit
   * @param location the new location for the unit.
   */
  @Override
  public void setLocation(Location location) {
    this.location = location;
  }

  /**
   * @return the movement that the unit can do
   */
  @Override
  public int getMovement() {
    return movement;
  }

  /**
   * The unit moves from his location to the new location.
   * @param targetLocation the final location.
   */
  @Override
  public void moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
            && targetLocation.getUnit() == null) {
      setLocation(targetLocation);
    }
  }

  /**
   * @param unit the unit who is going to be healed by a Cleric with an Staff
   */
  @Override
  public void healedByStaff(IUnit unit) {
  }

  /**
   * @param friend some unit that is going to be healed (DD)
   */
  @Override
  public void healing(IUnit friend) {
    this.healedByStaff(friend);
  }

  /**
   * An announcement of war
   * @param equippedItem the item who attacks to this unit
   */
  @Override
  public void attackedBy(IEquipableItem equippedItem) {
    int damage;
    if (this.getEquippedItem() != null) {
      damage = equippedItem.fightAgainst(this.getEquippedItem());
    } else {
      damage = equippedItem.getPower();
    }
    int health = this.getCurrentHitPoints();
      this.setCurrentHitPoints(Math.max(0, health - damage));
  }

  /**
   * @return the maximal amount of items that a unit can handle
   */
  @Override
  public int getMaxItems() {
    return maxItems;
  }

  /**
   * @param giver unit that gives an item
   * @param receiver who receives the item
   * @return if the exchange between those units is viable
   */
  @Override
  public boolean exchangeViable(IUnit giver, IUnit receiver) {
    // Conditions
    boolean theyAreClose = giver.getLocation().distanceTo(receiver.getLocation()) <= 1;
    boolean theGiverHaveSome = !giver.getItems().isEmpty();
    boolean theReceiverHaveSpc = receiver.getItems().size() < receiver.getMaxItems();
    if (theyAreClose && theGiverHaveSome && theReceiverHaveSpc) {
      return true;
    } else return false;
  }

  /**
   * It delete the i-th item of the unit's item list
   *
   * @param index the index of the item in the unit's list that we want to delete
   */
  @Override
  public void quitItem(int index) {
    List<IEquipableItem> items = this.getItems();
    items.remove(index);
    this.setItems(items);
  }

  /**
   * It add a new item to the unit's item list
   *
   * @param item the item that is going to be added to the unit's item list
   */
  @Override
  public void addItem(IEquipableItem item) {
    List<IEquipableItem> items = this.getItems();
    items.add(item);
    this.setItems(items);
  }

  /**
   * @param unit who receive the item
   * @param item the index of the item in the unit's list  that we want to give
   */
  @Override
  public void exchangeTo(IUnit unit, IEquipableItem item) {
    if (exchangeViable(this, unit)) {
      unit.addItem(item);
        int index = this.getItems().indexOf(item);
      this.quitItem(index);
    } else System.out.println("You can't do it");
  }

}
