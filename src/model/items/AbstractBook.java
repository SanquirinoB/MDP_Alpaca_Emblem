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
    private int magicID;

    /**
     * Constructor for a default book.
     * We define the magicID as:
     *  1-> soulBook
     *  2-> darkBook
     *  3-> lightBook
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange the maximum range of the item
     * @param magicType the magicID of the book
     *   This items, by default, are agressive
     */
    public AbstractBook(String name, int power, int minRange, int maxRange, int magicType) {
        super(name, power, minRange, maxRange, false);
        this.magicID = magicType;
    }
}
