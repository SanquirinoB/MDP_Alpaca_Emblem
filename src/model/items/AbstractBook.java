package model.items;

public abstract class AbstractBook extends AbstractItem{
    private int magicID;
    /**
     * Constructor for a default item without any special behaviour.
     * We define the magicID as:
     *  1-> soulBook
     *  2-> darkBook
     *  3-> lightBook
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange the maximum range of the item
     */
    public AbstractBook(String name, int power, int minRange, int maxRange, int magicType) {
        super(name, power, minRange, maxRange, false);
        this.magicID = magicType;
    }
}
