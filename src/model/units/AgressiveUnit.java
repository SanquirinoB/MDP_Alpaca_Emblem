package model.units;

import model.items.IEquipableItem;
import model.map.Location;

public class AgressiveUnit extends AbstractUnit {
    /**
     * Creates a new Unit.
     *
     * @param hitPoints the maximum amount of damage a unit can sustain
     * @param movement  the number of panels a unit can move
     * @param location  the current position of this unit on the map
     * @param maxItems  maximum amount of items this unit can carry
     * @param items
     */
    protected AgressiveUnit(int hitPoints, int movement, Location location, int maxItems, IEquipableItem... items) {
        super(hitPoints, movement, location, maxItems, items);
    }

    @Override
    public void equipItem(IEquipableItem item) {

    }

    @Override
    public void useItemOn(IUnit victim) {
        this.attack(victim);
    }

    /**
     * @param enemy the unit that is going to be attacked
     * @return if the units can start an attack.
     */
    @Override
    public boolean attackViable(IUnit enemy) {
        boolean allAlive = this.getCurrentHitPoints() > 0 && enemy.getCurrentHitPoints() > 0;
        if (((this.getEquippedItem() != null) && (!this.getEquippedItem().isHealer())) && allAlive) {
            int min_i = this.getEquippedItem().getMinRange();
            int max_i = this.getEquippedItem().getMaxRange();
            double dist = this.getLocation().distanceTo(enemy.getLocation());
            if (min_i <= dist && dist <= max_i) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    /**
     * Fight method
     *
     * @param enemy the unit who is going to be attacked
     */
    @Override
    public void attack(IUnit enemy) {
        if (this.attackViable(enemy)) {
            enemy.attackedBy(this.getEquippedItem());
            enemy.attackBack(this);
        }
    }

    /**
     * An answer to the initial attack
     *
     * @param enemy the unit who started the war
     */
    @Override
    public void attackBack(AbstractUnit enemy) {
        if (this.attackViable(enemy)) {
            int damage = this.getEquippedItem().fightAgainst(enemy.getEquippedItem());
            int health = enemy.getCurrentHitPoints();
            enemy.setCurrentHitPoints(Math.max(0, health - damage));
        }
    }

}
