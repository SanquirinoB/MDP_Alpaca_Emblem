package model.units;

import model.items.AbstractBook;
import model.items.IEquipableItem;
import model.map.Location;

import java.awt.print.Book;

public class Sorcerer extends AbstractUnit{
    /**
     * Creates a new Unit.
     *
     * @param hitPoints the maximum amount of damage a unit can sustain
     * @param movement  the number of panels a unit can move
     * @param location  the current position of this unit on the map
     * @param maxItems  maximum amount of items this unit can carry
     * @param items
     */
    public Sorcerer(int hitPoints, int movement, Location location, int maxItems, IEquipableItem... items) {
        super(hitPoints, movement, location, maxItems, true, items);
    }

    @Override
    public void equipItem(IEquipableItem item) {
        item.eqquipedBySorcerer(this);
    }
}
