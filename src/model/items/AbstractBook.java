package model.items;

/**
 * This class represents an abstract book.
 * <p>
 * An abstract book is an item that cannot be used in the
 * game, but that contains the implementation of some of the methods that are common for most
 * books, as the constructor.
 *
 * @author Fernanda Sanchirico Barrera
 * @since 2.0
 */

public abstract class AbstractBook extends AbstractItem{
    protected int magicID;

    /**
     * Constructor for a default book.
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange the maximum range of the item
     *   This items, by default, are agressive
     */
    public AbstractBook(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange, false);
    }
}
