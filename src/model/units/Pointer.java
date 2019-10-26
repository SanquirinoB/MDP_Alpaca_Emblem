package model.units;

import model.items.IEquipableItem;
import model.items.Staff;
import model.map.Location;

public class Pointer extends AbstractUnit {
    /**
     * This unit Born to be just a kind of null Unit, for the use on the locations.
     *
     * @param location the current position of this unit on the map
     */
    public Pointer(Location location) {
        super(0, 0, location, 0, new Staff("Neutral", 10, 1, 2));
    }

    @Override
    public void equipItem(IEquipableItem item) {
    }

    @Override
    public boolean attackViable(IUnit enemy) {
        return false;
    }

    @Override
    public void attack(IUnit enemy) {

    }

    @Override
    public void attackBack(AbstractUnit abstractUnit) {

    }

    @Override
    public void useItemOn(IUnit victim) {

    }
}
