package model.units;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * NEW ACTUALIZATION
 * New subclass of IUnit, with method for equip an item with DD
 *
 * @author Fernanda Sanchirico
 * @since 2.0
 */

public class Sorcerer extends AbstractUnit{
    /**
     * Creates a new Unit.
     *
     * @param hitPoints the maximum amount of damage a unit can sustain
     * @param movement  the number of panels a unit can move
     * @param location  the current position of this unit on the map
     * @param maxItems  maximum amount of items this unit can carry
     * @param items
     *
     * By default, this unit is agressive
     */
    public Sorcerer(int hitPoints, int movement, Location location, int maxItems, IEquipableItem... items) {
        super(hitPoints, movement, location, maxItems, true, items);
    }

    @Override
    public void equipItem(IEquipableItem item) {
        if (this.getItems().contains(item)) {
            item.equippedBySorcerer(this);
        }
    }
}
