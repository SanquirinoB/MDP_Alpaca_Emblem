package model.units;

import model.items.IEquipableItem;
import model.map.Location;

public class PassiveUnit extends AbstractUnit {
    /**
     * Creates a new Unit.
     *
     * @param hitPoints the maximum amount of damage a unit can sustain
     * @param movement  the number of panels a unit can move
     * @param location  the current position of this unit on the map
     * @param maxItems  maximum amount of items this unit can carry
     * @param items
     */
    protected PassiveUnit(int hitPoints, int movement, Location location, int maxItems, IEquipableItem... items) {
        super(hitPoints, movement, location, maxItems, items);
    }

    @Override
    public void equipItem(IEquipableItem item) {}

    @Override
    public boolean attackViable(IUnit enemy) {
        return false;
    }

    @Override
    public void attack(IUnit enemy) {}

    @Override
    public void attackBack(AbstractUnit abstractUnit) {
    }

    @Override
    public void useItemOn(IUnit victim) {
    }
}
