package model.units;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
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
 */
public abstract class AbstractUnit implements IUnit {

  protected final List<IEquipableItem> items = new ArrayList<>();
  private final boolean agressive;
  private int currentHitPoints;
  private final int movement;
  protected IEquipableItem equippedItem;
  private Location location;

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   * @param location
   *     the current position of this unit on the map
   * @param maxItems
   *     maximum amount of items this unit can carry
   */
  protected AbstractUnit(final int hitPoints, final int movement,
                         final Location location, final int maxItems, final boolean aggresive, final IEquipableItem... items) {
    this.currentHitPoints = hitPoints;
    this.movement = movement;
    this.location = location;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
    this.agressive = aggresive;
  }

  @Override
  public int getCurrentHitPoints() {
    return currentHitPoints;
  }

  @Override
  public  void setCurrentHitPoints(int i) { this.currentHitPoints = i; }

  @Override
  public List<IEquipableItem> getItems() {
    return List.copyOf(items);
  }

  @Override
  public IEquipableItem getEquippedItem() {
    return equippedItem;
  }

  @Override
  public void setEquippedItem(final IEquipableItem item) {
    this.equippedItem = item;
  }

  @Override
  public Location getLocation() {
    return location;
  }

  @Override
  public void setLocation(final Location location) {
    this.location = location;
  }

  @Override
  public int getMovement() {
    return movement;
  }

  @Override
  public void moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
        && targetLocation.getUnit() == null) {
      setLocation(targetLocation);
    }
  }

  @Override
  public boolean attackViable(IUnit enemy) {
    if (this.getEquippedItem() != null && !this.getEquippedItem().isHealer() && this.isAgressive()){
      int min_i = this.getEquippedItem().getMinRange();
      int max_i = this.getEquippedItem().getMaxRange();
      double dist = this.getLocation().distanceTo(enemy.getLocation());
      if(min_i <= dist && dist <= max_i) {
        return true;
      }
    }
    return false;
  }

  protected boolean isAgressive(){ return agressive; }

  @Override
  public void attack(IUnit enemy) {
    if(this.attackViable(enemy)) {
      enemy.attackedBy(this.getEquippedItem());
      enemy.attackBack(this);
    }
  }

  @Override
  public void attackedBy(IEquipableItem equippedItem) {
    int damage = equippedItem.fightAgainst(this.getEquippedItem()); // Por pensar
    int health = this.getCurrentHitPoints();
    this.setCurrentHitPoints(health - damage);
  }

  @Override
  public void attackBack(AbstractUnit enemy){
    if(this.attackViable(enemy)){
      int damage = this.getEquippedItem().fightAgainst(enemy.getEquippedItem());
      int health = enemy.getCurrentHitPoints();
      enemy.setCurrentHitPoints(health - damage);
    }
  }

}
