package model.items;

import model.units.Sorcerer;

/**
 * NEW ACTUALIZATION
 * New Class of item, new Methods for fighting
 *
 * @author Fernanda Sanchirico
 * @since 2.0
 */

public class LightBook extends AbstractBook{
    /**
     * Constructor for make a LightBook.
     * We define the magicID as:
     * 1-> soulBook
     * 2-> darkBook
     * 3-> lightBook
     *
     * @param name      the name of the item
     * @param power     the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange  the minimum range of the item
     * @param maxRange  the maximum range of the item
     */
    public LightBook(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange, 3);
    }

    @Override
    public int fightAgainst(IEquipableItem enemyEquippedItem) { return enemyEquippedItem.attackedByLightBook(this);    }

    @Override
    public int attackedByAxe(IEquipableItem axe) {
        return powerful(axe);
    }

    @Override
    public int attackedBySword(IEquipableItem sword) {
        return powerful(sword);
    }

    @Override
    public int attackedBySpear(IEquipableItem spear) {
        return powerful(spear);
    }

    @Override
    public int attackedBySoulBook(IEquipableItem soulBook) {
        return powerful(soulBook);
    }

    @Override
    public int attackedByDarkBook(IEquipableItem darkBook) {
        return weak(darkBook);
    }

    @Override
    public int attackedByLightBook(IEquipableItem lightBook) {
        return lightBook.getPower();
    }

    @Override
    public int attackedByBow(IEquipableItem bow) { return powerful(bow);}

    @Override
    public void equippedBySorcerer(Sorcerer sorcerer) {
        sorcerer.setEquippedItem(this);
    }
}
