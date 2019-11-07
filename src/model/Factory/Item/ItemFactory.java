package model.Factory.Item;

import model.items.IEquipableItem;

/**
 * Interface for the items factories
 *
 * @author Fernanda Sanchirico
 * @version 2.1
 * @since 2.0
 */

public interface ItemFactory {

    /**
     * @return desired item
     */
    IEquipableItem createI();
}
